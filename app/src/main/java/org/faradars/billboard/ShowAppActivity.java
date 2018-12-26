package org.faradars.billboard;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
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


public class ShowAppActivity extends AppCompatActivity implements AppAdapter.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    List<App> apps;
    private RecyclerView recyclerView;
    private AppAdapter adapter;
    private User user;
    private NavigationView navigationView;
    private TextView username, email_user,credit;
    private String appPackage;
    private int id;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appPackage = "";
        id = -1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_app);
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
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View vi = inflater.inflate(R.layout.nav_header, null, false); //log.xml is your file.

        username = (TextView) vi.findViewById(R.id.username);
        email_user = vi.findViewById(R.id.email_user);
        Bundle data = getIntent().getExtras();
        user = data.getParcelable("user_data");
        Toast.makeText(ShowAppActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
        username.setText(user.getName());
        email_user.setText(user.getEmail());

        //Show app item:
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<AppsResult> call = service.getapps();
        call.enqueue(new Callback<AppsResult>() {
            @Override
            public void onResponse(@Nullable Call<AppsResult> call, @Nullable Response<AppsResult> response) {
                AppsResult result = response.body();
                assert result != null;
                if (result.getStatus().equals("OK")) {
                    apps = result.getApp();
                    recyclerView = findViewById(R.id.recycler_view);
                    adapter = new AppAdapter(apps, ShowAppActivity.this);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(ShowAppActivity.this);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<AppsResult> call, Throwable t) {
                Toast.makeText(ShowAppActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        //Bundle data = getIntent().getExtras();
        //User user = data.getParcelable("user_data");
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Log.d("billboard", "user:"+user.getId());
        UserId userId=new UserId(user.getId());

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
                        View view = inflater.inflate(R.layout.nav_header, drawer, false); //log.xml is your file.
                        username = view.findViewById(R.id.username);
                        email_user = view.findViewById(R.id.email_user);
                        credit = view.findViewById(R.id.credit);
                        username.setText(curUser.getName());
                        email_user.setText(curUser.getEmail());
                        credit.setText("اعتبار: " + curUser.getCredit());
                        //drawer.addView(view);
                    }
                } else {
                    Log.e("Login", "Login Failed With Code : " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<UserResult> call, @NonNull Throwable t) {
                Toast.makeText(ShowAppActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(), "Here is home page!", Toast.LENGTH_LONG).show();
                break;
            case R.id.gift_history:
                Intent intent1 = new Intent(ShowAppActivity.this, ShowGiftHistoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.gift_shop:
                Intent intent = new Intent(ShowAppActivity.this, ShowGiftActivity.class);
                startActivity(intent);
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
                        Intent intent1 = new Intent(ShowAppActivity.this, LoginActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "شما با موفقیت خارج شدید", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<UserResult> call, Throwable t) {

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

    @SuppressLint("RestrictedApi")
    @Override
    public void onItemClicked(int position) {
        App clicked = apps.get(position);
        appPackage = clicked.getPackage_name();
        id = clicked.getId();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(clicked.getDownload_link()));
        //intent.putExtra("app_package", appPackage);
        Bundle bundle=new Bundle();
        bundle.putString("app_package",appPackage);
        startActivityForResult(intent,0,bundle);
        //startActivityForResult(intent, 0);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            boolean installed = false;

            Context context = this.getApplicationContext();
            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            final PackageManager pm = getPackageManager();
                //List<ResolveInfo> pkgAppsList = context.getPackageManager().queryIntentActivities( mainIntent, 0);
            List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
            for (ApplicationInfo packageInfo : packages) {
                    //Log.d(TAG, "Installed app :" + pm.getApplicationLabel(packageInfo).toString())hy;
                if (packageInfo.packageName.equals(appPackage)) {

                    installed = true;
                    break;
                }
            }
            if (installed) {
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<InstallResult> call = service.installApp(id);
                call.enqueue(new Callback<InstallResult>() {
                    @Override
                    public void onResponse(Call<InstallResult> call, Response<InstallResult> response) {
                        InstallResult installResult = response.body();
                        if (installResult.getStatus().equals("OK")) {
                            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<AppsResult> appCall = service.getapps();
                            appCall.enqueue(new Callback<AppsResult>() {
                                @Override
                                public void onResponse(@Nullable Call<AppsResult> call, @Nullable Response<AppsResult> response) {
                                    AppsResult result = response.body();
                                    assert result != null;
                                    if (result.getStatus().equals("OK")) {
                                        apps = result.getApp();
                                        recyclerView = findViewById(R.id.recycler_view);
                                        adapter = new AppAdapter(apps, ShowAppActivity.this);
                                        RecyclerView.LayoutManager manager = new LinearLayoutManager(ShowAppActivity.this);
                                        recyclerView.setLayoutManager(manager);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<AppsResult> call, Throwable t) {
                                    Toast.makeText(ShowAppActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<InstallResult> call, Throwable t) {

                    }
                });
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}