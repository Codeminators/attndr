package com.codeminator.attndr.attendance;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.codeminator.attndr.R;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by naman on 19/03/16.
 */
public class SearchingFragment extends Fragment {

    private ImageView found1, found2, found3, found4, found5, found6;

    private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);

    private BeaconManager beaconManager;
    FloatingActionButton fab;
    TextView status, beaconNumber;

    List<Beacon> apneBeacon;
    private BeaconListAdapter adapter;


    int beaconSize = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searching, container, false);

        found1 = (ImageView) rootView.findViewById(R.id.foundDevice1);
        found2 = (ImageView) rootView.findViewById(R.id.foundDevice2);
        found3 = (ImageView) rootView.findViewById(R.id.foundDevice3);
        found4 = (ImageView) rootView.findViewById(R.id.foundDevice4);
        found5 = (ImageView) rootView.findViewById(R.id.foundDevice5);
        found6 = (ImageView) rootView.findViewById(R.id.foundDevice5);

        final RippleBackground rippleBackground = (RippleBackground) rootView.findViewById(R.id.content);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.centerImage);

        initBeacon();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.stopRippleAnimation();
            }
        });

        adapter = new BeaconListAdapter(getActivity());

        final ListView list = (ListView) rootView.findViewById(R.id.device_list);
        list.setAdapter(adapter);


        rippleBackground.startRippleAnimation();

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        status = (TextView) rootView.findViewById(R.id.status);
        beaconNumber = (TextView) rootView.findViewById(R.id.beacon_number);

        apneBeacon = new ArrayList<Beacon>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimation();
            }
        });

        return rootView;
    }

    private void foundDevice(int id) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

        switch (id) {
            case 1:
                ArrayList<Animator> animatorList = new ArrayList<Animator>();
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(found1, "ScaleX", 0f, 1.2f, 1f);
                animatorList.add(scaleXAnimator);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(found1, "ScaleY", 0f, 1.2f, 1f);
                animatorList.add(scaleYAnimator);
                animatorSet.playTogether(animatorList);
                found1.setVisibility(View.VISIBLE);
                break;
            case 2:
                ArrayList<Animator> animatorList2 = new ArrayList<Animator>();
                ObjectAnimator scaleXAnimator2 = ObjectAnimator.ofFloat(found2, "ScaleX", 0f, 1.2f, 1f);
                animatorList2.add(scaleXAnimator2);
                ObjectAnimator scaleYAnimator2 = ObjectAnimator.ofFloat(found2, "ScaleY", 0f, 1.2f, 1f);
                animatorList2.add(scaleYAnimator2);
                animatorSet.playTogether(animatorList2);
                found2.setVisibility(View.VISIBLE);
                break;
            case 3:
                ArrayList<Animator> animatorList3 = new ArrayList<Animator>();
                ObjectAnimator scaleXAnimator3 = ObjectAnimator.ofFloat(found3, "ScaleX", 0f, 1.2f, 1f);
                animatorList3.add(scaleXAnimator3);
                ObjectAnimator scaleYAnimator3 = ObjectAnimator.ofFloat(found3, "ScaleY", 0f, 1.2f, 1f);
                animatorList3.add(scaleYAnimator3);
                animatorSet.playTogether(animatorList3);
                found3.setVisibility(View.VISIBLE);
                break;
            case 4:
                ArrayList<Animator> animatorList4 = new ArrayList<Animator>();
                ObjectAnimator scaleXAnimator4 = ObjectAnimator.ofFloat(found4, "ScaleX", 0f, 1.2f, 1f);
                animatorList4.add(scaleXAnimator4);
                ObjectAnimator scaleYAnimator4 = ObjectAnimator.ofFloat(found5, "ScaleY", 0f, 1.2f, 1f);
                animatorList4.add(scaleYAnimator4);
                animatorSet.playTogether(animatorList4);
                found4.setVisibility(View.VISIBLE);
                break;
            case 5:
                ArrayList<Animator> animatorList5 = new ArrayList<Animator>();
                ObjectAnimator scaleXAnimator5 = ObjectAnimator.ofFloat(found5, "ScaleX", 0f, 1.2f, 1f);
                animatorList5.add(scaleXAnimator5);
                ObjectAnimator scaleYAnimator5 = ObjectAnimator.ofFloat(found5, "ScaleY", 0f, 1.2f, 1f);
                animatorList5.add(scaleYAnimator5);
                animatorSet.playTogether(animatorList5);
                found5.setVisibility(View.VISIBLE);
                break;
            case 6:
                ArrayList<Animator> animatorList6 = new ArrayList<Animator>();
                ObjectAnimator scaleXAnimator6 = ObjectAnimator.ofFloat(found6, "ScaleX", 0f, 1.2f, 1f);
                animatorList6.add(scaleXAnimator6);
                ObjectAnimator scaleYAnimator6 = ObjectAnimator.ofFloat(found6, "ScaleY", 0f, 1.2f, 1f);
                animatorList6.add(scaleYAnimator6);
                animatorSet.playTogether(animatorList6);
                found6.setVisibility(View.VISIBLE);
                break;
        }
        animatorSet.start();
    }

    private void initBeacon() {
        beaconManager = new BeaconManager(getActivity());
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, final List<Beacon> beacons) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        apneBeacon.clear();

                        for (int b = 0; b < beacons.size(); b++) {
                            apneBeacon.add(beacons.get(b));
                        }

                        for (int a = 0; a < beacons.size(); a++) {
                            Beacon beacon = beacons.get(a);
//                            if ((beacon.getMajor() == 57887 && beacon.getMinor() == 7000) ||
//                                    (beacon.getMajor() == 14718 && beacon.getMinor() == 62967) ||
//                                    (beacon.getMajor() == 22686 && beacon.getMinor() == 47279) ||
//                                    (beacon.getMajor() == 20974 && beacon.getMinor() == 20212) ||
//                                    (beacon.getMajor() == 59729 && beacon.getMinor() == 58232) ||
//                                    (beacon.getMajor() == 47633 && beacon.getMinor() == 13930)) {
//
//                                apneBeacon.add(beacon);
//
//                            }
                            if (beacon.getMajor() == 55957 && beacon.getMinor() == 34167) {
                                apneBeacon.remove(beacon);
                            }
                        }
                        if (apneBeacon.size() > beaconSize) {
                            beaconSize = apneBeacon.size();
                            beaconNumber.setText(apneBeacon.size() + " beacons found");

                            for (int i = 1; i <= beaconSize; i++) {
                                foundDevice(i);
                            }

                            adapter.replaceWith(beacons);

                        }
                    }
                });
            }
        });

        beaconManager.setScanStatusListener(new BeaconManager.ScanStatusListener() {
            @Override
            public void onScanStart() {
                status.setText("Scanning started");
            }

            @Override
            public void onScanStop() {
                status.setText("Scanning stopped");
            }
        });
    }


    @Override
    public void onDestroy() {
        beaconManager.disconnect();

        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SystemRequirementsChecker.checkWithDefaultDialogs(getActivity())) {
            startScanning();
        }
    }

    @Override
    public void onStop() {
        beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS_REGION);
        super.onStop();
    }

    private void startScanning() {
        adapter.replaceWith(Collections.<Beacon>emptyList());
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(ALL_ESTIMOTE_BEACONS_REGION);
            }
        });
    }

    private void doAnimation() {

    }

}
