package com.example.arabicapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LearnOrPlayActivity extends AppCompatActivity {

    private Button menu_btn, learn, play;
    private ListView simpleList;

    String items[] = {"Home page", "Child level", "Send comment", "App evaluation"};

    int user_id, level;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        level = prefs.getInt("level", 1);
        user_id = prefs.getInt("user_id", 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_or_play);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        level = prefs.getInt("level", 1);
        user_id = prefs.getInt("user_id", 0);


        simpleList = (ListView) findViewById(R.id.listview_item);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, R.layout.list_view, R.id.textView, items);
        simpleList.setAdapter(arrayAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0: {
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));


                        break;
                    }
                    case 1: {
                        Toast.makeText(getApplicationContext(), "Your child level is" + level, Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 2: {

                        final AlertDialog.Builder popDialog = new AlertDialog.Builder(LearnOrPlayActivity.this);

                        LinearLayout linearLayout = new LinearLayout(LearnOrPlayActivity.this);
                        EditText comment = new EditText(LearnOrPlayActivity.this);

                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT
                        );
                        lp.setMargins(10, 10, 10, 10);
                        comment.setLayoutParams(lp);

                        linearLayout.addView(comment);
                        popDialog.setTitle("Add comment ");
                        popDialog.setView(linearLayout);
                        popDialog.setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        String comment_txt = comment.getText().toString();
                                        if (!comment_txt.equals(""))
                                            addComment(comment_txt);
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        popDialog.create();
                        popDialog.show();


                        break;

                    }
                    case 3: {

                        final AlertDialog.Builder popDialog = new AlertDialog.Builder(LearnOrPlayActivity.this);
                        LinearLayout linearLayout = new LinearLayout(LearnOrPlayActivity.this);
                        final RatingBar rating = new RatingBar(LearnOrPlayActivity.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        rating.setLayoutParams(lp);
                        rating.setNumStars(5);
                        rating.setStepSize(1);
                        linearLayout.addView(rating);
                        popDialog.setIcon(android.R.drawable.btn_star_big_on);
                        popDialog.setTitle("Add Rating: ");

                        popDialog.setView(linearLayout);

                        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            @Override
                            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                            }
                        });

                        popDialog.setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        String rating_txt = String.valueOf(rating.getProgress());
                                        addRating(rating_txt);
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        popDialog.create();
                        popDialog.show();


                        break;

                    }
                    default:

                        break;

                }

            }
        });
        menu_btn = findViewById(R.id.button_menu);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleList.setVisibility(View.VISIBLE);
                menu_btn.setVisibility(View.INVISIBLE);
            }
        });


        learn = findViewById(R.id.learn);
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LearnActivity.class);
                intent.putExtra("type", "learn");
                startActivity(intent);
            }
        });


        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LearnActivity.class);
                intent.putExtra("type", "play");
                startActivity(intent);

            }
        });


    }

    private void addRating(String rating_txt) {
        final String REQUEST_URL = Var.add_rating;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest jsonRequest = new StringRequest(
                Request.Method.POST,
                REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startActivity(new Intent(getApplicationContext(), LearnOrPlayActivity.class));
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
                return super.parseNetworkResponse(response);
            }

            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();

                MyData.put("user_id", String.valueOf(user_id));
                MyData.put("rating", rating_txt);

                return MyData;
            }
        };
        requestQueue.add(jsonRequest);


    }

    private void addComment(String comment_txt) {
        final String REQUEST_URL = Var.add_comment;

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest jsonRequest = new StringRequest(
                Request.Method.POST,
                REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startActivity(new Intent(getApplicationContext(), LearnOrPlayActivity.class));
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
                return super.parseNetworkResponse(response);
            }

            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();

                MyData.put("user_id", String.valueOf(user_id));
                MyData.put("comment", comment_txt);

                return MyData;
            }
        };
        requestQueue.add(jsonRequest);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        simpleList.setVisibility(View.INVISIBLE);
        menu_btn.setVisibility(View.VISIBLE);
        return super.onTouchEvent(event);
    }
}