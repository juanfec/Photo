package dev.v7.photo.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import dev.v7.photo.R;

public class MapsFragment extends Fragment {

    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng actual = new LatLng(0,0);
    private GoogleMap map;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            map.addMarker(new MarkerOptions().position(actual).title("Tú"));
            map.moveCamera(CameraUpdateFactory.newLatLng(actual));
            map.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getLastLocation();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getLastLocation(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                mFusedLocationClient.getLastLocation()
                        .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    // Logic to handle location object
                                    actual = new LatLng(location.getLatitude(), location.getLongitude());
                                    if(map != null){
                                        map.addMarker(new MarkerOptions().position(actual).title("Tú"));
                                        map.moveCamera(CameraUpdateFactory.newLatLng(actual));
                                        map.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                                    }

                                }
                            }
                        });

            } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.

            }
            else {
                ActivityResultLauncher<String[]> locationPermissionRequest =
                        registerForActivityResult(new ActivityResultContracts
                                        .RequestMultiplePermissions(), result -> {
                                    Boolean fineLocationGranted = result.getOrDefault(
                                            Manifest.permission.ACCESS_FINE_LOCATION, false);
                                    Boolean coarseLocationGranted = result.getOrDefault(
                                            Manifest.permission.ACCESS_COARSE_LOCATION,false);
                                    if (fineLocationGranted != null && fineLocationGranted) {
                                        mFusedLocationClient.getLastLocation()
                                                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                                                    @Override
                                                    public void onSuccess(Location location) {
                                                        // Got last known location. In some rare situations this can be null.
                                                        if (location != null) {
                                                            // Logic to handle location object
                                                            actual = new LatLng(location.getLatitude(), location.getLongitude());
                                                            if(map != null){
                                                                map.addMarker(new MarkerOptions().position(actual).title("Tú"));
                                                                map.moveCamera(CameraUpdateFactory.newLatLng(actual));
                                                                map.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                                                            }
                                                        }
                                                    }
                                                });
                                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                        // Only approximate location access granted.
                                    } else {
                                        // No location access granted.
                                        //Snackbar.make(view,"debes proporcionar permisos",Snackbar.LENGTH_LONG).show();
                                    }
                                }
                        );
                locationPermissionRequest.launch(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                });
            }
        }else {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        // check permission


    }
}