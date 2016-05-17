package app1.ducanh.ducanhvn.omber;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tajse on 5/17/2016.
 */
public class Info extends AppCompatActivity {

    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        email = (TextView) findViewById(R.id.contact_email);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Send email", " ");
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:")));
                Log.i("Successful", " ");
            }
        });

    }
}
