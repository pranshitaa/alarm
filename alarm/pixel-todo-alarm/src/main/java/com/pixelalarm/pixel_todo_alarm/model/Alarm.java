package com.pixelalarm.pixel_todo_alarm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime alarmTime;

    private String purpose;

    private boolean isActive;
}
