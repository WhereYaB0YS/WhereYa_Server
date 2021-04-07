package com.where.WhereYouAt.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentDateResponseDto {

    private Long id;

    private String name;

    private String destination;

    private String time;
}
