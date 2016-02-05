package elarrecifetrivial.codamasters.com.elarrecifetrivial;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

/**
 * Created by JCristobal on 03/01/2016.
 */
public class tabs  extends FragmentActivity {
    private FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("trivial"),
                TrivialImageActivity.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("radio group"),
                TrivialImageActivity.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("reloj"),
                TrivialImageActivity.class, null);
    }
}