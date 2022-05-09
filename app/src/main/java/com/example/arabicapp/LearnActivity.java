package com.example.arabicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class LearnActivity extends AppCompatActivity {

    private ImageView l6, l5, l4, l3, l2, l1, l0,
            l13, l12, l11, l10, l9, l8, l7,
            l20, l19, l18, l17, l16, l15, l14,
            l27, l26, l25, l24, l23, l22, l21;

    String type;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    int User_id;
    int level;
    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        level = prefs.getInt("level", 0);
        User_id = prefs.getInt("user_id", 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learn);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getString("type");
        }

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        level = prefs.getInt("level", 0);
         User_id = prefs.getInt("User_id", 0);

        l0 = findViewById(R.id.l0);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);
        l5 = findViewById(R.id.l5);
        l6 = findViewById(R.id.l6);
        l7 = findViewById(R.id.l7);
        l8 = findViewById(R.id.l8);
        l9 = findViewById(R.id.l9);
        l10 = findViewById(R.id.l10);
        l11 = findViewById(R.id.l11);
        l12 = findViewById(R.id.l12);
        l13 = findViewById(R.id.l13);
        l14 = findViewById(R.id.l14);
        l15 = findViewById(R.id.l15);
        l16 = findViewById(R.id.l16);
        l17 = findViewById(R.id.l17);
        l18 = findViewById(R.id.l18);
        l19 = findViewById(R.id.l19);
        l20 = findViewById(R.id.l20);
        l21 = findViewById(R.id.l21);
        l22 = findViewById(R.id.l22);
        l23 = findViewById(R.id.l23);
        l24 = findViewById(R.id.l24);
        l25 = findViewById(R.id.l25);
        l26 = findViewById(R.id.l26);
        l27 = findViewById(R.id.l27);

        ImageView[] IMGS = {l6, l5, l4, l3, l2, l1, l0,
                l13, l12, l11, l10, l9, l8, l7,
                l20, l19, l18, l17, l16, l15, l14,
                l27, l26, l25, l24, l23, l22, l21};
        Intent intent;

        if (type.equals("play")) {
            intent = new Intent(getApplicationContext(), LetterActivity.class);
        } else {
            intent = new Intent(getApplicationContext(), LearnLetter.class);
        }




        for (int i = 0; i < IMGS.length; i++) {
            int finalI = i;
            if (i > level)
                IMGS[i].setAlpha(190);
            else {
                IMGS[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent.putExtra("level_id", finalI);
                        startActivity(intent);
                    }
                });
            }
        }
        {


        }


    }


}