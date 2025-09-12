package com.ddebartolo.cinema_scheduler.controller;

import com.ddebartolo.cinema_scheduler.dto.open.MovieScheduleDTO;
import com.ddebartolo.cinema_scheduler.service.ScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

@Tag(name = "Public", description = "API pubbliche per la consultazione delle programmazioni")
@RestController
@RequestMapping("/public/movie-schedule")
public class PublicScheduleController {
    @GetMapping("/by-day")
    @Cacheable(value = "movieSchedulesByDayRange", key = "#start.toString() + '-' + #end.toString()")
    public List<com.ddebartolo.cinema_scheduler.dto.open.DayScheduleDto> getMovieSchedulesGroupedByDay(
            @Parameter(description = "Start date (yyyy-MM-dd)", example = "2025-09-13")
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @Parameter(description = "End date (yyyy-MM-dd)", example = "2025-09-15")
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return scheduleService.getMovieSchedulesGroupedByDay(start, end);
    }

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/by-date-range")
    @Cacheable(value = "movieSchedulesByDateRange", key = "#start.toString() + '-' + #end.toString()")
    public List<MovieScheduleDTO> getMovieSchedulesByDateRange(
            @Parameter(description = "Start date (yyyy-MM-dd)", example = "2025-09-13")
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @Parameter(description = "End date (yyyy-MM-dd)", example = "2025-09-15")
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return scheduleService.getMovieSchedulesByDateRange(start, end);
    }


}