package com.pixelalarm.pixel_todo_alarm.service;

import com.pixelalarm.pixel_todo_alarm.model.Alarm;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlarmService {

    private List<Alarm> alarms = new ArrayList<>();

    public List<Alarm> getAllAlarms() {
        return alarms;
    }

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }
}
