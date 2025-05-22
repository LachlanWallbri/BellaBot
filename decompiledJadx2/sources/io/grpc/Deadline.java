package io.grpc;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.language.Soundex;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Deadline implements Comparable<Deadline> {
    private final long deadlineNanos;
    private volatile boolean expired;
    private final Ticker ticker;
    private static final SystemTicker SYSTEM_TICKER = new SystemTicker();
    private static final long MAX_OFFSET = TimeUnit.DAYS.toNanos(36500);
    private static final long MIN_OFFSET = -MAX_OFFSET;
    private static final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class Ticker {
        public abstract long nanoTime();
    }

    public static Ticker getSystemTicker() {
        return SYSTEM_TICKER;
    }

    public static Deadline after(long j, TimeUnit timeUnit) {
        return after(j, timeUnit, SYSTEM_TICKER);
    }

    public static Deadline after(long j, TimeUnit timeUnit, Ticker ticker) {
        checkNotNull(timeUnit, "units");
        return new Deadline(ticker, timeUnit.toNanos(j), true);
    }

    private Deadline(Ticker ticker, long j, boolean z) {
        this(ticker, ticker.nanoTime(), j, z);
    }

    private Deadline(Ticker ticker, long j, long j2, boolean z) {
        this.ticker = ticker;
        long min = Math.min(MAX_OFFSET, Math.max(MIN_OFFSET, j2));
        this.deadlineNanos = j + min;
        this.expired = z && min <= 0;
    }

    public boolean isExpired() {
        if (!this.expired) {
            if (this.deadlineNanos - this.ticker.nanoTime() > 0) {
                return false;
            }
            this.expired = true;
        }
        return true;
    }

    public boolean isBefore(Deadline deadline) {
        checkTicker(deadline);
        return this.deadlineNanos - deadline.deadlineNanos < 0;
    }

    public Deadline minimum(Deadline deadline) {
        checkTicker(deadline);
        return isBefore(deadline) ? this : deadline;
    }

    public Deadline offset(long j, TimeUnit timeUnit) {
        return j == 0 ? this : new Deadline(this.ticker, this.deadlineNanos, timeUnit.toNanos(j), isExpired());
    }

    public long timeRemaining(TimeUnit timeUnit) {
        long nanoTime = this.ticker.nanoTime();
        if (!this.expired && this.deadlineNanos - nanoTime <= 0) {
            this.expired = true;
        }
        return timeUnit.convert(this.deadlineNanos - nanoTime, TimeUnit.NANOSECONDS);
    }

    public ScheduledFuture<?> runOnExpiration(Runnable runnable, ScheduledExecutorService scheduledExecutorService) {
        checkNotNull(runnable, "task");
        checkNotNull(scheduledExecutorService, "scheduler");
        return scheduledExecutorService.schedule(runnable, this.deadlineNanos - this.ticker.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public String toString() {
        long timeRemaining = timeRemaining(TimeUnit.NANOSECONDS);
        long abs = Math.abs(timeRemaining) / NANOS_PER_SECOND;
        long abs2 = Math.abs(timeRemaining) % NANOS_PER_SECOND;
        StringBuilder sb = new StringBuilder();
        if (timeRemaining < 0) {
            sb.append(Soundex.SILENT_MARKER);
        }
        sb.append(abs);
        if (abs2 > 0) {
            sb.append(String.format(Locale.US, ".%09d", Long.valueOf(abs2)));
        }
        sb.append("s from now");
        if (this.ticker != SYSTEM_TICKER) {
            sb.append(" (ticker=" + this.ticker + ")");
        }
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Deadline deadline) {
        checkTicker(deadline);
        long j = this.deadlineNanos - deadline.deadlineNanos;
        if (j < 0) {
            return -1;
        }
        return j > 0 ? 1 : 0;
    }

    public int hashCode() {
        return Arrays.asList(this.ticker, Long.valueOf(this.deadlineNanos)).hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        Ticker ticker = this.ticker;
        if (ticker != null ? ticker == deadline.ticker : deadline.ticker == null) {
            return this.deadlineNanos == deadline.deadlineNanos;
        }
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class SystemTicker extends Ticker {
        private SystemTicker() {
        }

        @Override // io.grpc.Deadline.Ticker
        public long nanoTime() {
            return System.nanoTime();
        }
    }

    private static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    private void checkTicker(Deadline deadline) {
        if (this.ticker == deadline.ticker) {
            return;
        }
        throw new AssertionError("Tickers (" + this.ticker + " and " + deadline.ticker + ") don't match. Custom Ticker should only be used in tests!");
    }
}
