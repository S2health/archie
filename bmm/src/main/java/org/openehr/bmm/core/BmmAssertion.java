package org.openehr.bmm.core;

public class BmmAssertion {

    private final String expression;

    private final String tag;

    public BmmAssertion(String expression, String tag) {
        this.expression = expression;
        this.tag = tag;
    }

    public String getExpression() {
        return expression;
    }

    public String getTag() {
        return tag;
    }
}
