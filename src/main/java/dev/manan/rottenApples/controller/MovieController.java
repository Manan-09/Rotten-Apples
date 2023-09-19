package dev.manan.rottenApples.controller;

import dev.manan.rottenApples.entity.Movie;
import dev.manan.rottenApples.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping()
    public ResponseEntity<List<Movie>> fetchAllMovies() {
        return ResponseEntity.ok(movieService.fetchAllMovies());
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> fetchMovieById(@PathVariable String imdbId) {
        return ResponseEntity.ok(movieService.fetchMovieById(imdbId));
    }
}
