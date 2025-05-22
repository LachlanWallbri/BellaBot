package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.ChannelLogger;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import java.text.MessageFormat;
import java.util.logging.Level;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class ChannelLoggerImpl extends ChannelLogger {
    private final TimeProvider time;
    private final ChannelTracer tracer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelLoggerImpl(ChannelTracer channelTracer, TimeProvider timeProvider) {
        this.tracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer, "tracer");
        this.time = (TimeProvider) Preconditions.checkNotNull(timeProvider, "time");
    }

    @Override // io.grpc.ChannelLogger
    public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
        logOnly(this.tracer.getLogId(), channelLogLevel, str);
        if (isTraceable(channelLogLevel)) {
            trace(channelLogLevel, str);
        }
    }

    @Override // io.grpc.ChannelLogger
    public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str, Object... objArr) {
        log(channelLogLevel, (isTraceable(channelLogLevel) || ChannelTracer.logger.isLoggable(toJavaLogLevel(channelLogLevel))) ? MessageFormat.format(str, objArr) : null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void logOnly(InternalLogId internalLogId, ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            ChannelTracer.logOnly(internalLogId, javaLogLevel, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void logOnly(InternalLogId internalLogId, ChannelLogger.ChannelLogLevel channelLogLevel, String str, Object... objArr) {
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            ChannelTracer.logOnly(internalLogId, javaLogLevel, MessageFormat.format(str, objArr));
        }
    }

    private boolean isTraceable(ChannelLogger.ChannelLogLevel channelLogLevel) {
        return channelLogLevel != ChannelLogger.ChannelLogLevel.DEBUG && this.tracer.isTraceEnabled();
    }

    private void trace(ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
        if (channelLogLevel == ChannelLogger.ChannelLogLevel.DEBUG) {
            return;
        }
        this.tracer.traceOnly(new InternalChannelz.ChannelTrace.Event.Builder().setDescription(str).setSeverity(toTracerSeverity(channelLogLevel)).setTimestampNanos(this.time.currentTimeNanos()).build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.internal.ChannelLoggerImpl$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C60661 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel = new int[ChannelLogger.ChannelLogLevel.values().length];

        static {
            try {
                $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[ChannelLogger.ChannelLogLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[ChannelLogger.ChannelLogLevel.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static InternalChannelz.ChannelTrace.Event.Severity toTracerSeverity(ChannelLogger.ChannelLogLevel channelLogLevel) {
        int i = C60661.$SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[channelLogLevel.ordinal()];
        if (i == 1) {
            return InternalChannelz.ChannelTrace.Event.Severity.CT_ERROR;
        }
        if (i == 2) {
            return InternalChannelz.ChannelTrace.Event.Severity.CT_WARNING;
        }
        return InternalChannelz.ChannelTrace.Event.Severity.CT_INFO;
    }

    private static Level toJavaLogLevel(ChannelLogger.ChannelLogLevel channelLogLevel) {
        int i = C60661.$SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[channelLogLevel.ordinal()];
        if (i == 1) {
            return Level.FINE;
        }
        if (i == 2) {
            return Level.FINER;
        }
        return Level.FINEST;
    }
}
