package com.where.WhereYouAt.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class LoginResponseDto {

    private String jwt;
}
