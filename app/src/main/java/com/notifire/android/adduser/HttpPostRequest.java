package com.notifire.android.adduser;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.notifire.android.LoginSuccessful;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpPostRequest extends AsyncTask<UserData, String, String> {

    private String TAG = getClass().getSimpleName();

    protected void onPreExecute (){
        Log.d(TAG + " Pre execute","On pre execute......");
    }

    @Override
    protected String doInBackground(UserData[] params) {
        InputStream inputStream;
        String result = null;

        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            String url = "http://cloudmessaging-cloudfly.rhcloud.com/addUser";
            HttpPost httpPost = new HttpPost(url);

            // Convert Person object to JSON string using Jackson Lib
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(params[0]);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert InputStream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
            e.printStackTrace();
        }

        // 11. return result
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG + " onPostExecute", "" + result);
        super.onPostExecute(result);
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        inputStream.close();
        return result.toString();
    }
}
