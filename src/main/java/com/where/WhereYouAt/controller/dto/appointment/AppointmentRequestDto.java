package com.where.WhereYouAt.controller.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequestDto {

    private Long id;

    private String name;

    private String memo;

    private LocalDate date;

    private LocalTime time;

    private DestinationDto destination;

    private List<String> friends;
}
