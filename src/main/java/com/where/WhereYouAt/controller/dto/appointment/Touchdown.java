package com.where.WhereYouAt.controller.dto.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Touchdown {

    @JsonProperty("user_nickname")
    private String userNickname;

    private Boolean check;

}
