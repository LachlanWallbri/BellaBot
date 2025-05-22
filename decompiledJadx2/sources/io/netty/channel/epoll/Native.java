package io.netty.channel.epoll;

import io.netty.channel.epoll.NativeDatagramPacketArray;
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
import java.nio.channels.ClosedChannelException;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class Native {
    public static final int EPOLLERR;
    public static final int EPOLLET;
    public static final int EPOLLIN;
    public static final int EPOLLOUT;
    public static final int EPOLLRDHUP;
    public static final boolean IS_SUPPORTING_SENDMMSG;
    public static final boolean IS_SUPPORTING_TCP_FASTOPEN;
    public static final String KERNEL_VERSION;
    private static final ClosedChannelException SENDMMSG_CLOSED_CHANNEL_EXCEPTION;
    private static final Errors.NativeIoException SENDMMSG_CONNECTION_RESET_EXCEPTION;
    private static final ClosedChannelException SPLICE_CLOSED_CHANNEL_EXCEPTION;
    private static final Errors.NativeIoException SPLICE_CONNECTION_RESET_EXCEPTION;
    public static final int TCP_MD5SIG_MAXKEYLEN;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Native.class);

    private static native int epollCreate();

    private static native int epollCtlAdd0(int i, int i2, int i3);

    private static native int epollCtlDel0(int i, int i2);

    private static native int epollCtlMod0(int i, int i2, int i3);

    private static native int epollWait0(int i, long j, int i2, int i3, int i4, int i5);

    private static native int eventFd();

    public static native void eventFdRead(int i);

    public static native void eventFdWrite(int i, long j);

    public static native int offsetofEpollData();

    private static native int sendmmsg0(int i, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3);

    public static native int sizeofEpollEvent();

    private static native int splice0(int i, long j, int i2, long j2, long j3);

    private static native int timerFd();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void timerFdRead(int i);

    static {
        try {
            offsetofEpollData();
        } catch (UnsatisfiedLinkError unused) {
            loadNativeLibrary();
        }
        Socket.initialize();
        EPOLLIN = NativeStaticallyReferencedJniMethods.epollin();
        EPOLLOUT = NativeStaticallyReferencedJniMethods.epollout();
        EPOLLRDHUP = NativeStaticallyReferencedJniMethods.epollrdhup();
        EPOLLET = NativeStaticallyReferencedJniMethods.epollet();
        EPOLLERR = NativeStaticallyReferencedJniMethods.epollerr();
        IS_SUPPORTING_SENDMMSG = NativeStaticallyReferencedJniMethods.isSupportingSendmmsg();
        IS_SUPPORTING_TCP_FASTOPEN = NativeStaticallyReferencedJniMethods.isSupportingTcpFastopen();
        TCP_MD5SIG_MAXKEYLEN = NativeStaticallyReferencedJniMethods.tcpMd5SigMaxKeyLen();
        KERNEL_VERSION = NativeStaticallyReferencedJniMethods.kernelVersion();
        SENDMMSG_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), Native.class, "sendmmsg(...)");
        SPLICE_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), Native.class, "splice(...)");
        SENDMMSG_CONNECTION_RESET_EXCEPTION = Errors.newConnectionResetException("syscall:sendmmsg(...)", Errors.ERRNO_EPIPE_NEGATIVE);
        SPLICE_CONNECTION_RESET_EXCEPTION = Errors.newConnectionResetException("syscall:splice(...)", Errors.ERRNO_EPIPE_NEGATIVE);
    }

    public static FileDescriptor newEventFd() {
        return new FileDescriptor(eventFd());
    }

    public static FileDescriptor newTimerFd() {
        return new FileDescriptor(timerFd());
    }

    public static FileDescriptor newEpollCreate() {
        return new FileDescriptor(epollCreate());
    }

    public static int epollWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray, FileDescriptor fileDescriptor2, int i, int i2) throws IOException {
        int epollWait0 = epollWait0(fileDescriptor.intValue(), epollEventArray.memoryAddress(), epollEventArray.length(), fileDescriptor2.intValue(), i, i2);
        if (epollWait0 >= 0) {
            return epollWait0;
        }
        throw Errors.newIOException("epoll_wait", epollWait0);
    }

    public static void epollCtlAdd(int i, int i2, int i3) throws IOException {
        int epollCtlAdd0 = epollCtlAdd0(i, i2, i3);
        if (epollCtlAdd0 < 0) {
            throw Errors.newIOException("epoll_ctl", epollCtlAdd0);
        }
    }

    public static void epollCtlMod(int i, int i2, int i3) throws IOException {
        int epollCtlMod0 = epollCtlMod0(i, i2, i3);
        if (epollCtlMod0 < 0) {
            throw Errors.newIOException("epoll_ctl", epollCtlMod0);
        }
    }

    public static void epollCtlDel(int i, int i2) throws IOException {
        int epollCtlDel0 = epollCtlDel0(i, i2);
        if (epollCtlDel0 < 0) {
            throw Errors.newIOException("epoll_ctl", epollCtlDel0);
        }
    }

    public static int splice(int i, long j, int i2, long j2, long j3) throws IOException {
        int splice0 = splice0(i, j, i2, j2, j3);
        return splice0 >= 0 ? splice0 : Errors.ioResult("splice", splice0, SPLICE_CONNECTION_RESET_EXCEPTION, SPLICE_CLOSED_CHANNEL_EXCEPTION);
    }

    public static int sendmmsg(int i, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3) throws IOException {
        int sendmmsg0 = sendmmsg0(i, nativeDatagramPacketArr, i2, i3);
        return sendmmsg0 >= 0 ? sendmmsg0 : Errors.ioResult("sendmmsg", sendmmsg0, SENDMMSG_CONNECTION_RESET_EXCEPTION, SENDMMSG_CLOSED_CHANNEL_EXCEPTION);
    }

    private static void loadNativeLibrary() {
        if (!SystemPropertyUtil.get("os.name").toLowerCase(Locale.UK).trim().startsWith("linux")) {
            throw new IllegalStateException("Only supported on Linux");
        }
        String str = "netty_transport_native_epoll_" + PlatformDependent.normalizedArch();
        ClassLoader classLoader = PlatformDependent.getClassLoader(Native.class);
        try {
            NativeLibraryLoader.load(str, classLoader);
        } catch (UnsatisfiedLinkError e) {
            try {
                NativeLibraryLoader.load("netty_transport_native_epoll", classLoader);
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
