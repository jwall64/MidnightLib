package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.Serializer;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class BooleanRequirement<T> implements Predicate<T> {

    public static <T> Serializer<BooleanRequirement<T>> serializer(Function<T, Boolean> getter) {
        return Serializer.BOOLEAN.map(ser -> ser.value, val -> new BooleanRequirement<>(getter, val));
    }

    private final Function<T, Boolean> getter;
    private final Boolean value;

    public BooleanRequirement(Function<T, Boolean> getter, Boolean value) {
        this.getter = getter;
        this.value = value;
    }

    @Override
    public boolean test(T data) {
        return Objects.equals(getter.apply(data), value);
    }
}
