package sa.dais.unive.it.airplane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class UIController {

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
        String serviceAirplane = "http://localhost:8080/rest/airplanes";
        RestTemplate template = new RestTemplate();
        Airplane[] airplanes = template.getForObject(serviceAirplane, Airplane[].class);
        return Arrays.asList(airplanes);
    }
    private Iterable<Airport> getAllAirports() {
        String serviceAirport = "http://localhost:8080/rest/airports";
        RestTemplate template = new RestTemplate();
        Airport[] airplanes = template.getForObject(serviceAirport, Airport[].class);
        return Arrays.asList(airplanes);
    }

    @RequestMapping("depart/{airplane}/{airport}")
    public String depart(@PathVariable String airplane, @PathVariable String airport) throws Exception {
        String serviceAirport = "http://localhost:8080/rest/depart/"+airplane+"/"+airport;
        RestTemplate template = new RestTemplate();
        String message = template.getForObject(serviceAirport, String.class);
        return "departed";
    }
}
