package io.opencensus.common;

import com.google.protobuf.util.TimeUtil;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Duration implements Comparable<Duration> {
    public abstract int getNanos();

    public abstract long getSeconds();

    public static Duration create(long j, int i) {
        if (j < TimeUtil.DURATION_SECONDS_MIN) {
            throw new IllegalArgumentException("'seconds' is less than minimum (-315576000000): " + j);
        }
        if (j > TimeUtil.DURATION_SECONDS_MAX) {
            throw new IllegalArgumentException("'seconds' is greater than maximum (315576000000): " + j);
        }
        if (i < -999999999) {
            throw new IllegalArgumentException("'nanos' is less than minimum (-999999999): " + i);
        }
        if (i > 999999999) {
            throw new IllegalArgumentException("'nanos' is greater than maximum (999999999): " + i);
        }
        if ((j < 0 && i > 0) || (j > 0 && i < 0)) {
            throw new IllegalArgumentException("'seconds' and 'nanos' have inconsistent sign: seconds=" + j + ", nanos=" + i);
        }
        return new AutoValue_Duration(j, i);
    }

    public static Duration fromMillis(long j) {
        return create(j / 1000, (int) ((j % 1000) * 1000000));
    }

    public long toMillis() {
        return TimeUnit.SECONDS.toMillis(getSeconds()) + TimeUnit.NANOSECONDS.toMillis(getNanos());
    }

    @Override // java.lang.Comparable
    public int compareTo(Duration duration) {
        int compareLongs = TimeUtils.compareLongs(getSeconds(), duration.getSeconds());
        return compareLongs != 0 ? compareLongs : TimeUtils.compareLongs(getNanos(), duration.getNanos());
    }
}
