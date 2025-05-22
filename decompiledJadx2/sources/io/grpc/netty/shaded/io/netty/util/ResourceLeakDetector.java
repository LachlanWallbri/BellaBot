package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ResourceLeakDetector<T> {
    private static final int DEFAULT_SAMPLING_INTERVAL = 128;
    private static final int DEFAULT_TARGET_RECORDS = 4;
    private static final String PROP_LEVEL = "io.grpc.netty.shaded.io.netty.leakDetection.level";
    private static final String PROP_LEVEL_OLD = "io.grpc.netty.shaded.io.netty.leakDetectionLevel";
    private static final String PROP_SAMPLING_INTERVAL = "io.grpc.netty.shaded.io.netty.leakDetection.samplingInterval";
    private static final String PROP_TARGET_RECORDS = "io.grpc.netty.shaded.io.netty.leakDetection.targetRecords";
    static final int SAMPLING_INTERVAL;
    private static final int TARGET_RECORDS;
    private static final AtomicReference<String[]> excludedMethods;
    private static Level level;
    private final Set<DefaultResourceLeak<?>> allLeaks;
    private final ReferenceQueue<Object> refQueue;
    private final Set<String> reportedLeaks;
    private final String resourceType;
    private final int samplingInterval;
    private static final Level DEFAULT_LEVEL = Level.SIMPLE;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ResourceLeakDetector.class);

    @Deprecated
    protected void reportInstancesLeak(String str) {
    }

    static {
        boolean z = false;
        if (SystemPropertyUtil.get("io.grpc.netty.shaded.io.netty.noResourceLeakDetection") != null) {
            z = SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.noResourceLeakDetection", false);
            logger.debug("-Dio.netty.noResourceLeakDetection: {}", Boolean.valueOf(z));
            logger.warn("-Dio.netty.noResourceLeakDetection is deprecated. Use '-D{}={}' instead.", PROP_LEVEL, DEFAULT_LEVEL.name().toLowerCase());
        }
        Level parseLevel = Level.parseLevel(SystemPropertyUtil.get(PROP_LEVEL, SystemPropertyUtil.get(PROP_LEVEL_OLD, (z ? Level.DISABLED : DEFAULT_LEVEL).name())));
        TARGET_RECORDS = SystemPropertyUtil.getInt(PROP_TARGET_RECORDS, 4);
        SAMPLING_INTERVAL = SystemPropertyUtil.getInt(PROP_SAMPLING_INTERVAL, 128);
        level = parseLevel;
        if (logger.isDebugEnabled()) {
            logger.debug("-D{}: {}", PROP_LEVEL, parseLevel.name().toLowerCase());
            logger.debug("-D{}: {}", PROP_TARGET_RECORDS, Integer.valueOf(TARGET_RECORDS));
        }
        excludedMethods = new AtomicReference<>(EmptyArrays.EMPTY_STRINGS);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum Level {
        DISABLED,
        SIMPLE,
        ADVANCED,
        PARANOID;

        static Level parseLevel(String str) {
            String trim = str.trim();
            for (Level level : values()) {
                if (trim.equalsIgnoreCase(level.name()) || trim.equals(String.valueOf(level.ordinal()))) {
                    return level;
                }
            }
            return ResourceLeakDetector.DEFAULT_LEVEL;
        }
    }

    @Deprecated
    public static void setEnabled(boolean z) {
        setLevel(z ? Level.SIMPLE : Level.DISABLED);
    }

    public static boolean isEnabled() {
        return getLevel().ordinal() > Level.DISABLED.ordinal();
    }

    public static void setLevel(Level level2) {
        level = (Level) ObjectUtil.checkNotNull(level2, "level");
    }

    public static Level getLevel() {
        return level;
    }

    @Deprecated
    public ResourceLeakDetector(Class<?> cls) {
        this(StringUtil.simpleClassName(cls));
    }

    @Deprecated
    public ResourceLeakDetector(String str) {
        this(str, 128, Long.MAX_VALUE);
    }

    @Deprecated
    public ResourceLeakDetector(Class<?> cls, int i, long j) {
        this(cls, i);
    }

    public ResourceLeakDetector(Class<?> cls, int i) {
        this(StringUtil.simpleClassName(cls), i, Long.MAX_VALUE);
    }

    @Deprecated
    public ResourceLeakDetector(String str, int i, long j) {
        this.allLeaks = Collections.newSetFromMap(new ConcurrentHashMap());
        this.refQueue = new ReferenceQueue<>();
        this.reportedLeaks = Collections.newSetFromMap(new ConcurrentHashMap());
        this.resourceType = (String) ObjectUtil.checkNotNull(str, "resourceType");
        this.samplingInterval = i;
    }

    @Deprecated
    public final ResourceLeak open(T t) {
        return track0(t);
    }

    public final ResourceLeakTracker<T> track(T t) {
        return track0(t);
    }

    private DefaultResourceLeak track0(T t) {
        Level level2 = level;
        if (level2 == Level.DISABLED) {
            return null;
        }
        if (level2.ordinal() < Level.PARANOID.ordinal()) {
            if (PlatformDependent.threadLocalRandom().nextInt(this.samplingInterval) != 0) {
                return null;
            }
            reportLeak();
            return new DefaultResourceLeak(t, this.refQueue, this.allLeaks);
        }
        reportLeak();
        return new DefaultResourceLeak(t, this.refQueue, this.allLeaks);
    }

    private void clearRefQueue() {
        while (true) {
            DefaultResourceLeak defaultResourceLeak = (DefaultResourceLeak) this.refQueue.poll();
            if (defaultResourceLeak == null) {
                return;
            } else {
                defaultResourceLeak.dispose();
            }
        }
    }

    protected boolean needReport() {
        return logger.isErrorEnabled();
    }

    private void reportLeak() {
        if (!needReport()) {
            clearRefQueue();
            return;
        }
        while (true) {
            DefaultResourceLeak defaultResourceLeak = (DefaultResourceLeak) this.refQueue.poll();
            if (defaultResourceLeak == null) {
                return;
            }
            if (defaultResourceLeak.dispose()) {
                String defaultResourceLeak2 = defaultResourceLeak.toString();
                if (this.reportedLeaks.add(defaultResourceLeak2)) {
                    if (defaultResourceLeak2.isEmpty()) {
                        reportUntracedLeak(this.resourceType);
                    } else {
                        reportTracedLeak(this.resourceType, defaultResourceLeak2);
                    }
                }
            }
        }
    }

    protected void reportTracedLeak(String str, String str2) {
        logger.error("LEAK: {}.release() was not called before it's garbage-collected. See https://netty.io/wiki/reference-counted-objects.html for more information.{}", str, str2);
    }

    protected void reportUntracedLeak(String str) {
        logger.error("LEAK: {}.release() was not called before it's garbage-collected. Enable advanced leak reporting to find out where the leak occurred. To enable advanced leak reporting, specify the JVM option '-D{}={}' or call {}.setLevel() See https://netty.io/wiki/reference-counted-objects.html for more information.", str, PROP_LEVEL, Level.ADVANCED.name().toLowerCase(), StringUtil.simpleClassName(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class DefaultResourceLeak<T> extends WeakReference<Object> implements ResourceLeakTracker<T>, ResourceLeak {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Set<DefaultResourceLeak<?>> allLeaks;
        private volatile int droppedRecords;
        private volatile TraceRecord head;
        private final int trackedHash;
        private static final AtomicReferenceFieldUpdater<DefaultResourceLeak<?>, TraceRecord> headUpdater = AtomicReferenceFieldUpdater.newUpdater(DefaultResourceLeak.class, TraceRecord.class, "head");
        private static final AtomicIntegerFieldUpdater<DefaultResourceLeak<?>> droppedRecordsUpdater = AtomicIntegerFieldUpdater.newUpdater(DefaultResourceLeak.class, "droppedRecords");

        DefaultResourceLeak(Object obj, ReferenceQueue<Object> referenceQueue, Set<DefaultResourceLeak<?>> set) {
            super(obj, referenceQueue);
            this.trackedHash = System.identityHashCode(obj);
            set.add(this);
            headUpdater.set(this, new TraceRecord(TraceRecord.BOTTOM));
            this.allLeaks = set;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ResourceLeakTracker, io.grpc.netty.shaded.io.netty.util.ResourceLeak
        public void record() {
            record0(null);
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ResourceLeakTracker, io.grpc.netty.shaded.io.netty.util.ResourceLeak
        public void record(Object obj) {
            record0(obj);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0046  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void record0(Object obj) {
            TraceRecord traceRecord;
            boolean z;
            TraceRecord traceRecord2;
            if (ResourceLeakDetector.TARGET_RECORDS <= 0) {
                return;
            }
            do {
                traceRecord = headUpdater.get(this);
                if (traceRecord == null) {
                    return;
                }
                int i = traceRecord.pos + 1;
                if (i >= ResourceLeakDetector.TARGET_RECORDS) {
                    z = PlatformDependent.threadLocalRandom().nextInt(1 << Math.min(i - ResourceLeakDetector.TARGET_RECORDS, 30)) != 0;
                    if (z) {
                        traceRecord2 = traceRecord.next;
                    }
                }
                traceRecord2 = traceRecord;
            } while (!headUpdater.compareAndSet(this, traceRecord, obj == null ? new TraceRecord(traceRecord2, obj) : new TraceRecord(traceRecord2)));
            if (z) {
                droppedRecordsUpdater.incrementAndGet(this);
            }
        }

        boolean dispose() {
            clear();
            return this.allLeaks.remove(this);
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ResourceLeak
        public boolean close() {
            if (!this.allLeaks.remove(this)) {
                return false;
            }
            clear();
            headUpdater.set(this, null);
            return true;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ResourceLeakTracker
        public boolean close(T t) {
            try {
                return close();
            } finally {
                reachabilityFence0(t);
            }
        }

        private static void reachabilityFence0(Object obj) {
            if (obj != null) {
                synchronized (obj) {
                }
            }
        }

        public String toString() {
            TraceRecord andSet = headUpdater.getAndSet(this, null);
            if (andSet == null) {
                return "";
            }
            int i = droppedRecordsUpdater.get(this);
            int i2 = 0;
            int i3 = 1;
            int i4 = andSet.pos + 1;
            StringBuilder sb = new StringBuilder(i4 * 2048);
            sb.append(StringUtil.NEWLINE);
            sb.append("Recent access records: ");
            sb.append(StringUtil.NEWLINE);
            HashSet hashSet = new HashSet(i4);
            while (andSet != TraceRecord.BOTTOM) {
                String traceRecord = andSet.toString();
                if (!hashSet.add(traceRecord)) {
                    i2++;
                } else if (andSet.next == TraceRecord.BOTTOM) {
                    sb.append("Created at:");
                    sb.append(StringUtil.NEWLINE);
                    sb.append(traceRecord);
                } else {
                    sb.append('#');
                    sb.append(i3);
                    sb.append(':');
                    sb.append(StringUtil.NEWLINE);
                    sb.append(traceRecord);
                    i3++;
                }
                andSet = andSet.next;
            }
            if (i2 > 0) {
                sb.append(": ");
                sb.append(i2);
                sb.append(" leak records were discarded because they were duplicates");
                sb.append(StringUtil.NEWLINE);
            }
            if (i > 0) {
                sb.append(": ");
                sb.append(i);
                sb.append(" leak records were discarded because the leak record count is targeted to ");
                sb.append(ResourceLeakDetector.TARGET_RECORDS);
                sb.append(". Use system property ");
                sb.append(ResourceLeakDetector.PROP_TARGET_RECORDS);
                sb.append(" to increase the limit.");
                sb.append(StringUtil.NEWLINE);
            }
            sb.setLength(sb.length() - StringUtil.NEWLINE.length());
            return sb.toString();
        }
    }

    public static void addExclusions(Class cls, String... strArr) {
        String[] strArr2;
        String[] strArr3;
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        for (int i = 0; i < length && (!hashSet.remove(declaredMethods[i].getName()) || !hashSet.isEmpty()); i++) {
        }
        if (!hashSet.isEmpty()) {
            throw new IllegalArgumentException("Can't find '" + hashSet + "' in " + cls.getName());
        }
        do {
            strArr2 = excludedMethods.get();
            strArr3 = (String[]) Arrays.copyOf(strArr2, strArr2.length + (strArr.length * 2));
            for (int i2 = 0; i2 < strArr.length; i2++) {
                int i3 = i2 * 2;
                strArr3[strArr2.length + i3] = cls.getName();
                strArr3[strArr2.length + i3 + 1] = strArr[i2];
            }
        } while (!excludedMethods.compareAndSet(strArr2, strArr3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class TraceRecord extends Throwable {
        private static final TraceRecord BOTTOM = new TraceRecord();
        private static final long serialVersionUID = 6065153674892850720L;
        private final String hintString;
        private final TraceRecord next;
        private final int pos;

        TraceRecord(TraceRecord traceRecord, Object obj) {
            this.hintString = obj instanceof ResourceLeakHint ? ((ResourceLeakHint) obj).toHintString() : obj.toString();
            this.next = traceRecord;
            this.pos = traceRecord.pos + 1;
        }

        TraceRecord(TraceRecord traceRecord) {
            this.hintString = null;
            this.next = traceRecord;
            this.pos = traceRecord.pos + 1;
        }

        private TraceRecord() {
            this.hintString = null;
            this.next = null;
            this.pos = -1;
        }

        @Override // java.lang.Throwable
        public String toString() {
            int i;
            StringBuilder sb = new StringBuilder(2048);
            if (this.hintString != null) {
                sb.append("\tHint: ");
                sb.append(this.hintString);
                sb.append(StringUtil.NEWLINE);
            }
            StackTraceElement[] stackTrace = getStackTrace();
            for (int i2 = 3; i2 < stackTrace.length; i2++) {
                StackTraceElement stackTraceElement = stackTrace[i2];
                String[] strArr = (String[]) ResourceLeakDetector.excludedMethods.get();
                while (true) {
                    if (i < strArr.length) {
                        i = (strArr[i].equals(stackTraceElement.getClassName()) && strArr[i + 1].equals(stackTraceElement.getMethodName())) ? 0 : i + 2;
                    } else {
                        sb.append('\t');
                        sb.append(stackTraceElement.toString());
                        sb.append(StringUtil.NEWLINE);
                        break;
                    }
                }
            }
            return sb.toString();
        }
    }
}
