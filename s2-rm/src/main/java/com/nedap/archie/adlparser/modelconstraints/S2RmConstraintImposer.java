package com.nedap.archie.adlparser.modelconstraints;

import com.nedap.archie.adlparser.modelconstraints.ReflectionConstraintImposer;
import org.s2.rminfo.S2RmInfoLookup;

/**
 * Constraints imposer for the Archie reference model implementation.
 *
 * Created by pieter.bos on 04/11/15.
 */
public class S2RmConstraintImposer extends ReflectionConstraintImposer {

    public S2RmConstraintImposer() {
        super(S2RmInfoLookup.getInstance());
    }

}
