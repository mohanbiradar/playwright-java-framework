package framework.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config.properties",
        "system:properties",
        "system:env"
})
public interface FrameworkConfig extends Config {

    @Key("browser")
    String browser();

    @Key("baseUrl")
    String baseUrl();

    @Key("headless")
    boolean headless();

    @Key("defaultTimeout")
    @DefaultValue("10000")
    int defaultTimeout();
}
