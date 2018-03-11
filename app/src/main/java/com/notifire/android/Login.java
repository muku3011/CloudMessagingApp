package com.notifire.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.notifire.android.adduser.UserData;

import static android.provider.Telephony.Carriers.PASSWORD;

public class Login extends AppCompatActivity {

    public static String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        loginButton();
    }

    public void loginButton() {
        final EditText usernameEditText = findViewById(R.id.usernameEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String useName = usernameEditText.getText().toString();
                        if ((useName.trim().length() > 3) && passwordEditText.getText().toString().equalsIgnoreCase("1234")) {
                            Toast.makeText(Login.this, "Welcome [ "+useName+" ]", Toast.LENGTH_SHORT).show();
                            userName = useName;
                            Intent intent = new Intent("com.notifire.android.LoginSuccessful");
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "User name should be of minimum 4 characters and use default password", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent("com.notifire.android.LoginFailed");
                            startActivity(intent);
                        }
                    }
                }
        );
    }

}
