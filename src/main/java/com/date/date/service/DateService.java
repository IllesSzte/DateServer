package com.date.date.service;

import com.date.date.dto.DateDTO;
import com.date.date.model.Date;
import com.date.date.repository.DateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DateService {
    @Autowired
    DateRepository dateRepository;

    public List<DateDTO> getAllDates() {
        List<Date> dates = dateRepository.findAll();

        List<DateDTO> dateDTOS = dates.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return dateDTOS;
    }

    public DateDTO convertToDTO(Date date) {
        return DateDTO.builder()
                .title(date.getTitle())
                .description(date.getDescription())
                .price(date.getPrice())
                .build();
    }

    public Date saveDate(DateDTO dateDto, int userId) {
        Date dateEntity = new Date();
        dateEntity.setTitle(dateDto.getTitle());
        dateEntity.setDescription(dateDto.getDescription());
        dateEntity.setPrice(dateDto.getPrice());
        dateEntity.setOwner(userId);
        dateEntity.setPlace(dateDto.getPlace());
        dateEntity.setCrowded(dateDto.getCrowded());
        dateEntity.setActivity(dateDto.getActivity());
        dateEntity.setSeason(dateDto.getSeason());
        dateEntity.setDuration(dateDto.getDuration());
        dateEntity.setDaytime(dateDto.getDaytime());
        return dateRepository.save(dateEntity);
    }
    public List<Date> searchDates(int price_in, String place_in, String crowded_in, String activity_in, String season_in, String duration_in, String daytime_in) {
            return dateRepository.searchDates(price_in, place_in, crowded_in, activity_in, season_in, duration_in, daytime_in);
    }
}
