package org.nye.progkorny.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.*;

@Entity
@Table(name = "event")
public class Event {
    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter @Setter
    private LocalDate date;
    @Getter @Setter
    private LocalTime time;
    @Getter @Setter
    private String location;
    @Getter @Setter @Enumerated
    private EventType eventType;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private int userId;

    public Event(int id, LocalDate date, LocalTime time, String location, EventType eventType, String description, int userId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.location = location;
        this.eventType = eventType;
        this.description = description;
        this.userId = userId;
    }
}
