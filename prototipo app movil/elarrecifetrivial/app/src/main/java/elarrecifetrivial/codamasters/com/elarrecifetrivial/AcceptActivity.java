package elarrecifetrivial.codamasters.com.elarrecifetrivial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 *
 */
public class AcceptActivity extends AppCompatActivity {

    private Button aceptButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);



        aceptButton = (Button) findViewById(R.id.btn_aceptar);
        aceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceptActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancelButton = (Button) findViewById(R.id.btn_cancelar);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceptActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
