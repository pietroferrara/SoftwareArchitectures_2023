package sa.dais.unive.it.airplane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class MainController {

    @GetMapping("/airplanes")
    public String showAllAirplanes(Model model) {
        model.addAttribute("airplanes", getAllAirplanes());
        return "airplanes";
    }

    private Collection<Airplane> getAllAirplanes() {
        Collection<Airplane> result = new ArrayList<>();
        Airplane a1 = new Airplane();
        a1.setName("airbus");
        a1.setAltitude(0);
        a1.setLatitude(0);
        a1.setLongitude(0);
        result.add(a1);
        return result;
    }
}
