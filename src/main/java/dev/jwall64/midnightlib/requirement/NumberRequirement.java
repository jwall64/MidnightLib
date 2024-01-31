package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.Serializer;
import dev.jwall64.midnightlib.math.Range;

import java.util.function.Function;
import java.util.function.Predicate;

public class NumberRequirement<T, N extends Comparable<N>> implements Predicate<T> {

    public static <T> Serializer<NumberRequirement<T, Integer>> forInt(Function<T, Integer> getter) {
        return serializer(getter, Range.INTEGER);
    }

    public static <T> Serializer<NumberRequirement<T, Long>> forLong(Function<T, Long> getter) {
        return serializer(getter, Range.LONG);
    }

    public static <T> Serializer<NumberRequirement<T, Double>> forDouble(Function<T, Double> getter) {
        return serializer(getter, Range.DOUBLE);
    }

    public static <T, N extends Comparable<N>> Serializer<NumberRequirement<T, N>> serializer(Function<T, N> getter, Serializer<Range<N>> serializer) {

        return serializer.map(NumberRequirement::getRange, range -> new NumberRequirement<>(getter, range));
    }

    private final Function<T, N> getter;
    private final Range<N> range;

    public NumberRequirement(Function<T, N> getter, Range<N> range) {
        this.getter = getter;
        this.range = range;
    }

    public Range<N> getRange() {
        return range;
    }

    @Override
    public boolean test(T t) {
        return range.isWithin(getter.apply(t));
    }

}
