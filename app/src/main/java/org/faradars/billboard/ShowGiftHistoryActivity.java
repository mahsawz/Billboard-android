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
import android.support.v7.widget.DefaultItemAnimator;
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

public class ShowGiftHistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<GiftHistory> gifts;
    private RecyclerView recyclerView;
    private GiftHistoryAdapter gAdapter;
    private TextView username, email_user, credit;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_history);
        forceRTLIfSupported();

        //Status bar:
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        //Menu item:
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Header:
        Bundle data = getIntent().getExtras();
        User user = data.getParcelable("user_data");
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<UserResult> call = service.getUser(user.getUser_id());
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(@NonNull Call<UserResult> call, @NonNull Response<UserResult> response) {
                if (response.isSuccessful()) {
                    UserResult result = response.body();
                    assert result != null;
                    if (result.getStatus().equals("OK")) {
                        User user = result.getUser();
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.nav_header, null); //log.xml is your file.
                        DrawerLayout drawer = findViewById(R.id.drawer_layout);
                        username = view.findViewById(R.id.username);
                        email_user = view.findViewById(R.id.email_user);
                        credit = view.findViewById(R.id.credit);
                        username.setText(user.getName());
                        email_user.setText(user.getEmail());
                        credit.setText("اعتبار: " + user.getCredit());
                        drawer.addView(view);
                    }
                } else {
                    Log.e("Login", "Login Failed With Code : " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<UserResult> call, @NonNull Throwable t) {
                Toast.makeText(ShowGiftHistoryActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });


        //show gift history item:
        Call<GiftHistoryResult> call1 = service.getGiftHistory();
        call1.enqueue(new Callback<GiftHistoryResult>() {

            @Override
            public void onResponse(Call<GiftHistoryResult> call, Response<GiftHistoryResult> response) {
                GiftHistoryResult result = response.body();
                assert result != null;
                if (result.getStatus().equals("OK")) {
                    gifts = result.getGift();
                    recyclerView = findViewById(R.id.giftHistoryRecycler);
                    gAdapter = new GiftHistoryAdapter(gifts, ShowGiftHistoryActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(gAdapter);
                }
            }

            @Override
            public void onFailure(Call<GiftHistoryResult> call, Throwable t) {
                Toast.makeText(ShowGiftHistoryActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Menu item:
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
                Intent intent1 = new Intent(ShowGiftHistoryActivity.this, ShowAppActivity.class);
                startActivity(intent1);
                break;
            case R.id.gift_shop:
                Intent intent = new Intent(ShowGiftHistoryActivity.this, ShowGiftActivity.class);
                startActivity(intent);
                break;
            case R.id.gift_history:
                Toast.makeText(getApplicationContext(), "Here is Gift History Page!", Toast.LENGTH_LONG).show();
                break;
            case R.id.setting:
                Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_LONG).show();
                break;
            case R.id.exit:
                Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(ShowGiftHistoryActivity.this, LoginActivity.class);
                startActivity(intent2);
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
}
