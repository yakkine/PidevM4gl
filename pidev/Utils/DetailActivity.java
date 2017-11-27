package com.example.rym.pidev.Utils;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rym.pidev.MainActivity;
import com.example.rym.pidev.R;

import org.json.JSONArray;

public class DetailActivity extends AppCompatActivity {

TextView nom , description , state , deadline ;
    Button btnsupprimer ;
    RequestQueue requestQueue;
    String url = "http://10.0.2.2:18080/pidevjee-web/rest/tasks/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nom = (TextView)findViewById(R.id.txtnom);
        description = (TextView)findViewById(R.id.description);

        state = (TextView)findViewById(R.id.state);
        deadline = (TextView)findViewById(R.id.deadline);
        btnsupprimer = (Button)findViewById(R.id.Supprimer);


        Intent intent =  getIntent();
        Bundle bundle=intent.getExtras();
        String title = bundle.getString("name");
        nom.setText(title);
        String des = bundle.getString("description");
        description.setText(des);

        String st = bundle.getString("state");
        state.setText(st);

        String dead = bundle.getString("deadline");
        deadline.setText(dead);
        final String unique = bundle.getString("id");



        btnsupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        requestQueue= Volley.newRequestQueue(DetailActivity.this);
                sendRequest(unique);
                startActivity(new Intent(DetailActivity.this,MainActivity.class));




            }
        }


        );


    }
    public void  sendRequest(String id){

        StringRequest jsonObjectRequest = new StringRequest(
                Request.Method.DELETE,
                url + id,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }



        );
           requestQueue.add(jsonObjectRequest);

    }
}



