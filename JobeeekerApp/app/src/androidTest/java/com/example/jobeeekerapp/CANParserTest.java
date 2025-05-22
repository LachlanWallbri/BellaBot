package com.example.jobeeekerapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.jobeeekerapp.serialize.ChargeState;
import com.example.jobeeekerapp.serialize.WheelError;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import java.util.List;

public class CANParserTest {

    private CANBus canBus;
    private CANParser canParser;

    @Before
    public void setUp() {
        canBus = CANBus.getInstance(null);
        canParser = canBus.getCanParser();
    }

    @Test
    public void testParseEncoderStationary() {
        byte[] bytes = {(byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
        canParser.parserManager(1, bytes);

        assertEquals(canBus.getEncoder().getSpeed(), new Encoder.Speed(0, 0));
        assertEquals(canBus.getEncoder().getDistance(), new Encoder.DoubleWheelDistance(0, 0));

        assertTrue(canBus.receivedEncoder);
    }

    @Test
    public void testParseEncoderLinearForwards() {
        byte[] bytes = {(byte)1, (byte)10, (byte)10, (byte)10, (byte)10, (byte)0, (byte)0};
        canParser.parserManager(1, bytes);

        assertTrue(canBus.getEncoder().getSpeed().getLineSpeed() > 0.0);
        assertEquals(0.0, canBus.getEncoder().getSpeed().getAngularSpeed(), 0.0);

        assertTrue(canBus.getEncoder().getDistance().getLeft() > 0.0);
        assertTrue(canBus.getEncoder().getDistance().getRight() > 0.0);

        assertTrue(canBus.receivedEncoder);
    }

    @Test
    public void testParseEncoderLinearBackwards() {
        byte[] bytes = {(byte)1, (byte)0, (byte)-10, (byte)0, (byte)-10, (byte)0, (byte)0};
        canParser.parserManager(1, bytes);

        assertTrue(canBus.getEncoder().getSpeed().getLineSpeed() < 0.0);
        assertEquals(0.0, canBus.getEncoder().getSpeed().getAngularSpeed(), 0.0);

        assertTrue(canBus.getEncoder().getDistance().getLeft() < 0.0);
        assertTrue(canBus.getEncoder().getDistance().getRight() < 0.0);

        assertTrue(canBus.receivedEncoder);
    }

    @Test
    public void testParseEncoderAngularRight() {
        byte[] bytes = {(byte)0, (byte)0, (byte)100, (byte)0, (byte)-100, (byte)0, (byte)0};
        canParser.parserManager(1, bytes);

        assertTrue(canBus.getEncoder().getSpeed().getAngularSpeed() < 0.0);
        assertEquals(0.0, canBus.getEncoder().getSpeed().getLineSpeed(), 0.0);

        assertTrue(canBus.getEncoder().getDistance().getLeft() > 0.0);
        assertTrue(canBus.getEncoder().getDistance().getRight() < 0.0);

        assertTrue(canBus.receivedEncoder);
    }

    @Test
    public void testParseEncoderAngularLeft() {
        byte[] bytes = {(byte)0, (byte)0, (byte)-100, (byte)0, (byte)100, (byte)0, (byte)0};
        canParser.parserManager(1, bytes);

        assertTrue(canBus.getEncoder().getSpeed().getAngularSpeed() > 0.0);
        assertEquals(0.0, canBus.getEncoder().getSpeed().getLineSpeed(), 0.0);

        assertTrue(canBus.getEncoder().getDistance().getLeft() < 0.0);
        assertTrue(canBus.getEncoder().getDistance().getRight() > 0.0);

        assertTrue(canBus.receivedEncoder);
    }

    @Test
    public void testParseBatteryPercent() {
        byte[] bytes = {(byte)0, (byte)50}; // Battery percent 50%
        canParser.parserManager(9, bytes);

        assertEquals(50, canBus.getLastPowerPercent());
    }

    @Test
    public void testParseChargingCharging() {
        byte[] bytes = {(byte)0, (byte)1}; // Charging state
        canParser.parserManager(13, bytes);

        assertEquals(ChargeState.Charging, canBus.getLastChargeState());
    }

    @Test
    public void testParseChargingIdle() {
        byte[] bytes = {(byte)0, (byte)0}; // Idle state
        canParser.parserManager(13, bytes);

        assertEquals(ChargeState.Idle, canBus.getLastChargeState());
    }

    @Test
    public void testParseIMUStationary() {
        byte[] bytes = {(byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
        canParser.parserManager(16, bytes);

        assertEquals(0.0, canBus.getGyroscope().getLast().getX(), 0.001);
        assertEquals(0.0, canBus.getGyroscope().getLast().getY(), 0.001);
        assertEquals(0.0, canBus.getGyroscope().getLast().getZ(), 0.001);

        assertTrue(canBus.receivedIMU);
    }

    @Test
    public void testParseIMUMoving() {
        byte[] bytes = {(byte)0, (byte)10, (byte)20, (byte)30, (byte)40, (byte)50, (byte)60};
        canParser.parserManager(16, bytes);

        assertTrue(canBus.getGyroscope().getLast().getX() > 0.0);
        assertTrue(canBus.getGyroscope().getLast().getY() > 0.0);
        assertTrue(canBus.getGyroscope().getLast().getZ() > 0.0);

        assertTrue(canBus.receivedIMU);
    }

    @Test
    public void testParseWheelErrorOldSingleError() {
        byte target = 1; // Binary: 00000001
        byte[] bytes = {(byte)66, target, target};
        canParser.parserManager(66, bytes);

        List<WheelError> errors = WheelError.getErrors1(target);

        assertEquals(canBus.getWheelError(true, 0), errors);
        assertEquals(canBus.getWheelError(false, 0), errors);
    }

    @Test
    public void testParseWheelErrorOldNoErrors() {
        byte target = 0; // Binary: 00000000
        byte[] bytes = {(byte)66, target, target};
        canParser.parserManager(66, bytes);

        List<WheelError> errors = WheelError.getErrors1(target);

        assertEquals(canBus.getWheelError(true, 0), errors);
        assertEquals(canBus.getWheelError(false, 0), errors);
    }

    @Test
    public void testParseWheelErrorOldAllErrors() {
        byte target = -1; // Binary: 11111111
        byte[] bytes = {(byte)66, target, target};
        canParser.parserManager(66, bytes);

        List<WheelError> errors = WheelError.getErrors1(target);

        assertEquals(canBus.getWheelError(true, 0), errors);
        assertEquals(canBus.getWheelError(false, 0), errors);
    }

    @Test
    public void testParseWheelErrorOldMultipleErrors() {
        byte target = 0b00010101; // Binary: 00010101 (SchOver, SpeedOverOld, SpdLose)
        byte[] bytes = {(byte)66, target, target};
        canParser.parserManager(66, bytes);

        List<WheelError> errors = WheelError.getErrors1(target);

        assertEquals(canBus.getWheelError(true, 0), errors);
        assertEquals(canBus.getWheelError(false, 0), errors);
    }

    @Test
    public void testParseWheelErrorNewProtocol1SpecificErrors() {
        byte[] bytes = {(byte)67, 0b00001111, 0, 0, 0};
        canParser.parserManager(67, bytes);

        List<WheelError> errorsLeft = WheelError.getErrors2(0b00001111, 0);
        List<WheelError> errorsRight = WheelError.getErrors2(0, 0);

        assertEquals(canBus.getWheelError(true, 1), errorsLeft);
        assertEquals(canBus.getWheelError(false, 1), errorsRight);
    }

    @Test
    public void testParseWheelErrorNewProtocol2MultipleErrors() {
        byte[] bytes = {(byte)71, 0b00001111, 0, 0b00010001, 0};
        canParser.parserManager(71, bytes);

        List<WheelError> errorsLeft = WheelError.getErrors3(0b00001111, 0);
        List<WheelError> errorsRight = WheelError.getErrors3(0b00010001, 0);

        assertEquals(canBus.getWheelError(true, 2), errorsLeft);
        assertEquals(canBus.getWheelError(false, 2), errorsRight);
    }

    @Test
    public void testParseWheelErrorNewProtocol1NoErrors() {
        byte[] bytes = {(byte)67, 0, 0, 0, 0}; // No errors
        canParser.parserManager(67, bytes);

        List<WheelError> errorsLeft = WheelError.getErrors2(0, 0); // No errors
        List<WheelError> errorsRight = WheelError.getErrors2(0, 0); // No errors

        assertEquals(canBus.getWheelError(true, 1), errorsLeft);
        assertEquals(canBus.getWheelError(false, 1), errorsRight);
    }

    @Test
    public void testParseAcceleration() {

        assertEquals(0.0, canBus.getLastAcceleration((byte) 1), 0.0001);
        assertEquals(0.0, canBus.getLastAcceleration((byte) 2), 0.0001);
        assertEquals(0.0, canBus.getLastAcceleration((byte) 3), 0.0001);

        // Signal of 10000, which should be an acceleration of 1
        byte[] bytes = {(byte)0, (byte)1, (byte)0, (byte)0, (byte)0b100111, (byte)0b00010000};
        canParser.parserManager(73, bytes);

        assertEquals(1, canBus.getLastAcceleration((byte) 1), 0.0001);
        assertEquals(0.0, canBus.getLastAcceleration((byte) 2), 0.0001);
        assertEquals(0.0, canBus.getLastAcceleration((byte) 3), 0.0001);
    }

    @Test
    public void testParseAccelerationAll() {

        assertEquals(0.0, canBus.getLastAcceleration((byte) 1), 0.0001);
        assertEquals(0.0, canBus.getLastAcceleration((byte) 2), 0.0001);
        assertEquals(0.0, canBus.getLastAcceleration((byte) 3), 0.0001);

        // Signal of 10000, which should be an acceleration of 1
        byte[] bytes = {(byte)0, (byte)1, (byte)0, (byte)0, (byte)0b100111, (byte)0b00010000};
        canParser.parserManager(73, bytes);
        byte[] bytes2 = {(byte)0, (byte)2, (byte)0, (byte)0, (byte)0b100111, (byte)0b00010000};
        canParser.parserManager(73, bytes2);
        byte[] bytes3 = {(byte)0, (byte)3, (byte)0, (byte)0, (byte)0b100111, (byte)0b00010000};
        canParser.parserManager(73, bytes3);

        assertEquals(1, canBus.getLastAcceleration((byte) 1), 0.0001);
        assertEquals(1, canBus.getLastAcceleration((byte) 2), 0.0001);
        assertEquals(1, canBus.getLastAcceleration((byte) 3), 0.0001);
    }

    @Test
    public void testParseBatteryCharge() {
        byte[] bytes = {(byte)0, (byte)0, (byte)0, (byte)50};
        canParser.parserManager(115, bytes);

        assertEquals(50, canBus.getLastPowerPercent());
        assertEquals(ChargeState.fromValue(bytes[1]), canBus.getLastChargeState());
    }
}