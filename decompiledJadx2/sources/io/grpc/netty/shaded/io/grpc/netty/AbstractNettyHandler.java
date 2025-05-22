package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.ChannelLogger;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2CodecUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Settings;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractNettyHandler extends GrpcHttp2ConnectionHandler {
    private static final long BDP_MEASUREMENT_PING = 1234;
    private static final long GRACEFUL_SHUTDOWN_NO_TIMEOUT = -1;
    private boolean autoTuneFlowControlOn;
    private ChannelHandlerContext ctx;
    private final FlowControlPinger flowControlPing;
    private final int initialConnectionWindow;
    private boolean initialWindowSent;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface PingLimiter {
        boolean isPingAllowed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractNettyHandler(ChannelPromise channelPromise, Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, ChannelLogger channelLogger, boolean z, PingLimiter pingLimiter) {
        super(channelPromise, http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, channelLogger);
        this.initialWindowSent = false;
        gracefulShutdownTimeoutMillis(-1L);
        this.initialConnectionWindow = http2Settings.initialWindowSize() == null ? -1 : http2Settings.initialWindowSize().intValue();
        this.autoTuneFlowControlOn = z;
        this.flowControlPing = new FlowControlPinger(pingLimiter == null ? new AllowPingLimiter() : pingLimiter);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        super.handlerAdded(channelHandlerContext);
        sendInitialConnectionWindow();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelActive(channelHandlerContext);
        sendInitialConnectionWindow();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public final void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (Http2CodecUtil.getEmbeddedHttp2Exception(th) == null) {
            onError(channelHandlerContext, false, th);
        } else {
            super.exceptionCaught(channelHandlerContext, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ChannelHandlerContext ctx() {
        return this.ctx;
    }

    private void sendInitialConnectionWindow() throws Http2Exception {
        if (this.initialWindowSent || !this.ctx.channel().isActive()) {
            return;
        }
        Http2Stream connectionStream = connection().connectionStream();
        decoder().flowController().incrementWindowSize(connectionStream, this.initialConnectionWindow - connection().local().flowController().windowSize(connectionStream));
        this.initialWindowSent = true;
        this.ctx.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowControlPinger flowControlPing() {
        return this.flowControlPing;
    }

    void setAutoTuneFlowControl(boolean z) {
        this.autoTuneFlowControlOn = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class FlowControlPinger {
        private static final int MAX_WINDOW_SIZE = 8388608;
        private int dataSizeSincePing;
        private float lastBandwidth;
        private long lastPingTime;
        private int pingCount;
        private final PingLimiter pingLimiter;
        private int pingReturn;
        private boolean pinging;

        public int maxWindow() {
            return 8388608;
        }

        public long payload() {
            return AbstractNettyHandler.BDP_MEASUREMENT_PING;
        }

        public FlowControlPinger(PingLimiter pingLimiter) {
            Preconditions.checkNotNull(pingLimiter, "pingLimiter");
            this.pingLimiter = pingLimiter;
        }

        public void onDataRead(int i, int i2) {
            if (AbstractNettyHandler.this.autoTuneFlowControlOn) {
                if (!isPinging() && this.pingLimiter.isPingAllowed()) {
                    setPinging(true);
                    sendPing(AbstractNettyHandler.this.ctx());
                }
                incrementDataSincePing(i + i2);
            }
        }

        public void updateWindow() throws Http2Exception {
            if (AbstractNettyHandler.this.autoTuneFlowControlOn) {
                this.pingReturn++;
                long nanoTime = System.nanoTime() - this.lastPingTime;
                if (nanoTime == 0) {
                    nanoTime = 1;
                }
                long dataSincePing = (getDataSincePing() * TimeUnit.SECONDS.toNanos(1L)) / nanoTime;
                Http2LocalFlowController flowController = AbstractNettyHandler.this.decoder().flowController();
                int min = Math.min(getDataSincePing() * 2, 8388608);
                setPinging(false);
                int initialWindowSize = flowController.initialWindowSize(AbstractNettyHandler.this.connection().connectionStream());
                if (min > initialWindowSize) {
                    float f = (float) dataSincePing;
                    if (f > this.lastBandwidth) {
                        this.lastBandwidth = f;
                        flowController.incrementWindowSize(AbstractNettyHandler.this.connection().connectionStream(), min - initialWindowSize);
                        flowController.initialWindowSize(min);
                        Http2Settings http2Settings = new Http2Settings();
                        http2Settings.initialWindowSize(min);
                        AbstractNettyHandler.this.frameWriter().writeSettings(AbstractNettyHandler.this.ctx(), http2Settings, AbstractNettyHandler.this.ctx().newPromise());
                    }
                }
            }
        }

        private boolean isPinging() {
            return this.pinging;
        }

        private void setPinging(boolean z) {
            this.pinging = z;
        }

        private void sendPing(ChannelHandlerContext channelHandlerContext) {
            setDataSizeSincePing(0);
            this.lastPingTime = System.nanoTime();
            AbstractNettyHandler.this.encoder().writePing(channelHandlerContext, false, AbstractNettyHandler.BDP_MEASUREMENT_PING, channelHandlerContext.newPromise());
            this.pingCount++;
        }

        private void incrementDataSincePing(int i) {
            setDataSizeSincePing(getDataSincePing() + i);
        }

        int getPingCount() {
            return this.pingCount;
        }

        int getPingReturn() {
            return this.pingReturn;
        }

        int getDataSincePing() {
            return this.dataSizeSincePing;
        }

        private void setDataSizeSincePing(int i) {
            this.dataSizeSincePing = i;
        }

        void setDataSizeAndSincePing(int i) {
            setDataSizeSincePing(i);
            this.lastPingTime = System.nanoTime() - TimeUnit.SECONDS.toNanos(1L);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class AllowPingLimiter implements PingLimiter {
        @Override // io.grpc.netty.shaded.io.grpc.netty.AbstractNettyHandler.PingLimiter
        public boolean isPingAllowed() {
            return true;
        }

        private AllowPingLimiter() {
        }
    }
}
