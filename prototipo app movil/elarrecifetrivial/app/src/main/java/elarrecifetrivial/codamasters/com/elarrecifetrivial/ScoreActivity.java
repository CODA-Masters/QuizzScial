package elarrecifetrivial.codamasters.com.elarrecifetrivial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.HashMap;

public class ScoreActivity extends AppCompatActivity {

    public static final String PREFS_KEY = "myPrefs";
    public static final String ALL_QUESTIONS = "all_questions";
    public static final String RIGHT_QUESTIONS = "right_questions";
    public static final String SUM_TIME = "sum_time";
    public static final String COINS = "coins";
    private int score, time_answer;
    private TextView score_text;
    private Button back_menu, est_button;
    private static Activity context;
    private HashMap<String, Integer> total_results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score = getIntent().getIntExtra("score", 0);
        time_answer = getIntent().getIntExtra("time_answer", 0);
        score_text = (TextView) findViewById(R.id.score_text);
        back_menu = (Button) findViewById(R.id.back_menu);

        score_text.setText("Has acertado " + score + " de " + TrivialActivity.getNumQuestions() + " preguntas.");
        if(score == TrivialActivity.getNumQuestions())
            score_text.setText("Has acertado " + score + " de " + TrivialActivity.getNumQuestions() + " preguntas. Enhorabuena has conseguido una moneda.");
        back_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        est_button = (Button) findViewById(R.id.estadisticas_button);
        est_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, StatsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        context = this;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                addInterstitialAd(context, R.string.intersticial_ad_unit_id);
            }
        };

        Handler h = new Handler();
        h.postDelayed(r, 500); // Wait 0.5 seconds then show interstitial

        updatePlayerStats();
    }

    private void updatePlayerStats(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(ALL_QUESTIONS, sharedPreferences.getInt(ALL_QUESTIONS, 0) + MainActivity.NUM_QUESTIONS);
        editor.putInt(RIGHT_QUESTIONS, sharedPreferences.getInt(RIGHT_QUESTIONS, 0) + score);
        editor.putInt(SUM_TIME, sharedPreferences.getInt(SUM_TIME, 0) + time_answer);

        if(score == TrivialActivity.getNumQuestions()){
            editor.putInt(COINS, sharedPreferences.getInt(COINS, 0) + 1);
        }

        editor.commit();

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void addInterstitialAd(final Activity context, final int id) {
        final InterstitialAd interstitial = new InterstitialAd(context);
        interstitial.setAdUnitId(context.getString(id));
        interstitial.loadAd(new AdRequest.Builder().build());
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Call displayInterstitial() function
                if (interstitial.isLoaded()) {
                    interstitial.show();
                }
            }
        });
    }
}
