package com.danflorio.modernartui;

/**
 * App title: Modern Art UI
 * Author: Dan Florio
 * Date created: 06/02/2015
 * Last modified: 06/04/2015
 *
 * Final lab assignment for Programming Mobile Applications for Android Handheld Systems: Part 1.
 * Displays a collection of colored views and a slider. The slider controls the colors of all the
 * views that aren't grey. Includes a 'More Information' menu item that displays a dialog box. The
 * user can then either choose to go to http://www.moma.com or dismiss the dialog box.
 **/

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;


public class MainActivity extends ActionBarActivity {

    // Define the container for the views
    private RelativeLayout artContainer;
    private static final String TAG = "appInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associate XML objects with java objects
        artContainer = (RelativeLayout) findViewById(R.id.artContainer);
        SeekBar colorSlider = (SeekBar) findViewById(R.id.colorSlider);

        // Define what happens when the user adjusts the slider
        colorSlider.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged ( SeekBar seekBar, int progress, boolean fromUser ) {
                // Loop through each child view of the art container
                for ( int i = 0; i < artContainer.getChildCount(); i++ ) {
                    View section = artContainer.getChildAt(i);

                    // Define a start color and end color (in this case the end color is the
                    // inverted start color
                    int startColor = Color.parseColor((String) section.getTag());
                    int endColor = 0x00FFFFFF - startColor;

                    // Only proceed if the view color isn't grey
                    if (getResources().getColor(R.color.grey) != startColor) {
                        // Convert hex color values to separate RGB values
                        // r1g1b1 = start color, r2g2b2 = end color
                        int r1 = (startColor >> 16) & 0xFF;
                        int g1 = (startColor >> 8) & 0xFF;
                        int b1 = startColor & 0xFF;
                        int r2 = (endColor >> 16) & 0xFF;
                        int g2 = (endColor >> 8) & 0xFF;
                        int b2 = endColor & 0xFF;

                        // Set the new background color for the view
                        section.setBackgroundColor(Color.rgb((int) (r1 + (r2-r1) * (progress / 100f)),
                                (int) (g1 + (g2-g1) * (progress / 100f)), (int) (b1 + (b2-b1) * (progress / 100f))));
                        section.invalidate();
                    }
                }
            }
            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) {

            }
            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) {

            }
        });
    }

    // Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handle action bar item clicks. Since there is only one menu item, this simply returns the item.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // Function to show the dialog, which is called when the user clicks 'More Information'
    public void showDialog ( MenuItem item ) {
        new MoreInformation().show( getFragmentManager(), TAG );
    }
}
