package com.example.arabicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, password;
    private Button register_btn;
    String full_name_txt, email_txt, password_txt;

    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.editText_name);
        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.password);

        register_btn = (Button) findViewById(R.id.button_register);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                full_name_txt = name.getText().toString();
                email_txt = email.getText().toString();
                password_txt = password.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                boolean flag = true;

                if (full_name_txt.isEmpty()) {
                    name.setError("This field is required");
                    flag = false;
                }
                if (email_txt.isEmpty()) {
                    email.setError("This field is required");
                    flag = false;
                }

                if (!email_txt.matches(emailPattern) )
                {
                    email.setError("Invalid email address");
                    flag = false;
                }

                if (password_txt.isEmpty()) {
                    password.setError("This field is required");
                    flag = false;
                }

                if (!isValidPassword(password_txt)) {
                    password.setError("invalid password");
                    flag = false;
                }

                if (!flag) {
                    return;
                }

                final String REQUEST_URL = Var.Signup_url;

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest jsonRequest = new StringRequest(
                        Request.Method.POST,
                        REQUEST_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("response", response);
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    Log.d("response", obj.getString("code"));
                                    if (obj.getString("code").equals("-1")) {
                                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                    } else {
                                        JSONObject data = obj.getJSONObject("data");

                                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                        editor.putInt("user_id",data.getInt("id") );
                                        editor.putInt("level", data.getInt("child_level"));
                                        editor.apply();
                                          startActivity(new Intent(getApplicationContext(),LearnOrPlayActivity.class));
                                    }
                                } catch (JSONException e) { } }
                        },
                        new Response.ErrorListener() {@Override public void onErrorResponse(VolleyError error) { }}
                ) {
                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        int mStatusCode = response.statusCode;
                        Log.d("mStatusCode", String.valueOf(mStatusCode));

                        return super.parseNetworkResponse(response);

                    }
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("name", full_name_txt);
                        MyData.put("email", email_txt);
                        MyData.put("password", password_txt);
                        return MyData;      }
                };
                requestQueue.add(jsonRequest);



            }
        });


    }

    private boolean isValidPassword(String password_txt) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9]{6,24}");

        return !TextUtils.isEmpty(password_txt) && PASSWORD_PATTERN.matcher(password_txt).matches();
    }

}