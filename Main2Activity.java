package com.esprit.pi.pidev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.pi.pidev.model.User;
import com.esprit.pi.pidev.remote.ApiUtils;
import com.esprit.pi.pidev.remote.ServiceUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    ServiceUser serviceUser;

    EditText txtEmail,txtfirstname,txtlastname,txtage,txtsexe,txtrole;
    Button  btnDel , btnupdate,editbtn;
    User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        serviceUser = ApiUtils.getServiceUser();
        txtEmail=(EditText) findViewById(R.id.txtEmail);
        txtfirstname= (EditText) findViewById(R.id.txtfirstname);
        txtlastname= (EditText) findViewById(R.id.txtlastname);
        txtsexe = (EditText) findViewById(R.id.sexe);
        txtrole = (EditText) findViewById(R.id.role);
        txtage = (EditText) findViewById(R.id.age);
        btnDel = (Button)findViewById(R.id.btnDel);
        btnupdate = (Button)findViewById(R.id.btnupdate);
        editbtn = (Button)findViewById(R.id.btnedit);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {

            u = (User) extras.getSerializable("user");
            txtEmail.setText(u.getEmail());
            txtfirstname.setText(u.getFirstname());
            txtlastname.setText(u.getLastname());
            txtage.setText(u.getAge()+"");
            txtsexe.setText(u.getSexe());
            txtrole.setText(u.getRole());


            txtEmail.setEnabled(false);
            txtfirstname.setEnabled(false);
            txtlastname.setEnabled(false);
            txtage.setEnabled(false);
            txtrole.setEnabled(false);
            txtsexe.setEnabled(false);
            btnupdate.setVisibility(View.INVISIBLE);

        }

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtEmail.setEnabled(true);
                txtfirstname.setEnabled(true);
                txtlastname.setEnabled(true);
                txtage.setEnabled(true);
                txtrole.setEnabled(true);
                txtsexe.setEnabled(true);
                btnupdate.setVisibility(View.VISIBLE);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u.setFirstname(txtfirstname.getText().toString());
                u.setLastname(txtlastname.getText().toString());
                u.setEmail(txtEmail.getText().toString());
                u.setRole(txtrole.getText().toString());
                u.setSexe(txtsexe.getText().toString());
                u.setAge(Integer.parseInt(txtage.getText().toString()));
                Call<User> call = serviceUser.updateuser(u);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(Main2Activity.this,"User Updated successfully",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Log.e("ERROR",t.getMessage());
                    }
                });
            }
        });
    btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Main2Activity.this, ""+u.getId(), Toast.LENGTH_LONG).show();
                Call<User> call = serviceUser.deleteUser(Integer.parseInt(String.valueOf(u.getId())));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(Main2Activity.this,"User Deleted successfully",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Log.e("ERROR",t.getMessage());
                    }
                });
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }
}

