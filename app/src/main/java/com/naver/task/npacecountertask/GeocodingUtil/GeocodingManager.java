package com.naver.task.npacecountertask.GeocodingUtil;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by minus on 2016-09-23.
 */

public class GeocodingManager implements LocationListener {

    private Context mContext = null;

    final private String URL_STRING = "https://openapi.naver.com/v1/map/reversegeocode";
    final private String CLIENT_ID = "h2FnzYPCrc7WKMwWBXUW";
    final private String CLIENT_SECRET = "2cN1Gjg9Qe";


    //Location
    Location location;
    double lat; // 위도
    double lon; // 경도
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean isGetLocation = false;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; //10m
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; //1min

    private LocationManager locationManager;



    public GeocodingManager(Context context)
    {
        this.mContext = context;
    }

    public String getAddress() throws IOException, JSONException
    {
        //get current position
        Location loc = getLocation();

        //reverse geocoding via Naver web api
        HttpURLConnection urlConnection = null;
        //String url_str = URL_STRING+"?encoding=utf-8&coord=latlng&output=json&query=127.1052133,37.3595316";
        String url_str = URL_STRING+"?encoding=utf-8&coord=latlng&output=json&query="+lon+","+lat;
        URL url = new URL(url_str);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
        urlConnection.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);

        //check 200 201 skip..
        int resCode = urlConnection.getResponseCode();

        //InputStream is = urlConnection.getInputStream();

        BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        char[] buffer = new char[1024];


        String jsonString = new String();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        jsonString = sb.toString();

        String address = "";

        JSONObject JObject = new JSONObject(jsonString);
        JSONArray jarray2 = JObject.getJSONObject("result").getJSONArray("items");
        address = jarray2.getJSONObject(0).getString("address");

        Log.d("minus", "JSON: " + jsonString);

        return address;

    }



    //Location Listners
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }


    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    //
    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // GPS 정보 가져오기
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // 현재 네트워크 상태 값 알아오기
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // GPS 와 네트워크사용이 가능하지 않을때 소스 구현
            } else {
                this.isGetLocation = true;
                // 네트워크 정보로 부터 위치값 가져오기
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this,  Looper.getMainLooper());

                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            // 위도 경도 저장
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                    }
                }

                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this, Looper.getMainLooper());
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                lat = location.getLatitude();
                                lon = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(this);
        }
    }
    public double getLatitude(){
        if(location != null){
            lat = location.getLatitude();
        }
        return lat;
    }
    public double getLongitude(){
        if(location != null){
            lon = location.getLongitude();
        }
        return lon;
    }

}
