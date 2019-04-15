package application.controller;
import application.model.Event;
import application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("lastEventsForHour")
    public List<Event> getLastEventsForHour() {
        return eventService.getLastEventsForHour();
    }

    @GetMapping("eventsWhereActivityIsNotOver")
    public List<Event> getEventsWhereActivityIsNotOver() {
        return eventService.getEventsWhereActivityIsNotOver();
    }

    @GetMapping("top-5")
    public List<String> getTopFive() {
        return eventService.getTopFiveForms();
    }
}