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
    public List<DateDTO> getAllDates() {
        return dateService.getAllDates();
    }

    @GetMapping(path = "/get-user-dates")
    public List<DateDTO> getUserDates(@RequestParam int userId) {
        return dateService.getUserDates(userId);
    }

    @GetMapping(path = "/get-user-and-partner-dates")
    public List<DateDTO> getUserAndPartnerDates(@RequestParam int userId) {
        return dateService.getUserAndPartnerDates(userId);
    }

    @PostMapping(path = "/create")
    public Date save(@RequestBody DateDTO dateDto, @RequestParam int userId) {
        return dateService.saveDate(dateDto, userId);
    }

    @DeleteMapping(path = "/delete-date")
    public void delete(@RequestParam int dateId) {
        dateService.deleteDate(dateId);
    }

    @GetMapping(path = "/get-filtered-dates")
    public List<Date> getFillteredDates(@RequestParam Integer userIn,
                                        @RequestParam(required = false) Integer priceIn,
                                        @RequestParam(required = false) String placeIn,
                                        @RequestParam(required = false) String crowdedIn,
                                        @RequestParam(required = false) String activityIn,
                                        @RequestParam(required = false) String seasonIn,
                                        @RequestParam(required = false) String durationIn,
                                        @RequestParam(required = false) String daytimeIn) {
        return dateService.searchDates(userIn, priceIn, placeIn, crowdedIn, activityIn, seasonIn, durationIn, daytimeIn);
    }
    @PutMapping(path = "/modify-date")
    public DateDTO modifyDate(@RequestBody DateDTO dateDTO,@RequestParam int id){
      return dateService.modifyDate(dateDTO,id);
    }
}
