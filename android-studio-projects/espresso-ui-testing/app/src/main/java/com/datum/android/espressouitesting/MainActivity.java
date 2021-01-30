package com.datum.android.espressouitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button mLogin, mSend;
    EditText mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);

        mLogin = findViewById(R.id.login_button);
        mLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
            intent.putExtra("username", mUsername.getText().toString());
            intent.putExtra("password", mPassword.getText().toString());
            startActivity(intent);
        });


        mSend = findViewById(R.id.send_button);
        mSend.setOnClickListener(view -> {
            String emailMessage = "I forgot my password";

            // Use an intent to launch an email app.
            // Send the order summary in the email body.
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:mutairibassam@gmail.com")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "New password!");
            intent.putExtra(Intent.EXTRA_TEXT, emailMessage);
            startActivity(intent);

        });
    }
}