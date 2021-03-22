package com.where.WhereYouAt.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendsResponseDto {

    private String profileImg;

    private String nickname;

    private boolean star;

}
