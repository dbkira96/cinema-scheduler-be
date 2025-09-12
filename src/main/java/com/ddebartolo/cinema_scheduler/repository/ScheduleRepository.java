package com.ddebartolo.cinema_scheduler.repository;

import com.ddebartolo.cinema_scheduler.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE (s.startDate BETWEEN :start AND :end) OR (s.endDate BETWEEN :start AND :end)")
    List<Schedule> findByStartOrEndDateBetween(@Param("start") LocalDate start,
                                               @Param("end") LocalDate end);
}
