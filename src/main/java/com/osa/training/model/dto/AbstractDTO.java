package com.osa.training.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public abstract class AbstractDTO {

    private Long id;
    private Long version;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
