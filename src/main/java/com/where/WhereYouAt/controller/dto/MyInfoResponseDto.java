package com.where.WhereYouAt.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyInfoResponseDto {

    private Long id;
    private String userId;
    private String nickname;

}
