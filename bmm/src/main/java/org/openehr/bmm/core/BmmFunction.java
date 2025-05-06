package org.openehr.bmm.core;

import java.util.List;

/**
 * A formal element with signature of the form: name ({arg:TArg}*):TResult.
 * A function is a computed (rather than data) element, generally assumed to be non-state-changing.
 */
public class BmmFunction<T extends BmmType> extends BmmRoutine<T> {

    public BmmFunction(String aName, T aType, boolean isMandatoryFlag, String aDocumentation) {
        super(aName, aType, isMandatoryFlag, aDocumentation);
    }

}
