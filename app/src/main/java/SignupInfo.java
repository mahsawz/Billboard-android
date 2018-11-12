package org.faradars.billboard;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                signUpFunc(username,emailInput,passwordInput);

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
    private void signUpFunc(String name,String emailInput,String passwordInput){
         final String BASE_URL = "Localhost:port/v1/user/<string:email>&<string:password>";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        GetDataService service = retrofit.create(GetDataService.class);

        //Defining the user object as we need to pass it with the call
        User user = new User(name, emailInput, passwordInput);

        //defining the call
        Call<Result> call = service.createUser(user);

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                if(response.code()==200)
                        Toast.makeText(context, response.body() != null ? response.body().getName() : null, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
