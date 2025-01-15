package com.nedap.archie.rules;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * TODO: this should contain all primitive types and primitive types should be merged into this
 * Created by pieter.bos on 27/10/15.
 */
public enum ExpressionType {
     BOOLEAN, STRING, INTEGER, REAL, DATE, TIME, DATETIME, DURATION, C_STRING;

    @JsonCreator
    public static ExpressionType fromString(String string) {
        switch(string) {
            case "Boolean":
                return BOOLEAN;
            case "String":
                return STRING;
            case "Integer":
                return INTEGER;
            case "Real":
                return REAL;
            case "Date":
                return DATE;
            case "Time":
                return TIME;
            case "Date_time":
                return DATETIME;
            case "Duration":
                return DURATION;
            case "C_STRING":
                return C_STRING;
        }
        return null;
    }

    @JsonValue
    public String toString() {
        switch(this) {
            case BOOLEAN:
                return "Boolean";
            case STRING:
                return "String";
            case INTEGER:
                return "Integer";
            case REAL:
                return "Real";
            case DATE:
                return "Date";
            case TIME:
                return "Time";
            case DATETIME:
                return "Date_time";
            case DURATION:
                return "Duration";
            case C_STRING:
                return "C_STRING";
        }
        return null;
    }
}
