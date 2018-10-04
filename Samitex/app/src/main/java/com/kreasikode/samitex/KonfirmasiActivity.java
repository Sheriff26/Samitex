package com.kreasikode.samitex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class KonfirmasiActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSimpanKonfirmasi;
    private ImageView btnBackScanData;
    private TextView tvMesin;
    private TextView tvTanggalShift;
    private TextView tvGroupOperator;
    private TextView tvCounter;
    private TextView tvCatatan;
    private ImageView konfirmFoto;

    private String Tanggal;
    private String Shift;
    private String Group;
    private String Operator;
    private String Mesin;
    private String Counter;
    private String Catatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        btnBackScanData = findViewById(R.id.back_icon);
        btnBackScanData.setOnClickListener(this);
        tvMesin = findViewById(R.id.tv_kode_mesin);
        tvTanggalShift = findViewById(R.id.tv_konfirmasi_tanggal);
        tvGroupOperator = findViewById(R.id.tv_nama_operator);
        tvCounter = findViewById(R.id.tv_jumlah_couter);
        tvCatatan = findViewById(R.id.tv_konfirmasi_catatan);
        konfirmFoto = findViewById(R.id.img_bukti_photo);

        btnSimpanKonfirmasi = findViewById(R.id.btn_simpan);
        btnSimpanKonfirmasi.setOnClickListener(this);

        Tanggal = getIntent().getExtras().getString("Tanggal");
        Shift = getIntent().getExtras().getString("Shift");
        Group = getIntent().getExtras().getString("Group");
        Operator = getIntent().getExtras().getString("Operator");
        Mesin = getIntent().getExtras().getString("Mesin");
        Counter = getIntent().getExtras().getString("Counter");
        Catatan = getIntent().getExtras().getString("Catatan");

        tvMesin.setText(Mesin);
        tvTanggalShift.setText(Tanggal+ " / " +Shift);
        tvGroupOperator.setText(Group+ " - " +Operator);
        tvCounter.setText(Counter);
        tvCatatan.setText(Catatan);

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
