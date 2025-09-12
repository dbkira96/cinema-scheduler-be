package com.ddebartolo.cinema_scheduler.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CinemaRoomDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    private Integer capacity;

    
    
}