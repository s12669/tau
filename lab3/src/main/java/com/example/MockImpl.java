package com.example;

import java.util.Date;

public class MockImpl implements Mock{
    @Override
    public boolean shouldRing() {
        return false;
    }

    @Override
    public Date addAlarmTime(Date time) {
        return null;
    }

    @Override
    public Date clearAlarmTime(Date time) {
        return null;
    }
}
