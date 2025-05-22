package io.opencensus.common;

import io.opencensus.common.ServerStatsFieldEnums;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class ServerStatsEncoding {
    public static final byte CURRENT_VERSION = 0;

    private ServerStatsEncoding() {
    }

    public static byte[] toBytes(ServerStats serverStats) {
        ByteBuffer allocate = ByteBuffer.allocate(ServerStatsFieldEnums.getTotalSize() + 1);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.put((byte) 0);
        allocate.put((byte) ServerStatsFieldEnums.EnumC7535Id.SERVER_STATS_LB_LATENCY_ID.value());
        allocate.putLong(serverStats.getLbLatencyNs());
        allocate.put((byte) ServerStatsFieldEnums.EnumC7535Id.SERVER_STATS_SERVICE_LATENCY_ID.value());
        allocate.putLong(serverStats.getServiceLatencyNs());
        allocate.put((byte) ServerStatsFieldEnums.EnumC7535Id.SERVER_STATS_TRACE_OPTION_ID.value());
        allocate.put(serverStats.getTraceOption());
        return allocate.array();
    }

    public static ServerStats parseBytes(byte[] bArr) throws ServerStatsDeserializationException {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        if (!wrap.hasRemaining()) {
            throw new ServerStatsDeserializationException("Serialized ServerStats buffer is empty");
        }
        byte b = wrap.get();
        if (b > 0 || b < 0) {
            throw new ServerStatsDeserializationException("Invalid ServerStats version: " + ((int) b));
        }
        long j = 0;
        byte b2 = 0;
        long j2 = 0;
        while (wrap.hasRemaining()) {
            ServerStatsFieldEnums.EnumC7535Id valueOf = ServerStatsFieldEnums.EnumC7535Id.valueOf(wrap.get() & 255);
            if (valueOf == null) {
                wrap.position(wrap.limit());
            } else {
                int i = C75341.$SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id[valueOf.ordinal()];
                if (i == 1) {
                    j = wrap.getLong();
                } else if (i == 2) {
                    j2 = wrap.getLong();
                } else if (i == 3) {
                    b2 = wrap.get();
                }
            }
        }
        try {
            return ServerStats.create(j, j2, b2);
        } catch (IllegalArgumentException e) {
            throw new ServerStatsDeserializationException("Serialized ServiceStats contains invalid values: " + e.getMessage());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.opencensus.common.ServerStatsEncoding$1 */
    /* loaded from: classes8.dex */
    static /* synthetic */ class C75341 {
        static final /* synthetic */ int[] $SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id = new int[ServerStatsFieldEnums.EnumC7535Id.values().length];

        static {
            try {
                $SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id[ServerStatsFieldEnums.EnumC7535Id.SERVER_STATS_LB_LATENCY_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id[ServerStatsFieldEnums.EnumC7535Id.SERVER_STATS_SERVICE_LATENCY_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id[ServerStatsFieldEnums.EnumC7535Id.SERVER_STATS_TRACE_OPTION_ID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
