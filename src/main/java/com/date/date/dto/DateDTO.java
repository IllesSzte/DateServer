package com.date.date.dto;

import com.date.date.model.Date;
import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Data
@NoArgsConstructor
public class DateDTO {
    private int id;
    private String title;
    private String description;
    private int price;
    private int owner;
    private String place;
    private String crowded;
    private String activity;
    private String season;
    private String duration;
    private String daytime;
}
