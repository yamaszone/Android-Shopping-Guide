package edu.sjsu.shoppingguide;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.google.android.maps.MapView;

public class LocationViewers extends LinearLayout {

	private MapLocationOverlay overlay;
	
    //  Known lat/long coordinates that we'll be using.
    private List<MapLocation> mapLocations;
    
    private DBManager dbManager;
    
    public static MapView mapView;
    
	public LocationViewers(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LocationViewers(Context context) {
		super(context);
		init();
	}
	
	public void init() {		
		//Log.w("LocationViewers", "Inside LocationViewers");
		setOrientation(VERTICAL);
		setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT));
		String api = getResources().getString(R.string.map_api_key);
		mapView = new MapView(getContext(),api);
		mapView.setEnabled(true);
		mapView.setClickable(true);
		addView(mapView);
		overlay = new MapLocationOverlay(this);
		mapView.getOverlays().add(overlay);
    	mapView.getController().setZoom(16);
    	//Log.w("LocationViewers", "Inside LocationViewers");
    	mapView.getController().setCenter(getMapLocations().get(0).getPoint());
    	
	}
	
//	public List<MapLocation> getMapLocations() {
//		Log.w("LocationViewers", "Inside LocationViewers"+dbManager.getMapLocations().get(0).getName());
//		//this.mapLocations = dbManager.getMapLocations();
//		//return this.mapLocations;
//		return dbManager.getMapLocations();
//	}
	public List<MapLocation> getMapLocations() {
		if (mapLocations == null) {
			mapLocations = new ArrayList<MapLocation>();
			mapLocations.add(new MapLocation("Stanford Shopping Center, Palo Alto, CA 94304",
					37.4422483,-122.1707266));
			mapLocations.add(new MapLocation("Macy's - Stanford Shopping Center, Palo Alto, CA 94304",
					37.4445711,-122.17211));
			mapLocations.add(new MapLocation("Nordstrom - Stanford Shopping Center, Palo Alto, CA 94304",
					37.4416555,-122.1713954));
			mapLocations.add(new MapLocation("Victoria's Secret - Stanford Shopping Center, Palo Alto, CA 94304",
					37.4436221,-122.1697041));
			mapLocations.add(new MapLocation("Sharon Heights Shopping Center, Menlo Park, CA 94025",
					37.4237529,-122.1964431));
			mapLocations.add(new MapLocation("San Antonio Center, Palo Alto, CA 94306",
					37.4263001,-122.149173));
		}
		return mapLocations;
	}

	public MapView getMapView() {
		return mapView;
	}
}