package org.nye.progkorny.controller;

import org.nye.progkorny.model.EventType;
import org.nye.progkorny.service.impl.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/eventtype")
public class EventTypeController {

        @Autowired
        private EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    // C
    @PostMapping(path = "/")
    public ResponseEntity<Void> insertEventType(@RequestBody EventType eventType) {
        boolean result = eventTypeService.addEventType(eventType);
        if (result) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }

    // R

    @GetMapping(path = "/")
    public List<EventType> getAllEventType() throws SQLException {
        return eventTypeService.getAllEventType();
    }

    @GetMapping(path = "/{id}")
    public EventType getEventTypeById(@PathVariable int id) throws SQLException {
        return eventTypeService.getEventTypeById(id);
    }

    @GetMapping(path = "/name", params = "name")
    public EventType getEventTypeByName(@RequestParam("name") String name) throws SQLException {
        return eventTypeService.getEventTypeByName(name);
    }

    // U
    @PutMapping(path = "/", params = "id")
    public ResponseEntity<Void> updateEventType(@RequestBody EventType newEventType, @RequestParam int id) throws SQLException {
        EventType eventType = eventTypeService.getEventTypeById(id);
        eventType.setName(newEventType.getName());
        if(eventTypeService.updateEventType(eventType)){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }

    // D
    @DeleteMapping(path = "/", params = "id")
    public ResponseEntity<Void> deleteEventType(@RequestParam("id") int id){
        boolean result = eventTypeService.deleteEventType(id);
        if (result) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }

}
