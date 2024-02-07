import java.util.HashMap;
import java.util.Map;
class BengaluruLocation {
    private static final Map<String, BengaluruLocation> bengaluruLocations = new HashMap<>();
static {
    bengaluruLocations.put("Majestic", new BengaluruLocation("Majestic", 12.9779, 77.5713));
    bengaluruLocations.put("Girinagar", new BengaluruLocation("Girinagar", 12.9417, 77.5466));
    bengaluruLocations.put("KR Market", new BengaluruLocation("KR Market", 12.9642, 77.5835));
    bengaluruLocations.put("Yelahanka", new BengaluruLocation("Yelahanka", 13.1007, 77.5963));
    bengaluruLocations.put("Whitefield", new BengaluruLocation("Whitefield", 12.9698, 77.7499));
    bengaluruLocations.put("Electronic city", new BengaluruLocation("Electronic City", 12.8447, 77.6609));
    bengaluruLocations.put("Koramangala", new BengaluruLocation("Koramangala", 12.9352, 77.6245));
    bengaluruLocations.put("Indiranagar", new BengaluruLocation("Indiranagar", 12.9784, 77.6408));
    bengaluruLocations.put("Jayanagar", new BengaluruLocation("Jayanagar", 12.9293, 77.5822));
    bengaluruLocations.put("BTM Layout", new BengaluruLocation("BTM Layout", 12.9156, 77.6101));
    bengaluruLocations.put("Banashankari", new BengaluruLocation("Banashankari", 12.9250, 77.5468));
    bengaluruLocations.put("Malleshwaram", new BengaluruLocation("Malleshwaram", 13.0077, 77.5706));
    bengaluruLocations.put("Shivajinagar", new BengaluruLocation("Shivajinagar", 12.9857, 77.6057));
    bengaluruLocations.put("Basavanagudi", new BengaluruLocation("Basavanagudi", 12.9424, 77.5713));
    bengaluruLocations.put("Hebbal", new BengaluruLocation("Hebbal", 13.0358, 77.5970));
    bengaluruLocations.put("Rajajinagar", new BengaluruLocation("Rajajinagar", 12.9880, 77.5548));
    bengaluruLocations.put("Banaswadi", new BengaluruLocation("Banaswadi", 13.0142, 77.6519));
    bengaluruLocations.put("Domlur", new BengaluruLocation("Domlur", 12.9625, 77.6382));
    bengaluruLocations.put("MG Road", new BengaluruLocation("MG Road", 12.9750, 77.6066));
    bengaluruLocations.put("Ulsoor", new BengaluruLocation("Ulsoor", 12.9770, 77.6249));
    bengaluruLocations.put("Marathahalli", new BengaluruLocation("Marathahalli", 12.9594, 77.6971));
    bengaluruLocations.put("Bellandur", new BengaluruLocation("Bellandur", 12.9279, 77.6715));
    bengaluruLocations.put("HSR Layout", new BengaluruLocation("HSR Layout", 12.9116, 77.6389));
    bengaluruLocations.put("bannerghattaRoad", new BengaluruLocation("Bannerghatta Road", 12.8000, 77.5996));
    bengaluruLocations.put("malleshpalya", new BengaluruLocation("Malleshpalya", 12.9676, 77.6958));
    bengaluruLocations.put("vijayanagar", new BengaluruLocation("Vijayanagar", 12.9718, 77.5452));
    bengaluruLocations.put("RR Nagar", new BengaluruLocation("RR Nagar", 12.9274, 77.5285));
    bengaluruLocations.put("JP Nagar", new BengaluruLocation("JP Nagar", 12.9063, 77.5855));
    bengaluruLocations.put("Kumaraswamy Layout", new BengaluruLocation("Kumaraswamy Layout", 12.9098, 77.5568));
}
    private final String name;
    private final double latitude;
    private final double longitude;

    private BengaluruLocation(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

public static boolean isValidLocation(String locationName) {
    for (String validLocation : bengaluruLocations.keySet()) {
        if (validLocation.equalsIgnoreCase(locationName.toUpperCase())) {
            return true;
        }
    }
    return false;
}

public static double getDistance(String startLocation, String endLocation) {
        BengaluruLocation start = bengaluruLocations.get(startLocation.toLowerCase());
        BengaluruLocation end = bengaluruLocations.get(endLocation.toLowerCase());
         if (start!= null && end!= null) {
            return haversine(start.latitude, start.longitude, end.latitude, end.longitude);
        } else {
            return -1;
        }
    }
    
    private static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371;// Radius of Earth in kms
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);
        double a = Math.sin(dLat / 2)*Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
                *Math.cos(Math.toRadians(lat2))*Math.sin(dLon / 2)*Math.sin(dLon / 2);
        double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R*c;
    }
}
