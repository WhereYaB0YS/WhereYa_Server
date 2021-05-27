package com.where.WhereYouAt.controller.dto.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Null;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentProximateDto {

    private String message;

    private Long id;

    private String name;

    @JsonProperty("place_name")
    private String placeName;

    private String time;

    private String timer;

}
