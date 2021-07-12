package com.example.hclflim.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hclflim.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isOnline();
    }

    private void isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni =cm.getActiveNetworkInfo();
        if(ni!=null&& ni.isConnected()) {
          startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        else {
            Toast.makeText(this, "Hiện tại bạn không có kết nối", Toast.LENGTH_SHORT).show();
        }
    }
}