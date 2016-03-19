package com.codeminator.attndr.reports;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.codeminator.attndr.Person;
import com.codeminator.attndr.R;
import com.codeminator.attndr.VolleySingleton;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by raghav on 19/03/16.
 */
public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.PersonViewHolder>{

    List<Person> persons;
    Context c;
    SemesterAdapter(List<Person> persons, Context context){
        this.c = context;
        this.persons = persons;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_semester, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.personName.setText(persons.get(position).name);
        holder.personPresent.setText(persons.get(position).daysPresent);
        if(persons.get(position).detention.equalsIgnoreCase("0"))
            holder.personPhoto.setImageResource(R.drawable.tick);
        if(persons.get(position).detention.equalsIgnoreCase("1"))
            holder.personPhoto.setImageResource(R.drawable.exclaim);
        if(persons.get(position).detention.equalsIgnoreCase("2"))
            holder.personPhoto.setImageResource(R.drawable.cross);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(c)
                        .setTitle("Low Attendance Alert")
                        .setMessage("Are you sure you want to send the message?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String url = "";

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
                                VolleySingleton.getInstance(c).getRequestQueue().add(loginRequest);
                            }

                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personPresent;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            View v = itemView;
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personPresent = (TextView)itemView.findViewById(R.id.person_percentage  );
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

}
