package com.where.WhereYouAt.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomMessage {
    private Long roomId;
    private MessageType type;
    private String name;
    private String x;
    private String y;
    private boolean touchdown;
}
