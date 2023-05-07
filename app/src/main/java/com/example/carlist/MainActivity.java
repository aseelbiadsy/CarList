package com.example.carlist;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {
    private ArrayList<Car> list;
    private ListView lvCustom;
    private CarAdapter adapter;
    private ImageButton imAdd;
    public  Car lastselected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==27)//update
        {
            if (resultCode==RESULT_OK)
            {
                String m=data.getExtras().getString("model");
                String p=data.getExtras().getString("passenger");
                String c=data.getExtras().getString("color");
                Bitmap bitmap=Helper.byteArrayToBitmap(data.getExtras().getByteArray("bitmap"));
                String s=data.getExtras().getString("speed");

                lastselected.setModel(m);
                lastselected.setPassengers(Integer.valueOf(p));
                lastselected.setColor(c);
                lastselected.setBitmap(bitmap);
                lastselected.setSpeed(Integer.valueOf(s));

                adapter.notifyDataSetChanged();
                Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initViews() {
        imAdd = findViewById(R.id.imAdd);
        lvCustom = (ListView) findViewById(R.id.lvCustom);

        imAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hhhhh", Toast.LENGTH_SHORT).show();
                Intent goo=new Intent(MainActivity.this,EditAddd.class);
                startActivityForResult(goo,29);
            }
        });
        lvCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastselected=adapter.getItem(position);
                Intent go=new Intent(MainActivity.this,EditAddd.class);
                go.putExtra("model",lastselected.getModel());
                go.putExtra("passenger",String.valueOf(lastselected.getPassengers()));
                go.putExtra("speed",String.valueOf(lastselected.getSpeed()));
                go.putExtra("color",lastselected.getColor());
                go.putExtra("bitmap",Helper.bitmapToArray(lastselected.getBitmap()));
                startActivityForResult(go,27);
            }
        });

        lvCustom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Car cars = adapter.getItem(position);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("remove");
                alertDialog.setMessage("Are you sure want delete?");
                alertDialog.setIcon(R.drawable.ic_baseline_delete_24);
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("yes",(dialog, which) -> {
                    adapter.remove(adapter.getItem(position));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                });
                alertDialog.setNegativeButton("no",(dialog, which) -> {
                    dialog.dismiss();
                });
                AlertDialog dialog=alertDialog.create();
                dialog.show();
                return true;
            }
        });
    }

    private void initList() {

        Bitmap bmw= BitmapFactory.decodeResource(getResources(),R.drawable.bmw);
        Bitmap audi= BitmapFactory.decodeResource(getResources(),R.drawable.audi);
        Bitmap ferrari= BitmapFactory.decodeResource(getResources(),R.drawable.black_ferrari);
        Bitmap ford= BitmapFactory.decodeResource(getResources(),R.drawable.blue_ford);
        Bitmap toyota= BitmapFactory.decodeResource(getResources(),R.drawable.yellow_toyota);

        Car c1 = new Car("BMW",4,"red",bmw,5);
        Car c2 = new Car("Ferrari",2,"black",audi,1);
        Car c3 = new Car("Ford",6,"blue",ferrari,6);
        Car c4 = new Car("Toyota",4,"yellow",ford,2);
        Car c5 = new Car("Audi",4,"green",toyota,4);

        list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);

        adapter = new CarAdapter(this, 0,0,list);
        lvCustom.setAdapter(adapter);
   }
}
