package org.openehr.bmm.core;

public abstract class BmmVariable<T extends BmmType> extends BmmFormalElement<T> {

    public BmmVariable(String aName, T aType, boolean isMandatoryFlag, String aDocumentation) {
        super(aName, aType, isMandatoryFlag, aDocumentation);
    }

}
