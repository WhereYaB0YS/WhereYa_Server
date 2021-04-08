package com.where.WhereYouAt.controller.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequestDto {

    private String name;

    private String memo;

    private LocalDateTime date;

    private String destination;

    private List<String> friends;
}
