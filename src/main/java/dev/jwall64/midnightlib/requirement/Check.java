package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.Serializer;
import dev.jwall64.mdcfg.registry.Registry;

public interface Check<T> {

    boolean check(T data);

    CheckType<T, ?> type();

    static <T> Serializer<Check<T>> serializer(Registry<?, CheckType<T, ?>> registry) {
        return registry.byIdSerializer().fieldOf("type").dispatch(CheckType::serializer, Check::type);
    }

}
