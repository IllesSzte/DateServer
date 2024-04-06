package com.date.date.controller;

import com.date.date.dto.DateDTO;
import com.date.date.model.Date;
import com.date.date.repository.DateRepository;
import com.date.date.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/date")
public class DateController {
    @Autowired
    DateService dateService;

    @GetMapping(path = "/get-all")
    private List<DateDTO> getAllDates() {
        return dateService.getAllDates();
    }

    @PostMapping(path = "/create")
    private Date save(@RequestBody DateDTO dateDto, @RequestParam int userId) {
        return dateService.saveDate(dateDto, userId);
    }
    @GetMapping(path ="/get-filtered-dates")
    public List<Date> getFillteredDates( @RequestParam int price_in,
                                          @RequestParam String place_in,
                                          @RequestParam String crowded_in,
                                          @RequestParam String activity_in,
                                          @RequestParam String season_in,
                                          @RequestParam String duration_in,
                                          @RequestParam String daytime_in){
        return dateService.searchDates(price_in,place_in,crowded_in,activity_in,season_in,duration_in,daytime_in);
    }

}
