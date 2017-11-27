package com.esprit.pi.pidev;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esprit.pi.pidev.model.ResObj;
import com.esprit.pi.pidev.model.User;
import com.esprit.pi.pidev.remote.ApiUtils;
import com.esprit.pi.pidev.remote.ServiceUser;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

EditText edEmail;
EditText edPassword;
Button btnLogin,btnInsc,senEmail;
ServiceUser serviceUser;
Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = getApplicationContext();
        setContentView(R.layout.activity_main);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnInsc = (Button) findViewById(R.id.btnInsc);
        serviceUser = ApiUtils.getServiceUser();
        senEmail = (Button)findViewById(R.id.sendmailmain);


        senEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SendMailActivity.class);
                startActivity(i);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                //validate form
                if(validateLogin(email,password)){
                    //do login
                    DoLogin(email,password);
                }
            }
        });

        btnInsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(c,UserActivity.class);
                startActivity(i);
            }
        });
    }

    private Boolean validateLogin(final String email,final String password){
        if(email==null || email.trim().length()==0){
            Toast.makeText(this, "email is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password==null || password.trim().length()==0){
            Toast.makeText(this, "password is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }
    private void DoLogin(final String email, final String password){
        Call<User> call=serviceUser.login(email,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User u= response.body();
                    if (u != null) {
                        //login start main activvity
                        /* u = new User() ;
                        u.setFirstname(u.getFirstname());
                        u.setLastname(u.getLastname(k));
                        u.setEmail(u.getEmail());
                        u.setSalaire(u.getSalaire());
                        u.setAge(u.getAge());
                        u.setEmail(u.getEmail());
                        u.setCin(u.getCin());*/

                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("user",  u);

                        startActivity(intent);

                    }
                } else {
                    Toast.makeText(MainActivity.this, "the email or password is incorrect", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Error please try again", Toast.LENGTH_SHORT).show();

        }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }

}