package it.woodcast.logic;

import it.woodcast.entity.CalendarEntity;
import it.woodcast.entity.CustomerServiceEntity;
import it.woodcast.entity.ResourceEntity;
import it.woodcast.repository.CalendarRepository;
import it.woodcast.resources.CalendarResurce;
import it.woodcast.resources.CalendarSaveResurce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CalendarFacade {
    @Autowired
    CalendarRepository calendarRepository;

    public List<CalendarResurce> createAll(CalendarResurce calendarResurce) {

        List<Date> day = gerateCalendar();
        List<CalendarEntity> calendarEntities = new ArrayList<>();
        CustomerServiceEntity customerServiceEntities = new CustomerServiceEntity();
        customerServiceEntities.setId(calendarResurce.getCustomerService().getId());
        calendarResurce.getResource().stream().forEach(resource -> {
            day.stream().forEach(date -> {
                CalendarEntity calendarEntity = new CalendarEntity();
                calendarEntity.setMonth(date);
                calendarEntity.setWorkingDay(BigDecimal.ZERO);
                calendarEntity.setCustomerServiceEntities(customerServiceEntities);
                ResourceEntity resourceEntities = new ResourceEntity();
                resourceEntities.setId(resource.getId());
                calendarEntity.setResourceEntities(resourceEntities);
                calendarEntities.add(calendarEntity);
            });
        });

        System.out.println(calendarEntities);
        calendarRepository.saveAll(calendarEntities);
        return new ArrayList<>();
    }

    public CalendarResurce save(CalendarSaveResurce calendarResurce) {

        CalendarEntity calendarEntity = calendarRepository.findById(calendarResurce.getCalendarId()).get();
        calendarEntity.setWorkingDay(calendarResurce.getWorkingDay());
        calendarEntity.setId(calendarResurce.getCalendarId());
        calendarRepository.save(calendarEntity);
        return new CalendarResurce();
    }

    private List<Date> gerateCalendar() {
        List<Date> day = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Imposta il giorno al primo giorno del mese
        calendar.set(Calendar.MONTH, Calendar.JANUARY); // Imposta il mese a gennaio

        int year = 2024; // L'anno di cui si desidera istanziare le date

        for (int i = 0; i < 12; i++) {
            calendar.set(Calendar.MONTH, i); // Imposta il mese corrente nel ciclo
            Date date = new Date(calendar.getTimeInMillis());
            day.add(date);
        }
        return day;
    }


}
