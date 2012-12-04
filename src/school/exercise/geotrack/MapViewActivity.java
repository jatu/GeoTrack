package school.exercise.geotrack;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.maps.MapActivity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/*
 * N‰ytt‰‰ kartan ja siihen luodut pisteet
 */

public class MapViewActivity extends MapActivity {

	MapView mapView;
	//MyLocationOverlay myLocationOverlay;
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
        //myLocationOverlay = new MyLocationOverlay(this, mapView);
        
        // add this overlay to the MapView and refresh it
        mapView.getOverlays().add((Locations)SingletonManager.getSingleton(Locations.class));
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
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//myLocationOverlay.enableMyLocation();
	}
	
	@Override
	protected void onPause() {
		super.onPause();//This line has to stay
		//Disable location detection
		//myLocationOverlay.disableCompass();
	}

	public void ShowOverlay(int index) {		
		Locations locations = (Locations)SingletonManager.getSingleton(Locations.class);
		OverlayItem overlayItem = (OverlayItem)locations.overlays.get(index);
		GeoPoint point = overlayItem.getPoint();
		
		mapController.setZoom(13);
		mapController.animateTo(point);					   
	}
}
