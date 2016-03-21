package com.singh.harsukh.retrofitpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by harsukh on 3/20/16.
 */
public class ServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList post = intent.getParcelableArrayListExtra("list");
        ObservableObject.getInstance().updateValue(post);
    }
}
