package org.faradars.billboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginInfo implements View.OnClickListener {
    private Context context;
    private LinearLayout loginForm;
    private EditText email;
    private EditText password;
    private Button loginBtn;
    private Button signUpBtn;

    public LoginInfo(Activity activity,int layoutID){
        context=activity.getApplicationContext();
        loginForm=activity.findViewById(layoutID);
        init();
    }
    private void init(){
        email=loginForm.findViewById(R.id.email);
        password=loginForm.findViewById(R.id.password);
        loginBtn=loginForm.findViewById(R.id.login);
        signUpBtn=loginForm.findViewById(R.id.signup);
        loginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==loginBtn.getId()){
            String emailInput=email.getText().toString().trim();
            String passwordInput=password.getText().toString().trim();
            if (isValidInput(emailInput,passwordInput)){
                Toast.makeText(context," ok!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidInput(String email,String password) {
        if (email.lastIndexOf("@")<=0 || !email.contains(".") || email.lastIndexOf(".") < email.lastIndexOf("@") || email.split("@").length >2) {
            Toast.makeText(context,R.string.invalidEmail,Toast.LENGTH_SHORT).show();
            this.email.requestFocus();
            return false;}
        if (password.length()<=0) {
            Toast.makeText(context,R.string.emptyPass,Toast.LENGTH_SHORT).show();
            this.password.requestFocus();
            return false;}
        return true;

    }
}
