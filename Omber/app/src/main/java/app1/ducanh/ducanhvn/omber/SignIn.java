package app1.ducanh.ducanhvn.omber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dell 3360 on 5/7/2016.
 */
public class SignIn extends AppCompatActivity {

    EditText id, pass;
    Button login, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        id = (EditText) findViewById(R.id.edit_id);
        pass = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_signin);
        cancel = (Button) findViewById(R.id.button_cancel);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(1==1) {
                    Toast.makeText(getApplication(),"Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplication(),"Đăng nhập không thành công, hãy thử lại!",Toast.LENGTH_SHORT).show();
                    id.setText("");
                    pass.setText("");
                }
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
