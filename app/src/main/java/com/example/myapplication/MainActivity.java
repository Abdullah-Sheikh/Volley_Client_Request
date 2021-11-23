package com.example.myapplication;

import static com.android.volley.VolleyLog.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView text;
    String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text_id);

        createUser();
    }

    private void createUser()
    {
        String url = "https:reqres.in/api/user";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("job", "leader");
            jsonObject.put("name","morpheus");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response.Listener<JSONObject> successBlock = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                     name =response.getString("name");
                    String job  = response.getString("job");
                    int id = response.getInt("id");
                    Toast.makeText(MainActivity.this, name + job, Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        };

        Response.ErrorListener failureBlock = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.networkResponse.statusCode==404) {

                    Toast.makeText(getApplicationContext(), "404 not found", Toast.LENGTH_SHORT).show();

                }

            }
        };

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }}, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);
        
    }


}