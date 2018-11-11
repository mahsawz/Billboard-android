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

public class SignupInfo implements View.OnClickListener {
    private Context context;
    private LinearLayout signupForm;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confirmPass;
    private Button signUpBtn;
    public SignupInfo(Activity activity, int layoutID){
        context=activity.getApplicationContext();
        signupForm=activity.findViewById(layoutID);
        init();
    }
    private void init(){
        name=signupForm.findViewById(R.id.username);
        email=signupForm.findViewById(R.id.email1);
        password=signupForm.findViewById(R.id.password1);
        confirmPass=signupForm.findViewById(R.id.confirm_pass);
        signUpBtn=signupForm.findViewById(R.id.signup1);
        signUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==signUpBtn.getId()){
            String username=name.getText().toString().trim();
            String emailInput=email.getText().toString().trim();
            String passwordInput=password.getText().toString().trim();
            String confirm_pass=confirmPass.getText().toString().trim();
            if (isValidInput(username,emailInput,passwordInput,confirm_pass)){
                Toast.makeText(context," ok!",Toast.LENGTH_SHORT).show();
			}
		}
	}
	

    private boolean isValidInput(String name,String email,String password,String confirmPass) {
        if (email.lastIndexOf("@")<=0 || !email.contains(".") || email.lastIndexOf(".") < email.lastIndexOf("@") || email.split("@").length >2) {
            Toast.makeText(context,R.string.invalidEmail,Toast.LENGTH_SHORT).show();
            this.email.requestFocus();
            return false;}
        if (password.length()<=0) {
            Toast.makeText(context,R.string.passIsEmpty,Toast.LENGTH_SHORT).show();
            this.password.requestFocus();
            return false;}
        if (name.length()<=2) {
            Toast.makeText(context,R.string.invalidUserName,Toast.LENGTH_SHORT).show();
            this.name.requestFocus();
            return false;}
        if (!confirmPass.equals(password)){
            Toast.makeText(context,R.string.invalidConfirmPass,Toast.LENGTH_SHORT).show();
            this.confirmPass.requestFocus();
            return false;}
        return true;

    }
}
