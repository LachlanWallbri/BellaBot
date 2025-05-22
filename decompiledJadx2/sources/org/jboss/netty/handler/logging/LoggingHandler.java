package org.jboss.netty.handler.logging;

import org.apache.commons.io.FilenameUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.logging.InternalLogLevel;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.mozilla.javascript.typedarrays.Conversions;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class LoggingHandler implements ChannelUpstreamHandler, ChannelDownstreamHandler {
    private final boolean hexDump;
    private final InternalLogLevel level;
    private final InternalLogger logger;
    private static final InternalLogLevel DEFAULT_LEVEL = InternalLogLevel.DEBUG;
    private static final String NEWLINE = String.format("%n", new Object[0]);
    private static final String[] BYTE2HEX = new String[256];
    private static final String[] HEXPADDING = new String[16];
    private static final String[] BYTEPADDING = new String[16];
    private static final char[] BYTE2CHAR = new char[256];

    static {
        int i = 0;
        int i2 = 0;
        while (i2 < 10) {
            StringBuilder sb = new StringBuilder(3);
            sb.append(" 0");
            sb.append(i2);
            BYTE2HEX[i2] = sb.toString();
            i2++;
        }
        while (i2 < 16) {
            StringBuilder sb2 = new StringBuilder(3);
            sb2.append(" 0");
            sb2.append((char) ((i2 + 97) - 10));
            BYTE2HEX[i2] = sb2.toString();
            i2++;
        }
        while (i2 < BYTE2HEX.length) {
            StringBuilder sb3 = new StringBuilder(3);
            sb3.append(' ');
            sb3.append(Integer.toHexString(i2));
            BYTE2HEX[i2] = sb3.toString();
            i2++;
        }
        int i3 = 0;
        while (true) {
            String[] strArr = HEXPADDING;
            if (i3 >= strArr.length) {
                break;
            }
            int length = strArr.length - i3;
            StringBuilder sb4 = new StringBuilder(length * 3);
            for (int i4 = 0; i4 < length; i4++) {
                sb4.append("   ");
            }
            HEXPADDING[i3] = sb4.toString();
            i3++;
        }
        int i5 = 0;
        while (true) {
            String[] strArr2 = BYTEPADDING;
            if (i5 >= strArr2.length) {
                break;
            }
            int length2 = strArr2.length - i5;
            StringBuilder sb5 = new StringBuilder(length2);
            for (int i6 = 0; i6 < length2; i6++) {
                sb5.append(' ');
            }
            BYTEPADDING[i5] = sb5.toString();
            i5++;
        }
        while (true) {
            char[] cArr = BYTE2CHAR;
            if (i >= cArr.length) {
                return;
            }
            if (i <= 31 || i >= 127) {
                BYTE2CHAR[i] = FilenameUtils.EXTENSION_SEPARATOR;
            } else {
                cArr[i] = (char) i;
            }
            i++;
        }
    }

    public LoggingHandler() {
        this(true);
    }

    public LoggingHandler(InternalLogLevel internalLogLevel) {
        this(internalLogLevel, true);
    }

    public LoggingHandler(boolean z) {
        this(DEFAULT_LEVEL, z);
    }

    public LoggingHandler(InternalLogLevel internalLogLevel, boolean z) {
        if (internalLogLevel == null) {
            throw new NullPointerException("level");
        }
        this.logger = InternalLoggerFactory.getInstance(getClass());
        this.level = internalLogLevel;
        this.hexDump = z;
    }

    public LoggingHandler(Class<?> cls) {
        this(cls, true);
    }

    public LoggingHandler(Class<?> cls, boolean z) {
        this(cls, DEFAULT_LEVEL, z);
    }

    public LoggingHandler(Class<?> cls, InternalLogLevel internalLogLevel) {
        this(cls, internalLogLevel, true);
    }

    public LoggingHandler(Class<?> cls, InternalLogLevel internalLogLevel, boolean z) {
        if (cls == null) {
            throw new NullPointerException("clazz");
        }
        if (internalLogLevel == null) {
            throw new NullPointerException("level");
        }
        this.logger = InternalLoggerFactory.getInstance(cls);
        this.level = internalLogLevel;
        this.hexDump = z;
    }

    public LoggingHandler(String str) {
        this(str, true);
    }

    public LoggingHandler(String str, boolean z) {
        this(str, DEFAULT_LEVEL, z);
    }

    public LoggingHandler(String str, InternalLogLevel internalLogLevel, boolean z) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        if (internalLogLevel == null) {
            throw new NullPointerException("level");
        }
        this.logger = InternalLoggerFactory.getInstance(str);
        this.level = internalLogLevel;
        this.hexDump = z;
    }

    public InternalLogger getLogger() {
        return this.logger;
    }

    public InternalLogLevel getLevel() {
        return this.level;
    }

    public void log(ChannelEvent channelEvent) {
        if (getLogger().isEnabled(this.level)) {
            String obj = channelEvent.toString();
            if (this.hexDump && (channelEvent instanceof MessageEvent)) {
                MessageEvent messageEvent = (MessageEvent) channelEvent;
                if (messageEvent.getMessage() instanceof ChannelBuffer) {
                    obj = obj + formatBuffer((ChannelBuffer) messageEvent.getMessage());
                }
            }
            if (channelEvent instanceof ExceptionEvent) {
                getLogger().log(this.level, obj, ((ExceptionEvent) channelEvent).getCause());
            } else {
                getLogger().log(this.level, obj);
            }
        }
    }

    private static String formatBuffer(ChannelBuffer channelBuffer) {
        int readableBytes = channelBuffer.readableBytes();
        StringBuilder sb = new StringBuilder(((readableBytes / 16) + (readableBytes % 15 == 0 ? 0 : 1) + 4) * 80);
        sb.append(NEWLINE + "         +-------------------------------------------------+" + NEWLINE + "         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |" + NEWLINE + "+--------+-------------------------------------------------+----------------+");
        int readerIndex = channelBuffer.readerIndex();
        int writerIndex = channelBuffer.writerIndex();
        int i = readerIndex;
        while (i < writerIndex) {
            int i2 = i - readerIndex;
            int i3 = i2 & 15;
            if (i3 == 0) {
                sb.append(NEWLINE);
                sb.append(Long.toHexString((i2 & 4294967295L) | Conversions.THIRTYTWO_BIT));
                sb.setCharAt(sb.length() - 9, '|');
                sb.append('|');
            }
            sb.append(BYTE2HEX[channelBuffer.getUnsignedByte(i)]);
            if (i3 == 15) {
                sb.append(" |");
                for (int i4 = i - 15; i4 <= i; i4++) {
                    sb.append(BYTE2CHAR[channelBuffer.getUnsignedByte(i4)]);
                }
                sb.append('|');
            }
            i++;
        }
        if (((i - readerIndex) & 15) != 0) {
            int i5 = readableBytes & 15;
            sb.append(HEXPADDING[i5]);
            sb.append(" |");
            for (int i6 = i - i5; i6 < i; i6++) {
                sb.append(BYTE2CHAR[channelBuffer.getUnsignedByte(i6)]);
            }
            sb.append(BYTEPADDING[i5]);
            sb.append('|');
        }
        sb.append(NEWLINE + "+--------+-------------------------------------------------+----------------+");
        return sb.toString();
    }

    @Override // org.jboss.netty.channel.ChannelUpstreamHandler
    public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        log(channelEvent);
        channelHandlerContext.sendUpstream(channelEvent);
    }

    @Override // org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        log(channelEvent);
        channelHandlerContext.sendDownstream(channelEvent);
    }
}
