package school.exercise.geotrack;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	SeekBar minimumDistance;
	SeekBar minimumTime;
	
	TextView distance;
	TextView time;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try {
			SingletonManager.registerSingleton(this, MainActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        minimumDistance = (SeekBar)findViewById(R.id.seekBar1);
        minimumTime = (SeekBar)findViewById(R.id.seekBar2);
        
        distance = (TextView) findViewById(R.id.distance);
        time = (TextView) findViewById(R.id.time);
        
        minimumDistance.setProgress(10);
        minimumTime.setProgress(5);

		minimumDistance.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						distance.setText(Integer.toString(progress));
					}
				});

		minimumTime.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				time.setText(Integer.toString(progress));
			}
		});
    }
    
	@Override
	public void onDestroy() {
		super.onDestroy();
		
        try {
			SingletonManager.unRegisterSingleton(MainActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
