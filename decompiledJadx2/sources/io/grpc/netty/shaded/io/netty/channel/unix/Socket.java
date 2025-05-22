package io.grpc.netty.shaded.io.netty.channel.unix;

import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Socket extends FileDescriptor {
    protected final boolean ipv6;
    public static final int UDS_SUN_PATH_SIZE = LimitsStaticallyReferencedJniMethods.udsSunPathSize();
    private static final AtomicBoolean INITIALIZED = new AtomicBoolean();

    private static native int accept(int i, byte[] bArr);

    private static native int bind(int i, boolean z, byte[] bArr, int i2, int i3);

    private static native int bindDomainSocket(int i, byte[] bArr);

    private static native int connect(int i, boolean z, byte[] bArr, int i2, int i3);

    private static native int connectDomainSocket(int i, byte[] bArr);

    private static native int disconnect(int i, boolean z);

    private static native int finishConnect(int i);

    private static native int getReceiveBufferSize(int i) throws IOException;

    private static native int getSendBufferSize(int i) throws IOException;

    private static native int getSoError(int i) throws IOException;

    private static native int getSoLinger(int i) throws IOException;

    private static native int getTrafficClass(int i, boolean z) throws IOException;

    private static native void initialize(boolean z);

    private static native int isBroadcast(int i) throws IOException;

    private static native boolean isIPv6(int i);

    public static native boolean isIPv6Preferred();

    private static native int isKeepAlive(int i) throws IOException;

    private static native int isReuseAddress(int i) throws IOException;

    private static native int isReusePort(int i) throws IOException;

    private static native int isTcpNoDelay(int i) throws IOException;

    private static native int listen(int i, int i2);

    private static native byte[] localAddress(int i);

    private static native int newSocketDgramFd(boolean z);

    private static native int newSocketDomainFd();

    private static native int newSocketStreamFd(boolean z);

    private static native int recvFd(int i);

    private static native DatagramSocketAddress recvFrom(int i, ByteBuffer byteBuffer, int i2, int i3) throws IOException;

    private static native DatagramSocketAddress recvFromAddress(int i, long j, int i2, int i3) throws IOException;

    private static native byte[] remoteAddress(int i);

    private static native int sendFd(int i, int i2);

    private static native int sendTo(int i, boolean z, ByteBuffer byteBuffer, int i2, int i3, byte[] bArr, int i4, int i5);

    private static native int sendToAddress(int i, boolean z, long j, int i2, int i3, byte[] bArr, int i4, int i5);

    private static native int sendToAddresses(int i, boolean z, long j, int i2, byte[] bArr, int i3, int i4);

    private static native void setBroadcast(int i, int i2) throws IOException;

    private static native void setKeepAlive(int i, int i2) throws IOException;

    private static native void setReceiveBufferSize(int i, int i2) throws IOException;

    private static native void setReuseAddress(int i, int i2) throws IOException;

    private static native void setReusePort(int i, int i2) throws IOException;

    private static native void setSendBufferSize(int i, int i2) throws IOException;

    private static native void setSoLinger(int i, int i2) throws IOException;

    private static native void setTcpNoDelay(int i, int i2) throws IOException;

    private static native void setTrafficClass(int i, boolean z, int i2) throws IOException;

    private static native int shutdown(int i, boolean z, boolean z2);

    public Socket(int i) {
        super(i);
        this.ipv6 = isIPv6(i);
    }

    private boolean useIpv6(InetAddress inetAddress) {
        return this.ipv6 || (inetAddress instanceof Inet6Address);
    }

    public final void shutdown() throws IOException {
        shutdown(true, true);
    }

    public final void shutdown(boolean z, boolean z2) throws IOException {
        int i;
        int inputShutdown;
        do {
            i = this.state;
            if (isClosed(i)) {
                throw new ClosedChannelException();
            }
            inputShutdown = (!z || isInputShutdown(i)) ? i : inputShutdown(i);
            if (z2 && !isOutputShutdown(inputShutdown)) {
                inputShutdown = outputShutdown(inputShutdown);
            }
            if (inputShutdown == i) {
                return;
            }
        } while (!casState(i, inputShutdown));
        int shutdown = shutdown(this.f8343fd, z, z2);
        if (shutdown < 0) {
            Errors.ioResult("shutdown", shutdown);
        }
    }

    public final boolean isShutdown() {
        int i = this.state;
        return isInputShutdown(i) && isOutputShutdown(i);
    }

    public final boolean isInputShutdown() {
        return isInputShutdown(this.state);
    }

    public final boolean isOutputShutdown() {
        return isOutputShutdown(this.state);
    }

    public final int sendTo(ByteBuffer byteBuffer, int i, int i2, InetAddress inetAddress, int i3) throws IOException {
        int i4;
        byte[] ipv4MappedIpv6Address;
        if (inetAddress instanceof Inet6Address) {
            ipv4MappedIpv6Address = inetAddress.getAddress();
            i4 = ((Inet6Address) inetAddress).getScopeId();
        } else {
            i4 = 0;
            ipv4MappedIpv6Address = NativeInetAddress.ipv4MappedIpv6Address(inetAddress.getAddress());
        }
        int sendTo = sendTo(this.f8343fd, useIpv6(inetAddress), byteBuffer, i, i2, ipv4MappedIpv6Address, i4, i3);
        if (sendTo >= 0) {
            return sendTo;
        }
        if (sendTo == Errors.ERROR_ECONNREFUSED_NEGATIVE) {
            throw new PortUnreachableException("sendTo failed");
        }
        return Errors.ioResult("sendTo", sendTo);
    }

    public final int sendToAddress(long j, int i, int i2, InetAddress inetAddress, int i3) throws IOException {
        int i4;
        byte[] ipv4MappedIpv6Address;
        if (inetAddress instanceof Inet6Address) {
            ipv4MappedIpv6Address = inetAddress.getAddress();
            i4 = ((Inet6Address) inetAddress).getScopeId();
        } else {
            i4 = 0;
            ipv4MappedIpv6Address = NativeInetAddress.ipv4MappedIpv6Address(inetAddress.getAddress());
        }
        int sendToAddress = sendToAddress(this.f8343fd, useIpv6(inetAddress), j, i, i2, ipv4MappedIpv6Address, i4, i3);
        if (sendToAddress >= 0) {
            return sendToAddress;
        }
        if (sendToAddress == Errors.ERROR_ECONNREFUSED_NEGATIVE) {
            throw new PortUnreachableException("sendToAddress failed");
        }
        return Errors.ioResult("sendToAddress", sendToAddress);
    }

    public final int sendToAddresses(long j, int i, InetAddress inetAddress, int i2) throws IOException {
        int i3;
        byte[] ipv4MappedIpv6Address;
        if (inetAddress instanceof Inet6Address) {
            ipv4MappedIpv6Address = inetAddress.getAddress();
            i3 = ((Inet6Address) inetAddress).getScopeId();
        } else {
            i3 = 0;
            ipv4MappedIpv6Address = NativeInetAddress.ipv4MappedIpv6Address(inetAddress.getAddress());
        }
        int sendToAddresses = sendToAddresses(this.f8343fd, useIpv6(inetAddress), j, i, ipv4MappedIpv6Address, i3, i2);
        if (sendToAddresses >= 0) {
            return sendToAddresses;
        }
        if (sendToAddresses == Errors.ERROR_ECONNREFUSED_NEGATIVE) {
            throw new PortUnreachableException("sendToAddresses failed");
        }
        return Errors.ioResult("sendToAddresses", sendToAddresses);
    }

    public final DatagramSocketAddress recvFrom(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        return recvFrom(this.f8343fd, byteBuffer, i, i2);
    }

    public final DatagramSocketAddress recvFromAddress(long j, int i, int i2) throws IOException {
        return recvFromAddress(this.f8343fd, j, i, i2);
    }

    public final int recvFd() throws IOException {
        int recvFd = recvFd(this.f8343fd);
        if (recvFd > 0) {
            return recvFd;
        }
        if (recvFd == 0) {
            return -1;
        }
        if (recvFd == Errors.ERRNO_EAGAIN_NEGATIVE || recvFd == Errors.ERRNO_EWOULDBLOCK_NEGATIVE) {
            return 0;
        }
        throw Errors.newIOException("recvFd", recvFd);
    }

    public final int sendFd(int i) throws IOException {
        int sendFd = sendFd(this.f8343fd, i);
        if (sendFd >= 0) {
            return sendFd;
        }
        if (sendFd == Errors.ERRNO_EAGAIN_NEGATIVE || sendFd == Errors.ERRNO_EWOULDBLOCK_NEGATIVE) {
            return -1;
        }
        throw Errors.newIOException("sendFd", sendFd);
    }

    public final boolean connect(SocketAddress socketAddress) throws IOException {
        int connectDomainSocket;
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            InetAddress address = inetSocketAddress.getAddress();
            NativeInetAddress newInstance = NativeInetAddress.newInstance(address);
            connectDomainSocket = connect(this.f8343fd, useIpv6(address), newInstance.address, newInstance.scopeId, inetSocketAddress.getPort());
        } else if (socketAddress instanceof DomainSocketAddress) {
            connectDomainSocket = connectDomainSocket(this.f8343fd, ((DomainSocketAddress) socketAddress).path().getBytes(CharsetUtil.UTF_8));
        } else {
            throw new Error("Unexpected SocketAddress implementation " + socketAddress);
        }
        if (connectDomainSocket >= 0) {
            return true;
        }
        if (connectDomainSocket == Errors.ERRNO_EINPROGRESS_NEGATIVE) {
            return false;
        }
        Errors.throwConnectException(MqttServiceConstants.CONNECT_ACTION, connectDomainSocket);
        return true;
    }

    public final boolean finishConnect() throws IOException {
        int finishConnect = finishConnect(this.f8343fd);
        if (finishConnect >= 0) {
            return true;
        }
        if (finishConnect == Errors.ERRNO_EINPROGRESS_NEGATIVE) {
            return false;
        }
        Errors.throwConnectException("finishConnect", finishConnect);
        return true;
    }

    public final void disconnect() throws IOException {
        int disconnect = disconnect(this.f8343fd, this.ipv6);
        if (disconnect < 0) {
            Errors.throwConnectException(MqttServiceConstants.DISCONNECT_ACTION, disconnect);
        }
    }

    public final void bind(SocketAddress socketAddress) throws IOException {
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            InetAddress address = inetSocketAddress.getAddress();
            NativeInetAddress newInstance = NativeInetAddress.newInstance(address);
            int bind = bind(this.f8343fd, useIpv6(address), newInstance.address, newInstance.scopeId, inetSocketAddress.getPort());
            if (bind < 0) {
                throw Errors.newIOException("bind", bind);
            }
            return;
        }
        if (socketAddress instanceof DomainSocketAddress) {
            int bindDomainSocket = bindDomainSocket(this.f8343fd, ((DomainSocketAddress) socketAddress).path().getBytes(CharsetUtil.UTF_8));
            if (bindDomainSocket < 0) {
                throw Errors.newIOException("bind", bindDomainSocket);
            }
        } else {
            throw new Error("Unexpected SocketAddress implementation " + socketAddress);
        }
    }

    public final void listen(int i) throws IOException {
        int listen = listen(this.f8343fd, i);
        if (listen < 0) {
            throw Errors.newIOException("listen", listen);
        }
    }

    public final int accept(byte[] bArr) throws IOException {
        int accept = accept(this.f8343fd, bArr);
        if (accept >= 0) {
            return accept;
        }
        if (accept == Errors.ERRNO_EAGAIN_NEGATIVE || accept == Errors.ERRNO_EWOULDBLOCK_NEGATIVE) {
            return -1;
        }
        throw Errors.newIOException("accept", accept);
    }

    public final InetSocketAddress remoteAddress() {
        byte[] remoteAddress = remoteAddress(this.f8343fd);
        if (remoteAddress == null) {
            return null;
        }
        return NativeInetAddress.address(remoteAddress, 0, remoteAddress.length);
    }

    public final InetSocketAddress localAddress() {
        byte[] localAddress = localAddress(this.f8343fd);
        if (localAddress == null) {
            return null;
        }
        return NativeInetAddress.address(localAddress, 0, localAddress.length);
    }

    public final int getReceiveBufferSize() throws IOException {
        return getReceiveBufferSize(this.f8343fd);
    }

    public final int getSendBufferSize() throws IOException {
        return getSendBufferSize(this.f8343fd);
    }

    public final boolean isKeepAlive() throws IOException {
        return isKeepAlive(this.f8343fd) != 0;
    }

    public final boolean isTcpNoDelay() throws IOException {
        return isTcpNoDelay(this.f8343fd) != 0;
    }

    public final boolean isReuseAddress() throws IOException {
        return isReuseAddress(this.f8343fd) != 0;
    }

    public final boolean isReusePort() throws IOException {
        return isReusePort(this.f8343fd) != 0;
    }

    public final boolean isBroadcast() throws IOException {
        return isBroadcast(this.f8343fd) != 0;
    }

    public final int getSoLinger() throws IOException {
        return getSoLinger(this.f8343fd);
    }

    public final int getSoError() throws IOException {
        return getSoError(this.f8343fd);
    }

    public final int getTrafficClass() throws IOException {
        return getTrafficClass(this.f8343fd, this.ipv6);
    }

    public final void setKeepAlive(boolean z) throws IOException {
        setKeepAlive(this.f8343fd, z ? 1 : 0);
    }

    public final void setReceiveBufferSize(int i) throws IOException {
        setReceiveBufferSize(this.f8343fd, i);
    }

    public final void setSendBufferSize(int i) throws IOException {
        setSendBufferSize(this.f8343fd, i);
    }

    public final void setTcpNoDelay(boolean z) throws IOException {
        setTcpNoDelay(this.f8343fd, z ? 1 : 0);
    }

    public final void setSoLinger(int i) throws IOException {
        setSoLinger(this.f8343fd, i);
    }

    public final void setReuseAddress(boolean z) throws IOException {
        setReuseAddress(this.f8343fd, z ? 1 : 0);
    }

    public final void setReusePort(boolean z) throws IOException {
        setReusePort(this.f8343fd, z ? 1 : 0);
    }

    public final void setBroadcast(boolean z) throws IOException {
        setBroadcast(this.f8343fd, z ? 1 : 0);
    }

    public final void setTrafficClass(int i) throws IOException {
        setTrafficClass(this.f8343fd, this.ipv6, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.unix.FileDescriptor
    public String toString() {
        return "Socket{fd=" + this.f8343fd + '}';
    }

    public static Socket newSocketStream() {
        return new Socket(newSocketStream0());
    }

    public static Socket newSocketDgram() {
        return new Socket(newSocketDgram0());
    }

    public static Socket newSocketDomain() {
        return new Socket(newSocketDomain0());
    }

    public static void initialize() {
        if (INITIALIZED.compareAndSet(false, true)) {
            initialize(NetUtil.isIpV4StackPreferred());
        }
    }

    protected static int newSocketStream0() {
        return newSocketStream0(isIPv6Preferred());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int newSocketStream0(boolean z) {
        int newSocketStreamFd = newSocketStreamFd(z);
        if (newSocketStreamFd >= 0) {
            return newSocketStreamFd;
        }
        throw new ChannelException(Errors.newIOException("newSocketStream", newSocketStreamFd));
    }

    protected static int newSocketDgram0() {
        return newSocketDgram0(isIPv6Preferred());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int newSocketDgram0(boolean z) {
        int newSocketDgramFd = newSocketDgramFd(z);
        if (newSocketDgramFd >= 0) {
            return newSocketDgramFd;
        }
        throw new ChannelException(Errors.newIOException("newSocketDgram", newSocketDgramFd));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int newSocketDomain0() {
        int newSocketDomainFd = newSocketDomainFd();
        if (newSocketDomainFd >= 0) {
            return newSocketDomainFd;
        }
        throw new ChannelException(Errors.newIOException("newSocketDomain", newSocketDomainFd));
    }
}
