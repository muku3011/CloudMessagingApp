package com.notifire.android.adduser;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Objects;

public interface RegisterToken {

    ObjectMapper objectMapper = new ObjectMapper();

    default void sendRegistrationToServer(String token) {

        RequestQueue queue = Volley.newRequestQueue((Context) this);

        String url = "http://192.168.1.148:8080/user";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    // response
                    Log.d("Response", response);
                },
                error -> {
                    // error
                    Log.d("Response", error.getMessage());
                }
        ) {
            @Override
            public byte[] getBody() {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                UserData userData = null;
                if (auth.getCurrentUser() != null) {
                    userData = new UserData();
                    userData.setUserName(auth.getCurrentUser().getEmail());
                    userData.setUserToken(token);
                }
                return Objects.requireNonNull(toJson(userData)).getBytes();
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queue.add(postRequest);
    }

    static String toJson(UserData userData) {
        try {
            return objectMapper.writeValueAsString(userData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
