package com.kreasikode.samitex;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnScan;
    private Button btnCounter;
    private Toolbar topToolbar;
    private DrawerLayout mdrawerlayout;
    private NavigationView navigationView;
    private ImageView imgMenu;
    final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imgMenu = findViewById(R.id.menu_icon);
        mdrawerlayout = findViewById(R.id.drawer_layout);
        topToolbar = findViewById(R.id.toolbar_layout);
        navigationView = findViewById(R.id.nav_view);

        btnScan = findViewById(R.id.btn_scan);
        btnScan.setOnClickListener(this);
        btnCounter = findViewById(R.id.btn_counter);
        btnCounter.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_scan:
                Intent loginIntent = new Intent(DashboardActivity.this, ScanDataActivity.class);
                startActivity(loginIntent);
                break;

            case R.id.btn_counter:
                Intent counterIntent = new Intent(DashboardActivity.this, CountListActivity.class);
                startActivity(counterIntent);
                break;

        }
    }

    boolean twice = false;
    @Override
    public void onBackPressed() {

        Log.d(TAG, "Click");

        if (twice == true){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }

        twice = true;
        Log.d(TAG, "Twice: " + twice);

        Toast.makeText(DashboardActivity.this,"Klik Tombol Kembali Dua Kali Untuk Keluar Aplikasi", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
                Log.d(TAG, "Twice: " + twice);
            }
        }, 3000);
    }

}
