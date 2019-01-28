package org.billboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowSurveyActivity extends AppCompatActivity implements SurveyAdapter.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    List<Survey> surveys;
    private RecyclerView recyclerView;
    private SurveyAdapter adapter;
    private NavigationView navigationView;
    private TextView username, email_user, credit;
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_survey);
        forceRTLIfSupported();

        // Status bar:
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // Menu item:
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        // Show survey item:
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<SurveyResult> call = service.getsurveys();
        call.enqueue(new Callback<SurveyResult>() {
            @Override
            public void onResponse(@Nullable Call<SurveyResult> call, @Nullable Response<SurveyResult> response) {
                SurveyResult result = response.body();
                assert result != null;
                if (result.getStatus().equals("OK")) {
                    surveys = result.getSurvey();
                    recyclerView = findViewById(R.id.recycler_view);
                    adapter = new SurveyAdapter(surveys, ShowSurveyActivity.this);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(ShowSurveyActivity.this);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<SurveyResult> call, Throwable t) {
                Toast.makeText(ShowSurveyActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Header:
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("billboard", "user:" + user.getId());
        UserId userId = new UserId(user.getId());
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<UserResult> call = service.getUser(userId);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(@NonNull Call<UserResult> call, @NonNull Response<UserResult> response) {
                if (response.isSuccessful()) {
                    UserResult result = response.body();
                    assert result != null;
                    if (result.getStatus().equals("OK")) {
                        User curUser = result.getUser();
                        DrawerLayout drawer = findViewById(R.id.drawer_layout);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.nav_header, drawer, false);
                        username = view.findViewById(R.id.username);
                        email_user = view.findViewById(R.id.email_user);
                        credit = view.findViewById(R.id.credit);
                        username.setText(curUser.getName());
                        email_user.setText(curUser.getEmail());
                        credit.setText("اعتبار: " + curUser.getCredit());
                        drawer.addView(view);
                    }
                } else {
                    Log.e("Login", "Login Failed With Code : " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResult> call, @NonNull Throwable t) {
                Toast.makeText(ShowSurveyActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        Survey clicked = surveys.get(position);
        final int id = clicked.getId();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ClickedSurveyResult> call = service.fillSurvey(id);
        call.enqueue(new Callback<ClickedSurveyResult>() {
            @Override
            public void onResponse(Call<ClickedSurveyResult> call, Response<ClickedSurveyResult> response) {
                ClickedSurveyResult surveyRes = response.body();
                assert surveyRes != null;
                if (surveyRes.getStatus().equals("OK")) {
                    Survey survey = surveyRes.getSurvey();
                    Intent intent = new Intent(ShowSurveyActivity.this, FillSurveyActivity.class);
                    intent.putExtra("survey", survey);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ClickedSurveyResult> call, Throwable t) {

            }
        });
    }


    // Menu item:
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_LONG).show();
                break;
            case R.id.home:
                Toast.makeText(getApplicationContext(), "Here is Home page!", Toast.LENGTH_LONG).show();
                break;
            case R.id.gift_history:
                Intent intent1 = new Intent(ShowSurveyActivity.this, ShowGiftHistoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.gift_shop:
                Intent intent = new Intent(ShowSurveyActivity.this, ShowGiftActivity.class);
                startActivity(intent);
                break;
            case R.id.survey:
                Toast.makeText(getApplicationContext(), "Survey", Toast.LENGTH_LONG).show();
                break;
            case R.id.setting:
                Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_LONG).show();
                break;
            case R.id.exit:
                Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_LONG).show();
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<UserResult> call = service.logout();
                call.enqueue(new Callback<UserResult>() {
                    @Override
                    public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                        Intent intent1 = new Intent(ShowSurveyActivity.this, LoginActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "شما با موفقیت خارج شدید:)", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<UserResult> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "عملیات ناموفق!!", Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}