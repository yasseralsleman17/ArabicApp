package com.example.arabicapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LetterActivity extends AppCompatActivity {

    RequestQueue rQueue;
    JSONObject jsonObject;


    String REQUEST_URL = "http:/192.168.100.143:5000/uploadfile?letter=";

    String letters[] = {"أ",
            "ب",
            "ت",
            "ث",
            "ج",
            "ح",
            "خ",
            "د",
            "ذ",
            "ر",
            "ز",
            "س",
            "ش",
            "ص",
            "ض",
            "ط",
            "ظ",
            "ع",
            "غ",
            "ف",
            "ق",
            "ك",
            "ل",
            "م",
            "ن",
            "ه",
            "و",
            "ي"
    };

    ImageView imageView3, imageView6;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    ImageButton imageButton;

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String fileName = null;

    private MediaRecorder recorder = null;

    private MediaPlayer player = null;

    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted) finish();

    }

    int level, user_id, level_id;

    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        level = prefs.getInt("level", 0);
        user_id = prefs.getInt("user_id", 0);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        imageView3 = findViewById(R.id.imageView3);
        imageView6 = findViewById(R.id.imageView6);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            level_id = extras.getInt("level_id");
        }


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        level = prefs.getInt("level", 0);
        user_id = prefs.getInt("user_id", 0);

        REQUEST_URL += letters[level_id];

        switch (level_id) {
            case 0: {
                imageView3.setImageResource(R.drawable.a);

                break;
            }
            case 1: {
                imageView3.setImageResource(R.drawable.b);

                break;
            }
            case 2: {
                imageView3.setImageResource(R.drawable.t);
                break;

            }
            case 3: {
                imageView3.setImageResource(R.drawable.th);
                break;

            }
            case 4: {
                imageView3.setImageResource(R.drawable.g);

                break;
            }
            case 5: {
                imageView3.setImageResource(R.drawable.hh);

                break;
            }
            case 6: {
                imageView3.setImageResource(R.drawable.kh);
                break;

            }
            case 7: {
                imageView3.setImageResource(R.drawable.d);

                break;
            }
            case 8: {
                imageView3.setImageResource(R.drawable.z);
                break;

            }
            case 9: {
                imageView3.setImageResource(R.drawable.r);

                break;
            }
            case 10: {
                imageView3.setImageResource(R.drawable.zz);
                break;

            }
            case 11: {
                imageView3.setImageResource(R.drawable.s);
                break;

            }
            case 12: {
                imageView3.setImageResource(R.drawable.sh);
                break;

            }
            case 13: {
                imageView3.setImageResource(R.drawable.ss);
                break;

            }
            case 14: {
                imageView3.setImageResource(R.drawable.dd);
                break;

            }
            case 15: {
                imageView3.setImageResource(R.drawable.tt);
                break;

            }
            case 16: {
                imageView3.setImageResource(R.drawable.zth);
                break;

            }
            case 17: {
                imageView3.setImageResource(R.drawable.aa);
                break;

            }
            case 18: {
                imageView3.setImageResource(R.drawable.gh);
                break;

            }
            case 19: {
                imageView3.setImageResource(R.drawable.f);

                break;
            }
            case 20: {
                imageView3.setImageResource(R.drawable.kk);
                break;

            }
            case 21: {
                imageView3.setImageResource(R.drawable.k);
                break;

            }
            case 22: {
                imageView3.setImageResource(R.drawable.l);
                break;

            }
            case 23: {
                imageView3.setImageResource(R.drawable.m);
                break;

            }
            case 24: {
                imageView3.setImageResource(R.drawable.n);
                break;

            }
            case 25: {
                imageView3.setImageResource(R.drawable.h);

                break;
            }
            case 26: {
                imageView3.setImageResource(R.drawable.w);
                break;

            }
            case 27: {
                imageView3.setImageResource(R.drawable.y);

                break;
            }

            default:

                break;

        }

        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/record_" + "a" + ".ogg";

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {


                    try {
                        stopRecording();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    if (ActivityCompat.checkSelfPermission(LetterActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(LetterActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);

                    } else {
                        startRecording();
                    }

                }
                return false;

            }
        });

        if (ActivityCompat.checkSelfPermission(LetterActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(LetterActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);

        }

    }

    public void showDialogThird(Activity activity, String msg) {

        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.correct);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButton1 = (Button) dialog.findViewById(R.id.ok);
        dialogButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }


    private void startRecording() {

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.OGG);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.OPUS);

        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }


    }

    private void stopRecording() throws IOException {
        recorder.stop();
        recorder.release();
        recorder = null;
        upload(fileName);

    }

    public void upload(String path) throws IOException {
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient().newBuilder()
                .build();
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("text/plain");
        okhttp3.RequestBody body = new okhttp3.MultipartBody.Builder().setType(okhttp3.MultipartBody.FORM)
                .addFormDataPart("file", "record_" + "a" + ".ogg",
                        okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/octet-stream"),
                                new File(path)))
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(REQUEST_URL)
                .method("POST", body)
                .build();

        new Thread() {

            public void run() {
                okhttp3.Response response = null;
                Looper.prepare();
                try {
                    response = client.newCall(request).execute();
                    String message = response.body().string();

                    if (message.equals("correct")) {
                        showDialogThird(LetterActivity.this, "أحسنت لفظك رائع");
                        if (level_id == level)
                            updetlevel();
                    } else {
                        showDialogThird(LetterActivity.this, "لا بأس حاول مجددا يا صغيري");
                    }
                } catch (IOException e) {
                }
                Looper.loop();
            }
        }.start();
    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        player.release();
        player = null;
    }

    private void updetlevel() {

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("level", level + 1);
        editor.apply();
        final String REQUEST_URL = Var.updet_level_url;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest jsonRequest = new StringRequest(
                Request.Method.POST,
                REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                MyData.put("child_level", String.valueOf(level + 1));

                return MyData;
            }
        };
        requestQueue.add(jsonRequest);


    }

    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }

        if (player != null) {
            player.release();
            player = null;
        }
    }


}