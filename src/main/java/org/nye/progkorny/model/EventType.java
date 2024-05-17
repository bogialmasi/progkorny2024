package org.nye.progkorny.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Data
@AllArgsConstructor
@Table(name = "eventtype")
public class EventType {
    @Id
    private int id;
    @NotNull
    String name;

}
