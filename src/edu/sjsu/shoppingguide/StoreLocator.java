package edu.sjsu.shoppingguide;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ZoomControls;

import com.google.android.maps.MapActivity;
import edu.sjsu.shoppingguide.LocationViewers;
import edu.sjsu.shoppingguide.R;

public class StoreLocator extends MapActivity{
	@Override
	public void onCreate(Bundle icicle) {
    	
        super.onCreate(icicle);

        setContentView(R.layout.map);
        
        
        ZoomControls zoomControls = (ZoomControls) findViewById(R.id.zoomcontrols);
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	//Log.w("StoreLocator", "Inside Store Locator");
                	LocationViewers.mapView.getController().zoomIn();      
                }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	LocationViewers.mapView.getController().zoomOut();
                }
        });
    }

    /**
     * Must let Google know that a route will not be displayed
     */
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	} 
}
