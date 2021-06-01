package com.where.WhereYouAt.vo;

import lombok.Data;

@Data
public class RoomMessage {

    private MessageType type;
    private String name;
    private String x;
    private String y;
}
