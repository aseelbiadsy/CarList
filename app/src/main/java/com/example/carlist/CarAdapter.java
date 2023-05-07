package com.example.carlist;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarAdapter extends ArrayAdapter<Car> {

    private Context context;
    private ArrayList<Car> list;

    public CarAdapter(@NonNull Context context, int i, int i1, ArrayList<Car> list) {
        super(context, R.layout.custom_layout, list);
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //inflate xml into object
        LayoutInflater layoutInflater=((AppCompatActivity)context).getLayoutInflater();
        //create view Object
        View view=layoutInflater.inflate(R.layout.custom_layout ,parent,false);
        //customLayout view
        Car cars=this.list.get(position);

        TextView tvColor=view.findViewById(R.id.color);
        TextView tvPassenger=view.findViewById(R.id.passengers);
        TextView tvModel=view.findViewById(R.id.model);
        TextView tvSpeed=view.findViewById(R.id.speed);
        ImageView imCar = view.findViewById(R.id.imCar);

        tvColor.setText(cars.getColor()+"");
        tvModel.setText(cars.getModel()+"");
        tvPassenger.setText(cars.getPassengers()+"");
        tvSpeed.setText(cars.getSpeed()+"");
        imCar.setImageBitmap(cars.getBitmap());

        return view;
    }
}
