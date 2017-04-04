package com.example;

import java.sql.Time;

public interface Waker {
    public boolean shouldRing(Time time);
    public Time addAlarmTime(Time time);
    public void clearAlarmTime(Time time);
}
