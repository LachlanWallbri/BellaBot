package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.channel.epoll.NativeDatagramPacketArray;
import io.grpc.netty.shaded.io.netty.channel.unix.Errors;
import io.grpc.netty.shaded.io.netty.channel.unix.FileDescriptor;
import io.grpc.netty.shaded.io.netty.channel.unix.Socket;
import io.grpc.netty.shaded.io.netty.util.internal.NativeLibraryLoader;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ThrowableUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.nio.channels.Selector;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Native {
    public static final int EPOLLERR;
    public static final int EPOLLET;
    public static final int EPOLLIN;
    public static final int EPOLLOUT;
    public static final int EPOLLRDHUP;
    static final boolean IS_SUPPORTING_RECVMMSG;
    public static final boolean IS_SUPPORTING_SENDMMSG;
    public static final boolean IS_SUPPORTING_TCP_FASTOPEN;
    public static final String KERNEL_VERSION;
    public static final int TCP_MD5SIG_MAXKEYLEN;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Native.class);

    private static native int epollBusyWait0(int i, long j, int i2);

    private static native int epollCreate();

    private static native int epollCtlAdd0(int i, int i2, int i3);

    private static native int epollCtlDel0(int i, int i2);

    private static native int epollCtlMod0(int i, int i2, int i3);

    private static native int epollWait(int i, long j, int i2, int i3);

    private static native int epollWait0(int i, long j, int i2, int i3, int i4, int i5);

    private static native int eventFd();

    public static native void eventFdRead(int i);

    public static native void eventFdWrite(int i, long j);

    public static native int offsetofEpollData();

    private static native int recvmmsg0(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3);

    private static native int sendmmsg0(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3);

    public static native int sizeofEpollEvent();

    private static native int splice0(int i, long j, int i2, long j2, long j3);

    private static native int timerFd();

    static native void timerFdRead(int i);

    static native void timerFdSetTime(int i, int i2, int i3) throws IOException;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x001c, code lost:
    
        if (r0 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0011, code lost:
    
        if (r0 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
    
        io.grpc.netty.shaded.io.netty.channel.unix.Socket.initialize();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.EPOLLIN = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollin();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.EPOLLOUT = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollout();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.EPOLLRDHUP = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollrdhup();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.EPOLLET = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollet();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.EPOLLERR = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollerr();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.IS_SUPPORTING_SENDMMSG = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.isSupportingSendmmsg();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.IS_SUPPORTING_RECVMMSG = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.isSupportingRecvmmsg();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.IS_SUPPORTING_TCP_FASTOPEN = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.isSupportingTcpFastopen();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.TCP_MD5SIG_MAXKEYLEN = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.tcpMd5SigMaxKeyLen();
        io.grpc.netty.shaded.io.netty.channel.epoll.Native.KERNEL_VERSION = io.grpc.netty.shaded.io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.kernelVersion();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x005e, code lost:
    
        return;
     */
    static {
        Selector selector;
        try {
            selector = Selector.open();
        } catch (IOException unused) {
            selector = null;
        }
        try {
            try {
                offsetofEpollData();
            } catch (UnsatisfiedLinkError unused2) {
                loadNativeLibrary();
            }
        } catch (Throwable th) {
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
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

    @Deprecated
    public static int epollWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray, FileDescriptor fileDescriptor2, int i, int i2) throws IOException {
        int i3;
        int i4;
        if (i == 0 && i2 == 0) {
            return epollWait(fileDescriptor, epollEventArray, 0);
        }
        if (i == Integer.MAX_VALUE) {
            i3 = 0;
            i4 = 0;
        } else {
            i3 = i;
            i4 = i2;
        }
        int epollWait0 = epollWait0(fileDescriptor.intValue(), epollEventArray.memoryAddress(), epollEventArray.length(), fileDescriptor2.intValue(), i3, i4);
        if (epollWait0 >= 0) {
            return epollWait0;
        }
        throw Errors.newIOException("epoll_wait", epollWait0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int epollWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray, boolean z) throws IOException {
        return epollWait(fileDescriptor, epollEventArray, z ? 0 : -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int epollWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray, int i) throws IOException {
        int epollWait = epollWait(fileDescriptor.intValue(), epollEventArray.memoryAddress(), epollEventArray.length(), i);
        if (epollWait >= 0) {
            return epollWait;
        }
        throw Errors.newIOException("epoll_wait", epollWait);
    }

    public static int epollBusyWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray) throws IOException {
        int epollBusyWait0 = epollBusyWait0(fileDescriptor.intValue(), epollEventArray.memoryAddress(), epollEventArray.length());
        if (epollBusyWait0 >= 0) {
            return epollBusyWait0;
        }
        throw Errors.newIOException("epoll_wait", epollBusyWait0);
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
        return splice0 >= 0 ? splice0 : Errors.ioResult("splice", splice0);
    }

    @Deprecated
    public static int sendmmsg(int i, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3) throws IOException {
        return sendmmsg(i, Socket.isIPv6Preferred(), nativeDatagramPacketArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int sendmmsg(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3) throws IOException {
        int sendmmsg0 = sendmmsg0(i, z, nativeDatagramPacketArr, i2, i3);
        return sendmmsg0 >= 0 ? sendmmsg0 : Errors.ioResult("sendmmsg", sendmmsg0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int recvmmsg(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3) throws IOException {
        int recvmmsg0 = recvmmsg0(i, z, nativeDatagramPacketArr, i2, i3);
        return recvmmsg0 >= 0 ? recvmmsg0 : Errors.ioResult("recvmmsg", recvmmsg0);
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
