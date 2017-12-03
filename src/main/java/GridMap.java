import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class GridMap {
    double max_lat = 60.092838;
    double min_lat = 59.809917;
    double max_lon = 30.583664;
    double min_lon = 30.064014;

    int count_lat = 60;
    int count_lon = 60;

    double size_lat = (max_lat - min_lat) / count_lat;
    double size_lon = (max_lon - min_lon) / count_lon;


    ArrayList<ArrayList<Grid>> map = new ArrayList<>();

    {
        for (int i = 0; i < count_lat; i++) {
            ArrayList<Grid> row = new ArrayList<>();
            map.add(row);
            for (int j = 0; j < count_lon; j++) {
                row.add(new Grid(min_lat + size_lat * i, min_lon + size_lon * j));
            }
        }
    }

    int getBucketLat(double lat) {
        int bucket = (int) ((lat - min_lat) / size_lat);
        //if ((bucket<0) || (bucket>count_lat))
        //System.out.println("getBucketLat "+bucket);
        //System.out.println("Lat " + lat + " Min " + min_lat + " Max " + max_lat);
        return bucket;
    }

    int getBucketLon(double lon) {
        int bucket = (int) ((lon - min_lon) / size_lon);
        //if ((bucket<0) || (bucket>count_lon)) //System.out.println("getBucketLon "+bucket);
        //System.out.println("Lon " + lon + " Min " + min_lon + " Max " + max_lon);
        return bucket;
    }

    void push(Amenity am) {
        int latbucket = getBucketLat(am.lat);
        int lonbucket = getBucketLon(am.lon);
        //System.out.println("JJJ = "+am);
        try {
            map.get(latbucket).get(lonbucket).push(am.amenity);
        } catch (Exception e) {
            //System.out.println("CANNOT PUSH : Lat " + latbucket + " Lon " + lonbucket);
            return;
        }
        //System.out.println("Pushed " + am);
    }

    @Override
    public String toString() {
        return "GridMap{" +
                "map=" + map +
                '}';
    }

    ArrayList<String> keys = new ArrayList<>();

    {
        Collections.addAll(keys,
                "bench", "waste_basket", "waste_disposal", "cafe", "atm", "pharmacy", "restaurant", "fast_food",
                "bank", "post_box", "parking", "bar", "bicycle_parking", "dentist", "toilets", "drinking_water", "doctors",
                "car_wash", "recycling", "telephone", "pub", "kindergarten", "police", "clinic", "parking_entrance",
                "vending_machine", "library", "post_office", "payment_terminal", "fountain", "school", "parking_space",
                "veterinary", "fuel", "bicycle_rental", "clock", "shelter", "place_of_worship", "driving_school", "cinema",
                "theatre", "nightclub", "arts_centre", "training", "community_centre", "social_facility", "hospital",
                "university", "language_school", "fire_station", "townhall", "embassy", "vehicle_ramp", "courthouse",
                "college", "water_point", "post_locker", "hunting_stand", "marketplace", "bus_station", "bureau_de_change",
                "ferry_terminal", "taxi", "sauna", "studio", "swimming_pool", "compressed_air", "public_building", "club",
                "stripclub", "grave_yard", "rescue_station", "childcare", "music_school", "car_rental", "coworking_space",
                "office", "letter_box", "smoking_area", "food_court", "social_centre", "biergarten", "gym", "healthcare",
                "feeding_place", "hookah_lounge", "vacuum_cleaner", "pet_grooming", "charging_station", "bbq", "gambling",
                "internet_cafe", "shower", "dormitory", "vehicle_inspection", "boat_rental", "security_booth", "register_office",
                "yes", "spa", "sport_school", "shop", "advertisement", "business_centre", "customs", "game_arcade", "ice_cream",
                "casino", "beauty salon", "beauty_salon", "dancing_school", "dance", "baby_hatch", "wifi", "credit", "beauty",
                "collection office", "trolley_stop", "clockshop", "clinic", "tailor", "cash", "waste_transfer_station",
                "bicycle_repair_station", "warehouse", "donation_box", "public_bath", "public_service", "sexshop",
                "printing_establishment", "preschool", "photo_booth", "optician", "sailor_school", "offices", "motorcycle_parking",
                "mortuary", "money_transfer", "exchange", "money_lender", "mending", "ski_rental", "amusement_arcade",
                "soccer_club", "hospice", "flag", "exhibition_center", "equipment_rental"
        );

        Collections.addAll(keys,
                "computer", "photo", "supermarket", "clothes", "yes", "car_repair", "bakery", "beauty", "paint",
                "alcohol", "convenience", "shoes", "doityourself", "books", "furniture", "florist", "hairdresser",
                "butcher", "car_parts", "car", "organic", "bicycle", "outdoor", "electronics", "toys", "gift", "gas",
                "hifi", "confectionery", "optician", "kiosk", "sports", "mobile_phone", "chemist", "newsagent",
                "department_store", "tyres", "baby_goods", "hardware", "dry_cleaning", "jewelry", "curtain", "fabric",
                "travel_agency", "door", "boutique", "stationery", "mobile_telephony", "sewing", "pet", "erotic", "pastry",
                "cosmetics", "ticket", "bathroom_furnishing", "kids", "laundry", "general", "greengrocer", "garden_centre",
                "second_hand", "tailor", "boat", "bag", "billiard", "shoe_repair", "beverages", "antiques", "pawnbroker",
                "hunting", "tea", "phone_repair", "video", "clothes_repair", "copyshop", "tools", "kitchen", "watch",
                "tobacco", "frame", "conditioners", "mall", "unknown", "bed", "seafood", "funeral_directors", "tattoo",
                "leather", "model", "wedding", "atelier", "skateboard", "repair", "hat", "orthopedic", "binding", "art",
                "glaziery", "variety_store", "electrical", "fashion", "estate_agent", "food", "vacant", "photo_studio",
                "orthopedics", "technics", "houseware", "musical_instrument", "sewing_machines", "bee_products", "money_lender",
                "motorcycle", "charity", "furnace", "deli", "music", "bookmaker", "massage", "brewing_supplies", "guns",
                "medical_supply", "watches", "underwear", "lighting", "orthopaedic", "jeweller_tools", "wine", "no", "tableware",
                "consignment", "window", "coffee", "adult", "electrotools", "orthopaedics", "sanitary engineering", "dairy",
                "hearing_aids", "outpost", "nutritional_supplements", "coins", "dressmaker", "interior_decoration",
                "boat motor", "tiles", "olive_oil", "radiotechnics", "sports_food", "electronics_repair", "fireworks",
                "umbrella", "boiler", "tile", "window_blind", "heater", "extinguisher", "wallpaper", "windows",
                "vacuum_cleaner", "trade", "e-cigarette", "audio", "fire_protection", "fabrics", "farm", "tyres_repair",
                "tabacco", "insurance", "scuba_diving", "craft", "cooking", "carpet", "doors", "fixme", "security", "military",
                "fishing", "games", "fireplace", "sports_nutrition", "milk", "vegetables", "pyrotechnics", "weapons", "clock",
                "shoes_repair", "miscelaneous", "zoo", "sushi", "market", "honey", "plastic", "video_games", "dive",
                "hairdresser_supply", "electrician", "anime", "mobile_repair", "spa", "oil", "knife", "religion", "monumen",
                "fixed_price", "spice", "camera", "flooring", "dog_beauty", "???", "plants", "gun", "perfumery", "hvac",
                "glazery", "nutrition_supplements", "storage_rental", "numismatics", "packaging", "каль", "plumbing", "Продукты",
                "bodybuilding_supplement", "party", "beekeeper", "motorcycle_repair", "collector", "ceramics", "herbalist",
                "locksmith", "spices", "equestrian", "wigs", "energy", "firewood", "razor", "baker_supply", "snowmobile",
                "appliance"
        );
    }

    public void toCSV() {
        Csv.Writer writer = new Csv.Writer("hello.csv").delimiter(',');

        // Headers
        for (String key : keys) {
            writer.value(key);
        }
        writer.newLine();

        for (ArrayList<Grid> row : map) {
            for (Grid grid : row) {
                writer.value("" + grid.lat).value("" + grid.lon);
                for (String key : keys) {
                    writer.value(grid.values.getOrDefault(key, 0).toString());
                }
                writer.newLine();
            }
        }
        writer.flush();
    }
}
