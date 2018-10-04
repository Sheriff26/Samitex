package com.kreasikode.samitex;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
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

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScanDataActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBackDashboard;
    private EditText etTanggal;
    private EditText etShift;
    private EditText etGroup;
    private EditText etOperator;
    private EditText etMesin;
    private EditText etCounter;
    private EditText etCatatan;
    private ImageView imgScanner;
    private ImageView tampilFoto;
    private ImageButton btnTakePicture;
    private Button btnSimpanData;

    static final int REQUEST_IMAGE_CAPTURE = 26;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_data);

        btnBackDashboard = findViewById(R.id.back_icon);
        btnBackDashboard.setOnClickListener(this);

        etTanggal = findViewById(R.id.et_tanggal);
        etTanggal.setOnClickListener(this);
        etShift = findViewById(R.id.et_shift);
        etGroup = findViewById(R.id.et_group);
        etOperator = findViewById(R.id.et_operator);
        etMesin = findViewById(R.id.et_mesin);
        etCounter = findViewById(R.id.et_jumlah_counter);
        etCatatan = findViewById(R.id.et_catatan);

        imgScanner = findViewById(R.id.img_scanner);
        imgScanner.setOnClickListener(this);
        btnTakePicture = findViewById(R.id.img_btn_take_photo);
        btnTakePicture.setOnClickListener(this);
        tampilFoto = findViewById(R.id.tampil_foto);

        btnSimpanData = findViewById(R.id.btn_simpan_data);
        btnSimpanData.setOnClickListener(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            tampilFoto.setImageBitmap(imageBitmap);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG,50,bs);

        }

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);
            return  dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){

            EditText etTanggal = (EditText) getActivity().findViewById(R.id.et_tanggal);

            // Create a Date variable/object with user chosen date
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            Date chosenDate = cal.getTime();

            // Format the date using style full
            DateFormat df_full = DateFormat.getDateInstance(DateFormat.FULL);
            String df_full_str = df_full.format(chosenDate);
            // Display the formatted date
            etTanggal.setText(df_full_str);
        }
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

            case R.id.back_icon:
                Intent backDashboardIntent = new Intent(ScanDataActivity.this, DashboardActivity.class);
                backDashboardIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backDashboardIntent);
                break;

            case R.id.img_btn_take_photo:
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
                }
                break;

            case R.id.et_tanggal:
                DialogFragment dFragment = new DatePickerFragment();
                dFragment.show(getFragmentManager(), "Date Picker");
                break;

            case R.id.btn_simpan_data:
                String Tanggal = etTanggal.getText().toString();
                String Shift = etShift.getText().toString();
                String Group = etGroup.getText().toString();
                String Operator = etOperator.getText().toString();
                String Mesin = etMesin.getText().toString();
                String Counter = etCounter.getText().toString();
                String Catatan = etCatatan.getText().toString();

                Intent simpanDataIntent = new Intent(ScanDataActivity.this, KonfirmasiActivity.class);
                simpanDataIntent.putExtra("Tanggal", Tanggal);
                simpanDataIntent.putExtra("Shift", Shift);
                simpanDataIntent.putExtra("Group", Group);
                simpanDataIntent.putExtra("Operator", Operator);
                simpanDataIntent.putExtra("Mesin", Mesin);
                simpanDataIntent.putExtra("Counter", Counter);
                simpanDataIntent.putExtra("Catatan", Catatan);

                startActivity(simpanDataIntent);
                break;
        }
    }

}
