package tn.esprit.kochbatiyakine.pidev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReceiverActivity extends AppCompatActivity {

    ListView lv;
    List<Map<String,String>> listitem=new ArrayList<>();
    Map<String,String> m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        System.out.println("test");
        lv=(ListView) findViewById(R.id.lv);



        JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, "http://10.0.2.2:18080/pidev-web/rest/mail/received",null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Toast.makeText(ReceiverActivity.this, "hguguf", Toast.LENGTH_SHORT).show();
                try {
                    // JSONObject jsa=new JSONObject(response);

                    for(int i=0;i<response.length();i++){
                        m=new HashMap<>();
                        JSONObject j=response.getJSONObject(i);



                        Iterator<?> key=j.keys();
                   /*     while(key.hasNext()){
                            String k= (String) key.next();
                            JSONObject s=j.getJSONObject(k);
                            m.put("id",String.valueOf(s.getInt("id")));
                            m.put("subject",s.getString("subject"));
                            l.add(m);

                        }
*/
                        m.put("email",String.valueOf(j.getJSONObject("sender").getString("email")));
                        m.put("subject",j.getString("subject"));
                        m.put("id",j.getString("id"));
                        m.put("context",j.getString("context"));
                        listitem.add(m);
                        // Toast.makeText(Main2Activity.this, s.getString("lastname"), Toast.LENGTH_LONG).show();






                    }
                    SimpleAdapter simpleAdapter=new SimpleAdapter(getApplicationContext(),listitem,R.layout.item_row,new String[]{"email","subject","context","id"},new int[]{R.id.t1,R.id.t2,R.id.cont,R.id.id});
                    lv.setAdapter(simpleAdapter);

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
        RequestQueue queue= Volley.newRequestQueue(ReceiverActivity.this);
        queue.add(stringRequest);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(ReceiverActivity.this, "gsgsrgrrgsr", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ReceiverActivity.this,SentDetailsActivity.class);
                Map<String,String> h=listitem.get(i);
                intent.putExtra("email",h.get("email"));
                intent.putExtra("subject",h.get("subject"));
                intent.putExtra("context",h.get("context"));
                intent.putExtra("id",h.get("id"));
                startActivity(intent);
            }
        });

    }
}
