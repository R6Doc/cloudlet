package com.encicla.appcloudlet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

@SuppressLint("Registered")
public class qr extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mscanerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr);
    }


    public void btnescanear(View v){
        mscanerview= new ZXingScannerView(this);
        setContentView(mscanerview);
        mscanerview.setResultHandler(this);
        mscanerview.startCamera();

    }


    @Override
    public void handleResult(Result result) {
        Log.v("HandleResult",result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultados del scan");
        builder.setMessage(result.getText());
        AlertDialog alertDialog= builder.create();
        alertDialog.show();

        mscanerview.resumeCameraPreview(this);
    }
}
