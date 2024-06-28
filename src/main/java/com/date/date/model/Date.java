package com.date.date.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String place = "Does not matter";
    private String crowded = "Does not matter";
    private String activity = "Does not matter";
    private String season = "Does not matter";
    private String duration = "Does not matter";
    private String daytime = "Does not matter";
    @Override
    public String toString() {
        return "Date [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price + ", owner="
                + owner + ", place=" + place + ", crowded=" + crowded + ", activity=" + activity + ", season=" + season
                + ", duration=" + duration + ", daytime=" + daytime + "]";
    }
    
}
 