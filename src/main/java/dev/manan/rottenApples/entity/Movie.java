package dev.manan.rottenApples.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.manan.rottenApples.dto.MovieRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "movies")
public class Movie extends AuditableEntity{
    @Id
    private String movieId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private Integer score;

    public final static String MOVIE_ID = "movieId";
    public final static String SCORE = "score";
    public final static String POSTER = "poster";

    public static Movie from(MovieRequestDTO requestDTO) {
        Movie movie = new Movie();
        movie.setMovieId(requestDTO.getMovieId());
        movie.setTitle(requestDTO.getTitle());
        movie.setGenres(requestDTO.getGenres());
        movie.setTrailerLink(requestDTO.getTrailerLink());
        movie.setReleaseDate(requestDTO.getReleaseDate());
        movie.initializeAuditFields();
        return movie;
    }
}
