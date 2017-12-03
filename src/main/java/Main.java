

public class Main {
    public static void main(String[] args) {
        //for (Criminal cr : Criminal.getCrilinals()) {
        // System.out.println(cr);
        //}
        GridMap map = new GridMap();
        for (Amenity am : Amenity.getAmenities("data/amenities.csv")) {
            //System.out.println(am);
            map.push(am);
        }
        for (Amenity am : Amenity.getAmenities("data/shops.csv")) {
            System.out.println(am);
            map.push(am);
        }
        map.toCSV();
        //System.out.println(map);

    }
}
