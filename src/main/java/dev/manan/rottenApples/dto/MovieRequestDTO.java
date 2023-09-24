package dev.manan.rottenApples.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestDTO {
    private String movieId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private List<String> genres;
}
