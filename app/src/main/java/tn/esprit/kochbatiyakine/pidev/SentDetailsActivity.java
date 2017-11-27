package tn.esprit.kochbatiyakine.pidev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SentDetailsActivity extends AppCompatActivity {
public TextView mail,Subject,context;
    public Button delete;
    int id;

    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_details);
        mail=(TextView)findViewById(R.id.mail);
        Subject=(TextView)findViewById(R.id.Subject);
        context=(TextView)findViewById(R.id.context);
        delete=(Button)findViewById(R.id.delete);
       b = getIntent().getExtras();
        mail.setText(b.getString("email"));
        Subject.setText(b.getString("subject"));
        context.setText(b.getString("context"));

delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        JSONObject js = new JSONObject();
        try {
            js.put("name","anything");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue= Volley.newRequestQueue(SentDetailsActivity.this);
        StringRequest str=new StringRequest(Request.Method.DELETE, "http://10.0.2.2:18080/pidev-web/rest/mail/delete/"+b.get("id") ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //
              /*  Iterator<?> key=response.keys();
                while (key.hasNext()){
                    String k= (String) key.next();
                    try {
                        String x=response.getString(k);
                        Toast.makeText(SentDetailsActivity.this,x,Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
*/
                Toast.makeText(SentDetailsActivity.this,response,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(SentDetailsActivity.this,Main2Activity.class);
                startActivity(i);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
        requestQueue.add(str);
    }
});

    }
}
