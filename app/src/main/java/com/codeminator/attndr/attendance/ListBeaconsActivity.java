package com.codeminator.attndr.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codeminator.attndr.R;

public class ListBeaconsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchingFragment()).commit();

    }

}
