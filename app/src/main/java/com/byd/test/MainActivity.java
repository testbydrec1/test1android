package com.byd.test;

import android.arch.lifecycle.LifecycleOwner;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.byd.test.adapter.PagerAdapter;
import com.byd.test.fragments.FixturesFragment;
import com.byd.test.fragments.ResultsFragment;

public class MainActivity extends AppCompatActivity implements ResultsFragment.OnFragmentInteractionListener, FixturesFragment.OnFragmentInteractionListener, LifecycleOwner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        TabLayout tablLayout = (TabLayout) findViewById(R.id.tab_layout);
        tablLayout.addTab(tablLayout.newTab().setText("Fixtures"));
        tablLayout.addTab(tablLayout.newTab().setText("Results"));
        tablLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tablLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablLayout));
        tablLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
