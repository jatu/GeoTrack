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

public class MapViewActivity extends MapActivity implements SimpleLocationListener {

	MapView mapView;
	MyLocationOverlay myLocationOverlay;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);        
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        // create an overlay that shows our current location
        myLocationOverlay = new MyLocationOverlay(this, mapView);
        
        // add this overlay to the MapView and refresh it
        mapView.getOverlays().add(myLocationOverlay);
        mapView.postInvalidate();
        
        // call convenience method that zooms map on our location
        //zoomToMyLocation();
        //((ListViewActivity)(((SingletonManager)getApplication()).getSingleton(ListViewActivity.class))).PainuVittuun();
        
        //((SingletonManager)getApplication()).registerSingleton(this, MapViewActivity.class);
        ((SimpleLocationRegisterator)getApplication()).registerSimpleLocationListener(this);        
        

        
    }
/*
    public void getLocation(GPSTracker gps) {
    	Location l = gps.getLocation();
    	double longitude = l.getLongitude();
    	double langitude = l.getLatitude();
    	
    	//mapView.
    }*/
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_map_view, menu);
        return true;
    }
    
//    private void zoomToMyLocation() {
//    	
//    	/*GeoPoint myLocationGeoPoint = myLocationOverlay.getMyLocation();
//    	GeoPoint p = new GeoPoint(
////    	Location location = gpsTracker.getLocation();
//        if(myLocationGeoPoint != null) {
//                mapView.getController().animateTo(myLocationGeoPoint);
//                mapView.getController().setZoom(8);
//                
//                //mapView.getController().scrollBy(location, y)*/
//    	
//    	Location location = gpsTracker.getLocation();
//    	
//    	if (location != null) {
//    		GeoPoint geoPoint = new GeoPoint((int)(location.getLatitude() * 1e6), (int)(location.getLongitude() * 1e6));
//    		mapView.getController().animateTo(geoPoint);
//        }
//        else {
//                Toast.makeText(this, "Cannot determine location", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void onLocationChanged(Location location)
    {
    	
    }
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
	}
	
	@Override
	protected void onPause() {
		super.onPause();//This line has to stay
		//Disable location detection
		myLocationOverlay.disableCompass();
	}
}
