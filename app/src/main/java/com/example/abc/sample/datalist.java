package com.example.abc.sample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import static android.R.attr.id;
import static android.R.attr.name;

public class datalist extends ArrayAdapter<String> {
    private  Integer[] id;
    private  String[] contact;
    private Activity context;
    TextView textViewid;
    public datalist(Activity context , Integer[] id, String[] contact) {
            super(context, R.layout.activity_datalist,contact);
            this.context = context;
            this.contact = contact;
            this.id = id;

        }

    @Override
    public View getView(int position , View convertView , ViewGroup parent)
    {
        Log.e("position", String.valueOf(id[position]));
        Log.e("phoneid",contact[position]);
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_datalist,null,true);

        textViewid = (TextView)listViewItem.findViewById(R.id.t1);
        TextView textViewContact = (TextView)listViewItem.findViewById(R.id.t2);
        textViewid.setText(id[position]+"");
        textViewContact.setText(contact[position]);
        return listViewItem;


    }


}

