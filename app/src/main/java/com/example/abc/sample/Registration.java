package com.example.abc.sample;

import android.util.Log;

/**
 * Created by ABC on 10-08-2017.
 */

public class Registration {

    int id;
    String phone;

public  Registration()
{

}
    public Registration(int id, String phone) {
        Log.e("eee" , phone);
        Log.e("id" , String.valueOf(id));
        this.id = id;
        this.phone = phone;

    }

    public Registration(String phone) {

        this.phone = phone;

    }

    public int getId() {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }



    public String getphone() {
        return phone;
    }


    public void setphone(String phone) {
        this.phone = phone;
    }





}
