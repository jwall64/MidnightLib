package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.SerializeContext;
import dev.jwall64.mdcfg.serializer.SerializeResult;

public interface RequirementType<T> {

    <C> SerializeResult<Requirement<T>> create(SerializeContext<C> ctx, C value, boolean invert);

}
