package org.faradars.billboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAppActivity extends AppCompatActivity {

    List<App> apps = new ArrayList<>();
    private RecyclerView recyclerView;
    private AppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_app);
        Bundle data = getIntent().getExtras();
        User user = data.getParcelable("user_data");
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<AppsResult> call = service.getapps();
        call.enqueue(new Callback<AppsResult>() {
            @Override
            public void onResponse(Call<AppsResult> call, Response<AppsResult> response) {
                AppsResult result = response.body();
                if (result.getStatus().equals("OK")) {
                    List<App> apps = result.getApp();
                    recyclerView = recyclerView.findViewById(R.id.recycler_view);
                    adapter = new AppAdapter(apps);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
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
