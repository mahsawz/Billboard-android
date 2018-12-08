package org.faradars.billboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowGiftActivity extends AppCompatActivity implements GiftAdapter.OnItemClickListener {
    List<Gift> gifts = new ArrayList<>();
    private RecyclerView recyclerView;
    private GiftAdapter gAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gift);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GiftShop> call = service.giftShop();
        call.enqueue(new Callback<GiftShop>() {
            @Override
            public void onResponse(@NonNull Call<GiftShop> call, @NonNull Response<GiftShop> response) {
                GiftShop giftRes = response.body();
                assert giftRes != null;
                gifts = giftRes.getGifts();
                recyclerView = findViewById(R.id.giftRecycler);
                gAdapter = new GiftAdapter(gifts, ShowGiftActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(gAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<GiftShop> call, @NonNull Throwable t) {
                Toast.makeText(ShowGiftActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        Gift clicked = gifts.get(position);
        int id = clicked.getId();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GiftShopResult> call = service.buyGift(id);
        call.enqueue(new Callback<GiftShopResult>() {
            @Override
            public void onResponse(Call<GiftShopResult> call, Response<GiftShopResult> response) {
                GiftShopResult shopRes = response.body();
                assert shopRes != null;
                if (shopRes.getStatus().equals("OK")) {
                    ShoppingRecord records = shopRes.getRecord();
                    Intent intent = new Intent(ShowGiftActivity.this, BuyGiftActivity.class);
                    intent.putExtra("record", records);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<GiftShopResult> call, Throwable t) {

            }
        });
    }
}
