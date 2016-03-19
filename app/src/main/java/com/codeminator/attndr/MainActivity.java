package com.codeminator.attndr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startListBeaconsActivity(DistanceBeaconActivity.class.getName());

    }

    private void startListBeaconsActivity(String extra) {
        Intent intent = new Intent(MainActivity.this, ListBeaconsActivity.class);
        intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, extra);
        startActivity(intent);
    }
}
