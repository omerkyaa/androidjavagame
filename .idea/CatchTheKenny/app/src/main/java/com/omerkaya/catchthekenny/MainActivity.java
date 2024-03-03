package com.omerkaya.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;

    int score;

    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray; //bu köşeli parantez dizi işaretidir. Resimleri tek tek tanımlamak yerine böyle yapıp dizi içine alabiliriz.

    Handler handler; //runnable çalışması için bunu yaparız.
    Runnable runnable; //resimleri belli süre aralıklarla açsın diye bunu kullanıyorum.







    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);

        score = 0;

        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);

        imageArray = new ImageView[] {imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8};
        //bu üsttekini bir loop döngüsü ouşturmak için yaptık. Resimlerin açılıp kapanması için.

        hideImages(); //bu void imageları saklayacak.

        new CountDownTimer(30000, 1000) { //bu geriye doğru zaman sayımı yapar.



            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                timeText.setText("Time OFF!"); //süre bitince bu yazsın.
                handler.removeCallbacks(runnable); //runnable'ı durdur süre bitince demiş olduk.
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE); //bu her resmi görünmez yapacak.
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // eveti seçince restart yapacak.

                        Intent intent = getIntent();
                        finish(); //güncel aktivite başlamadan önce bitmeli
                        startActivity(intent); // aktiviteyi tekrar başlatmak için



                    }
                });

                alert.setNegativeButton("No ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show(); //no diyince bu çıkacak.

                        
                    }
                });

                alert.show(); // en son bu yapılmak zorunda.



            }
        }.start(); //başlatmamız için bunu yazmalıyız.

    }

    public void increaseScore (View view) {

        score++; //score=score+1 aynısı

        scoreText.setText("Score: " + score);

    }
    public void hideImages() {

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE); //bu her resmi görünmez yapacak.
                }

                Random random = new Random(); //bunu yapıp aşağıya resim sayısını yazarsam bana rastgele resimler açar.
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500); //yarım saniyede bir rastgele resimler çıkaracak.


            }
        };

        handler.post(runnable);




    }










}