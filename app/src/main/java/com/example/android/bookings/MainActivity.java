package com.example.android.bookings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {
    protected static int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(count == 0)
        {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                Toast.makeText(this, "Using your current location", Toast.LENGTH_SHORT).show();
            }else{
                showGPSDisabledAlertToUser();
            }
            count+=1;
        }
    }

    protected void onStart(){
        super.onStart();

        if(!isNetworkAvailable())
        {
            Intent i = new Intent(MainActivity.this, NoInternetActivity.class);
            startActivity(i);
            finish();
        }
    }

    protected void onResume(){
        super.onResume();

        if(!isNetworkAvailable())
        {
            Intent i = new Intent(MainActivity.this, NoInternetActivity.class);
            startActivity(i);
            finish();
        }
    }

    protected void onPause(){
        super.onPause();
    }

    protected void onStop(){
        super.onStop();
    }

    protected void onRestart(){
        super.onRestart();

        if(!isNetworkAvailable())
        {
            Intent i = new Intent(MainActivity.this, NoInternetActivity.class);
            startActivity(i);
            finish();
        }
    }

    protected void onDestroy(){
        super.onDestroy();
    }

    private void showGPSDisabledAlertToUser() {     //Method to show Alert Dialog Box if GPS is not connected
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Location Service is disabled!")
                .setCancelable(false)
                .setPositiveButton("Settings->Location",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
