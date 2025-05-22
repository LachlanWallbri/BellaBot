package io.netty.channel.kqueue;

import io.netty.channel.unix.Errors;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.Socket;
import io.netty.util.internal.NativeLibraryLoader;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class Native {
    static final short EVFILT_READ;
    static final short EVFILT_SOCK;
    static final short EVFILT_USER;
    static final short EVFILT_WRITE;
    static final short EV_ADD;
    static final short EV_ADD_CLEAR_ENABLE;
    static final short EV_CLEAR;
    static final short EV_DELETE;
    static final short EV_DELETE_DISABLE;
    static final short EV_DISABLE;
    static final short EV_ENABLE;
    static final short EV_EOF;
    static final short EV_ERROR;
    static final int NOTE_CONNRESET;
    static final int NOTE_DISCONNECTED;
    static final int NOTE_RDHUP;
    static final int NOTE_READCLOSED;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Native.class);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int keventAddUserEvent(int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int keventTriggerUserEvent(int i, int i2);

    private static native int keventWait(int i, long j, int i2, long j2, int i3, int i4, int i5);

    private static native int kqueueCreate();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int offsetofKEventFFlags();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int offsetofKEventFilter();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int offsetofKEventFlags();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int offsetofKEventIdent();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int offsetofKeventData();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int sizeofKEvent();

    static {
        try {
            sizeofKEvent();
        } catch (UnsatisfiedLinkError unused) {
            loadNativeLibrary();
        }
        Socket.initialize();
        EV_ADD = KQueueStaticallyReferencedJniMethods.evAdd();
        EV_ENABLE = KQueueStaticallyReferencedJniMethods.evEnable();
        EV_DISABLE = KQueueStaticallyReferencedJniMethods.evDisable();
        EV_DELETE = KQueueStaticallyReferencedJniMethods.evDelete();
        EV_CLEAR = KQueueStaticallyReferencedJniMethods.evClear();
        EV_ERROR = KQueueStaticallyReferencedJniMethods.evError();
        EV_EOF = KQueueStaticallyReferencedJniMethods.evEOF();
        NOTE_READCLOSED = KQueueStaticallyReferencedJniMethods.noteReadClosed();
        NOTE_CONNRESET = KQueueStaticallyReferencedJniMethods.noteConnReset();
        short noteDisconnected = KQueueStaticallyReferencedJniMethods.noteDisconnected();
        NOTE_DISCONNECTED = noteDisconnected;
        NOTE_RDHUP = noteDisconnected | NOTE_READCLOSED | NOTE_CONNRESET;
        EV_ADD_CLEAR_ENABLE = (short) (EV_ADD | EV_CLEAR | EV_ENABLE);
        EV_DELETE_DISABLE = (short) (EV_DELETE | EV_DISABLE);
        EVFILT_READ = KQueueStaticallyReferencedJniMethods.evfiltRead();
        EVFILT_WRITE = KQueueStaticallyReferencedJniMethods.evfiltWrite();
        EVFILT_USER = KQueueStaticallyReferencedJniMethods.evfiltUser();
        EVFILT_SOCK = KQueueStaticallyReferencedJniMethods.evfiltSock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FileDescriptor newKQueue() {
        return new FileDescriptor(kqueueCreate());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int keventWait(int i, KQueueEventArray kQueueEventArray, KQueueEventArray kQueueEventArray2, int i2, int i3) throws IOException {
        int keventWait = keventWait(i, kQueueEventArray.memoryAddress(), kQueueEventArray.size(), kQueueEventArray2.memoryAddress(), kQueueEventArray2.capacity(), i2, i3);
        if (keventWait >= 0) {
            return keventWait;
        }
        throw Errors.newIOException("kevent", keventWait);
    }

    private static void loadNativeLibrary() {
        String trim = SystemPropertyUtil.get("os.name").toLowerCase(Locale.UK).trim();
        if (!trim.startsWith("mac") && !trim.contains("bsd") && !trim.startsWith("darwin")) {
            throw new IllegalStateException("Only supported on BSD");
        }
        String str = "netty_transport_native_kqueue_" + PlatformDependent.normalizedArch();
        ClassLoader classLoader = PlatformDependent.getClassLoader(Native.class);
        try {
            NativeLibraryLoader.load(str, classLoader);
        } catch (UnsatisfiedLinkError e) {
            try {
                NativeLibraryLoader.load("netty_transport_native_kqueue", classLoader);
                logger.debug("Failed to load {}", str, e);
            } catch (UnsatisfiedLinkError e2) {
                ThrowableUtil.addSuppressed(e, e2);
                throw e;
            }
        }
    }

    private Native() {
    }
}
