package com.grimg.customlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.grimg.customlistview.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    CustomAdapter adapter;
    List<String> listTitles, listDescriptions;
    public List<Item> listItems;

    private String mJSONURLString = "http://10.0.2.2:18080/pidev-web/rest/documents";

    RequestQueue requestQueue;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        listTitles = new ArrayList<>();
        listDescriptions = new ArrayList<>();
        listItems = new ArrayList<>();



        requestQueue = Volley.newRequestQueue(this);
        adapter = new CustomAdapter(MainActivity.this, R.layout.item, listItems);
        sendRequest();


    }

    public void sendRequest() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        listView.setAdapter(adapter);
                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                final JSONObject student = response.getJSONObject(i);

                                // Get the current student (json object) data
                                int id = student.getInt("id");
                                String name = student.getString("name");
                                String etat = student.getString("etat");
                                JSONObject user= student.getJSONObject("user");
                                String usrname = user.getString("lastname");



                                Item item = new Item(String.valueOf(id)  ," Name: " + name +" \n Etat: " +etat + " \n User: " + usrname);
                                listItems.add(item);

                                //items clicks on list view

                               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        String itemTitle = ((TextView) view.findViewById(R.id.txtTitle)).getText().toString();
                                        String itemTitle2 = ((TextView) view.findViewById(R.id.txtDescription)).getText().toString();

                                        //open the activity and pass the data
                                        Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                                        intent.putExtra("titles", itemTitle);
                                        intent.putExtra("desc", itemTitle2);


                                        startActivity(intent);
                                    }
                                });

                               /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                        Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                                        intent.putExtra("EXTRA_ID", String.valueOf());
                                        startActivity(intent);
                                    }
                                }); */

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }
}
