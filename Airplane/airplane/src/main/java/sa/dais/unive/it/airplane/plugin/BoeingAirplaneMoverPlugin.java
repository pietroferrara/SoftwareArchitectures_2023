package sa.dais.unive.it.airplane.plugin;

import sa.dais.unive.it.airplane.Airplane;
import sa.dais.unive.it.airplane.Airport;

public class BoeingAirplaneMoverPlugin implements AirplaneMoverPlugin {
    @Override
    public void approach(Airplane airplane, Airport airport) {
        if(airplane.getLatitude()!=airport.getLatitude()) {
            if(airplane.getLatitude()> airport.getLatitude())
                airplane.setLatitude(Math.max(airport.getLatitude(), airplane.getLatitude()-5));
            else airplane.setLatitude(Math.min(airport.getLatitude(), airplane.getLatitude()+5));
        }
        if(airplane.getLongitude()!=airport.getLongitude()) {
            if(airplane.getLongitude()> airport.getLongitude())
                airplane.setLongitude(Math.max(airport.getLongitude(), airplane.getLongitude()-5));
            else airplane.setLongitude(Math.min(airplane.getLongitude(), airplane.getLongitude()+5));
        }
    }

    @Override
    public boolean supports(Airplane airplane) {
        if(airplane.getName().equals("boeing"))
            return true;
        else return false;
    }
}
