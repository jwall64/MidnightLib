import org.junit.Test;
import dev.jwall64.midnightlib.config.ConfigSection;
import dev.jwall64.midnightlib.config.serialization.json.JsonConfigProvider;

import java.io.File;

public class TestJson {

    @Test
    public void testJson() {

        ConfigSection sec = new ConfigSection().with("test", "&6Test");
        JsonConfigProvider.INSTANCE.saveToFile(sec, new File("test.json"));

    }

}
