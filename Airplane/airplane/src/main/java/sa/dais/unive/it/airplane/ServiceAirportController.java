package sa.dais.unive.it.airplane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAirportController {
    @Autowired
    private AirportRepository airportRepository;


    @GetMapping("/rest/airports")
    public Iterable<Airport> showAllAirports() {
        return airportRepository.findAll();
    }

}
