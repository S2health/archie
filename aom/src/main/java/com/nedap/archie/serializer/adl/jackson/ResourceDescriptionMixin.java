package com.nedap.archie.serializer.adl.jackson;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nedap.archie.base.terminology.TerminologyCode;
import org.openehr.odin.jackson.serializers.TermCodeAsStringConverter;

@Deprecated
public class ResourceDescriptionMixin {

    @JsonSerialize(converter = TermCodeAsStringConverter.class)
    private TerminologyCode lifecycleState;
}
