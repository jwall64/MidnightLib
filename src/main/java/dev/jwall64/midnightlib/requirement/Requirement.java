package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.serializer.ObjectSerializer;
import dev.jwall64.mdcfg.serializer.SerializeContext;
import dev.jwall64.mdcfg.serializer.SerializeResult;
import dev.jwall64.mdcfg.serializer.Serializer;
import dev.jwall64.midnightlib.registry.RegistryBase;

import java.util.Objects;

public class Requirement<T> {

    private final RequirementType<T> type;
    private final String value;

    public Requirement(RequirementType<T> type, String value) {
        this.type = type;
        this.value = value;
    }

    public boolean check(T data) {
        return type.check(data, this, value);
    }

    public RequirementType<T> getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public static <T> Serializer<Requirement<T>> simpleSerializer(RegistryBase<?, RequirementType<T>> registry) {
        return ObjectSerializer.create(
                registry.nameSerializer().entry("type", Requirement<T>::getType),
                Serializer.STRING.entry("value", Requirement<T>::getValue),
                Requirement<T>::new
        );
    }

    public static <T> Serializer<Requirement<T>> serializer(RegistryBase<?, RequirementType<T>> registry) {

        return new Serializer<>() {
            @Override
            public <O> SerializeResult<O> serialize(SerializeContext<O> context, Requirement<T> value) {

                if(value instanceof MultiRequirement) {
                    return MultiRequirement.multiSerializer(registry).serialize(context, (MultiRequirement<T>) value);
                }

                return simpleSerializer(registry).serialize(context, value);
            }

            @Override
            public <O> SerializeResult<Requirement<T>> deserialize(SerializeContext<O> context, O value) {

                O values = context.get("values", value);
                if(values != null && context.isList(values)) {
                    return MultiRequirement.multiSerializer(registry).deserialize(context, value).flatMap(req -> req);
                }

                return simpleSerializer(registry).deserialize(context, value);
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requirement<?> that = (Requirement<?>) o;
        return Objects.equals(type, that.type) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
