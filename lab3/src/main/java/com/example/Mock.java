package com.example;

import java.util.Date;

public interface Mock {
    public boolean shouldRing();
    public Date addAlarmTime(Date time);
    public Date clearAlarmTime(Date time);
}
