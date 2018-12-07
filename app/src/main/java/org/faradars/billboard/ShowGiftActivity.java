package org.faradars.billboard;

<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

<<<<<<< HEAD
public class ShowGiftActivity extends AppCompatActivity implements GiftAdapter.OnItemClickListener {
    List<Gift> gifts = new ArrayList<>();
    private RecyclerView recyclerView;
    private GiftAdapter gAdapter;

=======
public class ShowGiftActivity extends AppCompatActivity {
    List<Gift> gifts=new ArrayList<>();
    private RecyclerView recyclerView;
    private GiftAdapter gAdapter;
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gift);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GiftShop> call = service.giftShop();
        call.enqueue(new Callback<GiftShop>() {
            @Override
            public void onResponse(@NonNull Call<GiftShop> call, @NonNull Response<GiftShop> response) {
<<<<<<< HEAD
                GiftShop giftRes = response.body();
                assert giftRes != null;
                gifts = giftRes.getGifts();
                recyclerView = findViewById(R.id.giftRecycler);
                gAdapter = new GiftAdapter(gifts, ShowGiftActivity.this);
=======
                GiftShop giftRes=response.body();
                gifts=giftRes.getGifts();
                recyclerView = findViewById(R.id.giftRecycler);
                gAdapter = new GiftAdapter(gifts);
>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(gAdapter);
<<<<<<< HEAD
=======

>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9
            }

            @Override
            public void onFailure(@NonNull Call<GiftShop> call, @NonNull Throwable t) {
<<<<<<< HEAD
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
=======


            }
        });


>>>>>>> 75a632d1f5f8e51bb4ddded9efca4afcd87c78e9
    }
}
