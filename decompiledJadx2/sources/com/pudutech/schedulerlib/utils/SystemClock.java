package com.pudutech.schedulerlib.utils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: SystemClock.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\u0003H\u0002J\u0006\u0010\t\u001a\u00020\u0003J\b\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/schedulerlib/utils/SystemClock;", "", TypedValues.Cycle.S_WAVE_PERIOD, "", "(J)V", "NOW", "Ljava/util/concurrent/atomic/AtomicLong;", "PERIOD", "currentTimeMillis", "now", "scheduleClockUpdating", "", "Companion", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SystemClock {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static SystemClock instance;
    private final AtomicLong NOW = new AtomicLong(System.currentTimeMillis());
    private final long PERIOD;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: SystemClock.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0004R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/schedulerlib/utils/SystemClock$Companion;", "", "()V", "instance", "Lcom/pudutech/schedulerlib/utils/SystemClock;", "getInstance", "()Lcom/pudutech/schedulerlib/utils/SystemClock;", "setInstance", "(Lcom/pudutech/schedulerlib/utils/SystemClock;)V", TmpConstant.PROPERTY_IDENTIFIER_GET, "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void setInstance(SystemClock systemClock) {
            SystemClock.instance = systemClock;
        }

        private final SystemClock getInstance() {
            if (SystemClock.instance == null) {
                SystemClock.instance = new SystemClock(1L);
            }
            return SystemClock.instance;
        }

        public final synchronized SystemClock get() {
            SystemClock companion;
            companion = getInstance();
            if (companion == null) {
                Intrinsics.throwNpe();
            }
            return companion;
        }
    }

    public SystemClock(long j) {
        this.PERIOD = j;
        scheduleClockUpdating();
    }

    public final long now() {
        return INSTANCE.get().currentTimeMillis();
    }

    private final long currentTimeMillis() {
        return this.NOW.get();
    }

    private final void scheduleClockUpdating() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() { // from class: com.pudutech.schedulerlib.utils.SystemClock$scheduleClockUpdating$executor$1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "SystemClock");
                thread.setDaemon(true);
                return thread;
            }
        });
        Runnable runnable = new Runnable() { // from class: com.pudutech.schedulerlib.utils.SystemClock$scheduleClockUpdating$1
            @Override // java.lang.Runnable
            public final void run() {
                AtomicLong atomicLong;
                atomicLong = SystemClock.this.NOW;
                atomicLong.set(System.currentTimeMillis());
            }
        };
        long j = this.PERIOD;
        scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, j, j, TimeUnit.MILLISECONDS);
    }
}
