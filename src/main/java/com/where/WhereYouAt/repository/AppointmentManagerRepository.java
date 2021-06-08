package com.where.WhereYouAt.repository;

import com.where.WhereYouAt.domain.AppointmentManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentManagerRepository extends JpaRepository<AppointmentManager,Long> {

//    @Query(value = "SELECT * FROM AppointmentManager a WHERE a.userId = :userId and passed=")
    List<AppointmentManager> findAllByUserId(Long userId);

    Optional<AppointmentManager> findByUserIdAndAppointmentId(Long userId, Long appointmentId);

    //현재 날짜 이전의 약속들만 뽑아내기
//    @Query(value = "SELECT * FROM AppointmentManager a WHERE a.user = :user and passed=")
//    List<AppointmentManager> findLastedAppointments(@Param("userId") Long userId);



}
