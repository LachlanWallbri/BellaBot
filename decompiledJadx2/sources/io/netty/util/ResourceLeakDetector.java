package io.netty.util;

import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class ResourceLeakDetector<T> {
    static final int DEFAULT_SAMPLING_INTERVAL = 128;
    private static final int DEFAULT_TARGET_RECORDS = 4;
    private static final String PROP_LEVEL = "io.netty.leakDetection.level";
    private static final String PROP_LEVEL_OLD = "io.netty.leakDetectionLevel";
    private static final String PROP_TARGET_RECORDS = "io.netty.leakDetection.targetRecords";
    private static final int TARGET_RECORDS;
    private static final AtomicReference<String[]> excludedMethods;
    private static Level level;
    private final ConcurrentMap<DefaultResourceLeak<?>, LeakEntry> allLeaks;
    private final ReferenceQueue<Object> refQueue;
    private final ConcurrentMap<String, Boolean> reportedLeaks;
    private final String resourceType;
    private final int samplingInterval;
    private static final Level DEFAULT_LEVEL = Level.SIMPLE;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ResourceLeakDetector.class);

    @Deprecated
    protected void reportInstancesLeak(String str) {
    }

    static {
        boolean z = false;
        if (SystemPropertyUtil.get("io.netty.noResourceLeakDetection") != null) {
            z = SystemPropertyUtil.getBoolean("io.netty.noResourceLeakDetection", false);
            logger.debug("-Dio.netty.noResourceLeakDetection: {}", Boolean.valueOf(z));
            logger.warn("-Dio.netty.noResourceLeakDetection is deprecated. Use '-D{}={}' instead.", PROP_LEVEL, DEFAULT_LEVEL.name().toLowerCase());
        }
        Level parseLevel = Level.parseLevel(SystemPropertyUtil.get(PROP_LEVEL, SystemPropertyUtil.get(PROP_LEVEL_OLD, (z ? Level.DISABLED : DEFAULT_LEVEL).name())));
        TARGET_RECORDS = SystemPropertyUtil.getInt(PROP_TARGET_RECORDS, 4);
        level = parseLevel;
        if (logger.isDebugEnabled()) {
            logger.debug("-D{}: {}", PROP_LEVEL, parseLevel.name().toLowerCase());
            logger.debug("-D{}: {}", PROP_TARGET_RECORDS, Integer.valueOf(TARGET_RECORDS));
        }
        excludedMethods = new AtomicReference<>(EmptyArrays.EMPTY_STRINGS);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
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
        if (level2 == null) {
            throw new NullPointerException("level");
        }
        level = level2;
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
        this.allLeaks = PlatformDependent.newConcurrentHashMap();
        this.refQueue = new ReferenceQueue<>();
        this.reportedLeaks = PlatformDependent.newConcurrentHashMap();
        if (str == null) {
            throw new NullPointerException("resourceType");
        }
        this.resourceType = str;
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

    private void reportLeak() {
        if (!logger.isErrorEnabled()) {
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
                if (this.reportedLeaks.putIfAbsent(defaultResourceLeak2, Boolean.TRUE) == null) {
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
        logger.error("LEAK: {}.release() was not called before it's garbage-collected. See http://netty.io/wiki/reference-counted-objects.html for more information.{}", str, str2);
    }

    protected void reportUntracedLeak(String str) {
        logger.error("LEAK: {}.release() was not called before it's garbage-collected. Enable advanced leak reporting to find out where the leak occurred. To enable advanced leak reporting, specify the JVM option '-D{}={}' or call {}.setLevel() See http://netty.io/wiki/reference-counted-objects.html for more information.", str, PROP_LEVEL, Level.ADVANCED.name().toLowerCase(), StringUtil.simpleClassName(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class DefaultResourceLeak<T> extends WeakReference<Object> implements ResourceLeakTracker<T>, ResourceLeak {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final ConcurrentMap<DefaultResourceLeak<?>, LeakEntry> allLeaks;
        private volatile int droppedRecords;
        private volatile Record head;
        private final int trackedHash;
        private static final AtomicReferenceFieldUpdater<DefaultResourceLeak<?>, Record> headUpdater = AtomicReferenceFieldUpdater.newUpdater(DefaultResourceLeak.class, Record.class, "head");
        private static final AtomicIntegerFieldUpdater<DefaultResourceLeak<?>> droppedRecordsUpdater = AtomicIntegerFieldUpdater.newUpdater(DefaultResourceLeak.class, "droppedRecords");

        DefaultResourceLeak(Object obj, ReferenceQueue<Object> referenceQueue, ConcurrentMap<DefaultResourceLeak<?>, LeakEntry> concurrentMap) {
            super(obj, referenceQueue);
            this.trackedHash = System.identityHashCode(obj);
            concurrentMap.put(this, LeakEntry.INSTANCE);
            headUpdater.set(this, new Record(Record.BOTTOM));
            this.allLeaks = concurrentMap;
        }

        @Override // io.netty.util.ResourceLeakTracker, io.netty.util.ResourceLeak
        public void record() {
            record0(null);
        }

        @Override // io.netty.util.ResourceLeakTracker, io.netty.util.ResourceLeak
        public void record(Object obj) {
            record0(obj);
        }

        private void record0(Object obj) {
            Record record;
            boolean z;
            Record record2;
            if (ResourceLeakDetector.TARGET_RECORDS <= 0) {
                return;
            }
            do {
                record = headUpdater.get(this);
                if (record == null) {
                    return;
                }
                int i = record.pos + 1;
                z = false;
                if (i >= ResourceLeakDetector.TARGET_RECORDS) {
                    boolean z2 = PlatformDependent.threadLocalRandom().nextInt(1 << Math.min(i - ResourceLeakDetector.TARGET_RECORDS, 30)) != 0;
                    record2 = z2 ? record.next : record;
                    z = z2;
                } else {
                    record2 = record;
                }
            } while (!headUpdater.compareAndSet(this, record, obj != null ? new Record(record2, obj) : new Record(record2)));
            if (z) {
                droppedRecordsUpdater.incrementAndGet(this);
            }
        }

        boolean dispose() {
            clear();
            return this.allLeaks.remove(this, LeakEntry.INSTANCE);
        }

        @Override // io.netty.util.ResourceLeak
        public boolean close() {
            if (!this.allLeaks.remove(this, LeakEntry.INSTANCE)) {
                return false;
            }
            clear();
            headUpdater.set(this, null);
            return true;
        }

        @Override // io.netty.util.ResourceLeakTracker
        public boolean close(T t) {
            return close() && t != null;
        }

        public String toString() {
            Record andSet = headUpdater.getAndSet(this, null);
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
            while (andSet != Record.BOTTOM) {
                String record = andSet.toString();
                if (!hashSet.add(record)) {
                    i2++;
                } else if (andSet.next == Record.BOTTOM) {
                    sb.append("Created at:");
                    sb.append(StringUtil.NEWLINE);
                    sb.append(record);
                } else {
                    sb.append('#');
                    sb.append(i3);
                    sb.append(':');
                    sb.append(StringUtil.NEWLINE);
                    sb.append(record);
                    i3++;
                }
                andSet = andSet.next;
            }
            if (i2 > 0) {
                sb.append(": ");
                sb.append(i);
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
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class Record extends Throwable {
        private static final Record BOTTOM = new Record();
        private static final long serialVersionUID = 6065153674892850720L;
        private final String hintString;
        private final Record next;
        private final int pos;

        Record(Record record, Object obj) {
            this.hintString = obj instanceof ResourceLeakHint ? ((ResourceLeakHint) obj).toHintString() : obj.toString();
            this.next = record;
            this.pos = record.pos + 1;
        }

        Record(Record record) {
            this.hintString = null;
            this.next = record;
            this.pos = record.pos + 1;
        }

        private Record() {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class LeakEntry {
        private static final int HASH;
        static final LeakEntry INSTANCE;

        public boolean equals(Object obj) {
            return obj == this;
        }

        static {
            LeakEntry leakEntry = new LeakEntry();
            INSTANCE = leakEntry;
            HASH = System.identityHashCode(leakEntry);
        }

        private LeakEntry() {
        }

        public int hashCode() {
            return HASH;
        }
    }
}
