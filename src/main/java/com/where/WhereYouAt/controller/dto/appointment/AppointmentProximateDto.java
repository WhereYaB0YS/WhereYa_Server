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

public class AppointmentProximateDto {

    private AppointmentResponseDto2 promise;

    @JsonProperty("lefttime")
    private LeftTime leftTime;
}
