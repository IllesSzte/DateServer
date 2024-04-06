package com.date.date.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "date")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description = title;
    private int price = 0;
    private int owner = 0;
    private String place = "Anywhere";
    private String crowded = "Medium";
    private String activity = "Usual";
    private String season = "Anytime";
    private String duration = "Half day";
    private String daytime = "Anytime";
}
 