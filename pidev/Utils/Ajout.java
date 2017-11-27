package com.example.rym.pidev.Utils;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;



import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.rym.pidev.R;


import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;



public class Ajout extends AppCompatActivity {


    EditText nom , description, deadline;

    Button btnajouter ;
    String url = "http://10.0.2.2:18080/pidevjee-web/rest/tasks";



    Calendar mycal=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);


        nom = (EditText) findViewById(R.id.nom);
        description = (EditText) findViewById(R.id.description);
        deadline = (EditText) findViewById(R.id.deadline);

        btnajouter = (Button) findViewById(R.id.btnajouter);




        deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Ajout.this, date, mycal.get(Calendar.YEAR), mycal.get(Calendar.MONTH), mycal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnajouter.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Map<String, String> map = new HashMap<String, String>();
                map.put("name", nom.getText().toString().trim());
                map.put("description", description.getText().toString().trim());
                map.put("deadline", deadline.getText().toString().trim());

                Intent i1 = new Intent(Ajout.this,TaskActivity.class);
                startActivity(i1);


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(map), new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        /*Context context = getApplicationContext();
                        CharSequence text ="ajout reussite ";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();*/
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       /* error.printStackTrace();
                        Context context = getApplicationContext();
                        CharSequence text = error.getMessage();
                        int duration = Toast.LENGTH_SHORT;

                       Toast toast = Toast.makeText(context, text, duration);
                     toast.show();*/
                    }
                })
                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");

                        return headers;
                    }
                };



                RequestQueue queue = Volley.newRequestQueue(Ajout.this);
                queue.add(request);


        }





        });



}




    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mycal.set(Calendar.YEAR, year);
            mycal.set(Calendar.MONTH, month);
            mycal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String format = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
            deadline.setText(simpleDateFormat.format(mycal.getTime()));

        }
    };





}
