package elarrecifetrivial.codamasters.com.elarrecifetrivial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 */
public class StartActivity extends AppCompatActivity {

    private ImageButton facebookButton, googleButton, twitterButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        facebookButton = (ImageButton) findViewById(R.id.imageButtonFaceBook);

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, AcceptActivity.class);
                startActivity(intent);
                finish();
            }
        });

        googleButton = (ImageButton) findViewById(R.id.imageButtonGoogle);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, AcceptActivity.class);
                startActivity(intent);
                finish();
            }
        });

        twitterButton = (ImageButton) findViewById(R.id.imageButtonTwitter);
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, AcceptActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton = (Button) findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
