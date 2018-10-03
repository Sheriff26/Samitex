package com.kreasikode.samitex;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
public class CountListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_list);

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentTanggal(),"Tanggal");
        adapter.AddFragment(new FragmentMesin(),"Mesin");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        btnBack = findViewById(R.id.back_icon);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_icon:
                Intent dashboardIntent = new Intent(CountListActivity.this, DashboardActivity.class);
                dashboardIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(dashboardIntent);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        Intent dashboardIntent = new Intent(CountListActivity.this, DashboardActivity.class);
        dashboardIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(dashboardIntent);
    }
}
