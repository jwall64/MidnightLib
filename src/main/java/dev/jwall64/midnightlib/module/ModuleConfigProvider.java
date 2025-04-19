package dev.jwall64.midnightlib.module;

import org.jetbrains.annotations.Nullable;
import dev.jwall64.mdcfg.ConfigObject;
import dev.jwall64.mdcfg.ConfigSection;
import dev.jwall64.mdcfg.registry.Identifier;

public interface ModuleConfigProvider {

    @Nullable
    ConfigSection getConfig(Identifier moduleId);

    void setConfig(Identifier moduleId, ConfigSection config);

    class Default implements ModuleConfigProvider {

        private final ConfigSection section;

        public Default(ConfigSection section) {
            this.section = section;
        }

        public ConfigSection getConfig() {
            return section;
        }

        @Nullable
        @Override
        public ConfigSection getConfig(Identifier moduleId) {
            ConfigObject obj = section.get(moduleId.toString());
            if(obj == null || !obj.isSection()) return null;
            return obj.asSection();
        }

        @Override
        public void setConfig(Identifier moduleId, ConfigSection config) {
            section.set(moduleId.toString(), config);
        }
    }

}
