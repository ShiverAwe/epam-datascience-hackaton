import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Amenity {
    public static List<Amenity> getAmenities(String filename) {
        ArrayList<Amenity> amenities = new ArrayList<>();
        InputStream is = Main.class.getResourceAsStream(filename);
        Csv.Reader reader = new Csv.Reader(new InputStreamReader(is))
                .delimiter(',').ignoreComments(true).ignoreEmptyLines(true);

        reader.readLine(); //Skip headers
        while (true) {
            try {
                String line = reader.readLine().toString();
                Amenity am = Amenity.fromLine(line);
                if (am != null)
                    amenities.add(am);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                break;
            }
        }
        return amenities;
    }

    public static Amenity fromLine(String line) {
        try {
            line = line
                    .replace("[", "")
                    .replace("]", "");
            String regex = "^([^,]+),([^,]+),([^,]+),([^,]+)$";
            //System.out.println(Pattern.matches(regex, line));
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            if (m.matches()) {
                Amenity am = new Amenity();
                am.amenity = m.group(1);
                am.name = m.group(2);
                am.lat = Double.parseDouble(m.group(3));
                am.lon = Double.parseDouble(m.group(4));
                return am;
            } else return null;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    String amenity = "";
    String name = "";
    Double lat = 0.0;
    Double lon = 0.0;

    @Override
    public String toString() {
        return "Amenity{" +
                "amenity='" + amenity + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
