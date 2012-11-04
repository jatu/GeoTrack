package school.exercise.geotrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.location.Location;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class ListViewActivity extends ListActivity implements SimpleLocationListener {

	
	protected List<HashMap<String, Object>> fillMaps;
	protected ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_view);
        
        //android.widget.ListView lv= (android.widget.ListView)findViewById(R.id.listview);
        listView = getListView();
        
        // create the grid item mapping
        String[] from = new String[] {"rowid", "col_1"};
        int[] to = new int[] { R.id.item1, R.id.item2 };
 
        // prepare the list of all records
        fillMaps = new ArrayList<HashMap<String, Object>>();

        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from, to);
        listView.setAdapter(adapter);
        
        try {
			((SingletonManager)getApplication()).registerSingleton(this, ListViewActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
        ((SimpleLocationRegisterator)getApplication()).registerSimpleLocationListener(this);
        
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
        try {
			((SingletonManager)getApplication()).unRegisterSingleton(ListViewActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
    public void onLocationChanged(Location location) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("rowid", new MyLocation(location));
        map.put("col_1", Calendar.getInstance().getTime().toLocaleString());
        fillMaps.add(map);
        ((SimpleAdapter)listView.getAdapter()).notifyDataSetChanged();
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_view, menu);
        return true;
    }
    
    @Override
    protected void onListItemClick(android.widget.ListView l, View v, int position, long id) {  	
 
    	Map<String, Object> map = (Map<String, Object>) listView.getAdapter().getItem(position);
    	Location location = (Location) map.get("rowid");
  
	    ((MapViewActivity)((SingletonManager)getApplication()).getSingleton(MapViewActivity.class)).ShowPosition(location);
	    //Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }

    
    
}
