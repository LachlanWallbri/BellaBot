package io.netty.handler.ssl;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.ReflectionUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Field;
import javax.crypto.SecretKey;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
public abstract class SslMasterKeyHandler extends ChannelInboundHandlerAdapter {
    private static final Class<?> SSL_SESSIONIMPL_CLASS;
    private static final Field SSL_SESSIONIMPL_MASTER_SECRET_FIELD;
    public static final String SYSTEM_PROP_KEY = "io.netty.ssl.masterKeyHandler";
    private static final Throwable UNAVAILABILITY_CAUSE;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SslMasterKeyHandler.class);

    protected abstract void accept(SecretKey secretKey, SSLSession sSLSession);

    static {
        Throwable th;
        Class<?> cls;
        Field field = null;
        try {
            cls = Class.forName("sun.security.ssl.SSLSessionImpl");
        } catch (Throwable th2) {
            th = th2;
            cls = null;
        }
        try {
            field = cls.getDeclaredField("masterSecret");
            th = ReflectionUtil.trySetAccessible(field, true);
        } catch (Throwable th3) {
            th = th3;
            logger.debug("sun.security.ssl.SSLSessionImpl is unavailable.", th);
            UNAVAILABILITY_CAUSE = th;
            SSL_SESSIONIMPL_CLASS = cls;
            SSL_SESSIONIMPL_MASTER_SECRET_FIELD = field;
        }
        UNAVAILABILITY_CAUSE = th;
        SSL_SESSIONIMPL_CLASS = cls;
        SSL_SESSIONIMPL_MASTER_SECRET_FIELD = field;
    }

    protected SslMasterKeyHandler() {
    }

    public static void ensureSunSslEngineAvailability() {
        Throwable th = UNAVAILABILITY_CAUSE;
        if (th != null) {
            throw new IllegalStateException("Failed to find SSLSessionImpl on classpath", th);
        }
    }

    public static Throwable sunSslEngineUnavailabilityCause() {
        return UNAVAILABILITY_CAUSE;
    }

    public static boolean isSunSslEngineAvailable() {
        return UNAVAILABILITY_CAUSE == null;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public final void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) {
        if (obj == SslHandshakeCompletionEvent.SUCCESS && SystemPropertyUtil.getBoolean(SYSTEM_PROP_KEY, false)) {
            SSLEngine engine = ((SslHandler) channelHandlerContext.pipeline().get(SslHandler.class)).engine();
            SSLSession session = engine.getSession();
            if (isSunSslEngineAvailable() && session.getClass().equals(SSL_SESSIONIMPL_CLASS)) {
                try {
                    accept((SecretKey) SSL_SESSIONIMPL_MASTER_SECRET_FIELD.get(session), session);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Failed to access the field 'masterSecret' via reflection.", e);
                }
            } else if (OpenSsl.isAvailable() && (engine instanceof ReferenceCountedOpenSslEngine)) {
                accept(((ReferenceCountedOpenSslEngine) engine).masterKey(), session);
            }
        }
        channelHandlerContext.fireUserEventTriggered(obj);
    }

    public static SslMasterKeyHandler newWireSharkSslMasterKeyHandler() {
        return new WiresharkSslMasterKeyHandler();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private static final class WiresharkSslMasterKeyHandler extends SslMasterKeyHandler {
        private static final InternalLogger wireshark_logger = InternalLoggerFactory.getInstance("io.netty.wireshark");
        private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

        private WiresharkSslMasterKeyHandler() {
        }

        @Override // io.netty.handler.ssl.SslMasterKeyHandler
        protected void accept(SecretKey secretKey, SSLSession sSLSession) {
            if (secretKey.getEncoded().length != 48) {
                throw new IllegalArgumentException("An invalid length master key was provided.");
            }
            wireshark_logger.warn("RSA Session-ID:{} Master-Key:{}", ByteBufUtil.hexDump(sSLSession.getId()).toLowerCase(), ByteBufUtil.hexDump(secretKey.getEncoded()).toLowerCase());
        }
    }
}
