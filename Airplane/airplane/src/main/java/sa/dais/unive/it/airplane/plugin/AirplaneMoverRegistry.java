package sa.dais.unive.it.airplane.plugin;

import org.springframework.plugin.core.PluginRegistry;
import sa.dais.unive.it.airplane.Airplane;

public class AirplaneMoverRegistry {
    public static PluginRegistry<AirplaneMoverPlugin, Airplane> registry =
            PluginRegistry.of(
                    new AirbusAirplaneMoverPlugin(),
                    new BoeingAirplaneMoverPlugin());
}
