package app1.ducanh.ducanhvn.omber;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharePreferences;
    EditText username, pass;
    Button login, cancel;
    TextView sign_up;

    static private final int GET_TEXT_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SignIn.CHECK_SIGNIN) {
            setContentView(R.layout.activity_main_when_signin_success);
        }
        else {
            setContentView(R.layout.activity_main);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharePreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String taikhoan, matkhau;
        taikhoan = sharePreferences.getString("TaiKhoan", "");
        matkhau = sharePreferences.getString("MatKhau", "");
        username = (EditText) findViewById(R.id.edit_id);
        pass = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_signin);
        cancel = (Button) findViewById(R.id.button_cancel);
        sign_up = (TextView) findViewById(R.id.bt_link_sign_up);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent1);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkcf, tkcf, mkdn, tkdn;
                mkcf = sharePreferences.getString("MatKhau", "");
                tkcf = sharePreferences.getString("TaiKhoan", "");
                tkdn = username.getText().toString();
                mkdn = pass.getText().toString();
                if (tkdn.equals(tkcf) && mkdn.equals(mkcf)) {
                    Toast.makeText(getApplication(), "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                    //SignIn.CHECK_SIGNIN = true
                    Intent intent2 = new Intent(MainActivity.this, MapCustomer.class);
                    startActivity(intent2);

                } else {
                    Toast.makeText(getApplication(), "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                    username.setText("");
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.map) {
            Intent intent = new Intent(MainActivity.this, MapRider.class);
            startActivity(intent);
        } else if (id == R.id.sign_out) {
            SignIn.CHECK_SIGNIN = false;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.feedback) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=")));
            }
            catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=")));
            }
        } else if (id == R.id.help) {
            Intent intent = new Intent(this, Info.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
