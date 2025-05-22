package com.pudutech.base;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* compiled from: PpmdEncode.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0004J\u0019\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0082 J\t\u0010\u000b\u001a\u00020\u0004H\u0082 J\u0011\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0082 J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/base/PpmdEncode;", "", "()V", "addData", "", "data", "", "len", "", "close", "nativeAddData", "nativeClose", "nativeOpen", "", "filename", "", "open", "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class PpmdEncode {
    public static final PpmdEncode INSTANCE = new PpmdEncode();

    private final native void nativeAddData(byte[] data, int len);

    private final native void nativeClose();

    private final native boolean nativeOpen(String filename);

    static {
        System.loadLibrary("ppmd_encode");
    }

    private PpmdEncode() {
    }

    public final boolean open(String filename) {
        Intrinsics.checkParameterIsNotNull(filename, "filename");
        return nativeOpen(filename);
    }

    public final void addData(byte[] data, int len) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        nativeAddData(data, len);
    }

    public final void close() {
        nativeClose();
    }
}
