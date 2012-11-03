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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class ListViewActivity extends ListActivity implements SimpleLocationListener {

	
	protected List<HashMap<String, String>> fillMaps;
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
        fillMaps = new ArrayList<HashMap<String, String>>();
        /*for(int i = 0; i < 10; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("rowid", "col_1_item_" + i);
            map.put("col_1", "col_2_item_" + i);
            fillMaps.add(map);
        }*/


        // fill in the grid_item layout

        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from, to);
        listView.setAdapter(adapter);
        
        //((SingletonManager)getApplication()).registerSingleton(this, ListViewActivity.class);
        ((SimpleLocationRegisterator)getApplication()).registerSimpleLocationListener(this);
        
	  }

	
    public void onLocationChanged(Location location) {
    	HashMap<String, String> map = new HashMap<String, String>();
        map.put("rowid", "" + location.getLatitude() + ", " + location.getLongitude());
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
    	String item = (String) getListAdapter().getItem(position);
	    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();	 
    }

    
    
}
