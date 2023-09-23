package dev.manan.rottenApples.controller;

import dev.manan.rottenApples.entity.Movie;
import dev.manan.rottenApples.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> fetchMovieById(@PathVariable String movieId) {
        return ResponseEntity.ok(movieService.fetchMovieById(movieId));
    }

    @PostMapping("/{movieId}/upload/poster")
    public ResponseEntity<Movie> uploadMoviePosterImage(@PathVariable String movieId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(movieService.uploadPoster(movieId, file));
    }
}
