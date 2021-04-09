package com.where.WhereYouAt.controller.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequestDto {

    private Long id;

    @NotEmpty
    private String name;

    private String memo;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;


    private DestinationDto destination;


    private List<String> friends;
}
