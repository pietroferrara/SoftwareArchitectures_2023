package sa.dais.unive.it.airplane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class MainController {

    @Autowired
    private AirplaneRepository airplaneRepository;


    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/airplanes")
    public String showAllAirplanes(Model model) {
        model.addAttribute("airplanes", getAllAirplanes());
        return "airplanes";
    }
    @GetMapping("/airports")
    public String showAllAirports(Model model) {
        model.addAttribute("airports", getAllAirports());
        return "airports";
    }
    private Iterable<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
        /*
        Collection<Airplane> result = new ArrayList<>();
        Airplane a1 = new Airplane();
        a1.setName("airbus");
        a1.setAltitude(0);
        a1.setLatitude(0);
        a1.setLongitude(0);
        result.add(a1);
        return result;*/
    }
    private Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @RequestMapping("depart/{airplane}/{airport}")
    public String depart(@PathVariable String airplane, @PathVariable String airport) throws Exception {
        System.out.println("Departed!");
        new FlightRunner(airplaneRepository.findById(airplane).get(), airportRepository.findById(airport).get(), airplaneRepository).start();
        return "departed";
    }
}
