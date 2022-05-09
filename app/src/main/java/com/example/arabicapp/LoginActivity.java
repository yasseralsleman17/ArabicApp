package com.example.arabicapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.arabicapp.Admin.AdminHomePage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private EditText usernane, password;
    private Button logIn;
    private String user_name_txt, password_txt;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernane = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);

        logIn = (Button) findViewById(R.id.button_login);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean flag = true;

                user_name_txt = usernane.getText().toString();
                password_txt = password.getText().toString();

                if (user_name_txt.isEmpty()) {
                    usernane.setError("This field is required");
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


                final String REQUEST_URL = Var.Signin_url;

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest jsonRequest = new StringRequest(
                        Request.Method.POST,
                        REQUEST_URL,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    Log.d("response", obj.getString("code"));
                                    if (obj.getString("code").equals("-1")) {
                                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                    } else {


                                        JSONObject data = obj.getJSONObject("data");
                                        if (data.getString("type").equals("2")) {
                                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

                                            editor.putInt("user_id", data.getInt("id"));
                                            editor.putInt("level", data.getInt("child_level"));
                                            editor.apply();

                                            startActivity(new Intent(getApplicationContext(), LearnOrPlayActivity.class));

                                        } else if (data.getString("type").equals("1")) {
                                            startActivity(new Intent(getApplicationContext(), AdminHomePage.class));


                                        }
                                    }
                                } catch (JSONException e) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }
                ) {
                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        int mStatusCode = response.statusCode;
                        Log.d("mStatusCode", String.valueOf(mStatusCode));


                        return super.parseNetworkResponse(response);

                    }

                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();

                        MyData.put("email", user_name_txt);
                        MyData.put("password", password_txt);
                        return MyData;
                    }
                };
                requestQueue.add(jsonRequest);

            }

        });


    }

    private boolean isValidPassword(String password_txt) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9]{3,24}");

        return !TextUtils.isEmpty(password_txt) && PASSWORD_PATTERN.matcher(password_txt).matches();
    }

}