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

public class Menu_utama extends AppCompatActivity {

    private Button riwayat,parkir,logout;
    private TextView textView3,B1,B2,B3,B4,A1,A2,A3,A4,textView5;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String text1,database_parkir;
    String riwayat_1,riwayat_2,riwayat_3,riwayat_4,riwayat_5,riwayat_6,riwayat_7,riwayat_8,riwayat_9,riwayat_10;

    String a1,a2,a3,a4,b1,b2,b3,b4;

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private final long startTime = 30 * 1000;
    private final long interval = 1 * 1000;
    private FirebaseAuth auth;
    private int waktu_loading=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        initView();
        //scan_database();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        countDownTimer = new Menu_utama.MyCountDownTimer(startTime, interval);
        scan_database();
        Bundle bundle = getIntent().getBundleExtra("emailpass");
        String email = bundle.getString("email");
        auth = FirebaseAuth.getInstance();
        String[] kata = email.split("\\@");
        text1 = kata[0];
       textView3.setText("Selamat Datang, "+text1);
        textView5.setVisibility(View.INVISIBLE);
        countDownTimer.start();
        timerHasStarted = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                opsi_menu();

            }
        },waktu_loading);

    }

    private void opsi_menu() {



        parkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan_riwayat();
                Intent intent=new Intent(getApplicationContext(),Menu_awal.class);
                intent.putExtra("DATA", text1);
                intent.putExtra("DATA_BALIK", "2");
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
            }
        });
        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scan_riwayat();

                Intent intent=new Intent(getApplicationContext(),Riwayat.class);
                intent.putExtra("DATA", text1);
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
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        countDownTimer.start();
        timerHasStarted = true;

        ShowDialog();

    }

    private void initView() {

        textView3 = findViewById(R.id.textView3);
        textView5 = findViewById(R.id.textView5);
        logout = findViewById(R.id.logout);
        riwayat = findViewById(R.id.riwayat);
        parkir = findViewById(R.id.parkir);
        A1 = findViewById(R.id.A1);
        A2 = findViewById(R.id.A2);
        A3 = findViewById(R.id.A3);
        A4 = findViewById(R.id.A4);
        B1 = findViewById(R.id.B1);
        B2 = findViewById(R.id.B2);
        B3 = findViewById(R.id.B3);
        B4 = findViewById(R.id.B4);


    }




    private void scan_riwayat(){
        mFirebaseDatabase = mFirebaseInstance.getReference("User");
        mFirebaseDatabase.child(text1).addValueEventListener(new ValueEventListener(){
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

        textView5.setText(riwayat_1);
        if(textView5.getText().toString().equals("")){
            mFirebaseDatabase = mFirebaseInstance.getReference("User");
            mFirebaseDatabase.child(text1).child("riwayat1").setValue("empty");
            mFirebaseDatabase.child(text1).child("riwayat2").setValue("empty");
            mFirebaseDatabase.child(text1).child("riwayat3").setValue("empty");
            mFirebaseDatabase.child(text1).child("riwayat4").setValue("empty");
            mFirebaseDatabase.child(text1).child("riwayat5").setValue("empty");
            mFirebaseDatabase.child(text1).child("riwayat6").setValue("empty");
            mFirebaseDatabase.child(text1).child("riwayat7").setValue("empty");
            mFirebaseDatabase.child(text1).child("riwayat8").setValue("empty");
            mFirebaseDatabase.child(text1).child("riwayat9").setValue("empty");
            mFirebaseDatabase.child(text1).child("riwayat10").setValue("empty");
            riwayat_10="empty";
            riwayat_9="empty";
            riwayat_8="empty";
            riwayat_7="empty";
            riwayat_6="empty";
            riwayat_5="empty";
            riwayat_4="empty";
            riwayat_3="empty";
            riwayat_2="empty";
            riwayat_1="empty";
        }

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
        mFirebaseDatabase = mFirebaseInstance.getReference("User");
        mFirebaseDatabase.child(text1).addValueEventListener(new ValueEventListener(){
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

        a1 = database_parkir.substring(0, 1);
        a2 = database_parkir.substring(1, 2);
        a3 = database_parkir.substring(2, 3);
        a4 = database_parkir.substring(3, 4);
        b1 = database_parkir.substring(4, 5);
        b2 = database_parkir.substring(5, 6);
        b3 = database_parkir.substring(6, 7);
        b4 = database_parkir.substring(7, 8);
        if(a1.equals("1")){
            A1.setVisibility(View.VISIBLE);
            //A1_text.setVisibility(View.INVISIBLE);
        }
        else{
            A1.setVisibility(View.INVISIBLE);
            //A1_text.setVisibility(View.VISIBLE);
        }
        if(a2.equals("2")){
            A2.setVisibility(View.VISIBLE);
            //A2_text.setVisibility(View.INVISIBLE);
        }
        else{
            A2.setVisibility(View.INVISIBLE);
        }
        if(a3.equals("3")){
            A3.setVisibility(View.VISIBLE);
            //A3_text.setVisibility(View.INVISIBLE);
        }
        else{
            A3.setVisibility(View.INVISIBLE);
            //A3_text.setVisibility(View.VISIBLE);
        }
        if(a4.equals("4")){
            A4.setVisibility(View.VISIBLE);
           // A4_text.setVisibility(View.INVISIBLE);
        }
        else{
            A4.setVisibility(View.INVISIBLE);
            //A4_text.setVisibility(View.VISIBLE);
        }
        if(b1.equals("5")){
            B1.setVisibility(View.VISIBLE);
            //B1_text.setVisibility(View.INVISIBLE);
        }
        else{
            B1.setVisibility(View.INVISIBLE);
            //B1_text.setVisibility(View.VISIBLE);
        }
        if(b2.equals("6")){
            B2.setVisibility(View.VISIBLE);
            //B2_text.setVisibility(View.INVISIBLE);
        }
        else{
            B2.setVisibility(View.INVISIBLE);
            //B2_text.setVisibility(View.VISIBLE);
        }
        if(b3.equals("7")){
            B3.setVisibility(View.VISIBLE);
            //B3_text.setVisibility(View.INVISIBLE);
        }
        else{
            B3.setVisibility(View.INVISIBLE);
            //B3_text.setVisibility(View.VISIBLE);
        }
        if(b4.equals("8")){
            B4.setVisibility(View.VISIBLE);
            //B4_text.setVisibility(View.INVISIBLE);
        }
        else{
            B4.setVisibility(View.INVISIBLE);
            //B4_text.setVisibility(View.VISIBLE);
        }
    }

}

