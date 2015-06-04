package com.danflorio.modernartui;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;


public class MainActivity extends ActionBarActivity {

    private RelativeLayout artContainer;
    private static final String TAG = "appInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artContainer = (RelativeLayout) findViewById(R.id.artContainer);
        SeekBar colorSlider = (SeekBar) findViewById(R.id.colorSlider);

        colorSlider.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged ( SeekBar seekBar, int progress, boolean fromUser ) {
                for ( int i = 0; i < artContainer.getChildCount(); i++ ) {
                    View section = artContainer.getChildAt(i);

                    int startColor = Color.parseColor((String) section.getTag());
                    int endColor = 0x00FFFFFF - startColor;

                    if (getResources().getColor(R.color.grey) != startColor) {
                        int r1 = (startColor >> 16) & 0xFF;
                        int g1 = (startColor >> 8) & 0xFF;
                        int b1 = startColor & 0xFF;
                        int r2 = (endColor >> 16) & 0xFF;
                        int g2 = (endColor >> 8) & 0xFF;
                        int b2 = endColor & 0xFF;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.more_information) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDialog ( MenuItem item ) {

        new MoreInformation().show( getFragmentManager(), TAG );
    }
}
