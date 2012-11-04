package school.exercise.geotrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

public class GeoTrack extends Application implements SimpleLocationListener {

	public List<HashMap<String, Object>> locationMaps;
	
	public GeoTrack() {
	
		locationMaps = new ArrayList<HashMap<String, Object>>();
		GPSTracker gpsTracker = new GPSTracker();
		gpsTracker.registerSimpleLocationListener(this);
		
		try {
			SingletonManager.registerSingleton(this, GeoTrack.class);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void onCreate() {
		
		try {
			SingletonManager.registerSingleton(this, GeoTrack.class);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void onLocationChanged(Location location) {
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("rowid", new MyLocation(location));
        map.put("col_1", Calendar.getInstance().getTime().toLocaleString());
        locationMaps.add(map);
	}
	
	
	
	
	
	
}
