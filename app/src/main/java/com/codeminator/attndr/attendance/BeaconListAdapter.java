package com.codeminator.attndr.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codeminator.attndr.MainActivity;
import com.codeminator.attndr.R;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;

import java.util.ArrayList;
import java.util.Collection;


public class BeaconListAdapter extends BaseAdapter {

  private ArrayList<Beacon> beacons;
  private LayoutInflater inflater;

  public BeaconListAdapter(Context context) {
    this.inflater = LayoutInflater.from(context);
    this.beacons = new ArrayList<>();
  }

  public void replaceWith(Collection<Beacon> newBeacons) {
    this.beacons.clear();
    this.beacons.addAll(newBeacons);
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return beacons.size();
  }

  @Override
  public Beacon getItem(int position) {
    return beacons.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    view = inflateIfRequired(view, position, parent);
    bind(getItem(position), view);
    return view;
  }

  private void bind(Beacon beacon, View view) {
    ViewHolder holder = (ViewHolder) view.getTag();
    if(beacon.getMajor() == 57887 && beacon.getMinor() == 7000) {
      MainActivity.p1.name = "Raghav Apoorv";
      MainActivity.p1.rollNo = "62";
      MainActivity.p1.present = true;
      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.p1.rollNo, com.estimote.sdk.Utils.computeAccuracy(beacon)));
    }
//    if(beacon.getMajor() == 14718 && beacon.getMinor() == 62967 && MainActivity.p2.getPresent() <= MainActivity.b) {
//      MainActivity.p1.name = "Raghav Apoorv";
//      MainActivity.p1.rollNo = "62";
//      MainActivity.p1.present = true;
//      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.p2.getRollNo(), com.estimote.sdk.Utils.computeAccuracy(beacon)));
//    }
//    if(beacon.getMajor() == 22686 && beacon.getMinor() == 47279 && MainActivity.p3.getPresent() <= MainActivity.c) {
//      MainActivity.p3.setPresent(MainActivity.p3.getPresent() + 1);
//      MainActivity.p3.getDates().add(date);
//      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.p3.getRollNo(), com.estimote.sdk.Utils.computeAccuracy(beacon)));
//    }
//    if(beacon.getMajor() == 20974 && beacon.getMinor() == 20212 && MainActivity.p4.getPresent() <= MainActivity.d) {
//      MainActivity.p4.setPresent(MainActivity.p4.getPresent() + 1);
//      MainActivity.p4.getDates().add(date);
//      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.p4.getRollNo(), com.estimote.sdk.Utils.computeAccuracy(beacon)));
//    }
//    if(beacon.getMajor() == 59729 && beacon.getMinor() == 58232 && MainActivity.p5.getPresent() <= MainActivity.e) {
//      MainActivity.p5.setPresent(MainActivity.p5.getPresent() + 1);
//      MainActivity.p5.getDates().add(date);
//      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.p5.getRollNo(), com.estimote.sdk.Utils.computeAccuracy(beacon)));
//    }
//    if(beacon.getMajor() == 47633 && beacon.getMinor() == 13930 && MainActivity.a6.getPresent() <= MainActivity.f) {
//      MainActivity.a6.setPresent(MainActivity.a6.getPresent() + 1);
//      MainActivity.a6.getDates().add(date);
//      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.a6.getRollNo(), com.estimote.sdk.Utils.computeAccuracy(beacon)));
//    }

    holder.macTextView.setText(String.format("MAC: %s (%.2fm)", beacon.getMacAddress().toStandardString(), Utils.computeAccuracy(beacon)));
    holder.majorTextView.setText("Major: " + beacon.getMajor());
    holder.minorTextView.setText("Minor: " + beacon.getMinor());
    holder.measuredPowerTextView.setText("MPower: " + beacon.getMeasuredPower());
    holder.rssiTextView.setText("RSSI: " + beacon.getRssi());
  }

  private View inflateIfRequired(View view, int position, ViewGroup parent) {
    if (view == null) {
      view = inflater.inflate(R.layout.beacon_item, null);
      view.setTag(new ViewHolder(view));
    }
    return view;
  }

  static class ViewHolder {
    final TextView macTextView;
    final TextView majorTextView;
    final TextView minorTextView;
    final TextView measuredPowerTextView;
    final TextView rssiTextView;

    ViewHolder(View view) {
      macTextView = (TextView) view.findViewWithTag("mac");
      majorTextView = (TextView) view.findViewWithTag("major");
      minorTextView = (TextView) view.findViewWithTag("minor");
      measuredPowerTextView = (TextView) view.findViewWithTag("mpower");
      rssiTextView = (TextView) view.findViewWithTag("rssi");
    }
  }
}
