package org.openehr.bmm.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BmmFeature<T extends BmmType> extends BmmFormalElement<T> {

    @JsonIgnore
    private BmmClass scope;

    BmmFeature() {
        super();
    }

    public BmmFeature(String aName, T aType, boolean isMandatoryFlag, String aDocumentation) {
        super(aName, aType, isMandatoryFlag, aDocumentation);
    }

    public BmmClass getScope() {
        return scope;
    }
}
