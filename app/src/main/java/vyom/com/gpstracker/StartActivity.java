package vyom.com.gpstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    Button registerbutton;
    private TextView alreadY_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        registerbutton=(Button)findViewById(R.id.bt_reg);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in10=new Intent(StartActivity.this,Register.class);
                startActivity(in10);
            }
        });

        alreadY_reg=(TextView)findViewById(R.id.tv_alrady_a_user);
        alreadY_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rady=new Intent(StartActivity.this,AlreadyRegistered.class);
                startActivity(rady);
            }
        });

    }
}
