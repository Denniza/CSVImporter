package application.service;


import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import application.model.Event;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import application.repository.EventRepository;


import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvEventService implements EventService {
    private final EventRepository repository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importEvents(String path) throws IOException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(path))
                .withCSVParser(new CSVParserBuilder().withSeparator(';')
                        .withIgnoreLeadingWhiteSpace(true)
                        .build()
                ).withSkipLines(1).build();

        List<String[]> allRows = reader.readAll();

        log.info("Rows are red from csv file, count: " + allRows.size());

        repository.saveAll(
                allRows.stream()
                        .map(row -> {
//                            log.info("Writing next line to database as Event entity: " + Arrays.toString(row));

                            try {
                                return new Event(
                                        row[0],
                                        row[2],
                                        row[3],
                                        row[4],
                                        row[5],
                                        row[6],
                                        row[7],
                                        row[8],
                                        row[9],
                                        row[10],
                                        new Date(Long.parseLong(row[1])),
                                        new SimpleDateFormat("yyyy-MM-dd-HH").parse(row[11])
                                );

                            } catch (ParseException e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                        .collect(Collectors.toList())
        );

        log.info("Entities are saved in database.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getLastEventsForHour() {
        return repository.findAllBeforeHourAgo(getOneHourAgo());
//
        }

    private Date getOneHourAgo() {
        return (new Date(new Date().getTime() - 1000*60*60));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getEventsWhereActivityIsNotOver() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getTopFiveForms() {
        List<Event> allEvents = repository.findAll();

        Map<String, Long> map = new HashMap<>();

        allEvents.stream()
                .map(Event::getFormId)
                .forEach(formId -> map.merge(formId, 1L, Long::sum));

        allEvents.forEach(System.out::println);

        return map.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}