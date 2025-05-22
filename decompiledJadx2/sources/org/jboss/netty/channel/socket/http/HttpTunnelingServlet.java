package org.jboss.netty.channel.socket.http;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.SocketAddress;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.local.DefaultLocalClientChannelFactory;
import org.jboss.netty.channel.local.LocalAddress;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class HttpTunnelingServlet extends HttpServlet {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String ENDPOINT = "endpoint";
    static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) HttpTunnelingServlet.class);
    private static final long serialVersionUID = 4259910275899756070L;
    private volatile ChannelFactory channelFactory;
    private volatile SocketAddress remoteAddress;

    public void init() throws ServletException {
        String initParameter = getServletConfig().getInitParameter(ENDPOINT);
        if (initParameter == null) {
            throw new ServletException("init-param 'endpoint' must be specified.");
        }
        try {
            this.remoteAddress = parseEndpoint(initParameter.trim());
            try {
                this.channelFactory = createChannelFactory(this.remoteAddress);
            } catch (Exception e) {
                throw new ServletException("Failed to create a channel factory.", e);
            } catch (ServletException e2) {
                throw e2;
            }
        } catch (Exception e3) {
            throw new ServletException("Failed to parse an endpoint.", e3);
        } catch (ServletException e4) {
            throw e4;
        }
    }

    protected SocketAddress parseEndpoint(String str) throws Exception {
        if (str.startsWith("local:")) {
            return new LocalAddress(str.substring(6).trim());
        }
        throw new ServletException("Invalid or unknown endpoint: " + str);
    }

    protected ChannelFactory createChannelFactory(SocketAddress socketAddress) throws Exception {
        if (socketAddress instanceof LocalAddress) {
            return new DefaultLocalClientChannelFactory();
        }
        throw new ServletException("Unsupported remote address type: " + socketAddress.getClass().getName());
    }

    public void destroy() {
        try {
            destroyChannelFactory(this.channelFactory);
        } catch (Exception e) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to destroy a channel factory.", e);
            }
        }
    }

    protected void destroyChannelFactory(ChannelFactory channelFactory) throws Exception {
        channelFactory.releaseExternalResources();
    }

    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (!"POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            if (logger.isWarnEnabled()) {
                logger.warn("Unallowed method: " + httpServletRequest.getMethod());
            }
            httpServletResponse.sendError(405);
            return;
        }
        ChannelPipeline pipeline = Channels.pipeline();
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        pipeline.addLast("handler", new OutboundConnectionHandler(outputStream));
        Channel newChannel = this.channelFactory.newChannel(pipeline);
        ChannelFuture awaitUninterruptibly = newChannel.connect(this.remoteAddress).awaitUninterruptibly();
        if (!awaitUninterruptibly.isSuccess()) {
            if (logger.isWarnEnabled()) {
                Throwable cause = awaitUninterruptibly.getCause();
                logger.warn("Endpoint unavailable: " + cause.getMessage(), cause);
            }
            httpServletResponse.sendError(503);
            return;
        }
        ChannelFuture channelFuture = null;
        try {
            httpServletResponse.setStatus(200);
            httpServletResponse.setHeader("Content-Type", "application/octet-stream");
            httpServletResponse.setHeader("Content-Transfer-Encoding", "binary");
            outputStream.flush();
            PushbackInputStream pushbackInputStream = new PushbackInputStream(httpServletRequest.getInputStream());
            while (newChannel.isConnected()) {
                try {
                    ChannelBuffer read = read(pushbackInputStream);
                    if (read == null) {
                        break;
                    } else {
                        channelFuture = newChannel.write(read);
                    }
                } catch (EOFException unused) {
                }
            }
        } finally {
            if (channelFuture == null) {
                newChannel.close();
            } else {
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    private static ChannelBuffer read(PushbackInputStream pushbackInputStream) throws IOException {
        int read;
        byte[] bArr;
        int read2;
        int available = pushbackInputStream.available();
        if (available > 0) {
            bArr = new byte[available];
            read2 = pushbackInputStream.read(bArr);
        } else {
            if (available != 0 || (read = pushbackInputStream.read()) < 0 || pushbackInputStream.available() < 0) {
                return null;
            }
            pushbackInputStream.unread(read);
            bArr = new byte[pushbackInputStream.available()];
            read2 = pushbackInputStream.read(bArr);
        }
        if (read2 == bArr.length) {
            return ChannelBuffers.wrappedBuffer(bArr);
        }
        return ChannelBuffers.wrappedBuffer(bArr, 0, read2);
    }

    /* loaded from: classes7.dex */
    private static final class OutboundConnectionHandler extends SimpleChannelUpstreamHandler {
        private final ServletOutputStream out;

        public OutboundConnectionHandler(ServletOutputStream servletOutputStream) {
            this.out = servletOutputStream;
        }

        @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
            ChannelBuffer channelBuffer = (ChannelBuffer) messageEvent.getMessage();
            synchronized (this) {
                channelBuffer.readBytes((OutputStream) this.out, channelBuffer.readableBytes());
                this.out.flush();
            }
        }

        @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, ExceptionEvent exceptionEvent) throws Exception {
            if (HttpTunnelingServlet.logger.isWarnEnabled()) {
                HttpTunnelingServlet.logger.warn("Unexpected exception while HTTP tunneling", exceptionEvent.getCause());
            }
            exceptionEvent.getChannel().close();
        }
    }
}
