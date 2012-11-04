package school.exercise.geotrack;

import android.location.Location;

public class MyLocation extends Location {
	
	 public MyLocation(Location l) {
		super(l);
	}

	 @Override
	 public String toString() {
		 return "" + getLatitude() + ", " + getLongitude();
	 }
	 
}
