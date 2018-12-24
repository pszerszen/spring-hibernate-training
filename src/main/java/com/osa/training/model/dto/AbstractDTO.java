package com.osa.training.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AbstractDTO {

    private Long id;
    private int version;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
