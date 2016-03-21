package com.singh.harsukh.retrofitpractice;

import java.util.Observable;

/**
 * Created by harsukh on 3/21/16.
 */
public class ObservableObject extends Observable {
    private static ObservableObject instance = new ObservableObject();

    public static ObservableObject getInstance() {
        return instance;
    }

    private ObservableObject() {
    }

    public void updateValue(Object data) {
        synchronized (this) {
            setChanged();
            notifyObservers(data);
        }
    }
}