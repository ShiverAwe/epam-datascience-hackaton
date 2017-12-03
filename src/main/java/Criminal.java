import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Criminal {
    public static List<Criminal> getCrilinals() {

        InputStream is = Main.class.getResourceAsStream("data/news_with_location.csv");
        Csv.Reader reader = new Csv.Reader(new InputStreamReader(is))
                .delimiter(',').ignoreComments(true).ignoreEmptyLines(true);

        reader.readLine(); //Skip headers
        while (true) { try {
            String line = reader.readLine().toString();
            Criminal cr = Criminal.fromLine(line);
            System.out.println(cr);
        } catch (Exception e){
            System.err.println(e.getMessage());
            break;
        }}
        return Collections.emptyList();
    }

    public static Criminal fromLine(String line) {
        try {
            line = line
                    .replace("[", "")
                    .replace("]", "");
            String regex = "^([0-9]+),(.+),([^,]+),([^,]+),([^,]+),([^,]+)$";
            System.out.println(Pattern.matches(regex, line));
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            if (m.matches()) {
                Criminal cr = new Criminal();
                cr.id = Integer.parseInt(m.group(1));
                cr.title = m.group(2);
                cr.time = m.group(3);
                cr.lat = Double.parseDouble(m.group(4));
                cr.lng = Double.parseDouble(m.group(5));
                cr.decreepart = m.group(6);
                return cr;
            } else return new Criminal();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new Criminal();
        }
    }

    int id = 0;
    String title = "";
    String time = "";
    Double lat = 0.0;
    Double lng = 0.0;
    String decreepart = "";


    @Override
    public String toString() {
        return "Criminal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", decreepart='" + decreepart + '\'' +
                '}';
    }
}
