package com.singh.harsukh.retrofitpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Observer{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        ObservableObject.getInstance().addObserver(this);
        startTask();
    }

    public void startTask()
    {
        startService(DownloadService.getIntent(this));
    }

    public void setList(ArrayList post)
    {
        //set the size of the list
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PostAdapter(post);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void update(Observable observable, Object data) {
        setList((ArrayList) data);
    }
}
