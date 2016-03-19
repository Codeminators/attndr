package com.codeminator.attndr;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.codeminator.attndr.reports.SemesterActivity;

/**
 * Created by prempal on 19/3/16.
 */
public class CourseDialog extends android.support.v4.app.DialogFragment {

    public CourseDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.dialog_courses, container);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("CS", Color.RED);
        ImageView image = (ImageView) rootView.findViewById(R.id.imageView);
        image.setImageDrawable(drawable);
        TextDrawable drawable2 = TextDrawable.builder()
                .buildRound("EM", Color.GRAY);
        ImageView image2 = (ImageView) rootView.findViewById(R.id.imageView2);
        image2.setImageDrawable(drawable2);
        TextDrawable drawable3 = TextDrawable.builder()
                .buildRound("EDC", Color.GREEN);
        ImageView image3 = (ImageView) rootView.findViewById(R.id.imageView3);
        image3.setImageDrawable(drawable3);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                startActivity(new Intent(view.getContext(), SemesterActivity.class));
            }
        });
        return rootView;
    }
}
