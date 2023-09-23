package dev.manan.rottenApples.service;

import dev.manan.rottenApples.entity.Movie;
import dev.manan.rottenApples.repo.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final StorageService storageService;

    private final long expiryWindow = 5*60*1000;

    public List<Movie> fetchAllMovies() {
        return movieRepository.findAll();
    }

    public Movie fetchMovieById(String movieId) {
        return movieRepository.findByMovieId(movieId).orElseThrow();
    }

    public Movie uploadPosterImage(String movieId, MultipartFile file) throws Exception {
        final String key = movieId + "_" + UUID.randomUUID();
        storageService.uploadImage(key, file);
        Movie movie = fetchMovieById(movieId);
        storageService.deleteObject(movie.getPoster());
        movie.setPoster(key);
        movie.initializeAuditFields();
        movieRepository.save(movie);
        return movie;
    }

    public URL generatePresignedUrlForAsset(String key) throws Exception {
        return storageService.generatePresignedUrl(key, expiryWindow);
    }

    public void deleteAsset(String key) throws Exception {
        storageService.deleteObject(key);
    }
}
