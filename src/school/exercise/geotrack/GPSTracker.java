package school.exercise.geotrack;
 
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
 
public class GPSTracker extends Service implements LocationListener  {
 
        private final Context mContext;
       
        // flag for GPS status
    boolean isGPSEnabled = false;
 
    // flag for network status
    boolean isNetworkEnabled = false;
 
    // flag for GPS status
    boolean canGetLocation = false;
 
    Location location; // location
    //double latitude; // latitude
    //double longitude; // longitude
   
 // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
 
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
 
    // Declaring a Location Manager
    protected LocationManager locationManager;
       
    public GPSTracker(Context context) {
        this.mContext = context;
        initLocationManager();
    }
   
    public Location getLocation()
    {
        return location;    
    }
   
   
    private void initLocationManager() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);
 
            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
 
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
 
 
               
 
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("GPS Enabled", "GPS Enabled");
   
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);                                  
                }
               
                // First get location from Network Provider
                if (location == null && isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                   
                }
 
                if (location != null)
                        this.canGetLocation = true;
                else
                        this.canGetLocation = false;
               
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
       
    }
   
        public void onLocationChanged(Location location) {
                this.location = location;
        }
 
        public void onProviderDisabled(String provider) {
                initLocationManager();         
        }
 
        public void onProviderEnabled(String provider) {
                initLocationManager();
        }
 
        public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public IBinder onBind(Intent intent) {
                // TODO Auto-generated method stub
                return null;
        }
 
}