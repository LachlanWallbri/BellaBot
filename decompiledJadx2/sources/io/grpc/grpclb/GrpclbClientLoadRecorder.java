package io.grpc.grpclb;

import com.google.common.base.Preconditions;
import com.google.protobuf.util.Timestamps;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.TimeProvider;
import io.grpc.lb.p072v1.ClientStats;
import io.grpc.lb.p072v1.ClientStatsPerToken;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class GrpclbClientLoadRecorder extends ClientStreamTracer.Factory {
    private Map<String, LongHolder> callsDroppedPerToken = new HashMap(1);
    private volatile long callsFailedToSend;
    private volatile long callsFinished;
    private volatile long callsFinishedKnownReceived;
    private volatile long callsStarted;
    private final TimeProvider time;
    private static final AtomicLongFieldUpdater<GrpclbClientLoadRecorder> callsStartedUpdater = AtomicLongFieldUpdater.newUpdater(GrpclbClientLoadRecorder.class, "callsStarted");
    private static final AtomicLongFieldUpdater<GrpclbClientLoadRecorder> callsFinishedUpdater = AtomicLongFieldUpdater.newUpdater(GrpclbClientLoadRecorder.class, "callsFinished");
    private static final AtomicLongFieldUpdater<GrpclbClientLoadRecorder> callsFailedToSendUpdater = AtomicLongFieldUpdater.newUpdater(GrpclbClientLoadRecorder.class, "callsFailedToSend");
    private static final AtomicLongFieldUpdater<GrpclbClientLoadRecorder> callsFinishedKnownReceivedUpdater = AtomicLongFieldUpdater.newUpdater(GrpclbClientLoadRecorder.class, "callsFinishedKnownReceived");

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class LongHolder {
        long num;

        private LongHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpclbClientLoadRecorder(TimeProvider timeProvider) {
        this.time = (TimeProvider) Preconditions.checkNotNull(timeProvider, "time provider");
    }

    @Override // io.grpc.ClientStreamTracer.Factory
    public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
        callsStartedUpdater.getAndIncrement(this);
        return new StreamTracer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordDroppedRequest(String str) {
        callsStartedUpdater.getAndIncrement(this);
        callsFinishedUpdater.getAndIncrement(this);
        synchronized (this) {
            LongHolder longHolder = this.callsDroppedPerToken.get(str);
            if (longHolder == null) {
                Map<String, LongHolder> map = this.callsDroppedPerToken;
                LongHolder longHolder2 = new LongHolder();
                map.put(str, longHolder2);
                longHolder = longHolder2;
            }
            longHolder.num++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientStats generateLoadReport() {
        ClientStats.Builder numCallsFinishedKnownReceived = ClientStats.newBuilder().setTimestamp(Timestamps.fromNanos(this.time.currentTimeNanos())).setNumCallsStarted(callsStartedUpdater.getAndSet(this, 0L)).setNumCallsFinished(callsFinishedUpdater.getAndSet(this, 0L)).setNumCallsFinishedWithClientFailedToSend(callsFailedToSendUpdater.getAndSet(this, 0L)).setNumCallsFinishedKnownReceived(callsFinishedKnownReceivedUpdater.getAndSet(this, 0L));
        Map<String, LongHolder> emptyMap = Collections.emptyMap();
        synchronized (this) {
            if (!this.callsDroppedPerToken.isEmpty()) {
                emptyMap = this.callsDroppedPerToken;
                this.callsDroppedPerToken = new HashMap(emptyMap.size());
            }
        }
        for (Map.Entry<String, LongHolder> entry : emptyMap.entrySet()) {
            numCallsFinishedKnownReceived.addCallsFinishedWithDrop(ClientStatsPerToken.newBuilder().setLoadBalanceToken(entry.getKey()).setNumCalls(entry.getValue().num).build());
        }
        return numCallsFinishedKnownReceived.build();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private class StreamTracer extends ClientStreamTracer {
        private volatile boolean anythingReceived;
        private volatile boolean headersSent;

        private StreamTracer() {
        }

        @Override // io.grpc.ClientStreamTracer
        public void outboundHeaders() {
            this.headersSent = true;
        }

        @Override // io.grpc.ClientStreamTracer
        public void inboundHeaders() {
            this.anythingReceived = true;
        }

        @Override // io.grpc.StreamTracer
        public void inboundMessage(int i) {
            this.anythingReceived = true;
        }

        @Override // io.grpc.StreamTracer
        public void streamClosed(Status status) {
            GrpclbClientLoadRecorder.callsFinishedUpdater.getAndIncrement(GrpclbClientLoadRecorder.this);
            if (!this.headersSent) {
                GrpclbClientLoadRecorder.callsFailedToSendUpdater.getAndIncrement(GrpclbClientLoadRecorder.this);
            }
            if (this.anythingReceived) {
                GrpclbClientLoadRecorder.callsFinishedKnownReceivedUpdater.getAndIncrement(GrpclbClientLoadRecorder.this);
            }
        }
    }
}
