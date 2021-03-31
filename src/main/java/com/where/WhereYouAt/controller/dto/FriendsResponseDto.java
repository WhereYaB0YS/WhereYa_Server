package com.where.WhereYouAt.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendsResponseDto implements Comparable<FriendsResponseDto> {

    private String profileImg;

    private String nickname;

    private boolean star;

    @Override
    public int compareTo(FriendsResponseDto o) {
        return nickname.compareTo(o.nickname);
    }
}
