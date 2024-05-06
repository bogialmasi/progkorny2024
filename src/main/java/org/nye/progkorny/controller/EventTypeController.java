package org.nye.progkorny.controller;

import org.nye.progkorny.service.impl.EventService;
import org.nye.progkorny.service.impl.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventtype")
public class EventTypeController {
    // NE LEGYEN BENNE POST ÉS DELETE!

        @Autowired
        private EventTypeService eventTypeServiceService;

    public EventTypeController(EventTypeService eventTypeServiceService) {
        this.eventTypeServiceService = eventTypeServiceService;
    }

    /*    MAPPING    */


}
