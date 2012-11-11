package school.exercise.geotrack;

import android.app.Application;

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
