package dev.manan.rottenApples.service;

import dev.manan.rottenApples.entity.AuditableEntity;
import dev.manan.rottenApples.entity.Movie;
import dev.manan.rottenApples.repo.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final StorageService storageService;
    private final MongoTemplate mongoTemplate;

    public List<Movie> fetchAllMovies() {
        return movieRepository.findAll();
    }

    public Movie fetchMovieById(String movieId) {
        return movieRepository.findByMovieId(movieId).orElseThrow();
    }

    public Movie uploadPoster(String movieId, MultipartFile file) {
        final String key = movieId+"_"+ UUID.randomUUID();
        storageService.uploadImage(key, file);
        Movie movie = fetchMovieById(movieId);
        movie.setPoster(key);
        movie.initializeAuditFields();
        movieRepository.save(movie);
        return movie;
    }
}
