package tn.esprit.kochbatiyakine.pidev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MailApiActivity extends AppCompatActivity {

    //Declaring EditText
    EditText editTextEmail,editTextSubject,editTextMessage;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_api);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        buttonSend = (Button) findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = editTextEmail.getText().toString();
                String subject = editTextSubject.getText().toString();
                String message = editTextMessage.getText().toString();

                Intent inetnt = new Intent(Intent.ACTION_SEND);
                inetnt.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                inetnt.putExtra(Intent.EXTRA_SUBJECT,subject);
                inetnt.putExtra(Intent.EXTRA_TEXT,message);

                inetnt.setType("message/rfc822");

                startActivity(Intent.createChooser(inetnt,"select email app"));



            }
        });
    }
}
