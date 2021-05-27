package com.where.WhereYouAt.controller.dto.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LastedAppointmentResponseDto {

    private Long id;

    private String name;

    @JsonProperty("place_name")
    private String placeName;

    private String date;

    private List<Touchdown>touchdownList;

}
