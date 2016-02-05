package elarrecifetrivial.codamasters.com.elarrecifetrivial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends /*FragmentActivity*/AppCompatActivity {
    private FragmentTabHost tabHost;
    public static final int NUM_QUESTIONS=5;
    private static final int NUM_AVAIBLE_QUESTIONS = 9;
    private static final String YOUTUBE_CHANNEL = "https://www.youtube.com/user/elarrecife";
    private Button startButton, statsButton, muroButton, tab_tmp, logoutButton;
    private TextView tv_coins;

    public static DBHelper db;
    private ArrayList<Integer> id_questions;
    private Question question;
    private int i_question=0;
    private int score=0;
    private static int coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

/*
        tabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("StatsActivity").setIndicator("trivial"),
                StatsActivity.class, null);
        tabHost.addTab(tabHost.newTabSpec("StatsActivity").setIndicator("radio group"),
                StatsActivity.class, null);
        tabHost.addTab(tabHost.newTabSpec("StatsActivity").setIndicator("reloj"),
                StatsActivity.class, null);
*/

        tab_tmp = (Button) findViewById(R.id.imageButton);
        tab_tmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Funcionalidad en desarrollo", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });




        id_questions = randomArrayList(NUM_QUESTIONS);
        initDB();

        question = db.getQuestion(id_questions.get(i_question));

        loadCoins();
        initView();
    }

    private void loadCoins(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ScoreActivity.PREFS_KEY, Context.MODE_PRIVATE);
        coins = sharedPreferences.getInt(ScoreActivity.COINS, 0);
    }

    private void initView(){
/*        tv_coins = (TextView) findViewById(R.id.coins);
        tv_coins.setText(Integer.toString(coins));
*/
        startButton = (Button) findViewById(R.id.button_start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.getType() == Question.IMAGE_QUESTION) {
                    Intent intent = new Intent(MainActivity.this, TrivialImageActivity.class);
                    intent.putIntegerArrayListExtra("id_questions", id_questions);
                    intent.putExtra("i_question", i_question);
                    intent.putExtra("score", score);
                    intent.putExtra("time_answer", 0);
                    startActivity(intent);
                    /*finish();*/
                } else {
                    Intent intent = new Intent(MainActivity.this, TrivialActivity.class);
                    intent.putIntegerArrayListExtra("id_questions", id_questions);
                    intent.putExtra("i_question", i_question);
                    intent.putExtra("score", score);
                    intent.putExtra("time_answer", 0);
                    startActivity(intent);
                    /*finish();*/
                }
            }
        });

        statsButton = (Button) findViewById(R.id.button_stats);

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(intent);
                /*finish();*/
            }
        });

        muroButton = (Button) findViewById(R.id.button_muro);

        muroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://i.emezeta.com/cache/img/1355_o.jpg");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
                /*finish();*/
            }
        });



    }

    private void initDB(){
        db = new DBHelper(this);
        try {
            db.createDataBase();
            db.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBHelper getDB(){
        return db;
    }

    public ArrayList<Integer> randomArrayList(int n){
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < n; i++)
        {
            int aux = random.nextInt(NUM_AVAIBLE_QUESTIONS)+1;
            while(list.contains(aux)){
                aux = random.nextInt(NUM_AVAIBLE_QUESTIONS)+1;
            }
            list.add(aux);
        }
        return list;
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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        if (id == R.id.imageButton) {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "Funcionalidades en desarrollo", Toast.LENGTH_SHORT);
            toast1.show();
        }

        return super.onOptionsItemSelected(item);
    }


}
