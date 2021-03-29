package com.example.login;

import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu_awal extends AppCompatActivity {

    private TextView nama,jenis_id;
    private Button Masuk,Keluar;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String text1,nilai_database,banding_barcode,text2;
    String a1,a2,a3,a4,b1,b2,b3,b4;
    String riwayat_1,riwayat_2,riwayat_3,riwayat_4,riwayat_5,riwayat_6,riwayat_7,riwayat_8,riwayat_9,riwayat_10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_awal);
        setTitle("Parkir Online USU");
        initView();
        text1=getIntent().getStringExtra("DATA_BALIK");
        text2=getIntent().getStringExtra("DATA");
        opsi_menu();
    }

    private void opsi_menu() {
        if(text1.equals("1")){
            nama.setText("Tekan Tombol Keluar utuk Membuka Portal");
            Masuk.setEnabled(false);
            Keluar.setEnabled(true);
        }
        if(text1.equals("2")){
            nama.setText("Tekan Tombol Masuk utuk Membuka Portal");
            Masuk.setEnabled(true);
            Keluar.setEnabled(false);
        }

        Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Lokasi_parkir.class);
                intent.putExtra("DATA_AWAL", text2);
                intent.putExtra("riwayat1x", riwayat_1);
                intent.putExtra("riwayat2x", riwayat_2);
                intent.putExtra("riwayat3x", riwayat_3);
                intent.putExtra("riwayat4x", riwayat_4);
                intent.putExtra("riwayat5x", riwayat_5);
                intent.putExtra("riwayat6x", riwayat_6);
                intent.putExtra("riwayat7x", riwayat_7);
                intent.putExtra("riwayat8x", riwayat_8);
                intent.putExtra("riwayat9x", riwayat_9);
                intent.putExtra("riwayat10x", riwayat_10);
                startActivity(intent);
                finish();
            }
        });
        Keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                proses_keluar();
            }
        });


    }


    private void proses_keluar(){
        Intent intent=new Intent(getApplicationContext(),Scan_Parkir.class);
        intent.putExtra("DATABASE_A1", a1);
        intent.putExtra("DATABASE_A2", a2);
        intent.putExtra("DATABASE_A3", a3);
        intent.putExtra("DATABASE_A4", a4);
        intent.putExtra("DATABASE_B1", b1);
        intent.putExtra("DATABASE_B2", b2);
        intent.putExtra("DATABASE_B3", b3);
        intent.putExtra("DATABASE_B4", b4);
        intent.putExtra("PARKIR", nilai_database);
        intent.putExtra("DATA_KELUAR", "1");
        intent.putExtra("DATA_BARCODE", banding_barcode);
        intent.putExtra("riwayat1x", riwayat_1);
        intent.putExtra("riwayat2x", riwayat_2);
        intent.putExtra("riwayat3x", riwayat_3);
        intent.putExtra("riwayat4x", riwayat_4);
        intent.putExtra("riwayat5x", riwayat_5);
        intent.putExtra("riwayat6x", riwayat_6);
        intent.putExtra("riwayat7x", riwayat_7);
        intent.putExtra("riwayat8x", riwayat_8);
        intent.putExtra("riwayat9x", riwayat_9);
        intent.putExtra("riwayat10x", riwayat_10);
        startActivity(intent);
        finish();
    }


    private void initView() {
        nama = findViewById(R.id.textView2);
        Masuk = findViewById(R.id.btn_masuk);
        Keluar = findViewById(R.id.btn_keluar);
        auth = FirebaseAuth.getInstance();
        a1=getIntent().getStringExtra("DATABASE_A1_KIRIM");
        a2=getIntent().getStringExtra("DATABASE_A2_KRIRM");
        a3=getIntent().getStringExtra("DATABASE_A3_KIRIM");
        a4=getIntent().getStringExtra("DATABASE_A4_KIRIM");
        b1=getIntent().getStringExtra("DATABASE_B1_KIRIM");
        b2=getIntent().getStringExtra("DATABASE_B2_KIRIM");
        b3=getIntent().getStringExtra("DATABASE_B3_KIRIM");
        b4=getIntent().getStringExtra("DATABASE_B4_KIRIM");
        nilai_database=getIntent().getStringExtra("PARKIR_KIRIM");
        banding_barcode=getIntent().getStringExtra("DATA_BARCODE_KIRIM");

        riwayat_1=getIntent().getStringExtra("riwayat1");
        riwayat_2=getIntent().getStringExtra("riwayat2");
        riwayat_3=getIntent().getStringExtra("riwayat3");
        riwayat_4=getIntent().getStringExtra("riwayat4");
        riwayat_5=getIntent().getStringExtra("riwayat5");
        riwayat_6=getIntent().getStringExtra("riwayat6");
        riwayat_7=getIntent().getStringExtra("riwayat7");
        riwayat_8=getIntent().getStringExtra("riwayat8");
        riwayat_9=getIntent().getStringExtra("riwayat9");
        riwayat_10=getIntent().getStringExtra("riwayat10");
    }

}
