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
import android.widget.TextView;

import com.codeminator.attndr.R;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naman on 19/03/16.
 */
public class SearchingFragment extends Fragment {

    private ImageView foundDevice;

    private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);

    private BeaconManager beaconManager;
    FloatingActionButton fab;
    TextView status, beaconNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searching, container, false);

        foundDevice = (ImageView) rootView.findViewById(R.id.foundDevice);

        final RippleBackground rippleBackground = (RippleBackground) rootView.findViewById(R.id.content);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.centerImage);

        initBeacon();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.stopRippleAnimation();
            }
        });

        rippleBackground.startRippleAnimation();

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        status = (TextView) rootView.findViewById(R.id.status);
        beaconNumber = (TextView) rootView.findViewById(R.id.beacon_number);

        return rootView;
    }

    private void foundDevice() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList = new ArrayList<Animator>();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator);
        animatorSet.playTogether(animatorList);
        foundDevice.setVisibility(View.VISIBLE);
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
                        beaconNumber.setText(beacons.size() + " beacons found");
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
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(ALL_ESTIMOTE_BEACONS_REGION);
            }
        });
    }

}
