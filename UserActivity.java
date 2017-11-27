package com.esprit.pi.pidev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.pi.pidev.model.User;
import com.esprit.pi.pidev.remote.ApiUtils;
import com.esprit.pi.pidev.remote.ServiceUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
  ServiceUser serviceUser;
  EditText edprenom;
  EditText edtUsername;
  EditText edmail;
  EditText edcin,edrole,edsexe;
  EditText edsalaire;
    EditText edPassword;
  EditText edage;

  Button btnSave;
  Button btnDel;
  CheckBox cbMale,cbFemale,cbingenieur,cbchefDepartement;
  //TextView txtUUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //edtUId = (EditText) findViewById(R.id.edtUId);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edprenom = (EditText) findViewById(R.id.edprenom);
        edmail = (EditText) findViewById(R.id.edmail);
        edcin = (EditText) findViewById(R.id.edcin);
        edsalaire = (EditText) findViewById(R.id.edsalaire);
        edage = (EditText) findViewById(R.id.edage);
        edrole = (EditText) findViewById(R.id.edrole);
        edsexe = (EditText) findViewById(R.id.edsexe);
        edPassword = (EditText) findViewById(R.id.edPassword);
        cbFemale = (CheckBox) findViewById(R.id.cbFemale);
        cbMale = (CheckBox) findViewById(R.id.cbMale);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
       // txtUId = (TextView) findViewById(R.id.txtUId);

        serviceUser = ApiUtils.getServiceUser();
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            final String userId = extras.getString("user_id");
            String userName = extras.getString("user_name");
            String userPrenom = extras.getString("user_prenom");
        }
//        edtUId.setText(userId);
       // edtUsername.setText(userName);
        //edprenom.setText(userPrenom);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = new User() ;
                u.setFirstname(edtUsername.getText().toString());
                u.setLastname(edprenom.getText().toString());
                u.setEmail(edmail.getText().toString());
                u.setSalaire(Float.valueOf(edsalaire.getText().toString()));
                u.setAge(Integer.valueOf(edage.getText().toString()));
                u.setEmail(edmail.getText().toString());
                u.setCin(edcin.getText().toString());

              u.setRole(edrole.getText().toString());

                if(cbFemale.isChecked())
                {
                    u.setSexe("femme");
                }else if(cbMale.isChecked())
                {
                    u.setSexe("Homme");
                }

                u.setPassword(edPassword.getText().toString());

               /* if(userId != null && userId.trim().length() > 0){
                    // update user
                    UpdateUser(u);
                }else {
                    //add user
                    addUser(u);
                }*/
                addUser(u);





            }
        });
      /*  btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,PostActivity.class);
                startActivity(intent);

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceUser.deleteUser(Integer.parseInt(userId));
                Intent intent = new Intent(UserActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public void addUser (User u){
        Call<User> call = serviceUser.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this,"User Created successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.e("ERROR",t.getMessage());
            }
        });

    }
    public void UpdateUser (User u){
        Call<User> call = serviceUser.updateuser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this,"User Updated successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.e("ERROR",t.getMessage());
            }
        });

    }

    public void DeleteUser (int id){
        Call<User> call = serviceUser.deleteUser(id );
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this,"User Deleted successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.e("ERROR",t.getMessage());
            }
        });

    }



}
