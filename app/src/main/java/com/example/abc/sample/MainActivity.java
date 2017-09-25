package com.example.abc.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView t1;
    EditText e1,e2;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 =(TextView)findViewById(R.id.t1);
        b1 = (Button)findViewById(R.id.b1);
        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        b3= (Button)findViewById(R.id.b3);
        b2 = (Button)findViewById(R.id.b2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.b3)
        {
            DATABASEHANDLER db = new DATABASEHANDLER(this);
            String uname = e1.getText().toString();
            Registration obj = new Registration(uname);
            if (obj != null) {
                Toast.makeText(getApplicationContext(), "REGISTRATION COMPLETED", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "REGISTRATION NOT COMPLETED", Toast.LENGTH_LONG).show();

            }
            db.addDATA(obj);
            Log.e("number" , uname);
            Log.e("obj" , String.valueOf(obj));
        }
        if (v.getId() == R.id.b1) {
            String uname = e1.getText().toString();
            String pass = e2.getText().toString();
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://10.0.2.2/sample.php?uname=" + uname + "&pass=" + pass;
            Log.e("url", url);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            String n = response;
                            int i = Integer.parseInt(n.trim());
                            if (i == 1) {

                                SharedPreferences shared = getSharedPreferences("number", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putString("unumber", e1.getText().toString());
                                editor.putString("id", n);


                                editor.commit();

                                Intent b = new Intent(getApplicationContext(), friends.class);
                                startActivity(b);
                            } else {
                                t1.setText("not running ");
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }


            });
            queue.add(stringRequest);


        }
        if(v.getId()==R.id.b2)
        {
            Intent i = new Intent(getApplicationContext() ,datadisplay.class);
            startActivity(i);
        }
    }
}
