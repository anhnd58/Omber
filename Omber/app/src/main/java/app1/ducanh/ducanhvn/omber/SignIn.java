package app1.ducanh.ducanhvn.omber;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static boolean CHECK_SIGNIN = false;
    SharedPreferences sharePreferences;
    EditText username, pass;
    Button login, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        sharePreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String taikhoan, matkhau;
        taikhoan = sharePreferences.getString("TaiKhoan", "");
        matkhau = sharePreferences.getString("MatKhau", "");
        username = (EditText) findViewById(R.id.edit_id);
        pass = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_signin);
        cancel = (Button) findViewById(R.id.button_cancel);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkcf,tkcf,mkdn, tkdn;
                mkcf = sharePreferences.getString("MatKhau","");
                tkcf = sharePreferences.getString("TaiKhoan", "");
                tkdn = username.getText().toString();
                mkdn = pass.getText().toString();
                if(tkdn.equals(tkcf) && mkdn.equals(mkcf)){
                    Toast.makeText(getApplication(),"Đăng nhập thành công ",Toast.LENGTH_SHORT).show();
                    Intent sgintent = getIntent();
                    CHECK_SIGNIN = true;
                    sgintent.putExtra("isLogin", CHECK_SIGNIN);
                    setResult(RESULT_OK, sgintent);
                    finish();
                    Intent intent2 = new Intent(SignIn.this, MapCustomer.class);
                    startActivity(intent2);
                }else{
                    Toast.makeText(getApplication(),"Đăng nhập không thành công",Toast.LENGTH_SHORT).show();
                    username.setText("");
                    pass.setText("");
                }

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                pass.setText("");
            }
        });

    }
}
