package com.ddebartolo.cinema_scheduler.controller;

import com.ddebartolo.cinema_scheduler.dto.MovieDto;
import com.ddebartolo.cinema_scheduler.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Movies", description = "API di gestione film + schedulazioni")
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    
    @GetMapping("/page")
    public Page<MovieDto> getMoviesPage(Pageable pageable) {
        return movieService.getMoviesPage(pageable);
    }

    // Public endpoint: get movie by id
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public MovieDto addMovie(@RequestBody MovieDto movie) {
        return movieService.addMovie(movie);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody MovieDto movie) {
        return movieService.updateMovie(id, movie)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}