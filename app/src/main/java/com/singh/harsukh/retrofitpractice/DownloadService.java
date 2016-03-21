package com.singh.harsukh.retrofitpractice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harsukh on 3/20/16.
 */
public class DownloadService extends IntentService
{

    /**
     * Creates an IntentService.*/
    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ServiceGenerator.WPClient service = ServiceGenerator.createService(ServiceGenerator.WPClient.class);
        Call<RowPost> call  = service.contributors();
        call.enqueue(new Callback<RowPost>() {
            @Override
            public void onResponse(Call<RowPost> call, Response<RowPost> response) {
                int status_code = response.code();
                Log.e("DownloadService", "" + status_code);
                RowPost list = response.body();
                Intent intent = new Intent();
                String output = getApplicationContext().toString();
                intent.setAction("output");
                intent.putParcelableArrayListExtra("list", new ArrayList<RowPost.PostsBean>(list.getPosts()));
                sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<RowPost> call, Throwable t) {
                Log.e("DownloadService", "failed call", t);
            }
        });
    }


    public static Intent getIntent(Context context)
    {
        Intent intent = new Intent(context, DownloadService.class);
        return intent;
    }
}
