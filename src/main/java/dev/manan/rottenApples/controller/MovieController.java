package dev.manan.rottenApples.controller;

import dev.manan.rottenApples.entity.Movie;
import dev.manan.rottenApples.service.MovieService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

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
    public ResponseEntity<Movie> uploadMoviePosterImage(@PathVariable String movieId, @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(movieService.uploadPosterImage(movieId, file));
    }

    @GetMapping("/asset/{key}")
    public ResponseEntity<URL> fetchMovieAssetByKey(@PathVariable String key) throws Exception {
        return ResponseEntity.ok(movieService.generatePresignedUrlForAsset(key));
    }

    @DeleteMapping("/asset/{key}")
    public ResponseEntity<String> deleteMovieAssetByKey(@PathVariable String key) throws Exception {
        movieService.deleteAsset(key);
        return ResponseEntity.ok("SUCCESS");
    }
}
