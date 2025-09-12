package com.ddebartolo.cinema_scheduler.dto.open;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class RoomScheduleDTO {
    @Schema(name = "id", description = "ID della sala")
    @JsonProperty("id")
    private Long roomId;

    @Schema(name = "name", description = "Nome della sala")
    @JsonProperty("name")
    private String roomName;

    @Schema(name = "times", description = "Orari di proiezione (formato HH:mm)", type = "array", example = "[\"18:30\", \"21:00\"]", implementation = String.class)
    @JsonProperty("times")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private List<LocalTime> scheduledTimes;
}