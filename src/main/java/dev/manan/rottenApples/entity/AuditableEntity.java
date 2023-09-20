package dev.manan.rottenApples.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditableEntity {
    protected Long createdTime;
    protected Long updatedTime;

    protected void initializeAuditFields() {
        Long currentTime = System.currentTimeMillis();
        if(isNull(createdTime)) {
            createdTime = currentTime;
        }
        updatedTime = currentTime;
    }

    public static final String UPDATED_TIME = "updatedTime";
}
