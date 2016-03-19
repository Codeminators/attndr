package com.codeminator.attndr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment {


    public CourseFragment() {
        // Required empty public constructor
    }

    public static CourseFragment newInstance(String courseName, String time, String location) {

        Bundle args = new Bundle();
        args.putString("courseName", courseName);
        args.putString("time", time);
        args.putString("location", location);
        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_course, container, false);
        TextView courseName = (TextView) rootView.findViewById(R.id.course_name);
        TextView location = (TextView) rootView.findViewById(R.id.location);
        TextView time = (TextView) rootView.findViewById(R.id.time);

        Bundle bundle = getArguments();
        courseName.setText(bundle.getString("courseName"));
        location.setText(bundle.getString("location"));
        time.setText(bundle.getString("time"));
        return rootView;
    }

}
