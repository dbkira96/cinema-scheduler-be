package com.ddebartolo.cinema_scheduler.repository;

import com.ddebartolo.cinema_scheduler.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testSaveAndFindMovie() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDuration(120);
        movie.setGenre("Drama");
        Movie saved = movieRepository.save(movie);
        Optional<Movie> found = movieRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Test Movie");
    }
}
