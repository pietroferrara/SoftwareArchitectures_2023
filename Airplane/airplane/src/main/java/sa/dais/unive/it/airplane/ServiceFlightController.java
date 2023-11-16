package sa.dais.unive.it.airplane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceFlightController {
    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AirportRepository airportRepository;


    @GetMapping("rest/depart/{airplane}/{airport}")
    public String depart(@PathVariable String airplane, @PathVariable String airport) throws Exception {
        System.out.println("Departed!");
        new FlightRunner(
                airplaneRepository.findById(airplane).get(),
                airportRepository.findById(airport).get(), airplaneRepository).start();
        return "departed";
    }
}
