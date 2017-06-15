package vyom.com.gpstracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.support.v7.media.MediaControlIntent.EXTRA_MESSAGE;

public class Register extends AppCompatActivity {

    Button btnprofile;
    private EditText i_phonenumber;
    private EditText i_Fullname;
    private EditText i_EmailAddress;
    private EditText i_SOS1;
    private EditText i_SOS2;
    private FirebaseAuth auth;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnprofile = (Button) findViewById(R.id.btn_reg);
        i_phonenumber = (EditText) findViewById(R.id.edi_pass_no);
        i_Fullname = (EditText) findViewById(R.id.edi_full_name);
        i_EmailAddress = (EditText) findViewById(R.id.edi_email_address);
        i_SOS1 = (EditText) findViewById(R.id.edi_sos_1);
        i_SOS2 = (EditText) findViewById(R.id.edi_sos_2);
        progressbar = (ProgressBar) findViewById(R.id.progress_bar);

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String passnumber = i_phonenumber.getText().toString().trim();
                final String fullname = i_Fullname.getText().toString().trim();
                final String emailadress = i_EmailAddress.getText().toString().trim();
                final String sos1 = i_SOS1.getText().toString().trim();
                String sos2 = i_SOS2.getText().toString().trim();

                if (TextUtils.isEmpty(passnumber)) {
                    Toast.makeText(getApplicationContext(), "Enter password(phonenumber)!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(fullname)) {
                    Toast.makeText(getApplicationContext(), "Enter Fullname !", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(emailadress)) {
                    Toast.makeText(getApplicationContext(), "Enter emailaddress !", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(sos1)) {
                    Toast.makeText(getApplicationContext(), "Enter SOS number 1 !", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(sos2)) {
                    Toast.makeText(getApplicationContext(), "Enter SOS number 2 !", Toast.LENGTH_SHORT).show();
                }

                progressbar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(emailadress, passnumber).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressbar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Intent i=new Intent(Register.this,MainActivity.class);
                            i.putExtra(EXTRA_MESSAGE,passnumber);
                            i.putExtra(EXTRA_MESSAGE,emailadress);
                            i.putExtra(EXTRA_MESSAGE,fullname);
                            i.putExtra(EXTRA_MESSAGE,sos1);
                            startActivity(i);
                            finish();
                        }
                    }
                });

            }
        });
    }

    protected void onResume() {
        super.onResume();
        progressbar.setVisibility(View.GONE);
    }
}