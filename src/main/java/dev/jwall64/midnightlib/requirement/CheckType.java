package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.Serializer;
import dev.jwall64.mdcfg.TypeReference;

public interface CheckType<T, C extends Check<T>> {

    TypeReference<C> type();

    Serializer<C> serializer();

}
