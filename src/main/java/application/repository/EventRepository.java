package application.repository;


import application.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    @Query("FROM Event e where e.subtype='start'")
    List<Event> getStartedEvents();

    @Query("FROM Event e where e.subtype not like 'start' and e.subtype not like 'send'")
    List<Event> getEventsWhichNotEnded();

    @Query("SELECT e.ssoid, e.subtype from Event e, Event sec ")
    List<Event> getEventsWhereActivityIsNotOver();

    @Query("FROM Event e WHERE e.time <= :hourAgo")
    List<Event> findAllBeforeHourAgo(@Param("hourAgo") Date hourAgo);


    @Query("FROM Event e ORDER BY e.formId DESC")
    List<Event> findAllOrderByFormId();
}