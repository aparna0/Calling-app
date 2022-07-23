package com.example.callingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phone_no;
    ImageView phone;
    int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone_no = findViewById(R.id.edittext);
        phone = findViewById(R.id.imageview);

        phone.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               makeCall();
            }
        });
    }
    public void makeCall(){

        String number = phone_no.getText().toString();

        if(number.trim().length() > 0){

            if(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){


                String dial = "tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)) );

            }
            else{
                Toast.makeText(this,"permission DENIED",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }


        }
        else{
            Toast.makeText(this,"Enter phone number",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makeCall();
            }
            else{
                Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
