package com.notifire.android.adduser;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserData implements Serializable {

    private String userName;
    private String userToken;

    public String getUserName() {
        return userName;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}