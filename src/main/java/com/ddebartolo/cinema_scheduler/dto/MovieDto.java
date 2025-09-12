package com.ddebartolo.cinema_scheduler.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MovieDto {

    @Schema(hidden = true)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Schema(example = "Paprika")
    private String title;

    @NotNull
    @Size(min = 1, max = 255)
    @Schema(example = "Satoshi Kon")
    private String director;

    @Schema(name = "image", description = "URL dell'immagine del film")
    @JsonProperty("image")
    private String image;

    @NotNull
    @Min(1)
    @Schema(example = "90")
    private Integer duration; 

    @Schema(example = "2006-11-25")
    private LocalDate releaseDate;

    @Schema(example = "Sci-Fi")
    private String genre;


    
}
