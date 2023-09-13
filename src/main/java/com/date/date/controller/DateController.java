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

}
