package io.netty.channel.unix;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class Limits {
    public static final int IOV_MAX = LimitsStaticallyReferencedJniMethods.iovMax();
    public static final int UIO_MAX_IOV = LimitsStaticallyReferencedJniMethods.uioMaxIov();
    public static final long SSIZE_MAX = LimitsStaticallyReferencedJniMethods.ssizeMax();
    public static final int SIZEOF_JLONG = LimitsStaticallyReferencedJniMethods.sizeOfjlong();

    private Limits() {
    }
}
