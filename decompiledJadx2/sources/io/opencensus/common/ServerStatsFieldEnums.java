package io.opencensus.common;

import java.util.TreeMap;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class ServerStatsFieldEnums {
    private static final int TOTALSIZE = computeTotalSize();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.opencensus.common.ServerStatsFieldEnums$Id */
    /* loaded from: classes8.dex */
    public enum EnumC7535Id {
        SERVER_STATS_LB_LATENCY_ID(0),
        SERVER_STATS_SERVICE_LATENCY_ID(1),
        SERVER_STATS_TRACE_OPTION_ID(2);

        private static final TreeMap<Integer, EnumC7535Id> map = new TreeMap<>();
        private final int value;

        static {
            for (EnumC7535Id enumC7535Id : values()) {
                map.put(Integer.valueOf(enumC7535Id.value), enumC7535Id);
            }
        }

        EnumC7535Id(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        @Nullable
        public static EnumC7535Id valueOf(int i) {
            return map.get(Integer.valueOf(i));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public enum Size {
        SERVER_STATS_LB_LATENCY_SIZE(8),
        SERVER_STATS_SERVICE_LATENCY_SIZE(8),
        SERVER_STATS_TRACE_OPTION_SIZE(1);

        private final int value;

        Size(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }

    private ServerStatsFieldEnums() {
    }

    private static int computeTotalSize() {
        int i = 0;
        for (Size size : Size.values()) {
            i = i + size.value() + 1;
        }
        return i;
    }

    public static int getTotalSize() {
        return TOTALSIZE;
    }
}
