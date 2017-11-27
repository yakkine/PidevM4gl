package com.grimg.customlistview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.grimg.customlistview.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Amine on 10/3/2017.
 */

public class ItemActivity extends AppCompatActivity {

    TextView txt ;
    String url = "http://10.0.2.2:18080/pidev-web/rest/documents/";
    Button supprimer;
    Button mail;
    RequestQueue requestQueue;

    //String url = x + title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        supprimer = (Button) findViewById(R.id.supp);
        mail = (Button) findViewById(R.id.mail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String title = bundle.getString("titles");
        String title2 = bundle.getString("desc");


        txt = (TextView) findViewById(R.id.textView);
        txt.setText(title2);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ItemActivity.this, MailApiActivity.class));
            }

        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue = Volley.newRequestQueue(ItemActivity.this);
                sendRequest( title);
                startActivity(new Intent(ItemActivity.this, MainActivity.class));
            }

        });
    }

        public void sendRequest(String id){

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.DELETE,
                    url+id,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                           Toast.makeText(ItemActivity.this, "Document deleted", Toast.LENGTH_LONG).show();
                        }
                    }
            );

            // Add JsonArrayRequest to the RequestQueue
            requestQueue.add(jsonArrayRequest);




    }
}
