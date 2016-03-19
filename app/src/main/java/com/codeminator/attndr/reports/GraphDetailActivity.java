package com.codeminator.attndr.reports;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codeminator.attndr.MainActivity;
import com.codeminator.attndr.R;
import com.github.florent37.hollyviewpager.HollyViewPager;
import com.github.florent37.hollyviewpager.HollyViewPagerConfigurator;

import java.util.ArrayList;

/**
 * Created by naman on 19/03/16.
 */
public class GraphDetailActivity extends AppCompatActivity {

    HollyViewPager hollyViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Attendance List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<Integer> presentList = new ArrayList<>();
        final ArrayList<Integer> absentList = new ArrayList<>();

        if (MainActivity.p1.present) {
            presentList.add(1);
        } else {
            absentList.add(1);
        }
        if (MainActivity.p2.present) {
            presentList.add(2);
        } else {
            absentList.add(2);
        }
        if (MainActivity.p3.present) {
            presentList.add(3);
        } else {
            absentList.add(3);
        }
        if (MainActivity.p4.present) {
            presentList.add(4);
        } else {
            absentList.add(4);
        }
        if (MainActivity.p5.present) {
            presentList.add(5);
        } else {
            absentList.add(5);
        }

        hollyViewPager = (HollyViewPager) findViewById(R.id.hollyViewPager);

        hollyViewPager.getViewPager().setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));
        hollyViewPager.setConfigurator(new HollyViewPagerConfigurator() {
            @Override
            public float getHeightPercentForPage(int page) {
                return ((page + 2) % 10) * 10;
            }
        });

        hollyViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return AttendanceListFragment.newInstance(presentList, absentList, position);
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return "Absent";
                } else return "Present";
            }
        });


    }
}
