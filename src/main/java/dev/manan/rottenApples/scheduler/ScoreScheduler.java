package dev.manan.rottenApples.scheduler;

import dev.manan.rottenApples.entity.AuditableEntity;
import dev.manan.rottenApples.entity.Movie;
import dev.manan.rottenApples.service.MovieService;
import dev.manan.rottenApples.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScoreScheduler {

    private final MovieService movieService;

    private final ReviewService reviewService;

    private final MongoTemplate mongoTemplate;

    // Run the task every 5 minutes (300,000 milliseconds)
    @Scheduled(fixedRate = 5*60*1000)
    public void updateMovieScore() {
        List<Movie> movies = movieService.fetchAllMovies();
        movies.parallelStream().forEach(movie -> {
            mongoTemplate.updateFirst(
                    Query.query(Criteria.where(Movie.MOVIE_ID).is(movie.getMovieId())),
                    new Update().set(Movie.SCORE, reviewService.getAverageScoreForMovie(movie.getMovieId()))
                            .set(AuditableEntity.UPDATED_TIME, System.currentTimeMillis()),
                    Movie.class
            );
        });
    }
}