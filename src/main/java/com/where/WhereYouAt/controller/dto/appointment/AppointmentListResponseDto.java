package com.where.WhereYouAt.controller.dto.appointment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentListResponseDto {

    private List<?> promiseList;
}
