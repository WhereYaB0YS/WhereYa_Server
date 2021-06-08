package com.where.WhereYouAt.service;

import com.where.WhereYouAt.domain.Appointment;
import com.where.WhereYouAt.exception.NotExistedAppointmentException;
import com.where.WhereYouAt.repository.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class RoomService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void checkTouchdown(Long roomId, String name) {
        Appointment appointment = appointmentRepository.findById(roomId)
                .orElseThrow(NotExistedAppointmentException::new);
        for(int i=0; i<appointment.getAppointmentList().size();i++) {
            if (appointment.getAppointmentList().get(i).getUser().getNickname().equals(name)) {
                appointment.getAppointmentList().get(i).setTouchdown(true);
            }
        }
    }

}
