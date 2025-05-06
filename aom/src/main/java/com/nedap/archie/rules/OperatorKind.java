package com.nedap.archie.rules;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;
import java.util.Set;

/**
 * Created by pieter.bos on 27/10/15.
 */
public enum OperatorKind {
    @JsonProperty(value="eq")
    @JsonAlias("eq")
    eq("op_eq", "="),
    @JsonProperty(value="ne")
    @JsonAlias("ne")
    ne("op_ne", "!=", "≠"),
    @JsonProperty(value="le")
    @JsonAlias("le")
    le("op_le", "<=", "≤"),
    @JsonProperty(value="lt")
    @JsonAlias("lt")
    lt("op_lt", "<"),
    @JsonProperty(value="ge")
    @JsonAlias("ge")
    ge("op_ge", ">=", "≥"),
    @JsonProperty(value="gt")
    @JsonAlias("gt")
    gt("op_gt", ">"),
    @JsonProperty(value="matches")
    @JsonAlias("matches")
    matches("op_matches", "matches", "∈", "is_in"),
    @JsonProperty(value="not")
    @JsonAlias("not")
    not("op_not", "not", "!", "∼", "¬"),
    @JsonProperty(value="and")
    @JsonAlias("and")
    and("op_and", "and", "∧"),
    @JsonProperty(value="or")
    @JsonAlias("or")
    or("op_or", "or", "∨"),
    @JsonProperty(value="xor")
    @JsonAlias("xor")
    xor("op_xor", "xor", "⊻"),
    @JsonProperty(value="implies")
    @JsonAlias("implies")
    implies("op_implies", "implies", "⇒"),
    @JsonProperty(value="for_all")
    @JsonAlias("for_all")
    for_all("op_for_all", "for_all", "∀", "every"),
    @JsonProperty(value="exists")
    @JsonAlias("exists")
    exists("op_exists", "exists" ,"∃"),
    @JsonProperty(value="plus")
    @JsonAlias("plus")
    plus("op_plus", "+"),
    @JsonProperty(value="minus")
    @JsonAlias("minus")
    minus("op_minus", "-"),
    @JsonProperty(value="multiply")
    @JsonAlias("multiply")
    multiply("op_multiply", "*"),
    @JsonProperty(value="divide")
    @JsonAlias("divide")
    divide("op_divide", "/"),
    @JsonProperty(value="modulo")
    @JsonAlias("modulo")
    modulo("op_modulo", "%"),
    @JsonProperty(value="exponent")
    @JsonAlias("exponent")
    exponent("op_exponent", "^");


    private final String identifier;
    private final ImmutableSet<String> codes;

    OperatorKind(String identifier, String... items) {
        this.identifier = identifier;
        codes = ImmutableSet.copyOf(items);
    }

    public String getDefaultCode() {
        return codes.iterator().next();
    }

    public static OperatorKind parseFromIdentifier(String identifier) {
        for(OperatorKind operator:values()) { //TODO: a hash implementation might be faster
            if(operator.name().equals(identifier)) {
                return operator;
            } else if (operator.getIdentifier().equals(identifier)) {
                return operator;
            }
        }
        return null;
    }

    public static OperatorKind parse(String operatorString) {
        operatorString = operatorString.toLowerCase();
        for(OperatorKind operator:values()) { //TODO: a hash implementation might be faster
            if(operator.codes.contains(operatorString)) {
                return operator;
            }
        }
        return null;
    }

    public String getIdentifier() {
        return identifier;
    }
}
