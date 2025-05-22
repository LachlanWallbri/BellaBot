package com.unsw.bellabot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotSame;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;

public class CANSingletonTest {

    private Context context;

    @Before
    public void setUp() {
        // Initialize the context, typically from ApplicationProvider
        context = ApplicationProvider.getApplicationContext();

    }

    @Test
    public void testCANBusSingletonInstanceUnique() {
        // Ensure that only one instance of CANBus is created
        CANBus instance1 = CANBus.getInstance(context);
        CANBus instance2 = CANBus.getInstance(context);

        assertNotSame(instance1, CANBus.getInstance());
        assertSame(instance2, CANBus.getInstance());
    }

    @Test
    public void testSingletonInstanceCreation() {
        // Initialize the CANBus instance
        CANBus canBusInstance1 = CANBus.getInstance(context);
        CANBus canBusInstance2 = CANBus.getInstance();

        // Assert that both instances are the same
        assertNotNull(canBusInstance1);
        assertNotNull(canBusInstance2);
        assertSame(canBusInstance1, canBusInstance2);
    }

    @Test
    public void testCANBusSingletonInitialises() {
        // Ensure that the singleton instance is initialised with the context
        CANBus instance = CANBus.getInstance(context);

        assertNotNull(instance);
    }
}