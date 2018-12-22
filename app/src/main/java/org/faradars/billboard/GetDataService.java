package org.faradars.billboard;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService<C> {

    @POST("/api/users")
    Call<User> createUser(
            @Body SignupInfo info);

    @POST("/api/login")
    Call<UserResult> loginUser(
            @Body LoginInfo info);

    @GET("/api/showApps")
    Call<AppsResult> getapps();

    @GET("/api/showGift")
    Call<AppsResult> giftShop();

    @GET("/api/buyGift")
    Call<GiftShopResult> buyGift();

    @GET("/api/gifthistory")
    Call<GiftHistoryResult> getGiftHistory();

    @GET("/api/shoppingresult/{gift_id}")
    Call<GiftShopResult> buyGift(@Path("gift_id") int id);

    @POST("/api/getUser")
    Call<User> getUser(@Path("user_id") int id);
}
