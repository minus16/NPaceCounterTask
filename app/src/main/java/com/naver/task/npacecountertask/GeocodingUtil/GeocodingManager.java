package com.naver.task.npacecountertask.GeocodingUtil;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by minus on 2016-09-23.
 */

public class GeocodingManager {

    final private String URL_STRING = "https://openapi.naver.com/v1/map/reversegeocode";

    public GeocodingManager()
    {

    }

    public String getAddress() throws IOException, JSONException
    {
        //get current position


        //reverse geocoding via Naver api
        HttpURLConnection urlConnection = null;
        String url_str = URL_STRING+"?encoding=utf-8&coord=latlng&output=json&query=127.1052133,37.3595316";
        URL url = new URL(url_str);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        //urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("X-Naver-Client-Id", "h2FnzYPCrc7WKMwWBXUW");
        urlConnection.setRequestProperty("X-Naver-Client-Secret", "2cN1Gjg9Qe");

        //urlConnection.setReadTimeout(10000);
        //urlConnection.setConnectTimeout(15000);
        //urlConnection.setDoOutput(true);
        //urlConnection.connect();

        //check 200 201 skip..
        int resCode = urlConnection.getResponseCode();

        //InputStream is = urlConnection.getInputStream();

        BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        char[] buffer = new char[1024];

        String jsonString = new String();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();

        Log.d("minus", "JSON: " + jsonString);

        return jsonString;

    }

}
