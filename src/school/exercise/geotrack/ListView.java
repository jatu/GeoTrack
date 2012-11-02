package school.exercise.geotrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class ListView extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_view);
        
        //android.widget.ListView lv= (android.widget.ListView)findViewById(R.id.listview);
        android.widget.ListView lv = getListView();
        
        // create the grid item mapping
        String[] from = new String[] {"rowid", "col_1"};
        int[] to = new int[] { R.id.item1, R.id.item2 };
 
        // prepare the list of all records
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        for(int i = 0; i < 10; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("rowid", "col_1_item_" + i);
            map.put("col_1", "col_2_item_" + i);
            fillMaps.add(map);
        }


        // fill in the grid_item layout

        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from, to);
        lv.setAdapter(adapter);

	  }

	/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_view);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
            // Use your own layout
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list_view, R.id.label, values);
            setListAdapter(adapter);
   
    }*/

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
