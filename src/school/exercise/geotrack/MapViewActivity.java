package school.exercise.geotrack;

import android.app.LocalActivityManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.maps.MapActivity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MapViewActivity extends MapActivity {

	MapView mapView;
	MyLocationOverlay myLocationOverlay;
	MapController mapController;
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);	  
	}	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);                

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        mapController = mapView.getController();
        
        // create an overlay that shows our current location
        myLocationOverlay = new MyLocationOverlay(this, mapView);
        
        // add this overlay to the MapView and refresh it
        mapView.getOverlays().add(myLocationOverlay);
        mapView.postInvalidate();
        
        // call convenience method that zooms map on our location
        //zoomToMyLocation();

    	try {
			SingletonManager.registerSingleton(this, MapViewActivity.class);					        
    	} catch (Exception e) {			
    		e.printStackTrace();
    	}
                
    }
    
	@Override
	public void onDestroy() {
		super.onDestroy();
		
        try {
			SingletonManager.unRegisterSingleton(MapViewActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

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

    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return true;
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

	public void ShowPosition(Location location) {
		int lat = (int) (location.getLatitude() * 1E6);
	    int lng = (int) (location.getLongitude() * 1E6);
		GeoPoint point = new GeoPoint(lat, lng);
		mapController.setZoom(10);
		mapController.animateTo(point);					   
	}
}
