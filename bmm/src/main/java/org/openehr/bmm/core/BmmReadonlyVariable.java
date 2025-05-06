package org.openehr.bmm.core;

public abstract class BmmReadonlyVariable<T extends BmmType> extends BmmVariable<T> {

    public BmmReadonlyVariable(String aName, T aType, boolean isMandatoryFlag, String aDocumentation) {
        super(aName, aType, isMandatoryFlag, aDocumentation);
    }

}
