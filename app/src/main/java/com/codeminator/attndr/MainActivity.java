package com.codeminator.attndr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.codeminator.attndr.attendance.ListBeaconsActivity;

public class MainActivity extends AppCompatActivity {

    public static Person p1 = new Person();
    public static Person p2 = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startListBeaconsActivity(DistanceBeaconActivity.class.getName());
//        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
//        startActivity(intent);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        assert viewPager != null;
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
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

        Button takeAttendace = (Button) findViewById(R.id.button);
        assert takeAttendace != null;
        takeAttendace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startListBeaconsActivity();
            }
        });

        final Button reports = (Button) findViewById(R.id.button2);
        assert reports != null;
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(reports.getContext(), ReportActivity.class));
                FragmentManager fm = getSupportFragmentManager();

                CourseDialog editNameDialog = new CourseDialog();

                editNameDialog.show(fm, "dialog");
            }
        });
    }
    public static Person p3 = new Person();

    private void startListBeaconsActivity() {
        Intent intent = new Intent(MainActivity.this, ListBeaconsActivity.class);
        startActivity(intent);
    }
    public static Person p4 = new Person();
    public static Person p5 = new Person();
}
