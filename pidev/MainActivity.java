package com.example.rym.pidev;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.rym.pidev.Utils.Ajout;
import com.example.rym.pidev.Utils.TaskActivity;


public class MainActivity extends AppCompatActivity {
Button btn , btnajout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btnlist);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,TaskActivity.class);
                startActivity(i);
            }
        });

        btnajout=(Button)findViewById(R.id.btnAjout);
       btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this,Ajout.class);
                startActivity(i1);
            }
        });



    }
}
