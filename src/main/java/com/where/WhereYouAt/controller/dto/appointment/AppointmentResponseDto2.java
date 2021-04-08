package com.where.WhereYouAt.controller.dto.appointment;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AppointmentResponseDto2 {

    private Long id;

    private String name;

    private String memo;

    private DestinationDto destination;

    private String date;

    private String time;

    private List<String> friends;

}
