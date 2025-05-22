package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.StreamTracer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class StatsTraceContext {
    public static final StatsTraceContext NOOP = new StatsTraceContext(new StreamTracer[0]);
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final StreamTracer[] tracers;

    public static StatsTraceContext newClientContext(CallOptions callOptions, Attributes attributes, Metadata metadata) {
        List<ClientStreamTracer.Factory> streamTracerFactories = callOptions.getStreamTracerFactories();
        if (streamTracerFactories.isEmpty()) {
            return NOOP;
        }
        ClientStreamTracer.StreamInfo build = ClientStreamTracer.StreamInfo.newBuilder().setTransportAttrs(attributes).setCallOptions(callOptions).build();
        StreamTracer[] streamTracerArr = new StreamTracer[streamTracerFactories.size()];
        for (int i = 0; i < streamTracerArr.length; i++) {
            streamTracerArr[i] = streamTracerFactories.get(i).newClientStreamTracer(build, metadata);
        }
        return new StatsTraceContext(streamTracerArr);
    }

    public static StatsTraceContext newServerContext(List<? extends ServerStreamTracer.Factory> list, String str, Metadata metadata) {
        if (list.isEmpty()) {
            return NOOP;
        }
        StreamTracer[] streamTracerArr = new StreamTracer[list.size()];
        for (int i = 0; i < streamTracerArr.length; i++) {
            streamTracerArr[i] = list.get(i).newServerStreamTracer(str, metadata);
        }
        return new StatsTraceContext(streamTracerArr);
    }

    StatsTraceContext(StreamTracer[] streamTracerArr) {
        this.tracers = streamTracerArr;
    }

    public List<StreamTracer> getTracersForTest() {
        return new ArrayList(Arrays.asList(this.tracers));
    }

    public void clientOutboundHeaders() {
        for (StreamTracer streamTracer : this.tracers) {
            ((ClientStreamTracer) streamTracer).outboundHeaders();
        }
    }

    public void clientInboundHeaders() {
        for (StreamTracer streamTracer : this.tracers) {
            ((ClientStreamTracer) streamTracer).inboundHeaders();
        }
    }

    public void clientInboundTrailers(Metadata metadata) {
        for (StreamTracer streamTracer : this.tracers) {
            ((ClientStreamTracer) streamTracer).inboundTrailers(metadata);
        }
    }

    public <ReqT, RespT> Context serverFilterContext(Context context) {
        Context context2 = (Context) Preconditions.checkNotNull(context, "context");
        for (StreamTracer streamTracer : this.tracers) {
            context2 = ((ServerStreamTracer) streamTracer).filterContext(context2);
            Preconditions.checkNotNull(context2, "%s returns null context", streamTracer);
        }
        return context2;
    }

    public void serverCallStarted(ServerStreamTracer.ServerCallInfo<?, ?> serverCallInfo) {
        for (StreamTracer streamTracer : this.tracers) {
            ((ServerStreamTracer) streamTracer).serverCallStarted(serverCallInfo);
        }
    }

    public void streamClosed(Status status) {
        if (this.closed.compareAndSet(false, true)) {
            for (StreamTracer streamTracer : this.tracers) {
                streamTracer.streamClosed(status);
            }
        }
    }

    public void outboundMessage(int i) {
        for (StreamTracer streamTracer : this.tracers) {
            streamTracer.outboundMessage(i);
        }
    }

    public void inboundMessage(int i) {
        for (StreamTracer streamTracer : this.tracers) {
            streamTracer.inboundMessage(i);
        }
    }

    public void outboundMessageSent(int i, long j, long j2) {
        for (StreamTracer streamTracer : this.tracers) {
            streamTracer.outboundMessageSent(i, j, j2);
        }
    }

    public void inboundMessageRead(int i, long j, long j2) {
        for (StreamTracer streamTracer : this.tracers) {
            streamTracer.inboundMessageRead(i, j, j2);
        }
    }

    public void outboundUncompressedSize(long j) {
        for (StreamTracer streamTracer : this.tracers) {
            streamTracer.outboundUncompressedSize(j);
        }
    }

    public void outboundWireSize(long j) {
        for (StreamTracer streamTracer : this.tracers) {
            streamTracer.outboundWireSize(j);
        }
    }

    public void inboundUncompressedSize(long j) {
        for (StreamTracer streamTracer : this.tracers) {
            streamTracer.inboundUncompressedSize(j);
        }
    }

    public void inboundWireSize(long j) {
        for (StreamTracer streamTracer : this.tracers) {
            streamTracer.inboundWireSize(j);
        }
    }
}
