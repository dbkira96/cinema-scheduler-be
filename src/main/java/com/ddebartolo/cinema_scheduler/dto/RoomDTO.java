package com.ddebartolo.cinema_scheduler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO per la sala cinema")
public class RoomDTO {
    
    @Schema(description = "ID della sala")
    private Long id;

    @Schema(description = "Nome della sala")
    private String name;
    
}
