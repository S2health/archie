package org.s2.serialisation.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.nedap.archie.json.DurationDeserializer;
import com.nedap.archie.json.DurationSerializer;
import org.threeten.extra.PeriodDuration;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.TemporalAmount;

/**
 * A Jackson module that overrides the standard JavaTimeModule serializers and deserializers for ISO 8601 Durations.
 * It supports the negative format as used in OpenEHR, and it can parse PeriodDurations if required
 */
public class S2DurationModule extends SimpleModule {

    public S2DurationModule() {
        super("s2-duration-module");

        addDeserializer(TemporalAmount.class, new DurationDeserializer());

        addSerializer(Duration.class, new DurationSerializer());
        addSerializer(Period.class, new DurationSerializer());
        addSerializer(PeriodDuration.class, new DurationSerializer());
    }
}
