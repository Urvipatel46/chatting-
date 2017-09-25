package com.example.abc.sample;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ABC on 08-08-2017.
 */

public class customlist extends ArrayAdapter {

    private String[] name;
    private  String[] notification;
    private Activity context;
    private  String[] status;

    public customlist(Activity context, String[] name) {
        super(context, R.layout.activity_custom);
    }
    public void customlist()
    {

    }

    public customlist(Activity context, String[] notification , String[] name, String[] status ) {
        super(context, R.layout.activity_custom , name);
        this.notification = notification;
        this.context = context;
        this.name = name;
        this.status = status;

    }
    @Override
    public View getView(int position , View convertView , ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View listviewItem = null;

        if(Integer.parseInt(status[position])==1)
        {
            listviewItem= inflater.inflate(R.layout.withoutnotification, null, true);
            TextView tname = (TextView)listviewItem.findViewById(R.id.t1);
            tname.setText(name[position]+"");

        }
        else
        {
            listviewItem= inflater.inflate(R.layout.activity_custom, null, true);
            TextView tname = (TextView)listviewItem.findViewById(R.id.t1);
            TextView tnotification = (TextView)listviewItem.findViewById(R.id.t2);

            tname.setText(name[position]+"");
            tnotification.setText(notification[position]+"");

        }


        return listviewItem;
    }
}
