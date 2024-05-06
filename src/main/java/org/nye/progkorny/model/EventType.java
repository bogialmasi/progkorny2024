package org.nye.progkorny.model;

import jakarta.persistence.Entity;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "eventType")
public enum EventType {
    CELEBRATION,
    VACATION,
    FRIENDS,
    HOBBY,
    SCHOOL,
    WORK;

}
