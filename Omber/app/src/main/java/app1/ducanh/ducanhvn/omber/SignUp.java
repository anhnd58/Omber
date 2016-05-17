package app1.ducanh.ducanhvn.omber;

import android.content.Context;
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
public class SignUp extends AppCompatActivity {
    SharedPreferences sharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        final EditText textUser = (EditText) findViewById(R.id.edit_id);
        final EditText textPass = (EditText) findViewById(R.id.edit_password);
        final EditText textPassAgain = (EditText) findViewById(R.id.edit_repassword);
        final EditText textName = (EditText) findViewById(R.id.edit_name);
        final EditText textPhone = (EditText) findViewById(R.id.edit_phone);
        final EditText textEmail = (EditText) findViewById(R.id.edit_mail);


        Button signup = (Button) findViewById(R.id.button_signup);
        Button cancel = (Button) findViewById(R.id.button_cancel);

        sharePreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String taikhoan, matkhau;
        taikhoan = sharePreferences.getString("TaiKhoan", "");
        matkhau = sharePreferences.getString("MatKhau","");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kiểm tra tên ĐN, email, sđt, pass
                if (!checkNull(textUser.getText().toString())){
                    Toast.makeText(SignUp.this, "Chưa có tên đăng nhập", Toast.LENGTH_SHORT).show();
                }
                if (!checkNull(textPass.getText().toString())){
                    Toast.makeText(SignUp.this, "Chưa có mật khẩu", Toast.LENGTH_SHORT).show();
                }
                if (!checkNull(textName.getText().toString())){
                    Toast.makeText(SignUp.this, "Bạn chưa đặt tên", Toast.LENGTH_SHORT).show();
                }
                if (!checkEmail(textEmail.getText().toString())){
                    Toast.makeText(SignUp.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                if (!checkPhone(textPhone.getText().toString())) {
                    Toast.makeText(SignUp.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                if (!checkPass(textPass.getText().toString(), textPassAgain.getText().toString())){
                    Toast.makeText(SignUp.this, "Mật khẩu chưa khớp", Toast.LENGTH_SHORT).show();
                }
                if (!checkUserName(textUser.getText().toString())){
                    Toast.makeText(SignUp.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                }
                //nạp vào CSDL
                SharedPreferences.Editor edit = sharePreferences.edit();
                edit.putString("TaiKhoan",textUser.getText().toString());
                edit.putString("MatKhau",textPass.getText().toString());
                edit.putString("TenTK", textName.getText().toString());
                edit.putString("SoDT", textPhone.getText().toString());
                edit.commit();
                Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();

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

    private boolean checkNull(String str){
        if (str.length() == 0) return false;
        return true;
    }

    private boolean checkEmail(String email){
        for (int i = 0; i < email.length(); i++){
            if (email.charAt(i) == '@') return true;
        }
        return false;
    }

    private  boolean checkPhone(String phone){
        if (!checkNull(phone)) return false;
        for (int i = 0; i < phone.length(); i++){
            if (phone.charAt(i) < '0' || phone.charAt(i) > '9') return false;
            i++;
        }
        return true;
    }

    private boolean checkPass(String pass, String passAgain){
        if (pass.equals(passAgain)) return true;
        return false;
    }

    private boolean checkUserName(String username){
        String tkcf = sharePreferences.getString("TaiKhoan", "");
        if (username.equals(tkcf)) return false;
        return true;
    }
}

