package dev.jwall64.midnightlib.registry;


import dev.jwall64.mdcfg.serializer.InlineSerializer;

public class StringRegistry<T> extends RegistryBase<String ,T> {

    public StringRegistry() {
        super(true);
    }

    public StringRegistry(boolean allowDuplicateValues) {
        super(allowDuplicateValues);
    }

    @Override
    public InlineSerializer<T> nameSerializer() {
        return InlineSerializer.of(this::getId, this::get);
    }

}
