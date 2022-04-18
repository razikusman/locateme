package com.example.locateme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class DashBoard extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        tabLayout = findViewById(R.id.tabLayout);
        pager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(pager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) ;

        vpAdapter.addFragment( new fragment1() , "Tab 1");
        vpAdapter.addFragment( new fragmnt2() , "Tab 2");
        vpAdapter.addFragment( new fragment3() , "Tab 2");

        pager.setAdapter(vpAdapter);
    }
}