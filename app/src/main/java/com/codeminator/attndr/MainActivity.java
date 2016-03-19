package com.codeminator.attndr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        assert viewPager != null;
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return CourseFragment.newInstance("Communication Systems - I", "9 - 11 AM", "TW1FF1 (EEE-1)");
                    case 1:
                        return CourseFragment.newInstance("Communication Systems - I", "12 - 1 PM", "TW1FF3 (ECE-2)");
                    case 2:
                        return CourseFragment.newInstance("Electrical Machines", "1 - 2 PM", "Smart Hall 1 (EE-2)");
                    case 3:
                        return CourseFragment.newInstance("Electronic Devices & Systems", "4 - 5 PM", "Edusat Hall (EEE-2)");
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

    }
}
