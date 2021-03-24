package com.where.WhereYouAt.repository;

import com.where.WhereYouAt.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
