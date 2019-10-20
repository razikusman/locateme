package com.example.locateme;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Locatememap extends FragmentActivity implements OnMapReadyCallback{

        private GoogleMap mMap;
        private static final int requesstlocation =500;
        ArrayList<LatLng> listpoints;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_locatememap);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            listpoints = new ArrayList<>();
        }


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.getUiSettings().setZoomControlsEnabled(true);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requesstlocation);
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    if(listpoints.size()==2){
                        listpoints.clear();
                        mMap.clear();
                    }

                    listpoints.add(latLng);

                    MarkerOptions mrkeroptions = new MarkerOptions();
                    mrkeroptions.position(latLng);

                    if(listpoints.size()==1){
                        mrkeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                    }
                    else{
                        mrkeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }
                    mMap.addMarker(mrkeroptions);

                    if(listpoints.size()==2){
                        String url=getRequsetUrl (listpoints.get(0) , listpoints.get(1));
                    }

                }
            });
        }

        private String getRequsetUrl(LatLng origin, LatLng dest) {
            String str_orig = "origin" + origin.latitude + "," + origin.longitude;
            String str_dest = "destination" + dest.latitude + "," + dest.longitude;
            String sensor = "sensor=false";
            String mode = "mode=driving";
            String parm = str_orig +"&"+str_dest +"&"+sensor +"&"+mode;
            String output = "json";
            String url = "https://maps.googleaips.com/maps/api/direction/"+output+"?"+parm;
            return url;
        }

        private String requestdirection(String reqUrl) throws IOException {
            String respoonseString = "";
            InputStream inputStream =null;
            HttpURLConnection httpURLConnection= null;
            try {
                URL url = new URL(reqUrl);
                httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                while ((line=bufferedReader.readLine()) != null){
                    stringBuffer.append(line);

                }

                respoonseString = stringBuffer.toString();
                bufferedReader.close();
                inputStreamReader.close();

            } catch ( Exception e) {
                e.printStackTrace();
            }finally {
                if (inputStream != null){
                    inputStream .close();
                }
                httpURLConnection.disconnect();
            }
            return respoonseString;
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode){
                case requesstlocation:
                    if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        mMap.setMyLocationEnabled(true);
                    }
                    break;
            }
        }
}
