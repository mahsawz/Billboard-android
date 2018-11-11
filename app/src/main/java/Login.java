package org.faradars.billboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        Button signUp= findViewById(R.id.signup);
        signUp.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.signup){
            Intent intent=new Intent(Login.this,SignUp.class);
            startActivity(intent);
        }
    }
}
