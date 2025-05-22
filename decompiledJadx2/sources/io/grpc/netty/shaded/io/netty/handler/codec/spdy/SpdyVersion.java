package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum SpdyVersion {
    SPDY_3_1(3, 1);

    private final int minorVersion;
    private final int version;

    SpdyVersion(int i, int i2) {
        this.version = i;
        this.minorVersion = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getVersion() {
        return this.version;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinorVersion() {
        return this.minorVersion;
    }
}
