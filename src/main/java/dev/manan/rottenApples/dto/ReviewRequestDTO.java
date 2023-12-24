package dev.manan.rottenApples.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDTO {
    private String movieId;
    private String body;
    @Min(1)
    @Max(100)
    private Integer score;
}
