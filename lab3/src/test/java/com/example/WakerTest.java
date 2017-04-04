package com.example;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static org.easymock.EasyMock.*;


@RunWith(EasyMockRunner.class)
public class WakerTest {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @TestSubject
    public Waker waker = new WakerImpl();

    @Mock
    Waker mockW;

    @Test
    public void showTest() {
        expect(mockW.addAlarmTime()).andReturn("M").andReturn("N");
        replay(mockW);
        assertEquals(true, waker.shouldRing());
        assertEquals(false, waker.shouldRing());
        verify(mockW);
    }
}