package com.where.WhereYouAt.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentFriendDto {

    private String nickname;
    private String profileImg;

}
