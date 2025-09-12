package com.ddebartolo.cinema_scheduler.model;

import java.time.LocalDateTime;

import com.ddebartolo.cinema_scheduler.model.enums.RoomTechnology;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CINEMA_ROOM")
@Data
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int capacity;

    @Enumerated(EnumType.STRING)
    private RoomTechnology technology;


    @Column(updatable = false)
    private String createUser;
    private String updateUser;
    @Column(updatable = false)
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

   
}