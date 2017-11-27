package com.example.rym.pidev.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.rym.pidev.Adapters.ValidAdapter;
import com.example.rym.pidev.Entites.Task;
import com.example.rym.pidev.MainActivity;
import com.example.rym.pidev.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskActivity extends AppCompatActivity {

    public  static List<Task> lst_tasks=new CopyOnWriteArrayList<Task>();
    List<Map<String,String>> l;

    private ListView listView;
    ValidAdapter adapter;
    String json_url="http://10.0.2.2:18080/pidevjee-web/rest/tasks";
    Button bntDelete ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        viewDemande();


    }


    public void viewDemande() {

        listView = (ListView) findViewById(R.id.list_valid);
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        JsonArrayRequest jar = new JsonArrayRequest(json_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(i);
                                System.out.println("----------------------" + jsonObject);
                                Task c = new Task();
                                // c.setType(jsonObject.getString("type"));
                                c.setName(jsonObject.getString("name"));
                                c.setDescription(jsonObject.getString("description"));
                                c.setDeadline(jsonObject.getString("deadline"));
                                c.setState(jsonObject.getString("state"));

                                //JSONObject user = jsonObject.getJSONObject("user");
                               // String username =user.getString("lastname");
                                //c.setUser(username);

                                lst_tasks.add(c);


                                c.setId(jsonObject.getInt("id"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MainActivity.class.getSimpleName(), "Error: " + error.getMessage());
                //  hidePDialog();
            }
        });


        MySingleton.getIns(this).addToRequ(jar);

        adapter = new ValidAdapter(this, lst_tasks);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TaskActivity.this, DetailActivity.class);
                Task task = lst_tasks.get(position);
                intent.putExtra("name", task.getName());
                intent.putExtra("description", task.getDescription());
                intent.putExtra("state", task.getState());
                intent.putExtra("deadline", task.getDeadline());
                intent.putExtra("id",task.getId()+"");

                startActivity(intent);


            }
        });

    }
        }