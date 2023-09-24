package dev.manan.rottenApples.controller;

import dev.manan.rottenApples.dto.ReviewRequestDTO;
import dev.manan.rottenApples.entity.Movie;
import dev.manan.rottenApples.entity.Review;
import dev.manan.rottenApples.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{movieId}")
    public ResponseEntity<List<Review>> fetchMovieById(@PathVariable String movieId) {
        return ResponseEntity.ok(reviewService.fetchReviewsByMovieId(movieId));
    }

    @PostMapping()
    public ResponseEntity<Review> createMovie(@RequestBody ReviewRequestDTO requestDTO) {
        return ResponseEntity.ok(reviewService.createReview(requestDTO));
    }
}
