package com.ddebartolo.cinema_scheduler.dto.open;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@JsonPropertyOrder({"id", "title", "rooms"})
public class MovieScheduleDTO {
    @Schema(name = "id", description = "ID del film")
    @JsonProperty("id")
    private Long movieId;

    @Schema(name = "image", description = "URL dell'immagine del film")
    @JsonProperty("image")
    private String image;

    @Schema(name = "title", description = "Titolo del film")
    @JsonProperty("title")
    private String title;

    @Schema(name = "rooms", description = "Sale e orari di proiezione")
    @JsonProperty("rooms")
    private List<RoomScheduleDTO> rooms;
}