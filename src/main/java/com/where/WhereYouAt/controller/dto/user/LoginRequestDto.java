package com.where.WhereYouAt.controller.dto.user;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    @NotNull
    private String userId;

    @NotNull
    private String password;
}
