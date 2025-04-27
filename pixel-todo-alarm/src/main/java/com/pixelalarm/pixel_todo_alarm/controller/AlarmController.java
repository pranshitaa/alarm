package com.pixelalarm.pixel_todo_alarm.controller;

import com.pixelalarm.pixel_todo_alarm.model.Alarm;
import com.pixelalarm.pixel_todo_alarm.service.AlarmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/alarms")
public class AlarmController {

    private final AlarmService alarmService;

    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping
    public String getAlarms(Model model) {
        model.addAttribute("alarms", alarmService.getAllAlarms());
        return "alarms"; // Match alarms.html file
    }

    @PostMapping("/create")
    public String createAlarm(@RequestParam("time") String time, @RequestParam("purpose") String purpose) {
        LocalDateTime alarmTime = LocalDateTime.parse(time);
        Alarm alarm = new Alarm(alarmTime, purpose);
        alarmService.addAlarm(alarm);
        return "redirect:/alarms";
    }

    @GetMapping("/check")
    @ResponseBody
    public String checkAlarms() {
        LocalDateTime now = LocalDateTime.now();
        for (Alarm alarm : alarmService.getAllAlarms()) {
            if (alarm.isActive() &&
                alarm.getAlarmTime().isBefore(now.plusSeconds(1)) &&
                alarm.getAlarmTime().isAfter(now.minusSeconds(1))) {
                return "RING";
            }
        }
        return "NO_RING";
    }
}
