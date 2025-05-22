package com.unsw.bellabot;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CANSendTest {

    private Context context;
    private CANBus canBus;

    @Before
    public void setUp() {
        // Initialize the context, typically from ApplicationProvider
        context = ApplicationProvider.getApplicationContext();
        canBus = CANBus.getInstance(context);
        clearLogcat();
    }

    @Test
    public void testCANSendEmpty() throws InterruptedException {
        byte[] data = new byte[] {};
        canBus.sendGBYM(data);

        // Capture logcat logs
        String logs = getLogcatLogs();

        assertTrue(logs.contains("send empty data"));

        ArrayBlockingQueue<byte[]> queue = canBus.getDataQueue();
        byte[] bytes = queue.poll(10, TimeUnit.MILLISECONDS);
        assertNull(bytes);
    }

    @Test
    public void testCANSendNull() throws InterruptedException {
        byte[] data = null;
        canBus.sendGBYM(data);

        // Capture logcat logs
        String logs = getLogcatLogs();

        assertTrue(logs.contains("send empty data"));

        ArrayBlockingQueue<byte[]> queue = canBus.getDataQueue();
        byte[] bytes = queue.poll(10, TimeUnit.MILLISECONDS);
        assertNull(bytes);
    }

    @Test
    public void testCANSendWithoutPadding() throws InterruptedException {
        byte[] data = new byte[] {1, 2, 3, 4};
        canBus.sendGBYM(data);

        // Check data is padded
        String logs = getLogcatLogs();
        assertTrue(logs.contains("data padded to 7 bytes"));

        ArrayBlockingQueue<byte[]> queue = canBus.getDataQueue();
        byte[] bytes = queue.take();

        byte[] expected = {1,2,3,4,0,0,0};
        for (int i = 0; i < 7; i++) {
            assertEquals(bytes[i], expected[i]);
        }

        // Confirm checksum was generated
        assertNotEquals(bytes[7], (byte) 0);
    }

    @Test
    public void testCANSendWithAllPadding() throws InterruptedException {
        byte[] data = new byte[] {1};
        canBus.sendGBYM(data);

        // Check data is padded
        String logs = getLogcatLogs();
        assertTrue(logs.contains("data padded to 7 bytes"));

        ArrayBlockingQueue<byte[]> queue = canBus.getDataQueue();
        byte[] bytes = queue.take();

        byte[] expected = {1,0,0,0,0,0,0};
        for (int i = 0; i < 7; i++) {
            assertEquals(bytes[i], expected[i]);
        }

        // Confirm checksum was generated
        assertNotEquals(bytes[7], (byte) 0);
    }

    @Test
    public void testCANSendNoPaddingNeeded() throws InterruptedException {
        byte[] data = new byte[] {1,2,3,4,5,6,7};
        canBus.sendGBYM(data);

        ArrayBlockingQueue<byte[]> queue = canBus.getDataQueue();
        byte[] bytes = queue.take();

        byte[] expected = data;
        for (int i = 0; i < 7; i++) {
            assertEquals(bytes[i], expected[i]);
        }

        // Confirm checksum was generated
        assertNotEquals(bytes[7], (byte) 0);
    }

    @Test
    public void testCANSendFullByte() throws InterruptedException {
        byte[] data = new byte[] {1,2,3,4,5,6,7,8};
        canBus.sendGBYM(data);

        ArrayBlockingQueue<byte[]> queue = canBus.getDataQueue();
        byte[] bytes = queue.take();

        byte[] expected = data;
        // Confirm checksum did not overwrite the last data point
        for (int i = 0; i < 8; i++) {
            assertEquals(bytes[i], expected[i]);
        }
    }

    @Test
    public void testCANSendConfirmChecksum() throws InterruptedException {
        byte[] data = new byte[] {1,2,3};
        canBus.sendGBYM(data);

        // Check data is padded
        String logs = getLogcatLogs();
        assertTrue(logs.contains("data padded to 7 bytes"));

        ArrayBlockingQueue<byte[]> queue = canBus.getDataQueue();
        byte[] bytes = queue.take();

        // calc checksum
        byte checksum = 0;
        for (byte b : data) {
            checksum += b;
        }

        byte[] expected = {1,2,3,0,0,0,0, checksum};
        for (int i = 0; i < 8; i++) {
            assertEquals(bytes[i], expected[i]);
        }
    }

    @Test
    public void testCANSendTooLarge() throws InterruptedException {
        byte[] data = new byte[] {1,2,3,4,5,6,7,8,9};
        canBus.sendGBYM(data);

        // Check data is padded
        String logs = getLogcatLogs();
        assertFalse(logs.contains("send empty data"));
        assertFalse(logs.contains("data padded to 7 bytes"));
        assertTrue(logs.contains("send invalid data size: 9"));

        ArrayBlockingQueue<byte[]> queue = canBus.getDataQueue();
        byte[] bytes = queue.poll(10, TimeUnit.MILLISECONDS);

        assertNull(bytes);
    }


    public static String getLogcatLogs() {
        StringBuilder logs = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logs.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs.toString();
    }

    public static void clearLogcat() {
        try {
            Process process = Runtime.getRuntime().exec("logcat -c");
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}