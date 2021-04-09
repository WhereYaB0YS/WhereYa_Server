package com.where.WhereYouAt.controller.dto.user;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    @NotEmpty
    private String userId;


    @NotEmpty
    private String password;
}
