package com.where.WhereYouAt.controller.dto.appointment;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AppointmentDateListResponseDto {

    private List<AppointmentDateResponseDto> appointmentList ;
}
