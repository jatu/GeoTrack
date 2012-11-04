package school.exercise.geotrack;

import java.util.Timer;
import java.util.TimerTask;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashActivity extends Activity implements SimpleLocationListener {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        try {
			SingletonManager.registerSingleton(this, SplashActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        		
        ((GPSTracker)SingletonManager.getSingleton(GPSTracker.class)).registerSimpleLocationListener(this);
        ((GPSTracker)SingletonManager.getSingleton(GPSTracker.class)).initLocationManager();
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		((GPSTracker)SingletonManager.getSingleton(GPSTracker.class)).unRegisterSimpleLocationListener(this);
		
        try {
			SingletonManager.unRegisterSingleton(SplashActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_splash, menu);
        return true;        
    }

	public void onLocationChanged(Location location) {
		finish();
	}


    
    
    
}
