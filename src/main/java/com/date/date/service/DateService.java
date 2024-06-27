package com.date.date.service;

import com.date.date.dto.DateDTO;
import com.date.date.model.Date;
import com.date.date.model.User;
import com.date.date.repository.DateRepository;
import com.date.date.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return convertToDTO(dates);
    }

    private List<DateDTO> convertToDTO(List<Date> dates) {
        return dates.stream().map(date -> DateDTO.builder()
                .title(date.getTitle())
                .description(date.getDescription())
                .price(date.getPrice())
                .place(date.getPlace())
                .crowded(date.getCrowded())
                .activity(date.getActivity())
                .season(date.getSeason())
                .duration(date.getDuration())
                .daytime(date.getDaytime())
                .build()).collect(Collectors.toList());
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

    public List<Date> searchDates(Integer userId, Integer price_in, String place_in, String crowded_in, String activity_in, String season_in, String duration_in, String daytime_in) {
        Integer partnerId = userRepository.findUserById(userId).getPartnerId();
        return dateRepository.searchDates(userId, partnerId, price_in, place_in, crowded_in, activity_in, season_in, duration_in, daytime_in);
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
        List<DateDTO> dates =  retrieveUserDates(userId);
        if (userService.hasPartner(user)) {
            dates.addAll(convertToDTO(dateRepository.findDateByOwner(partnerId)));
        }
        return dates;
    }

    private List<DateDTO> retrieveUserDates(int userId) {
        List<DateDTO> dates = new ArrayList<>(convertToDTO(dateRepository.findDateByOwner(userId)));
        dates.addAll(convertToDTO(dateRepository.findDateByOwner(0)));
        return dates;
    }

    public DateDTO modifyDate(DateDTO dateDTO, int id) {
        Optional<Date> optionalDate = dateRepository.findById(id);
        if (optionalDate.isPresent()) {
            Date date = optionalDate.get();
            date.setTitle(dateDTO.getTitle());
            date.setDescription(dateDTO.getDescription());
            date.setPrice(dateDTO.getPrice());
            date.setPlace(dateDTO.getPlace());
            date.setCrowded(dateDTO.getCrowded());
            date.setActivity(dateDTO.getActivity());
            date.setSeason(dateDTO.getSeason());
            date.setDuration(dateDTO.getDuration());
            date.setDaytime(dateDTO.getDaytime());
            dateRepository.save(date);
            return convertToDTO(date);
        } else {
            throw new IllegalArgumentException("Date with id " + id + " not found");
        }
    }
}
