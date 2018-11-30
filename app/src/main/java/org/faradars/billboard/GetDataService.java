package org.billboard;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService <C>{

    @POST("/api/users")
    Call<User> createUser(
            @Body SignupInfo info);

    @POST("/api/login")
    Call<User> loginUser(
            @Body LoginInfo info);

    @POST("/api/showApps")
    Call<AppsResult> getapps();
}
