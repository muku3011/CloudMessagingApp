package com.notifire.android.adduser;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserData implements Serializable {

    private String userName;
    private String registrationId;

    public String getUserName() {
        return userName;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}