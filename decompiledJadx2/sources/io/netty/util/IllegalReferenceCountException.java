package io.netty.util;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class IllegalReferenceCountException extends IllegalStateException {
    private static final long serialVersionUID = -2507492394288153468L;

    public IllegalReferenceCountException() {
    }

    public IllegalReferenceCountException(int i) {
        this("refCnt: " + i);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public IllegalReferenceCountException(int i, int i2) {
        this(r0.toString());
        StringBuilder sb;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("refCnt: ");
        sb2.append(i);
        sb2.append(", ");
        if (i2 > 0) {
            sb = new StringBuilder();
            sb.append("increment: ");
        } else {
            sb = new StringBuilder();
            sb.append("decrement: ");
            i2 = -i2;
        }
        sb.append(i2);
        sb2.append(sb.toString());
    }

    public IllegalReferenceCountException(String str) {
        super(str);
    }

    public IllegalReferenceCountException(String str, Throwable th) {
        super(str, th);
    }

    public IllegalReferenceCountException(Throwable th) {
        super(th);
    }
}
