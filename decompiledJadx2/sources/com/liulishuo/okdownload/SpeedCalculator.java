package com.liulishuo.okdownload;

import android.os.SystemClock;
import com.liulishuo.okdownload.core.Util;

/* loaded from: classes4.dex */
public class SpeedCalculator {
    long allIncreaseBytes;
    long beginTimestamp;
    long bytesPerSecond;
    long endTimestamp;
    long increaseBytes;
    long timestamp;

    public synchronized void reset() {
        this.timestamp = 0L;
        this.increaseBytes = 0L;
        this.bytesPerSecond = 0L;
        this.beginTimestamp = 0L;
        this.endTimestamp = 0L;
        this.allIncreaseBytes = 0L;
    }

    long nowMillis() {
        return SystemClock.uptimeMillis();
    }

    public synchronized void downloading(long j) {
        if (this.timestamp == 0) {
            this.timestamp = nowMillis();
            this.beginTimestamp = this.timestamp;
        }
        this.increaseBytes += j;
        this.allIncreaseBytes += j;
    }

    public synchronized void flush() {
        long nowMillis = nowMillis();
        long j = this.increaseBytes;
        long max = Math.max(1L, nowMillis - this.timestamp);
        this.increaseBytes = 0L;
        this.timestamp = nowMillis;
        this.bytesPerSecond = (((float) j) / ((float) max)) * 1000.0f;
    }

    public long getInstantBytesPerSecondAndFlush() {
        flush();
        return this.bytesPerSecond;
    }

    public synchronized long getBytesPerSecondAndFlush() {
        long nowMillis = nowMillis() - this.timestamp;
        if (nowMillis < 1000 && this.bytesPerSecond != 0) {
            return this.bytesPerSecond;
        }
        if (this.bytesPerSecond == 0 && nowMillis < 500) {
            return 0L;
        }
        return getInstantBytesPerSecondAndFlush();
    }

    public synchronized long getBytesPerSecondFromBegin() {
        return (((float) this.allIncreaseBytes) / ((float) Math.max(1L, (this.endTimestamp == 0 ? nowMillis() : this.endTimestamp) - this.beginTimestamp))) * 1000.0f;
    }

    public synchronized void endTask() {
        this.endTimestamp = nowMillis();
    }

    public String instantSpeed() {
        return getSpeedWithSIAndFlush();
    }

    public String speed() {
        return humanReadableSpeed(getBytesPerSecondAndFlush(), true);
    }

    public String lastSpeed() {
        return humanReadableSpeed(this.bytesPerSecond, true);
    }

    public synchronized long getInstantSpeedDurationMillis() {
        return nowMillis() - this.timestamp;
    }

    public String getSpeedWithBinaryAndFlush() {
        return humanReadableSpeed(getInstantBytesPerSecondAndFlush(), false);
    }

    public String getSpeedWithSIAndFlush() {
        return humanReadableSpeed(getInstantBytesPerSecondAndFlush(), true);
    }

    public String averageSpeed() {
        return speedFromBegin();
    }

    public String speedFromBegin() {
        return humanReadableSpeed(getBytesPerSecondFromBegin(), true);
    }

    private static String humanReadableSpeed(long j, boolean z) {
        return Util.humanReadableBytes(j, z) + "/s";
    }
}
