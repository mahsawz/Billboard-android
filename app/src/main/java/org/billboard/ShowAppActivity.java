package org.billboard;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ShowAppActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showapppage);
        context = this.getApplicationContext();
        Bundle data = getIntent().getExtras();
        User user = data.getParcelable("user_data");
        Toast.makeText(context, user.getName(), Toast.LENGTH_SHORT).show();
    }
}
