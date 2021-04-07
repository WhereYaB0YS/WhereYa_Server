package com.where.WhereYouAt.controller.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequestDto {

    private String name;

    private String memo;

    private LocalDateTime date;

    private String destination;

    private String friends;
}
