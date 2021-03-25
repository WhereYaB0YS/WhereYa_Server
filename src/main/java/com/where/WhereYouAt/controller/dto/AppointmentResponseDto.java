package com.where.WhereYouAt.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AppointmentResponseDto {

    private Long id;

    private String name;

    private String memo;

    private String destination;

    private LocalDateTime date;

    private List<AppointmentFriendDto> friends;
}
