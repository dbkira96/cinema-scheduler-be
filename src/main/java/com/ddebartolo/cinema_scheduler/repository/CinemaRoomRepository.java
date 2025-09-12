package com.ddebartolo.cinema_scheduler.repository;

import com.ddebartolo.cinema_scheduler.model.CinemaRoom;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {

    Optional<CinemaRoom> findByName(String name);
}