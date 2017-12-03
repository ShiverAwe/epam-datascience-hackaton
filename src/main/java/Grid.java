import java.util.HashMap;
import java.util.Map;

public class Grid {

    double lat;
    double lon;

    Map<String, Integer> values = new HashMap<>();

    void push(String name) {
        values.put( name, values.getOrDefault(name, 0) +1 );
    }

    public Grid(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", values=" + values +
                '}' + "\n";
    }
}
