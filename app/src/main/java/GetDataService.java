package org.faradars.billboard;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetDataService {

    @POST("/user/{username}&{password}")
    Call<Result> createUser(
            @Body User user);

    @FormUrlEncoded
    @POST("login")
    Call<LoginUser> loginUser(
            @Field("email") String email,
            @Field("password") String password);

}
