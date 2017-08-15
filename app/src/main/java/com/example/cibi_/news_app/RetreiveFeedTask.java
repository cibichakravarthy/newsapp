package com.example.cibi_.news_app;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static java.security.AccessController.getContext;

/**
 * Created by cibi_ on 7/30/2017.
 */

class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

    Context c1;
    private Exception exception;

    protected void onPreExecute() {

    }

    protected String doInBackground(Void... urls) {
        // Do some validation here

        URL url = null;
        try {
            url = new URL("https://newsapi.org/v1/articles?source=the-new-york-times&apikey=aa9b8d4e63224850bd34ceec5d4c7dcb");
        } catch (MalformedURLException e1) {
            //e.printStackTrace();

        }
        InputStream in = null;
        try {
            in = url.openStream();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        if(in != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");

                }
                return stringBuilder.toString();
            } catch (IOException e) {
                //e.printStackTrace();
            }

        }
        return "ERROR";
        }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
    }
}
