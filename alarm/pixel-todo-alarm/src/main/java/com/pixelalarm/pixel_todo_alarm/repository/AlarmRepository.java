package com.pixelalarm.pixel_todo_alarm.repository;

import com.pixelalarm.pixel_todo_alarm.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, UUID> {

    // You can define custom queries here later if needed, like find by purpose, etc.

}
