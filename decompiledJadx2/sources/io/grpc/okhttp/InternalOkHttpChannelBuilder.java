package io.grpc.okhttp;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalOkHttpChannelBuilder {
    public static void setStatsEnabled(OkHttpChannelBuilder okHttpChannelBuilder, boolean z) {
        okHttpChannelBuilder.setStatsEnabled(z);
    }

    public static void disableCheckAuthority(OkHttpChannelBuilder okHttpChannelBuilder) {
        okHttpChannelBuilder.disableCheckAuthority();
    }

    public static void enableCheckAuthority(OkHttpChannelBuilder okHttpChannelBuilder) {
        okHttpChannelBuilder.enableCheckAuthority();
    }

    private InternalOkHttpChannelBuilder() {
    }
}
