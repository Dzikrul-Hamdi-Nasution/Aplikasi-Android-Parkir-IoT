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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Lokasi_parkir extends AppCompatActivity {

    private Button pilihan;
    private TextView textView3,A1,A2,A3,A4,B1,B2,B3,B4,textView6;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String text1,database_parkir,nilai_database,yang_dipilih;
    String riwayat_1,riwayat_2,riwayat_3,riwayat_4,riwayat_5,riwayat_6,riwayat_7,riwayat_8,riwayat_9,riwayat_10;
    String a1,a2,a3,a4,b1,b2,b3,b4,lompat,banding_barcode;

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private final long startTime = 30 * 1000;
    private final long interval = 1 * 1000;
    private int waktu_loading=2500;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_parkir);
        setTitle("Denah Parkir");
        initView();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        date = dateFormat.format(calendar.getTime());
        mFirebaseInstance = FirebaseDatabase.getInstance();
        countDownTimer = new MyCountDownTimer(startTime, interval);
        scan_database();
        //opsi_menu();
        countDownTimer.start();
        timerHasStarted = true;
        pilihan.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                opsi_menu();

            }
        },waktu_loading);
    }

    private void opsi_menu() {


        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference("User");
                mFirebaseDatabase.child(text1).child("Kode Parkir").child("parkir").setValue("B1");
                mFirebaseDatabase.child(text1).child("Kode login").child("nilai").setValue("0");
                nilai_database="B1";
                yang_dipilih="B1";
                pilihan.setText("B1");
                pilihan.setVisibility(View.VISIBLE);
                //B1.setVisibility(View.VISIBLE);
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference("User");
                mFirebaseDatabase.child(text1).child("Kode Parkir").child("parkir").setValue("B2");
                mFirebaseDatabase.child(text1).child("Kode login").child("nilai").setValue("0");
                nilai_database="B2";
                yang_dipilih="B2";
                pilihan.setText("B2");
                pilihan.setVisibility(View.VISIBLE);
                //B2.setVisibility(View.VISIBLE);
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference("User");
                mFirebaseDatabase.child(text1).child("Kode Parkir").child("parkir").setValue("B3");
                mFirebaseDatabase.child(text1).child("Kode login").child("nilai").setValue("0");
                nilai_database="B3";
                yang_dipilih="B3";
                pilihan.setText("B3");
                pilihan.setVisibility(View.VISIBLE);
                //B3.setVisibility(View.VISIBLE);
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference("User");
                mFirebaseDatabase.child(text1).child("Kode Parkir").child("parkir").setValue("B4");
                mFirebaseDatabase.child(text1).child("Kode login").child("nilai").setValue("0");
                nilai_database="B4";
                yang_dipilih="B4";
                pilihan.setText("B4");
                pilihan.setVisibility(View.VISIBLE);
                //B4.setVisibility(View.VISIBLE);
            }
        });
        A1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference("User");
                mFirebaseDatabase.child(text1).child("Kode Parkir").child("parkir").setValue("A1");
                mFirebaseDatabase.child(text1).child("Kode login").child("nilai").setValue("0");
                nilai_database="A1";
                yang_dipilih="A1";
                pilihan.setText("A1");
                pilihan.setVisibility(View.VISIBLE);
                //A1.setVisibility(View.VISIBLE);
            }
        });
        A2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference("User");
                mFirebaseDatabase.child(text1).child("Kode Parkir").child("parkir").setValue("A2");
                mFirebaseDatabase.child(text1).child("Kode login").child("nilai").setValue("0");
                nilai_database="A2";
                yang_dipilih="A2";
                pilihan.setText("A2");
                pilihan.setVisibility(View.VISIBLE);
                //A2.setVisibility(View.VISIBLE);
            }
        });
        A3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference("User");
                mFirebaseDatabase.child(text1).child("Kode Parkir").child("parkir").setValue("A3");
                mFirebaseDatabase.child(text1).child("Kode login").child("nilai").setValue("0");
                nilai_database="A3";
                yang_dipilih="A3";
                pilihan.setText("A3");
                pilihan.setVisibility(View.VISIBLE);
            }
        });
        A4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = mFirebaseInstance.getReference("User");
                mFirebaseDatabase.child(text1).child("Kode Parkir").child("parkir").setValue("A4");
                mFirebaseDatabase.child(text1).child("Kode login").child("nilai").setValue("0");
                nilai_database="A4";
                yang_dipilih="A4";
                pilihan.setText("A4");
                pilihan.setVisibility(View.VISIBLE);
            }
        });

        pilihan.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                simpan_riwayat();
                Intent intent = new Intent(getApplicationContext(), Scan_Parkir.class);
                intent.putExtra("DATABASE_A1", a1);
                intent.putExtra("DATABASE_A2", a2);
                intent.putExtra("DATABASE_A3", a3);
                intent.putExtra("DATABASE_A4", a4);
                intent.putExtra("DATABASE_B1", b1);
                intent.putExtra("DATABASE_B2", b2);
                intent.putExtra("DATABASE_B3", b3);
                intent.putExtra("DATABASE_B4", b4);
                intent.putExtra("PARKIR", nilai_database);
                intent.putExtra("DATA_BARCODE", banding_barcode);
                intent.putExtra("DATA_KELUAR", "2");
                intent.putExtra("data_akun", text1);
                intent.putExtra("paket_1", riwayat_1);
                intent.putExtra("paket_2", riwayat_2);
                intent.putExtra("paket_3", riwayat_3);
                intent.putExtra("paket_4", riwayat_4);
                intent.putExtra("paket_5", riwayat_5);
                intent.putExtra("paket_6", riwayat_6);
                intent.putExtra("paket_7", riwayat_7);
                intent.putExtra("paket_8", riwayat_8);
                intent.putExtra("paket_9", riwayat_9);
                intent.putExtra("paket_10", riwayat_10);
                startActivity(intent);
                finish();

            }
        });



        countDownTimer.start();
        timerHasStarted = true;

        ShowDialog();

    }


    private void simpan_riwayat(){
        mFirebaseDatabase = mFirebaseInstance.getReference("User");
        textView6.setText(riwayat_1);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat1").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        textView6.setText(riwayat_2);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat2").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        textView6.setText(riwayat_3);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat3").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        textView6.setText(riwayat_4);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat4").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        textView6.setText(riwayat_5);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat5").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        textView6.setText(riwayat_6);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat6").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        textView6.setText(riwayat_7);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat7").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        textView6.setText(riwayat_8);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat8").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        textView6.setText(riwayat_9);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat9").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        textView6.setText(riwayat_10);
        if(textView6.getText().toString().equals("empty")){
            mFirebaseDatabase.child(text1).child("riwayat10").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            return;
        }
        else{
            mFirebaseDatabase = mFirebaseInstance.getReference("User");
            mFirebaseDatabase.child(text1).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    riwayat_1 = dataSnapshot.child("riwayat1").getValue(String.class);
                    riwayat_2 = dataSnapshot.child("riwayat2").getValue(String.class);
                    riwayat_3 = dataSnapshot.child("riwayat3").getValue(String.class);
                    riwayat_4 = dataSnapshot.child("riwayat4").getValue(String.class);
                    riwayat_5 = dataSnapshot.child("riwayat5").getValue(String.class);
                    riwayat_6 = dataSnapshot.child("riwayat6").getValue(String.class);
                    riwayat_7 = dataSnapshot.child("riwayat7").getValue(String.class);
                    riwayat_8 = dataSnapshot.child("riwayat8").getValue(String.class);
                    riwayat_9 = dataSnapshot.child("riwayat9").getValue(String.class);
                    riwayat_10 = dataSnapshot.child("riwayat10").getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            mFirebaseDatabase = mFirebaseInstance.getReference("User");
            mFirebaseDatabase.child(text1).child("riwayat1").setValue("Parkir = "+yang_dipilih+"  Waktu = "+date);
            mFirebaseDatabase.child(text1).child("riwayat2").setValue(riwayat_1);
            mFirebaseDatabase.child(text1).child("riwayat3").setValue(riwayat_2);
            mFirebaseDatabase.child(text1).child("riwayat4").setValue(riwayat_3);
            mFirebaseDatabase.child(text1).child("riwayat5").setValue(riwayat_4);
            mFirebaseDatabase.child(text1).child("riwayat6").setValue(riwayat_5);
            mFirebaseDatabase.child(text1).child("riwayat7").setValue(riwayat_6);
            mFirebaseDatabase.child(text1).child("riwayat8").setValue(riwayat_7);
            mFirebaseDatabase.child(text1).child("riwayat9").setValue(riwayat_8);
            mFirebaseDatabase.child(text1).child("riwayat10").setValue(riwayat_9);
            return;

        }



    }










    private void initView() {

        calendar = Calendar.getInstance();
        textView3 = findViewById(R.id.textView3);
        textView6 = findViewById(R.id.textView6);
        pilihan = findViewById(R.id.pilihan);
        A1 = findViewById(R.id.A1);
        A2 = findViewById(R.id.A2);
        A3 = findViewById(R.id.A3);
        A4 = findViewById(R.id.A4);
        B1 = findViewById(R.id.B1);
        B2 = findViewById(R.id.B2);
        B3 = findViewById(R.id.B3);
        B4 = findViewById(R.id.B4);
        text1=getIntent().getStringExtra("DATA_AWAL");
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

    }
    private void scan_database(){
        mFirebaseDatabase = mFirebaseInstance.getReference("data parkir");
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                database_parkir = dataSnapshot.child("nilai").getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void scan_database2(){
        mFirebaseDatabase = mFirebaseInstance.getReference("data barcode");
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                banding_barcode = dataSnapshot.child("nilai").getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //textView3.setText(banding_barcode);
    }


    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {
            countDownTimer.start();
            timerHasStarted = true;
            //ext.setText("Time's up!");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            scan_database();
            scan_database2();
        }
    }




    private void ShowDialog(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Notifikasi Sistem")
                .setMessage("Database Diperbaharui")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        data_awal();
                    }
                });

        alert.show();

    }






    private void data_awal(){
        a1 = database_parkir.substring(0, 1);
        a2 = database_parkir.substring(1, 2);
        a3 = database_parkir.substring(2, 3);
        a4 = database_parkir.substring(3, 4);
        b1 = database_parkir.substring(4, 5);
        b2 = database_parkir.substring(5, 6);
        b3 = database_parkir.substring(6, 7);
        b4 = database_parkir.substring(7, 8);
        if(a1.equals("1")){
            A1.setVisibility(View.INVISIBLE);
        }
        else{
            A1.setVisibility(View.VISIBLE);
        }
        if(a2.equals("2")){
            A2.setVisibility(View.INVISIBLE);
        }
        else{
            A2.setVisibility(View.VISIBLE);
        }
        if(a3.equals("3")){
            A3.setVisibility(View.INVISIBLE);
        }
        else{
            A3.setVisibility(View.VISIBLE);
        }
        if(a4.equals("4")){
            A4.setVisibility(View.INVISIBLE);
        }
        else{
            A4.setVisibility(View.VISIBLE);
        }
        if(b1.equals("5")){
            B1.setVisibility(View.INVISIBLE);
        }
        else{
            B1.setVisibility(View.VISIBLE);
        }
        if(b2.equals("6")){
            B2.setVisibility(View.INVISIBLE);
        }
        else{
            B2.setVisibility(View.VISIBLE);
        }
        if(b3.equals("7")){
            B3.setVisibility(View.INVISIBLE);
        }
        else{
            B3.setVisibility(View.VISIBLE);
        }
        if(b4.equals("8")){
            B4.setVisibility(View.INVISIBLE);
        }
        else{
            B4.setVisibility(View.VISIBLE);
        }
    }

}
