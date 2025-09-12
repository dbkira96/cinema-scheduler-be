package com.ddebartolo.cinema_scheduler.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ddebartolo.cinema_scheduler.model.CinemaRoom;

@Data
public class ScheduleDTO {
    @Schema(example = "1")
    private Long id;

    @NotNull
    private CinemaRoom room;
    
    @NotNull
    private MovieDto movie;

    @NotNull
    @Schema(example = "2025-09-15")
    private LocalDate startDate;

    @NotNull
    @Schema(example = "2025-09-15")
    private LocalDate endDate;

    @NotNull
    @Schema(example = "19:30", pattern = "HH:mm", description = "Orario in formato HH:mm")
    private LocalTime time;

    // getters and setters
}