package com.where.WhereYouAt.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderStatus {

    MALE(0,"male"),
    FEMALE(1,"female");

    private Integer id;
    private String title;
}