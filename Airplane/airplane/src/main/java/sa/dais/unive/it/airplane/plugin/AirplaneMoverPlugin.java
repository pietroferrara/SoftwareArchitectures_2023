package sa.dais.unive.it.airplane.plugin;

import org.springframework.plugin.core.Plugin;
import sa.dais.unive.it.airplane.Airplane;
import sa.dais.unive.it.airplane.Airport;

public interface AirplaneMoverPlugin extends Plugin<Airplane> {
    public void approach(Airplane airplane, Airport airport);
}
