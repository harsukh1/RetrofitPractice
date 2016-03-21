package com.singh.harsukh.retrofitpractice;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by harsukh on 3/11/16.
 */
public class ServiceGenerator {
    //http://www.washingtonpost.com/wp-srv/simulation/simulation_test.json
    private static final String BASE_URL = "https://www.washingtonpost.com";
    private static OkHttpClient buildLogger()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = null;
    public static <S> S createService(Class<S> serviceClass) {
        if(retrofit == null) {
            retrofit = builder.client(buildLogger()).build();
        }
        return retrofit.create(serviceClass);
    }
    public interface WPClient {
        @GET("wp-srv/simulation/simulation_test.json")
        Call<RowPost> contributors();
    }
}
