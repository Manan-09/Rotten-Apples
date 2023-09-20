package dev.manan.rottenApples.repo;

import dev.manan.rottenApples.entity.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Map;

public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByMovieId(String movieId);

}
