package com.ddebartolo.cinema_scheduler.dto.open;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class DayScheduleDto {

    private LocalDate date;
    private List<MovieScheduleDTO> schedules;

}
