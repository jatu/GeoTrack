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

public class GeoTrack extends Application {
	
	/*public GeoTrack() {
	
		locationMaps = new ArrayList<HashMap<String, Object>>();
		GPSTracker gpsTracker = new GPSTracker();
		gpsTracker.registerSimpleLocationListener(this);
		
		try {
			SingletonManager.registerSingleton(this, GeoTrack.class);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}*/

	@Override
	public void onCreate() {
		new GPSTracker();			
		new Locations(getResources().getDrawable(R.drawable.ic_launcher));
		
		try {
			SingletonManager.registerSingleton(this, GeoTrack.class);
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	
}
