package com.notifire.android;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.notifire.android.adduser.HttpPostRequest;
import com.notifire.android.adduser.UserData;

public class LoginSuccessful extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        generateToken();
    }

    public void generateToken() {
        final Button getTokenButton = findViewById(R.id.tokenButton);
        final TextView tokenTextView = findViewById(R.id.tokenTextView);

        getTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpPostRequest postRequest = new HttpPostRequest();
                String firebaseTokenId = null;
                if (getTokenButton.getText().toString().equalsIgnoreCase(getString(R.string.token_button_get_token))) {
                    firebaseTokenId = FirebaseInstanceId.getInstance().getToken();
                    Log.d(TAG, "Token [" + firebaseTokenId + "]");
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Token for firebase", firebaseTokenId);
                    clipboard.setPrimaryClip(clip);
                    tokenTextView.setText(firebaseTokenId);
                    Toast.makeText(LoginSuccessful.this, "Token copied to clipboard", Toast.LENGTH_LONG).show();
                    getTokenButton.setText(R.string.token_button_add_user);
                } else if (getTokenButton.getText().toString().equalsIgnoreCase(getString(R.string.token_button_add_user))) {
                    UserData userData = new UserData();
                    userData.setUserName(Login.userName);
                    userData.setRegistrationId(firebaseTokenId);
                    postRequest.execute(userData);
                    Toast.makeText(LoginSuccessful.this, "Status for [ " + Login.userName + " ] is " + postRequest.getStatus() + " ]", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Sent data for upload");
                    getTokenButton.setText(R.string.token_button_get_status);
                } else if (getTokenButton.getText().toString().equalsIgnoreCase(getString(R.string.token_button_get_status))) {
                    Toast.makeText(LoginSuccessful.this, "Status for [ " + Login.userName + " ] is " + postRequest.getStatus() + " ]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}