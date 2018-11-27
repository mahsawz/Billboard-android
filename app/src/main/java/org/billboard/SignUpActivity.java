package org.billboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context;
    private LinearLayout signupForm;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confirmPass;
    private Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuppage);
        context=this.getApplicationContext();
        signupForm=this.findViewById(R.id.signup_form);
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
                SignupInfo signupInfo=new SignupInfo(username,emailInput,passwordInput);
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call <User> call = service.createUser(signupInfo);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            Intent intent = new Intent(SignUpActivity.this, ShowAppActivity.class);
                            intent.putExtra("user_data", user);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

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
