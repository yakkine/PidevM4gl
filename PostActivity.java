package com.esprit.pi.pidev;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.esprit.pi.pidev.model.User;
        import com.esprit.pi.pidev.remote.ApiUtils;
        import com.esprit.pi.pidev.remote.ServiceUser;

        import java.util.ArrayList;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class PostActivity extends AppCompatActivity {


    TextView edname;


    Button btnInscription;
    Button btngetuserList;
    ListView listView;
    ServiceUser serviceUser;
    List<User> list = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btnInscription =(Button) findViewById(R.id.btnInscription);
        btngetuserList =(Button) findViewById(R.id.btngetuserList);
        listView = (ListView) findViewById(R.id.listView);
        serviceUser = ApiUtils.getServiceUser();
        btngetuserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get User list
                getUserList();
            }
        });
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this , UserActivity.class);
                intent.putExtra("user_name","");
                intent.putExtra("user_prenom","");
                intent.putExtra("user_salaire","");
                intent.putExtra("user_age","");
                intent.putExtra("user_cin","");
                intent.putExtra("user_mail","");
                intent.putExtra("user_password","");
                startActivity(intent);
            }
        });
    }

    public void getUserList(){
        Call<List<User>> call = serviceUser.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new UserAdapter(PostActivity.this, R.layout.list_user,list));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR: " , t.getMessage());
            }
        });
    }

}
