package dev.jwall64.midnightlib.module;

import dev.jwall64.midnightlib.config.ConfigSection;

public interface Module<T> {

    boolean initialize(ConfigSection section, T data);

    @Deprecated
    default void reload(ConfigSection config) { }

    default void disable() { }

}
