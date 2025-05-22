package io.netty.channel.kqueue;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
final class KQueueStaticallyReferencedJniMethods {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evAdd();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evClear();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evDelete();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evDisable();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evEOF();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evEnable();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evError();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evfiltRead();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evfiltSock();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evfiltUser();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short evfiltWrite();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short noteConnReset();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short noteDisconnected();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short noteReadClosed();

    private KQueueStaticallyReferencedJniMethods() {
    }
}
