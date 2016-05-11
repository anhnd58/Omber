package app1.ducanh.ducanhvn.omber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Dell 3360 on 5/7/2016.
 */
public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        EditText id = (EditText) findViewById(R.id.edit_id);
        EditText pass = (EditText) findViewById(R.id.edit_password);
        EditText repass = (EditText) findViewById(R.id.edit_repassword);
        EditText name = (EditText) findViewById(R.id.edit_name);
        EditText phone = (EditText) findViewById(R.id.edit_phone);

        Button signup = (Button) findViewById(R.id.button_signup);
        Button cancel = (Button) findViewById(R.id.button_cancel);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}

