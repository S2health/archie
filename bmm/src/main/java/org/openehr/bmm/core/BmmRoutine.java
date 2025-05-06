package org.openehr.bmm.core;

import java.util.ArrayList;
import java.util.List;

/**
 *     A feature defining a routine, scoped to a class.
 */
public abstract class BmmRoutine<T extends BmmType> extends BmmFeature<T> {

    /*
     * Formal parameters of the routine.
     */
    protected List<BmmParameter<? extends BmmType>> parameters = new ArrayList<>();

    /**
     *     Boolean conditions that must evaluate to True for the routine to execute correctly,
     *     May be used to generate exceptions if included in run-time build.
     *
     *     A False pre-condition implies an error in the passed parameters.
     */
    protected List<BmmAssertion> preConditions = new ArrayList<>();

    /**
     *     Boolean conditions that will evaluate to True if the routine executed correctly, May be used to generate exceptions if included in run-time build.
     *
     *     A False post-condition implies an error (i.e. bug) in routine code.
     */
    protected List<BmmAssertion> postConditions = new ArrayList<>();

    public BmmRoutine(String aName, T aType, boolean isMandatoryFlag, String aDocumentation) {
        super(aName, aType, isMandatoryFlag, aDocumentation);
    }

    public void setParameters(List<BmmParameter<? extends BmmType>> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(BmmParameter<? extends BmmType> parameter) {
        this.parameters.add(parameter);
    }

    public List<BmmParameter<? extends BmmType>> getParameters() {
        return parameters;
    }

    public List<BmmAssertion> getPreConditions() {
        return preConditions;
    }

    public List<BmmAssertion> getPostConditions() {
        return postConditions;
    }

    public void setPreConditions(List<BmmAssertion> preConditions) {
        this.preConditions = preConditions;
    }

    public void setPostConditions(List<BmmAssertion> postConditions) {
        this.postConditions = postConditions;
    }

    public void addPreCondition(BmmAssertion preCondition) {
        this.preConditions.add(preCondition);
    }

    public void addPostCondition(BmmAssertion postCondition) {
        this.postConditions.add(postCondition);
    }

}
