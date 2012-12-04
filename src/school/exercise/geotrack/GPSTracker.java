package school.exercise.geotrack;

import java.util.ArrayList;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/*
 * GPSTracker, hakee sijainnin GPS:stä annetuilla ehdoilla
 */

public class GPSTracker implements LocationListener, SimpleLocationListener {	
	
	// flag for GPS status
	private boolean isGPSEnabled = false;
    // flag for network status
	private boolean isNetworkEnabled = false;
	 // The minimum distance to change Updates in meters
    private static long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    // Declaring a Location Manager
    private boolean inited = false;
    
    protected LocationManager locationManager;
	
    private ArrayList<SimpleLocationListener> locationListeners;
    
    Location location; // location
	
	public GPSTracker() {
		locationListeners = new ArrayList<SimpleLocationListener>();
		try {
			SingletonManager.registerSingleton(this, GPSTracker.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMinDistance(int i) {
		MIN_DISTANCE_CHANGE_FOR_UPDATES = i;
		initLocationManager();
	}
	
	public void setMinTime(int i) {
		MIN_TIME_BW_UPDATES = 1000 * i;
		initLocationManager();
	}
	
	public long getMinDistance() {
		return MIN_DISTANCE_CHANGE_FOR_UPDATES;
	}
	
	public long getMinTime() {
		return MIN_TIME_BW_UPDATES;
	}
	
	public void registerSimpleLocationListener(SimpleLocationListener listener)
	{
		locationListeners.add(listener);
	}

	public boolean unRegisterSimpleLocationListener(SimpleLocationListener listener)
	{
		return locationListeners.remove(listener);
	}
	
	public void onLocationChanged(Location location)
	{
		try {
			((SplashActivity)SingletonManager.getSingleton(SplashActivity.class)).finish();
		} catch (Exception e) { }		
		
		this.location = location;
		for (SimpleLocationListener locationListener : locationListeners)
			locationListener.onLocationChanged(location);
	}

    public Location getLocation()
    {
    	return location;    
    }
    
    public void initLocationManager() {
        try {        	
        	Context applicationContext = (Context)SingletonManager.getSingleton(GeoTrack.class);
            locationManager = (LocationManager) applicationContext.getSystemService(Context.LOCATION_SERVICE);
            
            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
 
            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                

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
                
                inited = true;
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
    
    public boolean isInited() {
    	return inited;
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

	
}
