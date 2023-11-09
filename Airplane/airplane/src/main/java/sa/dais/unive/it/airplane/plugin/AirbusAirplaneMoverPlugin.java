package sa.dais.unive.it.airplane.plugin;

import sa.dais.unive.it.airplane.Airplane;
import sa.dais.unive.it.airplane.Airport;

public class AirbusAirplaneMoverPlugin implements AirplaneMoverPlugin {
    @Override
    public void approach(Airplane airplane, Airport airport) {
        if(airplane.getLatitude()!=airport.getLatitude()) {
            if(airplane.getLatitude()> airport.getLatitude())
                airplane.setLatitude(airplane.getLatitude()-1);
            else airplane.setLatitude(airplane.getLatitude()+1);
        }
        if(airplane.getLongitude()!=airport.getLongitude()) {
            if(airplane.getLongitude()> airport.getLongitude())
                airplane.setLongitude(airplane.getLongitude()-1);
            else airplane.setLongitude(airplane.getLongitude()+1);
        }
    }

    @Override
    public boolean supports(Airplane airplane) {
        if(airplane.getName().equals("airbus"))
            return true;
        else return false;
    }
}
