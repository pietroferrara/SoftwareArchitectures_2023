package sa.dais.unive.it.airplane;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="airplane")
public class Airplane {

    private String name;
    private int altitude, longitude, latitude;

    public Airplane() {
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "altitude")
    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }
    @Column(name = "longitude")
    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    @Column(name = "latitude")
    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return altitude == airplane.altitude && longitude == airplane.longitude && latitude == airplane.latitude && Objects.equals(name, airplane.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, altitude, longitude, latitude);
    }
}
