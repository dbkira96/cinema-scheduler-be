package com.ddebartolo.cinema_scheduler.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "SCHEDULE")
@Data
@EqualsAndHashCode(callSuper = true)
public class Schedule extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CinemaRoom room;

    @ManyToOne
    private Movie movie;

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime time;


}