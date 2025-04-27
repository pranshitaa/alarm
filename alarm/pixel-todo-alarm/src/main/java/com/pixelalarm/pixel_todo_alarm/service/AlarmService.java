package com.pixelalarm.pixel_todo_alarm.service;

import com.pixelalarm.pixel_todo_alarm.model.Alarm;
import com.pixelalarm.pixel_todo_alarm.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlarmService {
    public class AlarmDetails {
        // existing fields and methods
    
        private LocalDateTime alarmTime;
        private boolean isActive;
        private String purpose;
    
        public LocalDateTime getAlarmTime() {
            return alarmTime;
        }
    
        public String getPurpose() {
            return purpose;
        }
    
        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }
    
        public void setAlarmTime(LocalDateTime alarmTime) {
            this.alarmTime = alarmTime;
        }
    
        public boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }
    
    }

    @Autowired
    private AlarmRepository alarmRepository;

    public Alarm createAlarm(LocalDateTime alarmTime, String purpose) {
        Alarm alarm = new Alarm();
        alarm.setAlarmTime(alarmTime);
        alarm.setPurpose(purpose);
        alarm.setActive(true); // alarms start as active
        return alarmRepository.save(alarm);
    }

    public List<Alarm> getAllAlarms() {
        return alarmRepository.findAll();
    }

    public Optional<Alarm> getAlarmById(UUID id) {
        return alarmRepository.findById(id);
    }

    public void disableAlarm(UUID id) {
        Optional<Alarm> optionalAlarm = alarmRepository.findById(id);
        if (optionalAlarm.isPresent()) {
            Alarm alarm = optionalAlarm.get();
            alarm.setActive(false);
            alarmRepository.save(alarm);
        }
    }

    public void snoozeAlarm(UUID id) {
        Optional<Alarm> optionalAlarm = alarmRepository.findById(id);
        if (optionalAlarm.isPresent()) {
            Alarm alarm = optionalAlarm.get();
            alarm.setAlarmTime(alarm.getAlarmTime().plusMinutes(5)); // snooze by 5 minutes
            alarmRepository.save(alarm);
        }
    }
    
}
