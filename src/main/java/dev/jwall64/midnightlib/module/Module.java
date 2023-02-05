package dev.jwall64.midnightlib.module;

import dev.jwall64.mdcfg.ConfigSection;

public interface Module<T> {

    boolean initialize(ConfigSection section, T data);

    default void disable() { }

}
