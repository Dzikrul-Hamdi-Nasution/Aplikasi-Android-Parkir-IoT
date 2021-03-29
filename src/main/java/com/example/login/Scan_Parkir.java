package com.example.login;

import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.content.DialogInterface;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import info.vividcode.android.zxing.CaptureActivity;
import info.vividcode.android.zxing.CaptureActivityIntents;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Scan_Parkir extends AppCompatActivity {


    private ImageButton portal,button2,portal2;
    String hasil,nilai_database,gabung,lompat,DATA_KELUAR,data_akun;
    String a1,a2,a3,a4,b1,b2,b3,b4,date;
    String riwayat_1,riwayat_2,riwayat_3,riwayat_4,riwayat_5,riwayat_6,riwayat_7,riwayat_8,riwayat_9,riwayat_10;
    String paket_1,paket_2,paket_3,paket_4,paket_5,paket_6,paket_7,paket_8,paket_9,paket_10;
    private TextView textView4,imageView3,imageView4,imageView5,riwayat,parkir,scan_qr_cod;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String UserId,banding,banding_barcode;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scan__parkir);
        setTitle("Scan Barcode");
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        //dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        //date = dateFormat.format(calendar.getTime());
        inisialiasai();
        //textView4.setText(riwayat_1);


        //portal.setVisibility(View.INVISIBLE);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat intent baru untuk memanggil CaptureActivity bawaan ZXing
                Intent captureIntent = new Intent(Scan_Parkir.this, CaptureActivity.class);
                // Kemudian kita mengeset pesan yang akan ditampilkan ke user saat menjalankan QRCode scanning
                CaptureActivityIntents.setPromptMessage(captureIntent, "Barcode scanning...");
                // Melakukan startActivityForResult, untuk menangkap balikan hasil dari QR Code scanning
                startActivityForResult(captureIntent, 0);
            }
        });

        portal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DATA_KELUAR.equals("2")){
                    proses_masuk();
                }if(DATA_KELUAR.equals("1")){
                    proses_keluar();
                }

            }
        });

        portal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Scan_Parkir.this, Riwayat.class));
                Intent intent=new Intent(Scan_Parkir.this,Riwayat.class);
                intent.putExtra("DATA", data_akun);
                intent.putExtra("riwayat1", riwayat_1);
                intent.putExtra("riwayat2", riwayat_2);
                intent.putExtra("riwayat3", riwayat_3);
                intent.putExtra("riwayat4", riwayat_4);
                intent.putExtra("riwayat5", riwayat_5);
                intent.putExtra("riwayat6", riwayat_6);
                intent.putExtra("riwayat7", riwayat_7);
                intent.putExtra("riwayat8", riwayat_8);
                intent.putExtra("riwayat9", riwayat_9);
                intent.putExtra("riwayat10", riwayat_10);
                startActivity(intent);
                finish();
            }
        });


        portal.setVisibility(View.INVISIBLE);
        portal2.setVisibility(View.INVISIBLE);

        imageView5.setVisibility(View.INVISIBLE);
        imageView4.setVisibility(View.INVISIBLE);

        parkir.setVisibility(View.INVISIBLE);
        riwayat.setVisibility(View.INVISIBLE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Scan_Parkir.RESULT_OK && data != null) {
                String value = data.getStringExtra("SCAN_RESULT");
                //textView4.setText(value);
                if(value.equals(banding_barcode)){
                    //startActivity(new Intent(Scan_Parkir.this, Menu_awal.class));
                    portal.setVisibility(View.VISIBLE);
                    imageView5.setVisibility(View.VISIBLE);
                    parkir.setVisibility(View.VISIBLE);

                    button2.setVisibility(View.INVISIBLE);
                    imageView3.setVisibility(View.INVISIBLE);
                    scan_qr_cod.setVisibility(View.INVISIBLE);
                    ShowDialog();
                }
            } else if (resultCode == Scan_Parkir.RESULT_CANCELED) {
                textView4.setText("Scanning Gagal, mohon coba lagi.");
            }
        } else {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void onBackPressed() {

    }







    private void ShowDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Notifikasi Sistem")
                .setMessage("Silhakan menekan tombol buka Portal")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        refresh();
                        portal.setVisibility(View.VISIBLE);
                        imageView5.setVisibility(View.VISIBLE);
                        parkir.setVisibility(View.VISIBLE);
                        //startActivity(new Intent(Scan_Parkir.this, Menu_awal.class));

                    }
                });

        alert.show();
    }

    private void proses_keluar(){
        if(nilai_database.equals("A1")){
            gabung="0"+a2+a3+a4+b1+b2+b3+b4;
        }
        if(nilai_database.equals("A2")){
            gabung=a1+"0"+a3+a4+b1+b2+b3+b4;
        }
        if(nilai_database.equals("A3")){
            gabung=a1+a2+"0"+a4+b1+b2+b3+b4;
        }
        if(nilai_database.equals("A4")){
            gabung=a1+a2+a3+"0"+b1+b2+b3+b4;
        }
        if(nilai_database.equals("B1")){
            gabung=a1+a2+a3+a4+"0"+b2+b3+b4;
        }
        if(nilai_database.equals("B2")){
            gabung=a1+a2+a3+a4+b1+"0"+b3+b4;
        }
        if(nilai_database.equals("B3")){
            gabung=a1+a2+a3+a4+b1+b2+"0"+b4;
        }
        if(nilai_database.equals("B4")){
            gabung=a1+a2+a3+a4+b1+b2+b3+"0";
        }
        mFirebaseDatabase = mFirebaseInstance.getReference("data parkir");
        mFirebaseDatabase.child("nilai").setValue(gabung);



        ShowDialog2();
        button2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
        scan_qr_cod.setVisibility(View.INVISIBLE);

        portal.setVisibility(View.INVISIBLE);
        portal.setVisibility(View.INVISIBLE);
        imageView5.setVisibility(View.INVISIBLE);
        parkir.setVisibility(View.INVISIBLE);

        portal2.setVisibility(View.VISIBLE);
        imageView4.setVisibility(View.VISIBLE);
        riwayat.setVisibility(View.VISIBLE);


    }



    private void proses_masuk(){
        Intent intent=new Intent(getApplicationContext(),Menu_awal.class);
        intent.putExtra("DATABASE_A1_KIRIM", a1);
        intent.putExtra("DATABASE_A2_KRIRM", a2);
        intent.putExtra("DATABASE_A3_KIRIM", a3);
        intent.putExtra("DATABASE_A4_KIRIM", a4);
        intent.putExtra("DATABASE_B1_KIRIM", b1);
        intent.putExtra("DATABASE_B2_KIRIM", b2);
        intent.putExtra("DATABASE_B3_KIRIM", b3);
        intent.putExtra("DATABASE_B4_KIRIM", b4);
        intent.putExtra("PARKIR_KIRIM", nilai_database);
        intent.putExtra("DATA_BALIK", "1");
        intent.putExtra("DATA_BARCODE_KIRIM", banding_barcode);

        intent.putExtra("riwayat1", paket_1);
        intent.putExtra("riwayat2", paket_2);
        intent.putExtra("riwayat3", paket_3);
        intent.putExtra("riwayat4", paket_4);
        intent.putExtra("riwayat5", paket_5);
        intent.putExtra("riwayat6", paket_6);
        intent.putExtra("riwayat7", paket_7);
        intent.putExtra("riwayat8", paket_8);
        intent.putExtra("riwayat9", paket_9);
        intent.putExtra("riwayat10", paket_10);
        startActivity(intent);
        finish();
    }



private void inisialiasai(){

    textView4 = findViewById(R.id.textView4);
    imageView3 = findViewById(R.id.imageView3);
    imageView4 = findViewById(R.id.imageView4);
    imageView5 = findViewById(R.id.imageView5);
    riwayat = findViewById(R.id.riwayat);
    parkir = findViewById(R.id.parkir);
    scan_qr_cod = findViewById(R.id.scan_qr_cod);
    button2 = findViewById(R.id.button2);
    portal = findViewById(R.id.portal);
    portal2 = findViewById(R.id.portal2);
    a1=getIntent().getStringExtra("DATABASE_A1");
    a2=getIntent().getStringExtra("DATABASE_A2");
    a3=getIntent().getStringExtra("DATABASE_A3");
    a4=getIntent().getStringExtra("DATABASE_A4");
    b1=getIntent().getStringExtra("DATABASE_B1");
    b2=getIntent().getStringExtra("DATABASE_B2");
    b3=getIntent().getStringExtra("DATABASE_B3");
    b4=getIntent().getStringExtra("DATABASE_B4");
    nilai_database=getIntent().getStringExtra("PARKIR");
    banding_barcode=getIntent().getStringExtra("DATA_BARCODE");
    DATA_KELUAR=getIntent().getStringExtra("DATA_KELUAR");
    data_akun=getIntent().getStringExtra("data_akun");

    riwayat_1=getIntent().getStringExtra("riwayat1x");
    riwayat_2=getIntent().getStringExtra("riwayat2x");
    riwayat_3=getIntent().getStringExtra("riwayat3x");
    riwayat_4=getIntent().getStringExtra("riwayat4x");
    riwayat_5=getIntent().getStringExtra("riwayat5x");
    riwayat_6=getIntent().getStringExtra("riwayat6x");
    riwayat_7=getIntent().getStringExtra("riwayat7x");
    riwayat_8=getIntent().getStringExtra("riwayat8x");
    riwayat_9=getIntent().getStringExtra("riwayat9x");
    riwayat_10=getIntent().getStringExtra("riwayat10x");

    paket_1=getIntent().getStringExtra("paket_1");
    paket_2=getIntent().getStringExtra("paket_2");
    paket_3=getIntent().getStringExtra("paket_3");
    paket_4=getIntent().getStringExtra("paket_4");
    paket_5=getIntent().getStringExtra("paket_5");
    paket_6=getIntent().getStringExtra("paket_6");
    paket_7=getIntent().getStringExtra("paket_7");
    paket_8=getIntent().getStringExtra("paket_8");
    paket_9=getIntent().getStringExtra("paket_9");
    paket_10=getIntent().getStringExtra("paket_10");

}


    private void refresh() {
        if(nilai_database.equals("A1")){
            gabung="1"+a2+a3+a4+b1+b2+b3+b4;
        }
        if(nilai_database.equals("A2")){
            gabung=a1+"2"+a3+a4+b1+b2+b3+b4;
        }
        if(nilai_database.equals("A3")){
            gabung=a1+a2+"3"+a4+b1+b2+b3+b4;
        }
        if(nilai_database.equals("A4")){
            gabung=a1+a2+a3+"4"+b1+b2+b3+b4;
        }
        if(nilai_database.equals("B1")){
            gabung=a1+a2+a3+a4+"5"+b2+b3+b4;
        }
        if(nilai_database.equals("B2")){
            gabung=a1+a2+a3+a4+b1+"6"+b3+b4;
        }
        if(nilai_database.equals("B3")){
            gabung=a1+a2+a3+a4+b1+b2+"7"+b4;
        }
        if(nilai_database.equals("B4")){
            gabung=a1+a2+a3+a4+b1+b2+b3+"8";
        }
        mFirebaseDatabase = mFirebaseInstance.getReference("data parkir");
        mFirebaseDatabase.child("nilai").setValue(gabung);
    }


    private void ShowDialog2(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Notifikasi Sistem")
                .setMessage("Portal Telah Terbuka")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        alert.show();

    }

}