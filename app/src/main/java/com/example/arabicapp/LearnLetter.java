package com.example.arabicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class LearnLetter extends AppCompatActivity {


    int level_id;

    ImageView imageView3,imageView8;


    MediaPlayer mp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_letter);

        imageView3=findViewById(R.id.imageView5);
        imageView8=findViewById(R.id.imageView8);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            level_id = extras.getInt("level_id");
        }



        switch (level_id) {
            case 0: {
                imageView3.setImageResource(R.drawable.a);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.alif_001);

                break;
            }
            case 1: {
                imageView3.setImageResource(R.drawable.b);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.ba_002);

                break;
            }
            case 2: {
                imageView3.setImageResource(R.drawable.t);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.taa_003);


                break;

            }
            case 3: {
                imageView3.setImageResource(R.drawable.th);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.tha_004);

                break;

            }
            case 4: {
                imageView3.setImageResource(R.drawable.g);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.jeem_005);

                break;
            }
            case 5: {
                imageView3.setImageResource(R.drawable.hh);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.haa_006);

                break;
            }
            case 6: {
                imageView3.setImageResource(R.drawable.kh);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.khaa_007);

                break;

            }
            case 7: {
                imageView3.setImageResource(R.drawable.d);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.dal_008);

                break;
            }
            case 8: {
                imageView3.setImageResource(R.drawable.z);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.dhal_009);

                break;

            }
            case 9: {
                imageView3.setImageResource(R.drawable.r);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.raa_010);

                break;
            }
            case 10: {
                imageView3.setImageResource(R.drawable.zz);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.jaa_011);

                break;

            }
            case 11: {
                imageView3.setImageResource(R.drawable.s);
                                mp = MediaPlayer.create(getApplicationContext(), R.raw.seen_012);

                break;

            }
            case 12: {
                imageView3.setImageResource(R.drawable.sh);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.sheen_013);

                break;

            }
            case 13: {
                imageView3.setImageResource(R.drawable.ss);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.saad_014);

                break;

            }
            case 14: {
                imageView3.setImageResource(R.drawable.dd);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.dhaad_015);

                break;

            }
            case 15: {
                imageView3.setImageResource(R.drawable.tt);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.toa_016);

                break;

            }
            case 16: {
                imageView3.setImageResource(R.drawable.zth);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.dhaa_017);

                break;

            }
            case 17: {
                imageView3.setImageResource(R.drawable.aa);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.ain_018);

                break;

            }
            case 18: {
                imageView3.setImageResource(R.drawable.gh);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.ghain_019);

                break;

            }
            case 19: {
                imageView3.setImageResource(R.drawable.f);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.faa_020);


                break;
            }
            case 20: {
                imageView3.setImageResource(R.drawable.kk);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.qaaf_021);

                break;

            }
            case 21: {
                imageView3.setImageResource(R.drawable.k);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.kaaf_022);

                break;

            }
            case 22: {
                imageView3.setImageResource(R.drawable.l);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.laam_023);

                break;

            }
            case 23: {
                imageView3.setImageResource(R.drawable.m);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.meem_024);

                break;

            }
            case 24: {
                imageView3.setImageResource(R.drawable.n);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.noon_025);

                break;

            }
            case 25: {
                imageView3.setImageResource(R.drawable.h);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.ha_026);

                break;
            }
            case 26: {
                imageView3.setImageResource(R.drawable.w);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.waw_027);

                break;

            }
            case 27: {
                imageView3.setImageResource(R.drawable.y);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.yaa_028);


                break;
            }

            default:

                break;

        }


        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.setLooping(false);
                mp.seekTo(0);
                mp.start();
            }
        });

    }





}