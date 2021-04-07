package com.where.WhereYouAt.controller.dto;

import com.where.WhereYouAt.domain.Appointment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AppointmentDateListResponseDto {

    private List<AppointmentDateResponseDto> appointmentList ;
}
