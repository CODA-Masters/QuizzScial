package elarrecifetrivial.codamasters.com.elarrecifetrivial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Juan on 30/12/2015.
 */
public class LoginActivity extends AppCompatActivity {

    private TextView tv_user;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tv_user = (TextView) findViewById(R.id.user_text);

        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tv_user.getText().equals("")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user", tv_user.getText());
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrecta.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
