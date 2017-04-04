package com.example;

import java.sql.Time;

public class WakerImpl implements Waker {

    public Time T1;
    public Time T2;
    public boolean rang = false;

    @Override
    public boolean shouldRing(Time time) {
        rang = time == T1;
        return !rang;
    }

    @Override
    public Time addAlarmTime(Time time) {
        T1 = time;
        return time;
    }

    @Override
    public void clearAlarmTime(Time time) {
        T1 = null;
    }
}
