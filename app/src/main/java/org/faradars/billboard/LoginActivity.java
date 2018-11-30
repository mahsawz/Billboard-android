package org.faradars.billboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private LinearLayout loginForm;
    private EditText email;
    private EditText password;
    private Button loginBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button signUp = findViewById(R.id.signup);
        signUp.setOnClickListener(this);
        context = this.getApplicationContext();
        loginForm = this.findViewById(R.id.login_form);
        init();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.signup) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
        if (view.getId() == loginBtn.getId()) {
            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            if (isValidInput(emailInput, passwordInput)) {
                LoginInfo logininfo = new LoginInfo(passwordInput, emailInput);
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<UserResult> call = service.loginUser(logininfo);
                call.enqueue(new Callback<UserResult>() {
                    @Override
                    public void onResponse(@NonNull Call<UserResult> call, @NonNull Response<UserResult> response) {
                        UserResult result=response.body();
                        assert result != null;
                        if (result.getStatus().equals("OK")) {
                            User user = result.getUser();
                            Intent intent = new Intent(LoginActivity.this, ShowAppActivity.class);
                            intent.putExtra("user_data", user);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserResult> call, @NonNull Throwable t) {

                    }
                });
            } else {
                //TODO LOG
            }
        }
    }

    private void init() {
        email = loginForm.findViewById(R.id.email);
        password = loginForm.findViewById(R.id.password);
        loginBtn = loginForm.findViewById(R.id.login);
        signUpBtn = loginForm.findViewById(R.id.signup);
        loginBtn.setOnClickListener(this);

    }

    private boolean isValidInput(String email, String password) {
        if (email.lastIndexOf("@") <= 0 || !email.contains(".") || email.lastIndexOf(".") < email.lastIndexOf("@") || email.split("@").length > 2) {
            Toast.makeText(context, R.string.invalidEmail, Toast.LENGTH_SHORT).show();
            this.email.requestFocus();
            return false;
        }
        if (password.length() <= 0) {
            Toast.makeText(context, R.string.emptyPass, Toast.LENGTH_SHORT).show();
            this.password.requestFocus();
            return false;
        }
        return true;

    }
}
