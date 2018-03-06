package com.example.android.bookings;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoInternetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
    }
    protected void onStart(){
        super.onStart();

        if(isNetworkAvailable())
        {
            Intent i = new Intent(NoInternetActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    protected void onRestart(){
        super.onRestart();

        if(isNetworkAvailable())
        {
            Intent i = new Intent(NoInternetActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    protected void onResume(){
        super.onResume();

        if(isNetworkAvailable())
        {
            Intent i = new Intent(NoInternetActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
