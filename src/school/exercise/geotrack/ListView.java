package school.exercise.geotrack;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class ListView extends ListActivity {

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
            
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_view, menu);
        return true;
    }
}
