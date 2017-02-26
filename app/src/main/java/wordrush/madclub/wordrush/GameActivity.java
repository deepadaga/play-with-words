package wordrush.madclub.wordrush;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class GameActivity extends AppCompatActivity {

    private ImageView im1, im2, im3, im4, image1, image2, image3, image4;
    private Button check;
    private TextView skip_bt, home;
    private Button end;
    private TextView currentScore, test;
    WordRushDictionary dictionary;
    String word;
    ProgressBar mprogressBar;
    String sortedWord;
    int counter = 0;
    int a[] = new int[4];
    char[] array;
    boolean flag;
    String finalword;
    ImageView imageviewdown[] = new ImageView[4];
    ImageView imageViewup[] = new ImageView[4];
    char[] validWord = new char[4];
    private int score = 0;
    private int skip = 0;
    Counter timer;
    long currentTime;
    MediaPlayer player;
    int image[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o,
            R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v, R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        player = MediaPlayer.create(this, R.raw.song);
        player.start();
        setupView();
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new WordRushDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }


        newElement();
        timer = new Counter((long) 100000, 1000);
        timer.start();
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < 4) {
                    imageViewup[counter].setImageResource(image[a[0]]);
                    validWord[counter] = (char) (a[0] + 97);
                    counter++;
                } else {
                    im1.setImageResource(R.drawable.zero);
                    im2.setImageResource(R.drawable.zero);
                    im3.setImageResource(R.drawable.zero);
                    im4.setImageResource(R.drawable.zero);
                    counter = 0;

                }

            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < 4) {
                    imageViewup[counter].setImageResource(image[a[1]]);
                    validWord[counter] = (char) (a[1] + 97);
                    counter++;
                } else {
                    im1.setImageResource(R.drawable.zero);
                    im2.setImageResource(R.drawable.zero);
                    im3.setImageResource(R.drawable.zero);
                    im4.setImageResource(R.drawable.zero);
                    counter = 0;

                }
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < 4) {
                    imageViewup[counter].setImageResource(image[a[2]]);
                    validWord[counter] = (char) (a[2] + 97);
                    counter++;
                } else {
                    im1.setImageResource(R.drawable.zero);
                    im2.setImageResource(R.drawable.zero);
                    im3.setImageResource(R.drawable.zero);
                    im4.setImageResource(R.drawable.zero);
                    counter = 0;

                }
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < 4) {
                    imageViewup[counter].setImageResource(image[a[3]]);
                    validWord[counter] = (char) (a[3] + 97);
                    counter++;
                } else {
                    im1.setImageResource(R.drawable.zero);
                    im2.setImageResource(R.drawable.zero);
                    im3.setImageResource(R.drawable.zero);
                    im4.setImageResource(R.drawable.zero);
                    counter = 0;

                }
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalword = new String(validWord);
                flag = dictionary.isword(sortedWord, finalword);
                if (flag) {
                    score++;
                    reset();
                    newElement();
                    timer.cancel();
                    timer = new Counter((long) currentTime + 1000, 1000);
                    timer.start();
                } else {
                    im1.setImageResource(R.drawable.zero);
                    im2.setImageResource(R.drawable.zero);
                    im3.setImageResource(R.drawable.zero);
                    im4.setImageResource(R.drawable.zero);
                    counter = 0;
                }
                if (score % 3 == 0 && score != 0 && flag)
                    skip++;
                if (skip != 0)
                    skip_bt.setVisibility(View.VISIBLE);
                currentScore.setText(score + "");


            }
        });
        skip_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                newElement();
                skip--;
                if (skip == 0)
                    skip_bt.setVisibility(View.INVISIBLE);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.setImageResource(R.drawable.zero);
                im2.setImageResource(R.drawable.zero);
                im3.setImageResource(R.drawable.zero);
                im4.setImageResource(R.drawable.zero);
                counter = 0;
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                player.stop();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
        timer.cancel();
    }

    public void reset() {
        image1.setImageResource(R.drawable.zero);
        image2.setImageResource(R.drawable.zero);
        image3.setImageResource(R.drawable.zero);
        image4.setImageResource(R.drawable.zero);
        im1.setImageResource(R.drawable.zero);
        im2.setImageResource(R.drawable.zero);
        im3.setImageResource(R.drawable.zero);
        im4.setImageResource(R.drawable.zero);
        counter = 0;

    }

    public void newElement() {
        word = dictionary.SelectRandomWord();
        sortedWord = dictionary.sortedArray(word);
        array = dictionary.array(sortedWord);
        for (int i = 0; i < array.length; i++) {
            int temp = (int) array[i];
            int ind = temp - 97;
            imageviewdown[i].setImageResource(image[ind]);
            a[i] = ind;
        }
    }

    public void setupView() {
        im1 = (ImageView) findViewById(R.id.iv1);
        im2 = (ImageView) findViewById(R.id.iv2);
        im3 = (ImageView) findViewById(R.id.iv3);
        im4 = (ImageView) findViewById(R.id.iv4);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        check = (Button) findViewById(R.id.check);
        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        currentScore = (TextView) findViewById(R.id.score);
        test = (TextView) findViewById(R.id.test);
        skip_bt = (TextView) findViewById(R.id.skip);
        home = (TextView) findViewById(R.id.end);
        end = (Button) findViewById(R.id.clear);
        imageviewdown[0] = image1;
        imageviewdown[1] = image2;
        imageviewdown[2] = image3;
        imageviewdown[3] = image4;
        imageViewup[0] = im1;
        imageViewup[1] = im2;
        imageViewup[2] = im3;
        imageViewup[3] = im4;

    }


    public class Counter extends CountDownTimer {
        long millis_initial;

        public Counter(Long millisinFuture, long countdowninterval) {
            super(millisinFuture, countdowninterval);
            millis_initial = millisinFuture;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            currentTime = millisUntilFinished;
            test.setText("" + millisUntilFinished / 1000);
            mprogressBar.setProgress((int) (((millisUntilFinished / 1000))));

        }

        @Override
        public void onFinish() {
            Toast.makeText(getApplicationContext(), "Times Up !!", Toast.LENGTH_LONG).show();
            Intent play = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(play);
            test.setText("Done");
            finish();
        }
    }

}
