package it.woodcast.services;

import it.woodcast.entity.CalendarEntity;
import it.woodcast.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {
    @Autowired
    CalendarRepository calendarRepository;

    public List<CalendarEntity> saveAll(List<CalendarEntity> calendarEntities){
        return calendarRepository.saveAll(calendarEntities);

    }
    public void deleteAllByCustomer(Integer id){
        calendarRepository.deleteAllByCustomer(id);
    }



}
