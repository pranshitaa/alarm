package com.pixelalarm.pixel_todo_alarm.controller;

import com.pixelalarm.pixel_todo_alarm.model.Alarm;
import com.pixelalarm.pixel_todo_alarm.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/alarms")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @GetMapping
    public String listAlarms(Model model) {
        List<Alarm> alarms = alarmService.getAllAlarms();
        model.addAttribute("alarms", alarms);
        return "alarms";
    }

    @PostMapping("/create")
    public String createAlarm(@RequestParam("time") String time,
                               @RequestParam("purpose") String purpose) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime alarmTime = LocalDateTime.parse(time, formatter);
        alarmTime = alarmTime.withSecond(0).withNano(0);
        alarmService.createAlarm(alarmTime, purpose);
        return "redirect:/alarms";
    }

    @PostMapping("/disable/{id}")
    @ResponseBody
    public String disableAlarm(@PathVariable UUID id) {
        alarmService.disableAlarm(id);
        return "Alarm disabled";
    }

    @GetMapping("/capture")
    public String capturePage() {
        return "capture";
    }

    @PostMapping("/disable-after-photo")
    public String disableAlarmAfterPhoto() {
        List<Alarm> alarms = alarmService.getAllAlarms();
        for (Alarm alarm : alarms) {
            if (alarm.isActive()) {
                alarmService.disableAlarm(alarm.getId());
                break;
            }
        }
        return "celebrate";
    }

    @PostMapping("/snooze/{id}")
    public String snoozeAlarm(@PathVariable UUID id) {
        alarmService.snoozeAlarm(id);
        return "snoozed";
    }

    @GetMapping("/ringing")
    public String ringingPage(@RequestParam("purpose") String purpose,
                              @RequestParam("time") String time,
                              @RequestParam("id") UUID id,
                              Model model) {
        model.addAttribute("purpose", purpose);
        model.addAttribute("time", time);
        model.addAttribute("id", id);
        return "ringing";
    }
}
