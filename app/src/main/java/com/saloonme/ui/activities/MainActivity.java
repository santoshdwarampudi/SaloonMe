package com.saloonme.ui.activities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.saloonme.R;
import com.saloonme.ui.fragments.ClickAndShareFragment;
import com.saloonme.ui.fragments.HomeFragment;
import com.saloonme.ui.fragments.MoreFragment;
import com.saloonme.ui.fragments.ProfileFragment;
import com.saloonme.ui.fragments.SalonsFragment;
import com.saloonme.util.LocationSingleTon;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseAppCompactActivity {
    private Handler mHandler;
    FusedLocationProviderClient fusedLocationProviderClient;
    @BindView(R.id.bottom_navigation_view_linear)
    BubbleNavigationLinearView bottom_navigation_view_linear;
    @BindView(R.id.tv_location)
    TextView tv_location;

    @OnClick(R.id.location_layout)
    void onLocationClick() {

    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
        loadFragment(new HomeFragment());
        bottom_navigation_view_linear.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case 0:
                        loadFragment(new HomeFragment());
                        break;
                    case 1:
                        if (PrefUtils.getInstance().isLogin())
                            loadFragment(new ProfileFragment());
                        else
                            goToActivity(LoginActivity.class);
                        break;
                    case 2:
                        loadFragment(new SalonsFragment());
                        break;
                    case 3:
                        loadFragment(new ClickAndShareFragment());
                        break;
                    case 4:
                        loadFragment(new MoreFragment());
                        break;
                }
            }
        });
    }

    private void getLocation() {
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    PrefUtils.getInstance().saveLat(location.getLatitude() + "");
                    PrefUtils.getInstance().saveLogni(location.getLongitude() + "");
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    LocationSingleTon.instance().setLatLng(latLng);
                    setLocation();
                } else {
                    String lat = PrefUtils.getInstance().geLat();
                    String logni = PrefUtils.getInstance().geLogni();
                    if (!ValidationUtil.isNullOrEmpty(lat) && !ValidationUtil.isNullOrEmpty(logni)) {
                        double double_lat = Double.parseDouble(lat);
                        double double_longni = Double.parseDouble(logni);
                        LatLng latLng = new LatLng(double_lat, double_longni);
                        LocationSingleTon.instance().setLatLng(latLng);
                        setLocation();
                    }
                }
            }
        });
    }

    private void setLocation() {
        LatLng latLng = LocationSingleTon.instance().getLatLng();
        if (latLng != null) {
            Geocoder gcd = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = gcd.getFromLocation(latLng.latitude, latLng.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses != null && addresses.size() > 0) {
                tv_location.setText(
                        addresses.get(0).getLocality());
            } else {
                showToast("Unable to get location");
                finish();
            }
        }
    }

    private void loadFragment(final Fragment fragment) {
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, "");
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        // drawer_layout.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();

    }
}