package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.SerializeContext;
import dev.jwall64.mdcfg.serializer.SerializeResult;

public interface CheckType<T> {

    <O> SerializeResult<Check<T>> deserialize(SerializeContext<O> context, O value);

}
