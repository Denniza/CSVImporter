package application.listener;

import application.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {
    private final EventService eventService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Starting csv importing on application ready event...");

        try {
            eventService.importEvents("/home/andrey/IdeaProjects/CSVImporter/src/main/resources/test_case.csv");
        } catch (IOException e) {
            log.error("Application started wrong, dramatically dies...");
            System.exit(1);
        }
    }
}