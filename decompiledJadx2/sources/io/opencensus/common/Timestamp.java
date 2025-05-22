package io.opencensus.common;

import com.google.protobuf.util.TimeUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Timestamp implements Comparable<Timestamp> {
    public abstract int getNanos();

    public abstract long getSeconds();

    public static Timestamp create(long j, int i) {
        if (j < TimeUtil.DURATION_SECONDS_MIN) {
            throw new IllegalArgumentException("'seconds' is less than minimum (-315576000000): " + j);
        }
        if (j > TimeUtil.DURATION_SECONDS_MAX) {
            throw new IllegalArgumentException("'seconds' is greater than maximum (315576000000): " + j);
        }
        if (i < 0) {
            throw new IllegalArgumentException("'nanos' is less than zero: " + i);
        }
        if (i > 999999999) {
            throw new IllegalArgumentException("'nanos' is greater than maximum (999999999): " + i);
        }
        return new AutoValue_Timestamp(j, i);
    }

    public static Timestamp fromMillis(long j) {
        return create(floorDiv(j, 1000L), (int) (((int) floorMod(j, 1000L)) * 1000000));
    }

    public Timestamp addNanos(long j) {
        return plus(0L, j);
    }

    public Timestamp addDuration(Duration duration) {
        return plus(duration.getSeconds(), duration.getNanos());
    }

    public Duration subtractTimestamp(Timestamp timestamp) {
        long j;
        long seconds = getSeconds() - timestamp.getSeconds();
        int nanos = getNanos() - timestamp.getNanos();
        if (seconds >= 0 || nanos <= 0) {
            if (seconds > 0 && nanos < 0) {
                seconds--;
                j = nanos + 1000000000;
            }
            return Duration.create(seconds, nanos);
        }
        seconds++;
        j = nanos - 1000000000;
        nanos = (int) j;
        return Duration.create(seconds, nanos);
    }

    @Override // java.lang.Comparable
    public int compareTo(Timestamp timestamp) {
        int compareLongs = TimeUtils.compareLongs(getSeconds(), timestamp.getSeconds());
        return compareLongs != 0 ? compareLongs : TimeUtils.compareLongs(getNanos(), timestamp.getNanos());
    }

    private Timestamp plus(long j, long j2) {
        if ((j | j2) == 0) {
            return this;
        }
        return ofEpochSecond(TimeUtils.checkedAdd(TimeUtils.checkedAdd(getSeconds(), j), j2 / 1000000000), getNanos() + (j2 % 1000000000));
    }

    private static Timestamp ofEpochSecond(long j, long j2) {
        return create(TimeUtils.checkedAdd(j, floorDiv(j2, 1000000000L)), (int) floorMod(j2, 1000000000L));
    }

    private static long floorDiv(long j, long j2) {
        return BigDecimal.valueOf(j).divide(BigDecimal.valueOf(j2), 0, RoundingMode.FLOOR).longValue();
    }

    private static long floorMod(long j, long j2) {
        return j - (floorDiv(j, j2) * j2);
    }
}
