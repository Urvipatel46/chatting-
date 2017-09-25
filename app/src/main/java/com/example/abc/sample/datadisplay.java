package com.example.abc.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class datadisplay extends AppCompatActivity {
    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datadisplay);
        l1 = (ListView)findViewById(R.id.l1);

        DATABASEHANDLER db = new DATABASEHANDLER(this);
        List<Registration> contacts = db.getAllData();
        Integer []id = new Integer[contacts.size()];
        String []contact = new String[contacts.size()];
        int i=0;
        for(Registration cn : contacts )
        {
            id[i] = cn.getId();
            contact[i] = cn.getphone();
            Log.e("idqe", String.valueOf(id[i]));
            Log.e("contactt" , contact[i]);
            i++;
        }
        datalist datalist = new datalist(datadisplay.this, id,contact);


        l1 = (ListView)findViewById(R.id.l1);
        l1.setAdapter(datalist);

    }

}
