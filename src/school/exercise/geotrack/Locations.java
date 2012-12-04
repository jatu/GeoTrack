package school.exercise.geotrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.location.Location;

/*
 * Pit‰‰ yll‰ sijainteja ja sijainteihin upotettuja p‰‰llysteit‰
 */

@SuppressWarnings("rawtypes")
public class Locations extends ItemizedOverlay implements SimpleLocationListener {
	
	public ArrayList<HashMap<String, Object>> locations;
	public ArrayList<OverlayItem> overlays;
	
	public Locations(Drawable marker) {
		super(boundCenterBottom(marker));
		
		locations = new ArrayList<HashMap<String, Object>>();
		overlays = new ArrayList<OverlayItem>(); 
		
		GPSTracker gpsTracker = (GPSTracker)SingletonManager.getSingleton(GPSTracker.class);
		gpsTracker.registerSimpleLocationListener(this);
		
		try {
			SingletonManager.registerSingleton(this, Locations.class);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void onLocationChanged(Location location) {		
		addInfo(location, Calendar.getInstance().getTime());
        
        OverlayItem locationOverlay = new OverlayItem(new GeoPoint((int) (location.getLatitude() * 1e6d), (int) (location.getLongitude() * 1e6d)), "", ""); 
        addOverlay(locationOverlay);
	}
	
	/*public MyItemizedOverlay(Drawable marker, Context context) {
	    super(boundCenterBottom(marker));
	    mContext = context;
	}*/
	 
	private void addInfo(Location location, Date time) {
		HashMap<String, Object> locationInfo = new HashMap<String, Object>();
		locationInfo.put("rowid", new MyLocation(location));
        locationInfo.put("col_1", time.toLocaleString());
        locations.add(locationInfo);
	}
	
	private void addOverlay(OverlayItem overlay) {
	    overlays.add(overlay);
	    populate();
	}
	 
	@Override
	protected OverlayItem createItem(int i) {
	    return overlays.get(i);
	}
	 
	@Override
	public int size() {
	    return overlays.size();
	}
	
	 /*
	@Override
	protected boolean onTap(int i) {
	        //when you tap on the marker this will show the informations provided by you when you create in the 
	        //main class the OverlayItem
	        OverlayItem item = overlays.get(i);
	        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	        dialog.setTitle(item.getTitle());
	        dialog.setMessage(item.getSnippet());
	        dialog.show();
	        return true;
	}*/
	
}
