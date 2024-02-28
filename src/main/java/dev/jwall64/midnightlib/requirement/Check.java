package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.SerializeContext;
import dev.jwall64.mdcfg.serializer.SerializeResult;

import java.util.function.Predicate;

public interface Check<T> {

    boolean check(T data);

    <O> SerializeResult<O> serialize(SerializeContext<O> context);

    static <T> Check<T> of(Predicate<T> predicate) {
        return new Check<T>() {
            @Override
            public boolean check(T data) {
                return predicate.test(data);
            }

            @Override
            public <O> SerializeResult<O> serialize(SerializeContext<O> context) {
                return SerializeResult.failure("This check cannot be serialized!");
            }
        };
    }

}
