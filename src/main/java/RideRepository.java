import java.lang.reflect.Array;
import java.util.*;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRides = new HashMap<>();

    public void addRides(String userId, Ride[] ride1) {
        ArrayList<Ride> rides = this.userRides.get(userId);
        if (rides == null){
            this.userRides.put(userId, new ArrayList<>((Arrays.asList(ride1))));
        } else {

        }
    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}