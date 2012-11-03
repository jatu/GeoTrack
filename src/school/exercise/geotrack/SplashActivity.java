package school.exercise.geotrack;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        TimerTask task = new TimerTask() {
        	public void run() {
        		Intent mainIntent = new Intent().setClass(SplashActivity.this, TabLayoutActivity.class);
        		startActivity(mainIntent);
        	}
        };
        
        //((SingletonManager)getApplication()).registerSingleton(this, SplashActivity.class);
        
        Timer timer = new Timer();
        timer.schedule(task, 500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_splash, menu);
        return true;        
    }
    
    
    
}
