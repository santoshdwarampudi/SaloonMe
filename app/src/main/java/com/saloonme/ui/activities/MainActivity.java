package com.saloonme.ui.activities;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.saloonme.R;
import com.saloonme.ui.fragments.BlogsFragment;
import com.saloonme.ui.fragments.ClickAndShareFragment;
import com.saloonme.ui.fragments.HomeFragment;
import com.saloonme.ui.fragments.ProductsFragment;
import com.saloonme.ui.fragments.ProfileFragment;
import com.saloonme.util.LocationSingleTon;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseAppCompactActivity {
    private static final int AUTOCOMPLETE_REQUEST_CODE = 101;
    private Handler mHandler;
    FusedLocationProviderClient fusedLocationProviderClient;
    @BindView(R.id.bottom_navigation_view_linear)
    BubbleNavigationLinearView bottom_navigation_view_linear;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.iv_logout)
    ImageView iv_logout;

    @OnClick(R.id.iv_logout)
    void onLogoutClick() {
        showConfirmationForLogout();
    }

    @OnClick(R.id.tv_location)
    void onLocationChangeClick() {
        if (!Places.isInitialized()) {
           // Places.initialize(getApplicationContext(), "AIzaSyAMrS33N1Oq8Llu6t8pKFQbnMwFc_6BwZg");
           Places.initialize(getApplicationContext(), "AIzaSyCgvlGxgIM64Y0zO0_G9m-slg2_zKytBiU");
        }
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME,
                Place.Field.ADDRESS, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields).setCountry("IN")
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    private void showConfirmationForLogout() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_confirmation);

        TextView noTv = (TextView) dialog.findViewById(R.id.noTv);
        TextView yesTv = (TextView) dialog.findViewById(R.id.yesTv);
        TextView messageTv = (TextView) dialog.findViewById(R.id.messageTv);
        messageTv.setText(R.string.logout_msg);
        yesTv.setText(R.string.yes);
        noTv.setText(R.string.no);

        noTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yesTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtils.getInstance().clearSharedPref();
                goToActivity(LoginActivity.class);
                finishAffinity();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

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
        // loadFragment(new HomeFragment());
        bottom_navigation_view_linear.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case 0:
                        if (PrefUtils.getInstance().isLogin()) {
                            iv_logout.setVisibility(View.VISIBLE);
                        }
                        loadFragment(new HomeFragment());
                        break;
                    case 1:
                        if (PrefUtils.getInstance().isLogin()) {
                            loadFragment(new ProfileFragment());
                            iv_logout.setVisibility(View.VISIBLE);
                        } else
                            goToActivity(LoginActivity.class);
                        break;
                    case 2:
                        if (PrefUtils.getInstance().isLogin()) {
                            iv_logout.setVisibility(View.VISIBLE);
                        }
                        loadFragment(new BlogsFragment());
                        break;
                    case 3:
                        if (PrefUtils.getInstance().isLogin()) {
                            iv_logout.setVisibility(View.VISIBLE);
                        }
                        loadFragment(new ClickAndShareFragment());
                        break;
                    case 4:
                        if (PrefUtils.getInstance().isLogin()) {
                            iv_logout.setVisibility(View.VISIBLE);
                        }
                        loadFragment(new ProductsFragment());
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
                loadFragment(new HomeFragment());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                LocationSingleTon.instance().setLatLng(place.getLatLng());
                setLocation();
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Toast.makeText(this, "Error: " + status.getStatusMessage(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}