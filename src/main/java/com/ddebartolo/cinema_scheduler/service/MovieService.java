package com.ddebartolo.cinema_scheduler.service;

import com.ddebartolo.cinema_scheduler.dto.MovieDto;
import com.ddebartolo.cinema_scheduler.model.Movie;
import com.ddebartolo.cinema_scheduler.repository.MovieRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(this::convertToDto).toList();
    }

    private MovieDto convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }

    public Page<MovieDto> getMoviesPage(Pageable pageable) {
        Page<Movie> moviesPage = movieRepository.findAll(pageable);
        return moviesPage.map(this::convertToDto);
    }

    public Optional<MovieDto> getMovieById(Long id) {
        return movieRepository.findById(id).map(this::convertToDto);
    }

    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie = toEntity(movieDto);
        return convertToDto(movieRepository.save(movie));
    }

    public Optional<MovieDto> updateMovie(Long id, MovieDto movieDetails) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(movieDetails.getTitle());
            movie.setDuration(movieDetails.getDuration());
            movie.setReleaseDate(movieDetails.getReleaseDate());
            movie.setGenre(movieDetails.getGenre());
            Movie updated = movieRepository.save(movie);
            return convertToDto(updated);
        });
    }

    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Movie toEntity(MovieDto movieDto) {
            return modelMapper.map(movieDto, Movie.class);
    }
   

}