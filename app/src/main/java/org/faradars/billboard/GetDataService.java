package org.faradars.billboard;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {

    @POST("/api/signup")
    Call<UserResult> createUser(
            @Body SignupInfo info);

    @POST("/api/login")
    Call<UserResult> loginUser(
            @Body LoginInfo info);
    @GET("/api/logout")
    Call<UserResult> logout();
    @GET("/api/giftshop")
    Call<GiftShop> giftShop();

    @GET("/api/showApps/")
    Call<AppsResult> getapps();

    @GET("/api/shoppingresult/{gift_id}")
    Call<GiftShopResult> buyGift(@Path("gift_id") int id);

    @GET("/api/installApp/{app_id}")
    Call<InstallResult>installApp(@Path("app_id") int id);

    @POST("/api/getUser")
    Call<UserResult> getUser(@Body UserId userid);

    @GET("/api/gifthistory")
    Call<GiftHistoryResult> getGiftHistory();

}
