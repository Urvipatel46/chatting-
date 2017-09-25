package com.example.abc.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class chat extends AppCompatActivity implements View.OnClickListener {

    TextView t1;
    Button b1;
    EditText e1;
    ListView l1;
        String num,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        t1 = (TextView)findViewById(R.id.t1);
        b1 = (Button)findViewById(R.id.b1);
        Intent i = getIntent();
        number = i.getStringExtra("num");
        SharedPreferences shared = getSharedPreferences("number", Context.MODE_PRIVATE);
        num = shared.getString("unumber", null);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/statusupdate.php?uname="+num+"&rname="+number;
        Log.e("url",url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    int a = Integer.parseInt(response);
                    if(a == 1)
                    {
                        Toast.makeText(chat.this, "its done" , Toast.LENGTH_LONG).show();
                    }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }


        });
        queue.add(stringRequest);





                        t1.setText(number);
        t1 = (TextView) findViewById(R.id.t1);
        b1 = (Button) findViewById(R.id.b1);
        e1 = (EditText) findViewById(R.id.e1);
        l1 = (ListView) findViewById(R.id.listView1);


        b1.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        String mes = e1.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/chat.php?uname="+num+"&rname="+number+"&mes="+mes;
Log.e("url",url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        RequestQueue queue = Volley.newRequestQueue(chat.this);
                        String url = "http://10.0.2.2/display.php?uname="+num+"&rname="+number;
                        Log.e("url",url);
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try
                                        {
                                            JSONArray ar=new JSONArray(response);
                                            String name[]=new String[ar.length()];
                                            String sender[]=new String[ar.length()];
                                            for(int i=0;i<ar.length();i++) {
                                                JSONObject out = ar.getJSONObject(i);
                                                name[i]=out.getString("message");
                                                sender[i] = out.getString("sender_id");
                                                Log.e("ss",sender[i]);
                                                Log.e("dd",name[i]);

                                            }
                                            customchat customchat = new customchat(chat.this, name , sender );
                                            l1 = (ListView)findViewById(R.id.listView1);
                                            l1.setAdapter(customchat);

                                        }
                                        catch(JSONException ex){
                                            Log.e("result","Error:="+ex.getMessage());
                                        }




                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }


                        });
                        queue.add(stringRequest);




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }


        });
        queue.add(stringRequest);

    }

}
