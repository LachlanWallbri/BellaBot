package io.grpc.netty.shaded.io.netty.handler.proxy;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.DefaultSocks4CommandRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4ClientDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4ClientEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4CommandType;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Socks4ProxyHandler extends ProxyHandler {
    private static final String AUTH_USERNAME = "username";
    private static final String PROTOCOL = "socks4";
    private String decoderName;
    private String encoderName;
    private final String username;

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    public String protocol() {
        return PROTOCOL;
    }

    public Socks4ProxyHandler(SocketAddress socketAddress) {
        this(socketAddress, null);
    }

    public Socks4ProxyHandler(SocketAddress socketAddress, String str) {
        super(socketAddress);
        if (str != null && str.isEmpty()) {
            str = null;
        }
        this.username = str;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    public String authScheme() {
        return this.username != null ? "username" : "none";
    }

    public String username() {
        return this.username;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void addCodec(ChannelHandlerContext channelHandlerContext) throws Exception {
        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        String name = channelHandlerContext.name();
        Socks4ClientDecoder socks4ClientDecoder = new Socks4ClientDecoder();
        pipeline.addBefore(name, null, socks4ClientDecoder);
        this.decoderName = pipeline.context(socks4ClientDecoder).name();
        this.encoderName = this.decoderName + ".encoder";
        pipeline.addBefore(name, this.encoderName, Socks4ClientEncoder.INSTANCE);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void removeEncoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.pipeline().remove(this.encoderName);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void removeDecoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.pipeline().remove(this.decoderName);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected Object newInitialMessage(ChannelHandlerContext channelHandlerContext) throws Exception {
        String hostAddress;
        InetSocketAddress inetSocketAddress = (InetSocketAddress) destinationAddress();
        if (inetSocketAddress.isUnresolved()) {
            hostAddress = inetSocketAddress.getHostString();
        } else {
            hostAddress = inetSocketAddress.getAddress().getHostAddress();
        }
        Socks4CommandType socks4CommandType = Socks4CommandType.CONNECT;
        int port = inetSocketAddress.getPort();
        String str = this.username;
        if (str == null) {
            str = "";
        }
        return new DefaultSocks4CommandRequest(socks4CommandType, hostAddress, port, str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected boolean handleResponse(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        Socks4CommandStatus status = ((Socks4CommandResponse) obj).status();
        if (status == Socks4CommandStatus.SUCCESS) {
            return true;
        }
        throw new ProxyConnectException(exceptionMessage("status: " + status));
    }
}
