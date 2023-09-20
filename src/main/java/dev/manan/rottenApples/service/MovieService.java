package dev.manan.rottenApples.service;

import dev.manan.rottenApples.entity.Movie;
import dev.manan.rottenApples.repo.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> fetchAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> fetchMovieById(String movieId) {
        return movieRepository.findByMovieId(movieId);
    }
}
