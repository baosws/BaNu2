package com.company.banu.Notifier;

import com.company.banu.CallBack;

import java.util.ArrayList;

public class Observer<NameType, DataType> {
    NameType name;
    ArrayList<CallBack<? extends DataType>> callBacks;
    boolean called = false;
    DataType data;
    Observer(NameType name, DataType data) {
        this.name = name;
        this.data = data;
        callBacks = new ArrayList<>();
    }
    public void addCallback(CallBack cb) {
        callBacks.add(cb);
        if (called) {
            cb.call(data);
        }
    }

    public void callAll() {
        for (CallBack cb: callBacks) {
            cb.call(data);
        }
        called = true;
    }
}
