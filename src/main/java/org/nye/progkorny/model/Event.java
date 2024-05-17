package org.nye.progkorny.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    private int id;
    @NotNull
    private Timestamp datetime;
    private String location;
    private int eventTypeId;
    private String name;
    private int userId;
}
