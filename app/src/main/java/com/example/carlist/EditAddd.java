package com.example.carlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class EditAddd extends AppCompatActivity implements View.OnClickListener {

    Button takePhoto,btnSave,btnCancel;
    EditText model,passenger,color,speed;
    ImageView imAdd;
    Bitmap bitmap;
    private static final int REQUEST_IMAGE_CAPTURE =27;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUEST_IMAGE_CAPTURE)
        {
            if(resultCode ==RESULT_OK)
            {
                bitmap=(Bitmap) data.getExtras().get("data");
                if(bitmap!=null)
                {
                    imAdd.setImageBitmap(bitmap);
                }
                Toast.makeText(this, "your are beautiful", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_addd);
        init();
        Intent intent=getIntent();

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        takePhoto.setOnClickListener(this);

        if(intent.getExtras()!=null)//edit
        {
            //insert the data;
            String modell=intent.getExtras().getString("model");
            String passengerr=intent.getExtras().getString("passenger");
            String colorr=intent.getExtras().getString("color");
            bitmap=Helper.byteArrayToBitmap(intent.getExtras().getByteArray("bitmap"));
            String speedd=intent.getExtras().getString("speed");

            model.setText(modell);
            passenger.setText(passengerr);
            color.setText(colorr);
            imAdd.setImageBitmap(bitmap);
            speed.setText(speedd);

        }
//        else{
//            bitmap = ((BitmapDrawable)imAdd.getDrawable()).getBitmap();
//        }
    }
    public void init() {
        takePhoto = findViewById(R.id.takePhoto);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        model = findViewById(R.id.model);
        passenger = findViewById(R.id.passenger);
        color = findViewById(R.id.color);
        speed = findViewById(R.id.speed);
        imAdd = findViewById(R.id.iAdd);
    }

    @Override
    public void onClick(View v) {

        if(takePhoto==v)
        {
            Intent go=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(go,27);
        }
        if(btnSave==v)
        {
            if(model.getText().toString().length()>0
                    && passenger.getText().toString().length()>0
                      && color.getText().toString().length()>0
                        && speed.getText().toString().length()>0
                            && bitmap!=null){
                Intent data=new Intent();
                data.putExtra("model",model.getText().toString());
                data.putExtra("passenger",passenger.getText().toString());
                data.putExtra("color",color.getText().toString());
                data.putExtra("bitmap",Helper.bitmapToArray(bitmap));
                data.putExtra("speed",speed.getText().toString());
                setResult(RESULT_OK,data);
                this.finish();
            }
            else{
                Toast.makeText(this, "Please fill all Fields", Toast.LENGTH_SHORT).show();
            }
        }
        if(btnCancel==v)
        {
            Toast.makeText(this, "Canceld", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED,null);
            finish();
        }

    }
}