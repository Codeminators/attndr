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
    if(beacon.getMajor() == 14718 && beacon.getMinor() == 62967 ) {
      MainActivity.p2.name = "Prempal Singh";
      MainActivity.p2.rollNo = "63";
      MainActivity.p2.present = true;
      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.p2.rollNo, com.estimote.sdk.Utils.computeAccuracy(beacon)));
    }
    if(beacon.getMajor() == 22686 && beacon.getMinor() == 47279 ) {
      MainActivity.p3.name = "Naman Dwivedi";
      MainActivity.p3.rollNo = "64";
      MainActivity.p3.present = false;
      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.p3.rollNo, com.estimote.sdk.Utils.computeAccuracy(beacon)));
    }
    if(beacon.getMajor() == 20974 && beacon.getMinor() == 20212) {
      MainActivity.p4.name = "Shreya Sharma";
      MainActivity.p4.rollNo = "65";
      MainActivity.p4.present = false;
      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.p4.rollNo, com.estimote.sdk.Utils.computeAccuracy(beacon)));
    }
    if(beacon.getMajor() == 59729 && beacon.getMinor() == 58232) {

    }
    if(beacon.getMajor() == 47633 && beacon.getMinor() == 13930 ) {
      MainActivity.p5.rollNo = "66";
      MainActivity.p5.name = "Puja Mathur";
      MainActivity.p5.present = true;
      holder.macTextView.setText(String.format("Roll No: %s (%.2fm)", MainActivity.p5.rollNo, com.estimote.sdk.Utils.computeAccuracy(beacon)));
    }

//    holder.macTextView.setText(String.format("MAC: %s (%.2fm)", beacon.getMacAddress().toStandardString(), Utils.computeAccuracy(beacon)));
    holder.majorTextView.setText("Major: " + beacon.getMajor());
    holder.minorTextView.setText("Minor: " + beacon.getMinor());
//    holder.measuredPowerTextView.setText("MPower: " + beacon.getMeasuredPower());
//    holder.rssiTextView.setText("RSSI: " + beacon.getRssi());
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
