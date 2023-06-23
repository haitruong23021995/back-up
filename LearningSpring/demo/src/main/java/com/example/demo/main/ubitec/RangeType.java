package com.example.demo.main.ubitec;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RangeType {

    OPEN("(%s, %s)"), CLOSED("[%s, %s]"), OPEN_CLOSED("(%s, %s]"),
    CLOSED_OPEN("[%s, %s)"), LESS_THAN("[Infinitive, %s)"), AT_LEAST("[%s, Infinitive]"),
    AT_MOST("[Infinitive, %s]"), GREATER_THAN("(%s, Infinitive]"), ALL("[Infinitive, Infinitive]");

    private String signature;
}
