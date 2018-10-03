package com.kreasikode.samitex;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.zxing.integration.android.IntentIntegrator;

import java.text.DateFormat;
import java.util.Calendar;

public class ScanDataActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgScanner;
    private Button btnSimpanData;
    private ImageView btnBackDashboard;
    private ImageView tampilFoto;
    private EditText etTanggal;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ImageButton btnTakePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_data);

        etTanggal = findViewById(R.id.et_tanggal);
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ScanDataActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + " / " + month + " / " + year;
                etTanggal.setText(date);
            }
        };

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        imgScanner = findViewById(R.id.img_scanner);
        imgScanner.setOnClickListener(this);
        btnSimpanData = findViewById(R.id.btn_simpan_data);
        btnSimpanData.setOnClickListener(this);
        btnBackDashboard = findViewById(R.id.back_icon);
        btnBackDashboard.setOnClickListener(this);
        btnTakePicture = findViewById(R.id.img_btn_take_photo);
        btnTakePicture.setOnClickListener(this);

        tampilFoto = findViewById(R.id.tampil_foto);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_scanner:
                IntentIntegrator scannerIntegrator = new IntentIntegrator(ScanDataActivity.this);
                scannerIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scannerIntegrator.setPrompt("Scanner");
                scannerIntegrator.setCameraId(0);
                scannerIntegrator.setBeepEnabled(true);
                scannerIntegrator.setBarcodeImageEnabled(true);
                scannerIntegrator.initiateScan();
                break;

            case R.id.btn_simpan_data:
                Intent simpanDataIntent = new Intent(ScanDataActivity.this, KonfirmasiActivity.class);
                startActivity(simpanDataIntent);
                break;

            case R.id.back_icon:
                Intent backDashboardIntent = new Intent(ScanDataActivity.this, DashboardActivity.class);
                backDashboardIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backDashboardIntent);
                break;

            case R.id.img_btn_take_photo:
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePhotoIntent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    tampilFoto.setImageBitmap(bitmap);
                    break;
            }
        }

    }
}
