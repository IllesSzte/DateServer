package com.date.date.service;

import com.date.date.dto.DateDTO;
import com.date.date.model.Date;
import com.date.date.repository.DateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
        return dateRepository.save(dateEntity);
    }
}
