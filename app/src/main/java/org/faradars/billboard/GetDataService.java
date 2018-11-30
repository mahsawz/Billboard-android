package org.faradars.billboard;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetDataService {

    @POST("/api/signup")
    Call<UserResult> createUser(
            @Body SignupInfo info);

    @POST("/api/login")
    Call<UserResult> loginUser(
            @Body LoginInfo info);
    @POST("/api/giftshop")
    Call<GiftShop> giftShop();

    @POST("/api/showApps")
    Call<AppsResult> apps();


}
