package com.codeminator.attndr.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codeminator.attndr.MainActivity;
import com.codeminator.attndr.R;

public class ListBeaconsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        MainActivity.p1.name = "Raghav Apoorv";
        MainActivity.p1.daysPresent = "Days Present = 24";
        MainActivity.p1.detention = "0";

        MainActivity.p2.name = "Prempal Singh";
        MainActivity.p2.daysPresent = "Days Present = 2";
        MainActivity.p2.detention = "2";

        MainActivity.p3.name = "Naman Dwivedi";
        MainActivity.p3.daysPresent = "Days Present = 18";
        MainActivity.p3.detention = "1";

        MainActivity.p4.name = "Shreya Sharma";
        MainActivity.p4.daysPresent = "Days Present = 24";
        MainActivity.p4.detention = "0";

        MainActivity.p5.name = "Puja Mathur";
        MainActivity.p5.daysPresent = "Days Present = 17";
        MainActivity.p5.detention = "1";

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchingFragment()).commit();

    }

}
