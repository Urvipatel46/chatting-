package com.example.abc.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class friends extends AppCompatActivity {

    String num;
    ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        SharedPreferences shared = getSharedPreferences("number", Context.MODE_PRIVATE);
        num = shared.getString("unumber", null);

        l1 = (ListView)findViewById(R.id.listView1);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/friends.php?uname="+num;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONArray ar=new JSONArray(response);
                            String name[]=new String[ar.length()];
                            for(int i=0;i<ar.length();i++) {
                                JSONObject out = ar.getJSONObject(i);
                                name[i] = out.getString("name");
                                DATABASEHANDLER db = new DATABASEHANDLER(friends.this);
                                Registration obj = new Registration(name[i]);
                                db.addDATA(obj);
                            }
                                customlist customelist = new customlist(friends.this , name);
                                l1 = (ListView)findViewById(R.id.listView1);
                                l1.setAdapter(customelist);
                                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        String name = parent.getItemAtPosition(position).toString();
                                        Toast.makeText(friends.this, name , Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(getApplicationContext() , chat.class);
                                        i.putExtra("num", name);

                                        startActivity(i);

                                    }
                                });


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
}
