package org.openehr.bmm.core;

public class BmmParameter<T extends BmmType> extends BmmReadonlyVariable<T> {

    public BmmParameter(String aName, T aType, boolean isMandatoryFlag, String aDocumentation) {
        super(aName, aType, isMandatoryFlag, aDocumentation);
    }

}
