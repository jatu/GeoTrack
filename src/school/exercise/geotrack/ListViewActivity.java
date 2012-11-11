package school.exercise.geotrack;

import java.util.HashMap;
import java.util.List;

import android.location.Location;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;

import android.widget.SimpleAdapter;



public class ListViewActivity extends ListActivity implements SimpleLocationListener {

	protected List<HashMap<String, Object>> locationMaps;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_view);
        
        
        // create the grid item mapping
        String[] from = new String[] {"rowid", "col_1"};
        int[] to = new int[] { R.id.item1, R.id.item2 };
 
        // prepare the list of all records
        locationMaps = ((Locations)SingletonManager.getSingleton(Locations.class)).locations;

        SimpleAdapter adapter = new SimpleAdapter(this, locationMaps, R.layout.grid_item, from, to);
        getListView().setAdapter(adapter);
        
        try {
			SingletonManager.registerSingleton(this, ListViewActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
        ((GPSTracker)SingletonManager.getSingleton(GPSTracker.class)).registerSimpleLocationListener(this);
        
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
        try {
			SingletonManager.unRegisterSingleton(ListViewActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
    public void onLocationChanged(Location location) {
        ((SimpleAdapter)getListView().getAdapter()).notifyDataSetChanged();
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_view, menu);
        return true;
    }
    
    @Override
    protected void onListItemClick(android.widget.ListView l, View v, int position, long id) {  	 	    	
    	((TabLayoutActivity)SingletonManager.getSingleton(TabLayoutActivity.class)).tabHost.setCurrentTab(2);	    
    	((MapViewActivity)SingletonManager.getSingleton(MapViewActivity.class)).ShowOverlay(position);
    }

    
    
}
