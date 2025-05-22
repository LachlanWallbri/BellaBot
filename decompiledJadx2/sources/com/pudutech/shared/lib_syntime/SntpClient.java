package com.pudutech.shared.lib_syntime;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.tencent.bugly.BuglyStrategy;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import org.mozilla.javascript.typedarrays.Conversions;

/* loaded from: classes7.dex */
public class SntpClient {
    private static final int INDEX_ORIGINATE_TIME = 24;
    private static final int INDEX_RECEIVE_TIME = 32;
    private static final int INDEX_ROOT_DELAY = 4;
    private static final int INDEX_ROOT_DISPERSION = 8;
    private static final int INDEX_TRANSMIT_TIME = 40;
    private static final int INDEX_VERSION = 0;
    private static final int NTP_MODE = 3;
    private static final int NTP_PACKET_SIZE = 48;
    private static final int NTP_PORT = 123;
    private static final int NTP_VERSION = 3;
    private static final long OFFSET_1900_TO_1970 = 2208988800L;
    public static final int RESPONSE_INDEX_DISPERSION = 5;
    public static final int RESPONSE_INDEX_ORIGINATE_TIME = 0;
    public static final int RESPONSE_INDEX_RECEIVE_TIME = 1;
    public static final int RESPONSE_INDEX_RESPONSE_TICKS = 7;
    public static final int RESPONSE_INDEX_RESPONSE_TIME = 3;
    public static final int RESPONSE_INDEX_ROOT_DELAY = 4;
    public static final int RESPONSE_INDEX_SIZE = 8;
    public static final int RESPONSE_INDEX_STRATUM = 6;
    public static final int RESPONSE_INDEX_TRANSMIT_TIME = 2;
    private static final String TAG = SntpClient.class.getSimpleName();
    private static float _rootDelayMax = 100.0f;
    private static float _rootDispersionMax = 100.0f;
    private static int _serverResponseDelayMax = 750;
    public static int _udpSocketTimeoutInMillis = BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH;
    private AtomicLong _cachedDeviceUptime = new AtomicLong();
    private AtomicLong _cachedSntpTime = new AtomicLong();

    private double doubleMillis(long j) {
        return j / 65.536d;
    }

    /* renamed from: ui */
    private int m3304ui(byte b) {
        return b & 255;
    }

    public long getClockOffset(long[] jArr) {
        return ((jArr[1] - jArr[0]) + (jArr[2] - jArr[3])) / 2;
    }

    public boolean requestTime(String str, int i) throws IOException {
        return requestTime(str, _rootDelayMax, _rootDispersionMax, _serverResponseDelayMax, i);
    }

    public synchronized boolean requestTime(String str, float f, float f2, int i, int i2) throws IOException {
        DatagramSocket datagramSocket;
        DatagramSocket datagramSocket2 = null;
        try {
            try {
                byte[] bArr = new byte[48];
                DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, InetAddress.getByName(str), 123);
                writeVersion(bArr);
                long currentTimeMillis = System.currentTimeMillis();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                writeTimeStamp(bArr, 40, currentTimeMillis);
                datagramSocket = new DatagramSocket();
                try {
                    datagramSocket.setSoTimeout(i2);
                    datagramSocket.send(datagramPacket);
                    long[] jArr = new long[8];
                    datagramSocket.receive(new DatagramPacket(bArr, bArr.length));
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    jArr[7] = elapsedRealtime2;
                    long readTimeStamp = readTimeStamp(bArr, 24);
                    long readTimeStamp2 = readTimeStamp(bArr, 32);
                    long readTimeStamp3 = readTimeStamp(bArr, 40);
                    long j = currentTimeMillis + (elapsedRealtime2 - elapsedRealtime);
                    jArr[0] = readTimeStamp;
                    jArr[1] = readTimeStamp2;
                    jArr[2] = readTimeStamp3;
                    jArr[3] = j;
                    jArr[4] = read(bArr, 4);
                    double doubleMillis = doubleMillis(jArr[4]);
                    if (doubleMillis > f) {
                        throw new InvalidNtpServerResponseException("Invalid response from NTP server. %s violation. %f [actual] > %f [expected]", "root_delay", (float) doubleMillis, f);
                    }
                    jArr[5] = read(bArr, 8);
                    double doubleMillis2 = doubleMillis(jArr[5]);
                    if (doubleMillis2 > f2) {
                        throw new InvalidNtpServerResponseException("Invalid response from NTP server. %s violation. %f [actual] > %f [expected]", "root_dispersion", (float) doubleMillis2, f2);
                    }
                    byte b = (byte) (bArr[0] & 7);
                    if (b != 4 && b != 5) {
                        throw new InvalidNtpServerResponseException("untrusted mode value for TrueTime: " + ((int) b));
                    }
                    int i3 = bArr[1] & 255;
                    jArr[6] = i3;
                    if (i3 < 1 || i3 > 15) {
                        throw new InvalidNtpServerResponseException("untrusted stratum value for TrueTime: " + i3);
                    }
                    if (((byte) ((bArr[0] >> 6) & 3)) == 3) {
                        throw new InvalidNtpServerResponseException("unsynchronized server responded for TrueTime");
                    }
                    double abs = Math.abs((j - readTimeStamp) - (readTimeStamp3 - readTimeStamp2));
                    if (abs >= i) {
                        throw new InvalidNtpServerResponseException("%s too large for comfort %f [actual] >= %f [expected]", "server_response_delay", (float) abs, i);
                    }
                    long abs2 = Math.abs(readTimeStamp - System.currentTimeMillis());
                    if (abs2 >= 10000) {
                        throw new InvalidNtpServerResponseException("Request was sent more than 10 seconds back " + abs2);
                    }
                    Pdlog.m3275i(TAG, "---- SNTP successful response from ", str);
                    cacheTrueTimeInfo(jArr);
                    datagramSocket.close();
                    return true;
                } catch (Exception unused) {
                    datagramSocket2 = datagramSocket;
                    Pdlog.m3275i(TAG, "---- SNTP request failed for ", str);
                    if (datagramSocket2 != null) {
                        datagramSocket2.close();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (datagramSocket != null) {
                        datagramSocket.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                datagramSocket = datagramSocket2;
            }
        } catch (Exception unused2) {
        }
    }

    void cacheTrueTimeInfo(long[] jArr) {
        this._cachedSntpTime.set(sntpTime(jArr));
        this._cachedDeviceUptime.set(jArr[7]);
    }

    long sntpTime(long[] jArr) {
        return jArr[3] + getClockOffset(jArr);
    }

    public Date getNowTime() {
        long cachedSntpTime = getCachedSntpTime();
        long cachedDeviceUptime = getCachedDeviceUptime();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = (elapsedRealtime - cachedDeviceUptime) + cachedSntpTime;
        Pdlog.m3273d(TAG, "now（）success__cachedSntpTime = " + cachedSntpTime + "__cachedDeviceUptime = " + cachedDeviceUptime + "__deviceUptime = " + elapsedRealtime + "__now = " + j);
        return new Date(j);
    }

    long getCachedSntpTime() {
        return this._cachedSntpTime.get();
    }

    long getCachedDeviceUptime() {
        return this._cachedDeviceUptime.get();
    }

    private void writeVersion(byte[] bArr) {
        bArr[0] = 27;
    }

    private void writeTimeStamp(byte[] bArr, int i, long j) {
        long j2 = j / 1000;
        long j3 = j - (j2 * 1000);
        long j4 = j2 + OFFSET_1900_TO_1970;
        int i2 = i + 1;
        bArr[i] = (byte) (j4 >> 24);
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j4 >> 16);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j4 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j4 >> 0);
        long j5 = (j3 * Conversions.THIRTYTWO_BIT) / 1000;
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j5 >> 24);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j5 >> 16);
        bArr[i7] = (byte) (j5 >> 8);
        bArr[i7 + 1] = (byte) (Math.random() * 255.0d);
    }

    private long readTimeStamp(byte[] bArr, int i) {
        return ((read(bArr, i) - OFFSET_1900_TO_1970) * 1000) + ((read(bArr, i + 4) * 1000) / Conversions.THIRTYTWO_BIT);
    }

    private long read(byte[] bArr, int i) {
        return (m3304ui(bArr[i]) << 24) + (m3304ui(bArr[i + 1]) << 16) + (m3304ui(bArr[i + 2]) << 8) + m3304ui(bArr[i + 3]);
    }
}
