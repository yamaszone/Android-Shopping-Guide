package edu.sjsu.shoppingguide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BrowseItems extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Browse Items tab");
        setContentView(textview);
    }
}
