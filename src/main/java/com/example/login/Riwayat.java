package com.example.login;



import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

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

public class Riwayat extends AppCompatActivity {
    private ImageButton tampilkan,portal;
    private TextView dateTimeDisplay,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date,data_akun;

    String riwayat_1,riwayat_2,riwayat_3,riwayat_4,riwayat_5,riwayat_6,riwayat_7,riwayat_8,riwayat_9,riwayat_10;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private final long startTime = 30 * 1000;
    private final long interval = 1 * 1000;
    private FirebaseAuth auth;
    private int waktu_loading=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        setTitle("Riwayat Parkir");
        mFirebaseInstance = FirebaseDatabase.getInstance();
        inisialisasi();
        //countDownTimer = new MyCountDownTimer(startTime, interval);

        dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        date = dateFormat.format(calendar.getTime());

        opsi_menu();

    }




    private void opsi_menu() {
        portal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });







        tampilkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r1.setText(riwayat_1);
                r2.setText(riwayat_2);
                r3.setText(riwayat_3);
                r4.setText(riwayat_4);
                r5.setText(riwayat_5);
                r6.setText(riwayat_6);
                r7.setText(riwayat_7);
                r8.setText(riwayat_8);
                r9.setText(riwayat_9);
                r10.setText(riwayat_10);
                dateTimeDisplay.setText(date);
            }
        });


    }



    private void inisialisasi() {
        portal = findViewById(R.id.portal);
        tampilkan = findViewById(R.id.tampilkan);
        r1 = findViewById(R.id.rectangle_1);
        r2 = findViewById(R.id.rectangle_2);
        r3 = findViewById(R.id.rectangle_3);
        r4 = findViewById(R.id.rectangle_4);
        r5 = findViewById(R.id.rectangle_5);
        r6 = findViewById(R.id.rectangle_6);
        r7 = findViewById(R.id.rectangle_7);
        r8 = findViewById(R.id.rectangle_8);
        r9 = findViewById(R.id.rectangle_9);
        r10 = findViewById(R.id.rectangle_10);
        dateTimeDisplay = findViewById(R.id.waktu);
        calendar = Calendar.getInstance();
        data_akun=getIntent().getStringExtra("DATA");
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



    private void scan_database(){
        mFirebaseDatabase = mFirebaseInstance.getReference("User");
        mFirebaseDatabase.child(data_akun).addValueEventListener(new ValueEventListener(){
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
        }
    }


}
