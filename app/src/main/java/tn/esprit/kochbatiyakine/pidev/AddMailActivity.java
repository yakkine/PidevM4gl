package tn.esprit.kochbatiyakine.pidev;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AddMailActivity extends AppCompatActivity {
  EditText context,subject,email;
    ImageButton send;

int id=1;
    User u1;

    String url = "http://10.0.2.2:18080/pidev-web/rest/mail/add";

    Calendar mycal=Calendar.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mail);

        context = (EditText) findViewById(R.id.context);
        subject = (EditText) findViewById(R.id.subject);
        email = (EditText) findViewById(R.id.email);

        send = (ImageButton) findViewById(R.id.send);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddMailActivity.this, ""+getIdByMail(email.getText().toString().trim()).getId(), Toast.LENGTH_SHORT).show();
u1=new User();
                u1.setId(1);
                Map<String, String> map = new HashMap<String, String>();
                map.put("context", context.getText().toString().trim());
                map.put("subject", subject.getText().toString().trim());
                //map.put("date",Calendar.DATE);
                //map.put("receiver",getIdByMail(email.getText().toString().trim()).toString());
               // map.put("sender",u1.toString());

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(map), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(AddMailActivity.this, ""+getIdByMail(email.getText().toString().trim()), Toast.LENGTH_SHORT).show();
                        Toast.makeText(AddMailActivity.this, "sucessss", Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(getApplicationContext(),Main2Activity.class);
                        startActivity(intent1);




                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Context context = getApplicationContext();
                        CharSequence text = error.getMessage();
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");

                        return headers;
                    }
                }

                        ;



                RequestQueue queue = Volley.newRequestQueue(AddMailActivity.this);
                queue.add(request);








            }
        });


    }

    private User getIdByMail(String trim) {
final User user=new User();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://10.0.2.2:18080/pidev-web/rest/mail/user/"+email.getText().toString().trim(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // Toast.makeText(Main2Activity.this, response, Toast.LENGTH_SHORT).show();
                try {
                    // JSONObject jsa=new JSONObject(response);


                    JSONObject j=new JSONObject(response);


                     user.setId(j.getInt("id"));
                    Toast.makeText(AddMailActivity.this, j.getString("id"), Toast.LENGTH_SHORT).show();
                     user.setMail(j.getString("email"));









                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                error.getMessage();

            }
        });
        RequestQueue queue= Volley.newRequestQueue(AddMailActivity.this);
        queue.add(stringRequest);




        return user;
    }

}
