package com.codeminator.attndr.reports;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeminator.attndr.R;
import com.github.florent37.hollyviewpager.HollyViewPagerBus;

/**
 * Created by naman on 19/03/16.
 */
public class AttendanceListFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attendance_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerAdapter());

        HollyViewPagerBus.registerRecyclerView(getActivity(), recyclerView);

        return rootView;
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
            View view;
            switch (type) {
                case TYPE_HEADER:
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hvp_header_placeholder, viewGroup, false);
                    break;
                default:
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_card_attendance_list, viewGroup, false);
                    break;
            }
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 15;
        }
    }
}
