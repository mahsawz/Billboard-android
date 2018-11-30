package org.faradars.billboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowGiftActivity extends AppCompatActivity {
    List<Gift> gifts=new ArrayList<>();
    private RecyclerView recyclerView;
    private GiftAdapter gAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showgiftpage);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GiftShop> call = service.giftShop();
        call.enqueue(new Callback<GiftShop>() {
            @Override
            public void onResponse(@NonNull Call<GiftShop> call, @NonNull Response<GiftShop> response) {
                GiftShop giftRes=response.body();
                gifts=giftRes.getGifts();
                recyclerView = findViewById(R.id.giftRecycler);
                gAdapter = new GiftAdapter(gifts);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(gAdapter);

            }

            @Override
            public void onFailure(@NonNull Call<GiftShop> call, @NonNull Throwable t) {


            }
        });


    }
}
