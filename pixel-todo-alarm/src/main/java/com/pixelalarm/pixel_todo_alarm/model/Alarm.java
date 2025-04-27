package com.pixelalarm.pixel_todo_alarm.model;

import java.time.LocalDateTime;

public class Alarm {
    private LocalDateTime alarmTime;
    private String purpose;
    private boolean active;

    public Alarm() {}

    public Alarm(LocalDateTime alarmTime, String purpose) {
        this.alarmTime = alarmTime;
        this.purpose = purpose;
        this.active = true;
    }

    public LocalDateTime getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(LocalDateTime alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
