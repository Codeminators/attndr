package com.codeminator.attndr.reports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        Person p4 = new Person();
        Person p5 = new Person();
        p1.name = "Raghav Apoorv";
        p1.daysPresent = "Days Present = 24";
        p1.detention = "0";
        personList.add(p1);
        p2.name = "Prempal Singh";
        p2.daysPresent = "Days Present = 2";
        p2.detention = "2";
        personList.add(p2);
        p3.name = "Naman Dwivedi";
        p3.daysPresent = "Days Present = 18";
        p3.detention = "1";
        personList.add(p3);
        p4.name = "Pranav Gulati";
        p4.daysPresent = "Days Present = 24";
        p4.detention = "0";
        personList.add(p4);
        p5.name = "Shubham Kumar";
        p5.daysPresent = "Days Present = 17";
        p5.detention = "1";
        personList.add(p5);


        SemesterAdapter adapter = new SemesterAdapter(personList, this);
        rv.setAdapter(adapter);


    }
}
