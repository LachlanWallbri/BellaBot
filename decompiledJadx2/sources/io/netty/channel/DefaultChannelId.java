package io.netty.channel;

import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.MacAddressUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.codec.language.Soundex;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class DefaultChannelId implements ChannelId {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte[] MACHINE_ID;
    private static final int PROCESS_ID;
    private static final int PROCESS_ID_LEN = 4;
    private static final int RANDOM_LEN = 4;
    private static final int SEQUENCE_LEN = 4;
    private static final int TIMESTAMP_LEN = 8;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultChannelId.class);
    private static final AtomicInteger nextSequence = new AtomicInteger();
    private static final long serialVersionUID = 3884076183504074063L;
    private final byte[] data;
    private final int hashCode;
    private transient String longValue;
    private transient String shortValue;

    static {
        int i;
        String str = SystemPropertyUtil.get("io.netty.processId");
        int i2 = -1;
        if (str != null) {
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                i = -1;
            }
            if (i < 0) {
                logger.warn("-Dio.netty.processId: {} (malformed)", str);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("-Dio.netty.processId: {} (user-set)", Integer.valueOf(i));
                }
                i2 = i;
            }
        }
        if (i2 < 0) {
            i2 = defaultProcessId();
            if (logger.isDebugEnabled()) {
                logger.debug("-Dio.netty.processId: {} (auto-detected)", Integer.valueOf(i2));
            }
        }
        PROCESS_ID = i2;
        byte[] bArr = null;
        String str2 = SystemPropertyUtil.get("io.netty.machineId");
        if (str2 != null) {
            try {
                bArr = MacAddressUtil.parseMAC(str2);
            } catch (Exception e) {
                logger.warn("-Dio.netty.machineId: {} (malformed)", str2, e);
            }
            if (bArr != null) {
                logger.debug("-Dio.netty.machineId: {} (user-set)", str2);
            }
        }
        if (bArr == null) {
            bArr = MacAddressUtil.defaultMachineId();
            if (logger.isDebugEnabled()) {
                logger.debug("-Dio.netty.machineId: {} (auto-detected)", MacAddressUtil.formatAddress(bArr));
            }
        }
        MACHINE_ID = bArr;
    }

    public static DefaultChannelId newInstance() {
        return new DefaultChannelId();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:1|(2:2|3)|(2:4|5)|6|(1:8)|9|10|(2:12|13)(1:15)) */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0073, code lost:
    
        r0 = -1;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int defaultProcessId() {
        ClassLoader classLoader;
        String str;
        int indexOf;
        int i;
        try {
            classLoader = PlatformDependent.getClassLoader(DefaultChannelId.class);
        } catch (Throwable th) {
            th = th;
            classLoader = null;
        }
        try {
            Class<?> cls = Class.forName("java.lang.management.ManagementFactory", true, classLoader);
            str = (String) Class.forName("java.lang.management.RuntimeMXBean", true, classLoader).getMethod("getName", EmptyArrays.EMPTY_CLASSES).invoke(cls.getMethod("getRuntimeMXBean", EmptyArrays.EMPTY_CLASSES).invoke(null, EmptyArrays.EMPTY_OBJECTS), EmptyArrays.EMPTY_OBJECTS);
        } catch (Throwable th2) {
            th = th2;
            logger.debug("Could not invoke ManagementFactory.getRuntimeMXBean().getName(); Android?", th);
            try {
                str = Class.forName("android.os.Process", true, classLoader).getMethod("myPid", EmptyArrays.EMPTY_CLASSES).invoke(null, EmptyArrays.EMPTY_OBJECTS).toString();
            } catch (Throwable th3) {
                logger.debug("Could not invoke Process.myPid(); not Android?", th3);
                str = "";
            }
            indexOf = str.indexOf(64);
            if (indexOf >= 0) {
            }
            i = Integer.parseInt(str);
            if (i < 0) {
            }
        }
        indexOf = str.indexOf(64);
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        i = Integer.parseInt(str);
        if (i < 0) {
            return i;
        }
        int nextInt = PlatformDependent.threadLocalRandom().nextInt();
        logger.warn("Failed to find the current process ID from '{}'; using a random value: {}", str, Integer.valueOf(nextInt));
        return nextInt;
    }

    private DefaultChannelId() {
        byte[] bArr = MACHINE_ID;
        byte[] bArr2 = new byte[bArr.length + 4 + 4 + 8 + 4];
        this.data = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        writeInt(writeLong(writeInt(writeInt(MACHINE_ID.length + 0, PROCESS_ID), nextSequence.getAndIncrement()), Long.reverse(System.nanoTime()) ^ System.currentTimeMillis()), PlatformDependent.threadLocalRandom().nextInt());
        this.hashCode = Arrays.hashCode(this.data);
    }

    private int writeInt(int i, int i2) {
        byte[] bArr = this.data;
        int i3 = i + 1;
        bArr[i] = (byte) (i2 >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        int i6 = i5 + 1;
        bArr[i5] = (byte) i2;
        return i6;
    }

    private int writeLong(int i, long j) {
        byte[] bArr = this.data;
        int i2 = i + 1;
        bArr[i] = (byte) (j >>> 56);
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j >>> 48);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j >>> 40);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j >>> 32);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j >>> 24);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j >>> 16);
        int i8 = i7 + 1;
        bArr[i7] = (byte) (j >>> 8);
        int i9 = i8 + 1;
        bArr[i8] = (byte) j;
        return i9;
    }

    @Override // io.netty.channel.ChannelId
    public String asShortText() {
        String str = this.shortValue;
        if (str != null) {
            return str;
        }
        byte[] bArr = this.data;
        String hexDump = ByteBufUtil.hexDump(bArr, bArr.length - 4, 4);
        this.shortValue = hexDump;
        return hexDump;
    }

    @Override // io.netty.channel.ChannelId
    public String asLongText() {
        String str = this.longValue;
        if (str != null) {
            return str;
        }
        String newLongValue = newLongValue();
        this.longValue = newLongValue;
        return newLongValue;
    }

    private String newLongValue() {
        StringBuilder sb = new StringBuilder((this.data.length * 2) + 5);
        appendHexDumpField(sb, appendHexDumpField(sb, appendHexDumpField(sb, appendHexDumpField(sb, appendHexDumpField(sb, 0, MACHINE_ID.length), 4), 4), 8), 4);
        return sb.substring(0, sb.length() - 1);
    }

    private int appendHexDumpField(StringBuilder sb, int i, int i2) {
        sb.append(ByteBufUtil.hexDump(this.data, i, i2));
        sb.append(Soundex.SILENT_MARKER);
        return i + i2;
    }

    public int hashCode() {
        return this.hashCode;
    }

    @Override // java.lang.Comparable
    public int compareTo(ChannelId channelId) {
        if (this == channelId) {
            return 0;
        }
        if (channelId instanceof DefaultChannelId) {
            byte[] bArr = ((DefaultChannelId) channelId).data;
            int length = this.data.length;
            int length2 = bArr.length;
            int min = Math.min(length, length2);
            for (int i = 0; i < min; i++) {
                byte b = this.data[i];
                byte b2 = bArr[i];
                if (b != b2) {
                    return (b & 255) - (b2 & 255);
                }
            }
            return length - length2;
        }
        return asLongText().compareTo(channelId.asLongText());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DefaultChannelId)) {
            return false;
        }
        DefaultChannelId defaultChannelId = (DefaultChannelId) obj;
        return this.hashCode == defaultChannelId.hashCode && Arrays.equals(this.data, defaultChannelId.data);
    }

    public String toString() {
        return asShortText();
    }
}
