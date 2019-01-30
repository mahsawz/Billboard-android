package org.faradars.billboard;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FillSurveyActivity extends AppCompatActivity implements QusetionsAdapter.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private User user;
    private NavigationView navigationView;
    private TextView username, email_user, credit,surveyName;
    private Survey survey,curSurvey;
    private RecyclerView recyclerView;
    private QusetionsAdapter qAdapter;
    private Button submit;
    private List<String> selected;
    private String selectItems[];

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_survey);
        Bundle data = getIntent().getExtras();
        assert data != null;
        user = data.getParcelable("user_data");
        survey=data.getParcelable("survey_data");
        submit=findViewById(R.id.submit_survey);
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
        navigationView.setNavigationItemSelectedListener(this);

        //show questions

        if (survey==null)
            Log.d("billboard", "survey nullllllll:");
        //Log.d("billboard", "survey:"+survey.getQuestions());
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ClickedSurveyResult> call = service.fillSurvey(survey.getId());
        call.enqueue(new Callback<ClickedSurveyResult>() {
            @Override
            public void onResponse(Call<ClickedSurveyResult> call, Response<ClickedSurveyResult> response) {
                ClickedSurveyResult surveyRes = response.body();
                assert surveyRes != null;
                if (surveyRes.getStatus().equals("OK")) {
                    curSurvey = surveyRes.getSurvey();
                    surveyName=findViewById(R.id.survey_name);
                    surveyName.setText(curSurvey.getTitle());
                    recyclerView = findViewById(R.id.questionsRecycler);
                    qAdapter = new QusetionsAdapter(curSurvey.getQuestions(),FillSurveyActivity.this );
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(FillSurveyActivity.this);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(qAdapter);
                    selectItems=new String[curSurvey.getQuestions().size()];

                }
            }

            @Override
            public void onFailure(Call<ClickedSurveyResult> call, Throwable t) {

            }
        });
        submit.setOnClickListener(this);

    }
        // Header:
        @Override
        protected void onResume () {
            super.onResume();
            //Log.d("billboard", "user:" + user.getId());
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
                            username = drawer.findViewById(R.id.username);
                            email_user = drawer.findViewById(R.id.email_user);
                            credit = drawer.findViewById(R.id.credit);
                            username.setText(user.getName());
                            email_user.setText(curUser.getEmail());
                            credit.setText("اعتبار: " + curUser.getCredit());
                        }
                    } else {
                        Log.e("Login", "Login Failed With Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserResult> call, @NonNull Throwable t) {
                    Toast.makeText(FillSurveyActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
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
                //Toast.makeText(getApplicationContext(), "Here is Home page!", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(FillSurveyActivity.this, ShowAppActivity.class);
                intent3.putExtra("user_data", user);
                startActivity(intent3);
                break;
            case R.id.gift_history:
                Intent intent1 = new Intent(FillSurveyActivity.this, ShowGiftHistoryActivity.class);
                intent1.putExtra("user_data", user);
                startActivity(intent1);
                break;
            case R.id.gift_shop:
                Intent intent = new Intent(FillSurveyActivity.this, ShowGiftActivity.class);
                intent.putExtra("user_data", user);
                startActivity(intent);
                break;
            case R.id.survey:
                Intent intent4 = new Intent(FillSurveyActivity.this, ShowSurveyActivity.class);
                intent4.putExtra("user_data", user);
                startActivity(intent4);
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
                        Intent intent1 = new Intent(FillSurveyActivity.this, LoginActivity.class);
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
    public void onItemClicked(int position,int index) {
        Question curQuestion=curSurvey.getQuestions().get(position);

        String select=String.valueOf(index);
        //selected.add(position,select);
        selectItems[position]=select;



    }

    @Override
    public void onClick(View view) {
        if (view.getId()==submit.getId()){
            //if (selected.size()==curSurvey.getQuestions().size())
                Log.d("billboard", "submit" );
            int i;
            for (i=0;i<curSurvey.getQuestions().size();i++)
                if (selectItems[i]==null)
                    break;
            if (i==curSurvey.getQuestions().size()){
                selected= new ArrayList<String>(Arrays.asList(selectItems));
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<FillingResponse> call = service.submitFilling(new FilledSurveyInfo(selected));
                call.enqueue(new Callback<FillingResponse>() {
                    @Override
                    public void onResponse(Call<FillingResponse> call, Response<FillingResponse> response) {
                        Intent intent4 = new Intent(FillSurveyActivity.this, ShowSurveyActivity.class);
                        intent4.putExtra("user_data", user);
                        startActivity(intent4);
                    }

                    @Override
                    public void onFailure(Call<FillingResponse> call, Throwable t) {

                    }
                });
            }


        }
    }
}
