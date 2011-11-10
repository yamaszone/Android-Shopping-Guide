package edu.sjsu.shoppingguide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class DBManager extends Activity {
    /** Called when the activity is first created. */
	private List<MapLocation> mapLocations;
//	private List<MapLocation> mapLocations1;
	private int listIndex = 0;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        try {
        	
        	String destPath = "/data/data/" + getPackageName() + "/databases/MyDB";
        	//Log.w("DBManager", "Inside DBManager: Path = "+destPath);
        	File f = new File(destPath);
        	
        	if (!f.exists()) {
	        	copyDB( getBaseContext().getAssets().open("mydb"), new FileOutputStream(destPath));
        	}
        } catch (FileNotFoundException e) {
        		e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
//        mapLocations1 = this.getMapLocations();
//        DBAdapter db = new DBAdapter(this);
//        mapLocations = new ArrayList<MapLocation>();
//
//        //---get all contacts---
//        db.open();
//        Cursor c = db.getAllData();
//        if (c.moveToFirst())
//        {
//	        do {
//	        	mapLocations.add(new MapLocation(c.getString(1), c.getDouble(3),c.getDouble(4)));
//	        	//Log.w("DBM", "Cursor: " + c.getString(0));
//	        	displayData(c);
//	        	//getMapLocations(c);
//	        } while (c.moveToNext());
//        }
//        db.close();
    }
    
    public List<MapLocation> getMapLocations() {
    	
    	try {
        	
        	String destPath = "/data/data/" + getPackageName() + "/databases/MyDB";
        	//Log.w("DBManager", "Inside DBManager: Path = "+destPath);
        	File f = new File(destPath);
        	
        	if (!f.exists()) {
	        	copyDB( getBaseContext().getAssets().open("mydb"), new FileOutputStream(destPath));
        	}
        } catch (FileNotFoundException e) {
        		e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }

    	DBAdapter db = new DBAdapter(this);
        mapLocations = new ArrayList<MapLocation>();

        //---get all contacts---
        db.open();
        Cursor c = db.getAllData();
        if (c.moveToFirst())
        {
	        do {
	        	mapLocations.add(new MapLocation(c.getString(1), c.getDouble(3),c.getDouble(4)));
	        	//Log.w("DBM", "Cursor: " + c.getString(0));
	        	//displayData(c);
	        	//getMapLocations(c);
	        } while (c.moveToNext());
        }
        db.close();
        
        //Toast.makeText(this, "# of Elements: " + mapLocations.size(), Toast.LENGTH_LONG).show();
        
    	return mapLocations;
    }
    
    public void displayData(Cursor c)
    {
    	//mapLocations.add(new MapLocation(c.getString(1), c.getDouble(3),c.getDouble(4)));
    	
    	//Log.w("DBM", "Cursor: " + c);
    	//Log.w("DBManager", "Showing Cursor content: " + c);
//    	Toast.makeText(this, "sid: " + c.getString(0) + "\n" + "Store Name: " + c.getString(1) + "\n" +
//	    " Street: " + c.getString(2) + "\n" + " Lat: " + c.getString(3) + " Lon: " + c.getString(4), 
//	    Toast.LENGTH_LONG).show();
    	
    	Toast.makeText(this, "From MapLocation: " + mapLocations.get(listIndex).getName(), Toast.LENGTH_LONG).show();
    	listIndex++;
    }
    
    public void copyDB(InputStream inputStream, OutputStream outputStream)
    		throws IOException {
    	
    		//---copy 1K bytes at a time---
    		byte[] buffer = new byte[1024];
    		int length;
    		
    		while ((length = inputStream.read(buffer)) > 0) {
    			outputStream.write(buffer, 0, length);
    		}
    		
    		inputStream.close();
    		outputStream.close();
    }
}