package com.kreasikode.samitex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class KonfirmasiActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSimpanKonfirmasi;
    private ImageView btnBackScanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        btnSimpanKonfirmasi = findViewById(R.id.btn_simpan);
        btnSimpanKonfirmasi.setOnClickListener(this);

        btnBackScanData = findViewById(R.id.back_icon);
        btnBackScanData.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_simpan:
                Toast.makeText(KonfirmasiActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                break;

            case R.id.back_icon:
                Intent backDashboardIntent = new Intent(KonfirmasiActivity.this, ScanDataActivity.class);
                backDashboardIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backDashboardIntent);
                break;
        }

    }
}
