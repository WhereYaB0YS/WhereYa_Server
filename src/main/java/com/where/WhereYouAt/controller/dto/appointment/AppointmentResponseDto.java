package com.where.WhereYouAt.controller.dto.appointment;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AppointmentResponseDto {

    private Long id;

    private String name;

    private String memo;

    private String destination;

    private String date;

    private List<AppointmentFriendDto> friends;


}
