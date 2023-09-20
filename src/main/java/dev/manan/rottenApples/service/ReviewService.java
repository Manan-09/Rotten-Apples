package dev.manan.rottenApples.service;

import dev.manan.rottenApples.dto.AverageScoreResult;
import dev.manan.rottenApples.dto.ReviewRequestDTO;
import dev.manan.rottenApples.entity.AuditableEntity;
import dev.manan.rottenApples.entity.Movie;
import dev.manan.rottenApples.entity.Review;
import dev.manan.rottenApples.repo.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    public List<Review> fetchReviewsByMovieId(String movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    public Review createReview(ReviewRequestDTO requestDTO) {
        Review review = Review.from(requestDTO);
        reviewRepository.insert(review);
        mongoTemplate.updateFirst(
                Query.query(Criteria.where(Movie.MOVIE_ID).is(requestDTO.getMovieId())),
                new Update().set(Movie.SCORE, getAverageScoreForMovie(requestDTO.getMovieId()))
                        .set(AuditableEntity.UPDATED_TIME, System.currentTimeMillis()),
                Movie.class
        );
        return review;
    }

    private int getAverageScoreForMovie(String movieId) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where(Review.MOVIE_ID).is(movieId)),
                Aggregation.group().avg(Review.SCORE).as("averageScore"),
                Aggregation.project().andExpression("round('$averageScore')").as("roundedAverageScore")
        );
        AggregationResults<AverageScoreResult> results = mongoTemplate.aggregate(aggregation, Review.class, AverageScoreResult.class);
        AverageScoreResult result = results.getUniqueMappedResult();
        if (result != null) {
            return result.getRoundedAverageScore();
        } else {
            return 0;
        }
    }
}
