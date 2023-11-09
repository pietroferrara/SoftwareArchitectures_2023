package sa.dais.unive.it.airplane;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import sa.dais.unive.it.airplane.plugin.AirplaneMoverRegistry;
import sa.dais.unive.it.airplane.plugin.AirplaneMoverPlugin;

public class FlightRunner {

    public Airplane airplane;
    public Airport airport;

    public AirplaneRepository airplaneRepository;

    private AirplaneMoverPlugin plugin;


    public FlightRunner(Airplane airplane, Airport airport, AirplaneRepository airplaneRepository) {
        this.airplane = airplane;
        this.airport = airport;
        this.airplaneRepository = airplaneRepository;
        this.plugin = AirplaneMoverRegistry.registry.getPluginFor(airplane).get();
    }

    private CamelContext ctx;

    public void start() throws Exception {
        ctx = new DefaultCamelContext();
        RouteBuilder route = new FlightRouteBuilder();
        ctx.addRoutes(route);
        ctx.start();
    }

    public void stop() {
        ctx.stop();
    }

    private class FlightRouteBuilder extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("timer:simple?period=1000")
            .process(this::progress);

        }

        private void progress(Exchange exchange) {
            plugin.approach(airplane, airport);
            airplaneRepository.save(airplane);
            if(samePosition(airplane, airport)) {
                System.out.println("Destination arrived!");
                ctx.stop();
            }
        }

        private boolean samePosition(Airplane airplane, Airport airport) {
            return airplane.getLatitude()==airport.getLatitude() &&
                    airplane.getLongitude()==airport.getLongitude();
        }
    }
}
