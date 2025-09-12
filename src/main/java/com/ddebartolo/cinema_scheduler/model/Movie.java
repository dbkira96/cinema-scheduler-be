package com.ddebartolo.cinema_scheduler.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "MOVIE")
@Data
@EqualsAndHashCode(callSuper = true)
public class Movie extends AuditableEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int duration; // duration in minutes
    private LocalDate releaseDate;
    private String genre;
    private String director;
    private String image;

    @OneToMany(mappedBy = "movie")
    private List<Schedule> schedules;

    
}