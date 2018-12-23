package org.faradars.billboard;

<<<<<<< HEAD
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
=======
>>>>>>> Showapppage
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
<<<<<<< HEAD

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://0.0.0.0:5000/";

    public static Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieHandler cookieHandler = new CookieManager();
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
=======
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://172.17.10.136:5000/";

    public static Retrofit getRetrofitInstance() {
>>>>>>> Showapppage
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
<<<<<<< HEAD
                    .client(client)
                    .build();

=======
                    .build();
>>>>>>> Showapppage
        }
        return retrofit;
    }
}