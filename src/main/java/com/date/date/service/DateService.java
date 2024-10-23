package com.date.date.service;

import com.date.date.dto.DateDTO;
import com.date.date.model.Date;
import com.date.date.model.User;
import com.date.date.repository.DateRepository;
import com.date.date.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class DateService {
    @Autowired
    DateRepository dateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public List<DateDTO> getAllDates() {
        List<Date> dates = dateRepository.findAll();
        return convertDatesToDTOs(dates);
    }

    private List<DateDTO> convertDatesToDTOs(List<Date> dates) {
        return dates.stream().map(date -> DateDTO.builder()
                .id(date.getId())
                .title(date.getTitle())
                .description(date.getDescription())
                .price(date.getPrice())
                .owner(date.getOwner())
                .place(date.getPlace())
                .crowded(date.getCrowded())
                .activity(date.getActivity())
                .season(date.getSeason())
                .duration(date.getDuration())
                .daytime(date.getDaytime())
                .build()).collect(Collectors.toList());
    }

    private DateDTO convertDateToDTO(Date date) {
        return DateDTO.builder()
                .id(date.getId())
                .title(date.getTitle())
                .description(date.getDescription())
                .price(date.getPrice())
                .owner(date.getOwner())
                .place(date.getPlace())
                .crowded(date.getCrowded())
                .activity(date.getActivity())
                .season(date.getSeason())
                .duration(date.getDuration())
                .daytime(date.getDaytime())
                .build();
    }

    public Date saveDate(DateDTO dateDto, int userId) {
        Date dateEntity = new Date();
        dateEntity.setTitle(dateDto.getTitle());
        dateEntity.setDescription(dateDto.getDescription());
        dateEntity.setPrice(dateDto.getPrice());
        dateEntity.setOwner(dateDto.getOwner());
        dateEntity.setPlace(dateDto.getPlace());
        dateEntity.setCrowded(dateDto.getCrowded());
        dateEntity.setActivity(dateDto.getActivity());
        dateEntity.setSeason(dateDto.getSeason());
        dateEntity.setDuration(dateDto.getDuration());
        dateEntity.setDaytime(dateDto.getDaytime());
        return dateRepository.save(dateEntity);
    }

    public List<Date> searchDates(Integer user_in, Integer price_in, String place_in, String crowded_in,
            String activity_in, String season_in, String duration_in, String daytime_in) {
        Integer partner_in = userRepository.findUserById(user_in).getPartnerId();
        return dateRepository.searchDates(
                user_in,
                partner_in,
                price_in,
                place_in,
                crowded_in,
                activity_in,
                season_in,
                duration_in,
                daytime_in);
    }

    public void deleteDate(int dateId) {
        dateRepository.deleteById(dateId);
    }

    public List<DateDTO> getUserDates(int userId) {
        return retrieveUserDates(userId);
    }

    public List<DateDTO> getUserAndPartnerDates(int userId) {
        User user = userRepository.findUserById(userId);
        int partnerId = user.getPartnerId();
        List<DateDTO> dates = retrieveUserDates(userId);
        if (userService.hasPartner(user)) {
            dates.addAll(convertDatesToDTOs(dateRepository.findDateByOwner(partnerId)));
        }
        return dates;
    }

    private List<DateDTO> retrieveUserDates(int userId) {
        List<DateDTO> dates = new ArrayList<>(convertDatesToDTOs(dateRepository.findDateByOwner(userId)));
        dates.addAll(convertDatesToDTOs(dateRepository.findDateByOwner(0)));
        return dates;
    }

    @Transactional
    public DateDTO modifyDate(DateDTO dateDTO, int id) {
        Optional<Date> optionalDate = dateRepository.findById(id);
        if (optionalDate.isPresent()) {
            Date date = optionalDate.get();
            date.setId(id);
            date.setTitle(dateDTO.getTitle());
            date.setDescription(dateDTO.getDescription());
            date.setPrice(dateDTO.getPrice());
            date.setOwner(dateDTO.getOwner());
            date.setPlace(dateDTO.getPlace());
            date.setCrowded(dateDTO.getCrowded());
            date.setActivity(dateDTO.getActivity());
            date.setSeason(dateDTO.getSeason());
            date.setDuration(dateDTO.getDuration());
            date.setDaytime(dateDTO.getDaytime());
            dateRepository.save(date);
            Date savedDate = dateRepository.findById(id).orElse(null);
            System.out.println("Saved Date: " + savedDate);

            return convertDateToDTO(date);
        } else {
            throw new IllegalArgumentException("Date with id " + id + " not found");
        }
    }
}
