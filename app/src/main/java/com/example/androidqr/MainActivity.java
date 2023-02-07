package com.example.androidqr;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;


public class MainActivity extends AppCompatActivity {

    private Button buttonQr;
    private TextView textViewQR;
    private int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        buttonQr = findViewById(R.id.buttonOpenScan);
        textViewQR = findViewById(R.id.textViewQR);
        Intent intentt = new Intent(this, ScanActivity.class);

        buttonQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(intentt, requestCode); //Start activity pero espero respuesta

                }else {
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA);
                }
            }
        });

    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
    registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            System.out.println("Persmisos");
        } else {
            System.out.println("No persmisos");
        }
    });

    //Cuando se cierre la actividad anterior se ejecuta esto
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            textViewQR.setText(data.getData().toString());
        }
    }



}