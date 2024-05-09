package org.nye.progkorny.controller;

import org.nye.progkorny.model.Event;
import org.nye.progkorny.service.impl.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // C
    @PostMapping(path = "/")
    public ResponseEntity<Void> InsertEvent(@RequestBody Event event) {
        boolean result = eventService.addEvent(event);
        if (result) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }

    // R

    @GetMapping(path = "/")
    public List<Event> getAllEvent(){
        return eventService.getAllEvent();
    }

    @GetMapping(path = "/{id}")
    public Event getEventById(@PathVariable int id){
        return eventService.getEventById(id);
    }

    @GetMapping(path = "/user", params = "userId")
    public Event getEventByUserId(@RequestParam("userId") int userId){
        return eventService.getEventByUserId(userId);
    }

    @GetMapping(path = "/eventtype", params = "eventTypeId")
    public Event getEventByEventTypeId(@RequestParam("eventTypeId") int eventTypeId){
        return eventService.getEventByEventTypeId(eventTypeId);
    }

    @GetMapping(path = "/name", params = "name")
    public Event getEventByName(@RequestParam("name") String name){
        return eventService.getEventByName(name);
    }

    @GetMapping(path = "/location", params = "location")
    public Event getEventByLocation(@RequestParam("location") String location){
        return eventService.getEventByLocation(location);
    }

    @GetMapping(path = "/datetime", params = "datetime")
    public Event getEventByDateTime(@RequestParam("datetime") int datetime){
        return eventService.getEventByDateTime(datetime);
    }

    // U
    @PutMapping(path = "/", params = "id")
    public ResponseEntity<Void> updateEvent(@RequestBody Event newEvent, @RequestParam int id){
        Event event = eventService.getEventById(id);
        event.setDatetime(newEvent.getDatetime());
        event.setLocation(newEvent.getLocation());
        event.setEventTypeId(newEvent.getEventTypeId());
        event.setName(newEvent.getName());
        event.setUserId(newEvent.getUserId());
        if(eventService.updateEvent(event)){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }

    // D
    @DeleteMapping(path = "/", params = "id")
    public ResponseEntity<Void> deleteEvent(@RequestParam("id") int id){
        boolean result = eventService.deleteEvent(id);
        if (result) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }
}
