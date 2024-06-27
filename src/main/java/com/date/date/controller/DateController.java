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

    @GetMapping(path = "/get-user-dates")
    private List<DateDTO> getUserDates(@RequestParam int userId) {
        return dateService.getUserDates(userId);
    }

    @GetMapping(path = "/get-user-and-partner-dates")
    private List<DateDTO> getUserAndPartnerDates(@RequestParam int userId) {
        return dateService.getUserAndPartnerDates(userId);
    }

    @PostMapping(path = "/create")
    private Date save(@RequestBody DateDTO dateDto, @RequestParam int userId) {
        return dateService.saveDate(dateDto, userId);
    }

    @DeleteMapping(path = "/delete-date")
    private void delete(@RequestParam int dateId) {
        dateService.deleteDate(dateId);
    }

    @GetMapping(path = "/get-filtered-dates")
    public List<Date> getFillteredDates(@RequestParam Integer user_in,
                                        @RequestParam(required = false) Integer price_in,
                                        @RequestParam(required = false) String place_in,
                                        @RequestParam(required = false) String crowded_in,
                                        @RequestParam(required = false) String activity_in,
                                        @RequestParam(required = false) String season_in,
                                        @RequestParam(required = false) String duration_in,
                                        @RequestParam(required = false) String daytime_in) {
        return dateService.searchDates(user_in, price_in, place_in, crowded_in, activity_in, season_in, duration_in, daytime_in);
    }
    @PutMapping(path = "/modify-date")
    public DateDTO modifyDate(@RequestBody DateDTO dateDTO,@PathVariable Long id){
      return dateService.modifyDate(dateDTO,id);
    }
}
