package com.where.WhereYouAt.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FriendsListResponseDto {

    private List<FriendsResponseDto> friends;
}
