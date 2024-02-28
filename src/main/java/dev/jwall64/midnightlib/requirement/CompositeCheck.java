package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.ObjectSerializer;
import dev.jwall64.mdcfg.serializer.SerializeContext;
import dev.jwall64.mdcfg.serializer.SerializeResult;
import dev.jwall64.mdcfg.serializer.Serializer;
import dev.jwall64.midnightlib.math.Range;

import java.util.Collection;
import java.util.List;

public class CompositeCheck<T, R extends Requirement<T>> implements Check<T> {

    protected final Serializer<R> generalSerializer;
    protected final List<R> requirements;
    protected final Range<Integer> range;

    public CompositeCheck(Range<Integer> range, Collection<R> requirements) {
        this.generalSerializer = null;
        this.range = range;
        this.requirements = List.copyOf(requirements);
    }


    public CompositeCheck(Serializer<R> generalSerializer, Range<Integer> range, Collection<R> requirements) {
        this.generalSerializer = generalSerializer;
        this.range = range;
        this.requirements = List.copyOf(requirements);
    }

    @Override
    public boolean check(T t) {
        return checkAll(range, requirements, t);
    }

    @Override
    public <O> SerializeResult<O> serialize(SerializeContext<O> context) {
        if(generalSerializer == null) {
            return SerializeResult.failure("This check is not serializable!");
        }
        return serializer(generalSerializer).serialize(context, this);
    }

    public List<R> getRequirements() {
        return requirements;
    }

    public Range<Integer> getRange() {
        return range;
    }

    public static <T, R extends Requirement<T>> CheckType<T> type(Serializer<R> ser) {
        return new CheckType<T>() {
            @Override
            public <O> SerializeResult<Check<T>> deserialize(SerializeContext<O> context, O value) {
                return serializer(ser).deserialize(context, value).flatMap(chk -> chk);
            }
        };
    }

    public static <T, R extends Requirement<T>> Serializer<CompositeCheck<T, R>> serializer(Serializer<R> serializer) {
        return ObjectSerializer.create(
                Range.INTEGER.<CompositeCheck<T,R>>entry("count", CompositeCheck::getRange).optional(),
                serializer.listOf().entry("values", CompositeCheck::getRequirements),
                (range, list) -> new CompositeCheck<>(serializer, range, list)
        );
    }
    public static <T, R extends Requirement<T>> boolean checkAll(Range<Integer> range, Collection<R> requirements, T data) {

        Range<Integer> effectiveRange = range instanceof Range.All ? Range.exactly(requirements.size()) : range;

        int checked = 0;
        for(R r : requirements) {
            if(r.check(data)) {
                checked++;
            }
        }

        return effectiveRange.isWithin(checked);
    }

}
