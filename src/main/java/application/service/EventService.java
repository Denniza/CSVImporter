package application.service;

import application.model.Event;

import java.io.IOException;
import java.util.List;

public interface EventService {
    void importEvents(String path) throws IOException;

    List<Event> getLastEventsForHour();
    List<Event> getEventsWhereActivityIsNotOver();
    List<String> getTopFiveForms();


}