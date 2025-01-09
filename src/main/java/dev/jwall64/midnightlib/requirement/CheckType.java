package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.Serializer;

public interface CheckType<T, C extends Check<T>> {

    Serializer<C> serializer();

}
