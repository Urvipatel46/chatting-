package com.example.abc.sample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class customchat extends ArrayAdapter {
    private String[] mes;
    private String[] sender;
    private Activity context;
    int b;
    String num;
    public customchat(Activity context,String[] mes , String[] sender ) {
        super(context, R.layout.activity_customchat );
        this.sender = sender;
        this.context = context;
        this.mes = mes;

        SharedPreferences shared = context.getSharedPreferences("number", Context.MODE_PRIVATE);
        num = shared.getString("id", null);
        b = Integer.parseInt(num);
        Log.e("idddd",num);
    }

    @Override
    public int getCount() {
        return mes.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listviewItem = null;
        Log.e("sender_id", String.valueOf(position));
        if(Integer.parseInt(sender[position])==b)
        {
            listviewItem= inflater.inflate(R.layout.activity_customchat, null, true);
        }
        else
        {
            listviewItem= inflater.inflate(R.layout.activity_customchat2, null, true);
        }

        TextView tname = (TextView) listviewItem.findViewById(R.id.t1);
        tname.setText(mes[position] + "");


        return listviewItem;

    }
}
