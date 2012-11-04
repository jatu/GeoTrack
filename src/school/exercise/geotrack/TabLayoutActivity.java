package school.exercise.geotrack;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.app.TabActivity;
import android.content.Intent;

public class TabLayoutActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        
        TabHost tabHost = getTabHost();
        
        
        TabSpec mainSpec = tabHost.newTabSpec("Main");
        mainSpec.setIndicator("Main", getResources().getDrawable(R.drawable.ic_launcher));
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainSpec.setContent(mainIntent);
        
        TabSpec listSpec = tabHost.newTabSpec("List");
        listSpec.setIndicator("List", getResources().getDrawable(R.drawable.ic_launcher));
        Intent listIntent = new Intent(this, ListViewActivity.class);
        listSpec.setContent(listIntent);
        
        TabSpec mapSpec = tabHost.newTabSpec("Map");
        mapSpec.setIndicator("Map", getResources().getDrawable(R.drawable.ic_launcher));
        Intent mapIntent = new Intent(this, MapViewActivity.class);
        mapSpec.setContent(mapIntent);
        
        tabHost.addTab(mainSpec);
        tabHost.addTab(listSpec);
        tabHost.addTab(mapSpec);
  
        try {
			((SingletonManager)getApplication()).registerSingleton(this, TabLayoutActivity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tab_layout, menu);
        return true;
    }
}
