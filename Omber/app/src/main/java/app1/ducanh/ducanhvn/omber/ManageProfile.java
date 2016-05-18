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
 * Created by Dell 3360 on 5/12/2016.
 */
public class ManageProfile extends AppCompatActivity {
    SharedPreferences sharePreferences;
    EditText rename, rephone, address;
    Button save, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_profile);
        sharePreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String ten, sodienthoai,diachi;
        ten = sharePreferences.getString("TenTK", "");
        sodienthoai = sharePreferences.getString("SoDT", "");
        diachi = sharePreferences.getString("DiaChi", "");
        rename =(EditText) findViewById(R.id.et_name_profile);
        rename.setText(ten.toString());
        rephone =(EditText) findViewById(R.id.et_phone_profile);
        rephone.setText(sodienthoai.toString());
        address =(EditText) findViewById(R.id.et_address_profile);
        if(diachi!=null){
            address.setText(diachi.toString());
        }
        save = (Button) findViewById(R.id.bt_save_profile);
        cancel = (Button) findViewById(R.id.bt_cancel_profile);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sharePreferences.edit();
                edit.putString("DiaChi",address.getText().toString());
                Toast.makeText(ManageProfile.this,"Cập nhật thành công", Toast.LENGTH_SHORT).show();
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



}
