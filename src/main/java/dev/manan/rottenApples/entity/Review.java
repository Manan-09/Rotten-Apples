package dev.manan.rottenApples.entity;

import dev.manan.rottenApples.dto.ReviewRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")
public class Review extends AuditableEntity{
    @Id
    private String id;
    @Indexed
    private String movieId;
    private String body;
    private Integer score;

    public final static String MOVIE_ID = "movieId";
    public final static String SCORE = "score";

    public static Review from(ReviewRequestDTO requestDTO) {
        Review review = new Review();
        review.setId(UUID.randomUUID().toString());
        review.setMovieId(requestDTO.getMovieId());
        review.setBody(requestDTO.getBody());
        review.setScore(requestDTO.getScore());
        review.initializeAuditFields();
        return review;
    }
}
