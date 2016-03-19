package com.codeminator.attndr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.codeminator.attndr.reports.SemesterActivity;

import org.json.JSONObject;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent i = new Intent(ReportActivity.this, SemesterActivity.class);
        startActivity(i);
        Button sendAlert = (Button) findViewById(R.id.sendAlert);

        String url = "http://192.168.43.130/send_sms?query=Your%20ward%20has%20low%20attendance.";

        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(loginRequest);
    }
}
