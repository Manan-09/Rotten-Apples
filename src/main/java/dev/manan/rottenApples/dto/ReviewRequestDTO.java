package dev.manan.rottenApples.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDTO {
    private String movieId;
    private String body;
    private Integer score;
}
