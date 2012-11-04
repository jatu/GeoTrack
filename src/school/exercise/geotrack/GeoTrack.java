package school.exercise.geotrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GeoTrack extends Application implements SimpleLocationRegisterator, SimpleLocationListener, SingletonManager, LocationListener {

	
	
	ArrayList<SimpleLocationListener> locationListeners;
	Map<Class<?>, Object> singletones;
	
	public GeoTrack() {
		locationListeners = new ArrayList<SimpleLocationListener>();
		singletones = new HashMap<Class<?>, Object>();
		//initLocationManager();
		
	}

	public Object getSingleton(Class<?> cls) {
		return singletones.get(cls);		
	}	
	
	public void registerSingleton(Object activity, Class<?> cls) throws Exception {
		if (singletones.containsKey(cls)) {			
			throw new Exception("Cannot create more than one sigleton object per type.");
		}
		else
			singletones.put(cls, activity);
	}
	
	public void registerSimpleLocationListener(SimpleLocationListener listener)
	{
		locationListeners.add(listener);
		initLocationManager(); //Purkka
	}
	
	public void onLocationChanged(Location location)
	{
		this.location = location;
		for (SimpleLocationListener locationListener : locationListeners)
			locationListener.onLocationChanged(location);
	}
	
		// flag for GPS status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;
    // flag for GPS status
    boolean canGetLocation = false;
 
    Location location; // location

 // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    // Declaring a Location Manager
    protected LocationManager locationManager;
    
    public Location getLocation()
    {
    	return location;    
    }
    
    private void initLocationManager() {
        try {
            locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            
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

                if (location != null)
                	this.canGetLocation = true;
                else
                	this.canGetLocation = false;
                
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    	
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
