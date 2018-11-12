package org.faradars.billboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                loginFunc(emailInput,passwordInput);
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
    private void loginFunc(String emailInput,String passwordInput){
        final String BASE_URL = "Localhost:port/v1/user/<string:email>&<string:password>";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        GetDataService service = retrofit.create(GetDataService.class);

        //Defining the user object as we need to pass it with the call
        LoginUser user = new LoginUser(emailInput, passwordInput);

        //defining the call

        //calling the api
        Call<LoginUser> call = service.loginUser(emailInput,passwordInput);

        //calling the api

    }
}
