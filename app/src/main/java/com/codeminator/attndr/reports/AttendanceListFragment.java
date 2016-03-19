package com.codeminator.attndr.reports;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeminator.attndr.MainActivity;
import com.codeminator.attndr.Person;
import com.codeminator.attndr.R;
import com.github.florent37.hollyviewpager.HollyViewPagerBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naman on 19/03/16.
 */
public class AttendanceListFragment extends Fragment {

    RecyclerView recyclerView;
    List<Person> personList = new ArrayList<>();

    public static AttendanceListFragment newInstance(ArrayList<Integer> presentList, ArrayList<Integer> absentList, int page) {
        AttendanceListFragment fragment = new AttendanceListFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList("presentarray", presentList);
        args.putInt("page", page);
        args.putIntegerArrayList("absentarray", absentList);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attendance_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerAdapter());

        HollyViewPagerBus.registerRecyclerView(getActivity(), recyclerView);

        if (getArguments().getInt("page") == 0) {
            ArrayList<Integer> absentList = getArguments().getIntegerArrayList("absentarray");

            for (Integer integer : absentList) {
                switch (integer) {
                    case 1:
                        personList.add(MainActivity.p1);
                        break;
                    case 2:
                        personList.add(MainActivity.p2);
                        break;
                    case 3:
                        personList.add(MainActivity.p3);
                        break;
                    case 4:
                        personList.add(MainActivity.p4);
                        break;
                    case 5:
                        personList.add(MainActivity.p5);
                        break;
                }
            }
        } else {
            ArrayList<Integer> presentList = getArguments().getIntegerArrayList("presentarray");

            for (Integer integer : presentList) {
                switch (integer) {
                    case 1:
                        personList.add(MainActivity.p1);
                        break;
                    case 2:
                        personList.add(MainActivity.p2);
                        break;
                    case 3:
                        personList.add(MainActivity.p3);
                        break;
                    case 4:
                        personList.add(MainActivity.p4);
                        break;
                    case 5:
                        personList.add(MainActivity.p5);
                        break;
                }
            }
        }

        return rootView;
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        protected static final int TYPE_HEADER = 0;
        protected static final int TYPE_CELL = 1;

        @Override
        public int getItemViewType(int position) {
            switch (position) {
                case 0:
                    return TYPE_HEADER;
                default:
                    return TYPE_CELL;
            }
        }

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
            View view;
            switch (type) {
                case TYPE_HEADER:
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hvp_header_placeholder, viewGroup, false);
                    break;
                default:
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_card_attendance_list, viewGroup, false);
                    break;
            }
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int i) {
            if (i > 0) {
                viewHolder.name.setText(personList.get(i-1).name);
                viewHolder.rollNo.setText(personList.get(i-1).rollNo);
            }
        }

        @Override
        public int getItemCount() {
            return personList.size()+1;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            protected TextView name, rollNo;

            public ViewHolder(View itemView) {
                super(itemView);

                name = (TextView) itemView.findViewById(R.id.student_name);
                rollNo = (TextView) itemView.findViewById(R.id.student_rollno);

            }
        }
    }
}
