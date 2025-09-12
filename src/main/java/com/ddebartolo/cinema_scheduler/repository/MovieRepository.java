package com.ddebartolo.cinema_scheduler.repository;

import com.ddebartolo.cinema_scheduler.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

    // Custom query to find movies with schedules in a specific date range
    @Query("SELECT DISTINCT m FROM Movie m JOIN Schedule s ON s.movie = m " +
           "WHERE (s.startDate BETWEEN :start AND :end) OR (s.endDate BETWEEN :start AND :end)")
    List<Movie> findMoviesByScheduleDateRange(@Param("start") LocalDate start, @Param("end") LocalDate end);
}