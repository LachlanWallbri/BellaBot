package io.netty.channel.epoll;

import io.netty.channel.DefaultFileRegion;
import io.netty.channel.unix.Errors;
import io.netty.channel.unix.NativeInetAddress;
import io.netty.channel.unix.PeerCredentials;
import io.netty.channel.unix.Socket;
import io.netty.util.internal.ThrowableUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.ClosedChannelException;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
final class LinuxSocket extends Socket {
    private static final long MAX_UINT32_T = 4294967295L;
    private static final Errors.NativeIoException SENDFILE_CONNECTION_RESET_EXCEPTION = Errors.newConnectionResetException("syscall:sendfile(...)", Errors.ERRNO_EPIPE_NEGATIVE);
    private static final ClosedChannelException SENDFILE_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), Native.class, "sendfile(...)");

    private static native PeerCredentials getPeerCredentials(int i) throws IOException;

    private static native int getTcpDeferAccept(int i) throws IOException;

    private static native void getTcpInfo(int i, long[] jArr) throws IOException;

    private static native int getTcpKeepCnt(int i) throws IOException;

    private static native int getTcpKeepIdle(int i) throws IOException;

    private static native int getTcpKeepIntvl(int i) throws IOException;

    private static native int getTcpNotSentLowAt(int i) throws IOException;

    private static native int getTcpUserTimeout(int i) throws IOException;

    private static native int isIpFreeBind(int i) throws IOException;

    private static native int isIpTransparent(int i) throws IOException;

    private static native int isTcpCork(int i) throws IOException;

    private static native int isTcpFastOpenConnect(int i) throws IOException;

    private static native int isTcpQuickAck(int i) throws IOException;

    private static native long sendFile(int i, DefaultFileRegion defaultFileRegion, long j, long j2, long j3) throws IOException;

    private static native void setIpFreeBind(int i, int i2) throws IOException;

    private static native void setIpTransparent(int i, int i2) throws IOException;

    private static native void setTcpCork(int i, int i2) throws IOException;

    private static native void setTcpDeferAccept(int i, int i2) throws IOException;

    private static native void setTcpFastOpen(int i, int i2) throws IOException;

    private static native void setTcpFastOpenConnect(int i, int i2) throws IOException;

    private static native void setTcpKeepCnt(int i, int i2) throws IOException;

    private static native void setTcpKeepIdle(int i, int i2) throws IOException;

    private static native void setTcpKeepIntvl(int i, int i2) throws IOException;

    private static native void setTcpMd5Sig(int i, byte[] bArr, int i2, byte[] bArr2) throws IOException;

    private static native void setTcpNotSentLowAt(int i, int i2) throws IOException;

    private static native void setTcpQuickAck(int i, int i2) throws IOException;

    private static native void setTcpUserTimeout(int i, int i2) throws IOException;

    public LinuxSocket(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpDeferAccept(int i) throws IOException {
        setTcpDeferAccept(intValue(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpQuickAck(boolean z) throws IOException {
        setTcpQuickAck(intValue(), z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpCork(boolean z) throws IOException {
        setTcpCork(intValue(), z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpNotSentLowAt(long j) throws IOException {
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException("tcpNotSentLowAt must be a uint32_t");
        }
        setTcpNotSentLowAt(intValue(), (int) j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpFastOpen(int i) throws IOException {
        setTcpFastOpen(intValue(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpFastOpenConnect(boolean z) throws IOException {
        setTcpFastOpenConnect(intValue(), z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTcpFastOpenConnect() throws IOException {
        return isTcpFastOpenConnect(intValue()) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpKeepIdle(int i) throws IOException {
        setTcpKeepIdle(intValue(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpKeepIntvl(int i) throws IOException {
        setTcpKeepIntvl(intValue(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpKeepCnt(int i) throws IOException {
        setTcpKeepCnt(intValue(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpUserTimeout(int i) throws IOException {
        setTcpUserTimeout(intValue(), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIpFreeBind(boolean z) throws IOException {
        setIpFreeBind(intValue(), z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIpTransparent(boolean z) throws IOException {
        setIpTransparent(intValue(), z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getTcpInfo(EpollTcpInfo epollTcpInfo) throws IOException {
        getTcpInfo(intValue(), epollTcpInfo.info);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTcpMd5Sig(InetAddress inetAddress, byte[] bArr) throws IOException {
        NativeInetAddress newInstance = NativeInetAddress.newInstance(inetAddress);
        setTcpMd5Sig(intValue(), newInstance.address(), newInstance.scopeId(), bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTcpCork() throws IOException {
        return isTcpCork(intValue()) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTcpDeferAccept() throws IOException {
        return getTcpDeferAccept(intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTcpQuickAck() throws IOException {
        return isTcpQuickAck(intValue()) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getTcpNotSentLowAt() throws IOException {
        return getTcpNotSentLowAt(intValue()) & 4294967295L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTcpKeepIdle() throws IOException {
        return getTcpKeepIdle(intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTcpKeepIntvl() throws IOException {
        return getTcpKeepIntvl(intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTcpKeepCnt() throws IOException {
        return getTcpKeepCnt(intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTcpUserTimeout() throws IOException {
        return getTcpUserTimeout(intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isIpFreeBind() throws IOException {
        return isIpFreeBind(intValue()) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isIpTransparent() throws IOException {
        return isIpTransparent(intValue()) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeerCredentials getPeerCredentials() throws IOException {
        return getPeerCredentials(intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long sendFile(DefaultFileRegion defaultFileRegion, long j, long j2, long j3) throws IOException {
        defaultFileRegion.open();
        long sendFile = sendFile(intValue(), defaultFileRegion, j, j2, j3);
        return sendFile >= 0 ? sendFile : Errors.ioResult("sendfile", (int) sendFile, SENDFILE_CONNECTION_RESET_EXCEPTION, SENDFILE_CLOSED_CHANNEL_EXCEPTION);
    }

    public static LinuxSocket newSocketStream() {
        return new LinuxSocket(newSocketStream0());
    }

    public static LinuxSocket newSocketDgram() {
        return new LinuxSocket(newSocketDgram0());
    }

    public static LinuxSocket newSocketDomain() {
        return new LinuxSocket(newSocketDomain0());
    }
}
