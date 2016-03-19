package com.codeminator.attndr.reports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codeminator.attndr.MainActivity;
import com.codeminator.attndr.Person;
import com.codeminator.attndr.R;

import java.util.ArrayList;

public class SemesterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        ArrayList<Person> personList = new ArrayList<Person>();
        
        MainActivity.p1.name = "Raghav Apoorv";
        MainActivity.p1.daysPresent = "Days Present = 24";
        MainActivity.p1.detention = "0";
        personList.add(MainActivity.p1);
        MainActivity.p2.name = "Prempal Singh";
        MainActivity.p2.daysPresent = "Days Present = 2";
        MainActivity.p2.detention = "2";
        personList.add(MainActivity.p2);
        MainActivity.p3.name = "Naman Dwivedi";
        MainActivity.p3.daysPresent = "Days Present = 18";
        MainActivity.p3.detention = "1";
        personList.add(MainActivity.p3);
        MainActivity.p4.name = "Shreya Sharma";
        MainActivity.p4.daysPresent = "Days Present = 24";
        MainActivity.p4.detention = "0";
        personList.add(MainActivity.p4);
        MainActivity.p5.name = "Puja Mathur";
        MainActivity.p5.daysPresent = "Days Present = 17";
        MainActivity.p5.detention = "1";
        personList.add(MainActivity.p5);

        SemesterAdapter adapter = new SemesterAdapter(personList, this);
        rv.setAdapter(adapter);


    }
}
