package school.exercise.geotrack;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.maps.MapActivity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MapViewActivity extends MapActivity {

	MapView mapView;
	MyLocationOverlay myLocationOverlay;
	GPSTracker gpsTracker;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        
        gpsTracker = new GPSTracker(this);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        // create an overlay that shows our current location
        myLocationOverlay = new MyLocationOverlay(this, mapView);
        
        // add this overlay to the MapView and refresh it
        mapView.getOverlays().add(myLocationOverlay);
        mapView.postInvalidate();
        
        // call convenience method that zooms map on our location
        zoomToMyLocation();
        
/*        if(gpsTracker.getLocation() != null) {
        	//Kun tämä suoritetaan niin spläshi loppuu
        }
*/
        
    }

    public void getLocation(GPSTracker gps) {
    	Location l = gps.getLocation();
    	double longitude = l.getLongitude();
    	double langitude = l.getLatitude();
    	
    	//mapView.
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_map_view, menu);
        return true;
    }
    
    private void zoomToMyLocation() {
    	
    	getLocation(gpsTracker);
    	
    	GeoPoint myLocationGeoPoint = myLocationOverlay.getMyLocation();
    	
    	Location location = gpsTracker.getLocation();
        if(myLocationGeoPoint != null) {
                mapView.getController().animateTo(myLocationGeoPoint);
                mapView.getController().setZoom(10);
                
                //mapView.getController().scrollBy(location, y)
        }
        else {
                Toast.makeText(this, "Cannot determine location", Toast.LENGTH_SHORT).show();
        }
}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void onResume() {
		myLocationOverlay.enableMyLocation();
	}
	
	protected void onPause() {
		super.onPause();//This line has to stay
		//Disable location detection
		myLocationOverlay.disableCompass();
	}
}
