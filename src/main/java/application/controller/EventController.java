package application.controller;
import application.model.Event;
import application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/main")
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/lastEventsForHour")
    public String getLastEventsForHour(Model model) {
        model.addAttribute("events",eventService.getLastEventsForHour());
        return "lastEventsForHour";
    }

    @GetMapping("/notOverActivities")
    public String getEventsWhereActivityIsNotOver(Model model) {
        model.addAttribute("events", eventService.getEventsWhereActivityIsNotOver());
        return "notOverActivities";
    }

    @GetMapping("/top-5")
    public String getTopFive(Model model) {

        model.addAttribute("events",eventService.getTopFiveForms());
        return "top5Forms";
    }
}