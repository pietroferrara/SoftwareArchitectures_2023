package sa.dais.unive.it.airplane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAirplaneController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @GetMapping("/rest/airplanes")
    public Iterable<Airplane> showAllAirplanes() {
        return airplaneRepository.findAll();
    }

}
