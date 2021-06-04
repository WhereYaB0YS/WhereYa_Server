package com.where.WhereYouAt.controller.dto.appointment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AppointmentcheckDateDto {

    private List<LocalDate> datesWithEvent;
}
