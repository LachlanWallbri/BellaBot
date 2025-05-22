package io.grpc.stub;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Preconditions;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class StreamObservers {
    public static <V> void copyWithFlowControl(final Iterator<V> it, final CallStreamObserver<V> callStreamObserver) {
        Preconditions.checkNotNull(it, MapElement.Source.SOURCE);
        Preconditions.checkNotNull(callStreamObserver, TypedValues.Attributes.S_TARGET);
        callStreamObserver.setOnReadyHandler(new Runnable() { // from class: io.grpc.stub.StreamObservers.1FlowControllingOnReadyHandler
            private boolean completed;

            @Override // java.lang.Runnable
            public void run() {
                if (this.completed) {
                    return;
                }
                while (CallStreamObserver.this.isReady() && it.hasNext()) {
                    CallStreamObserver.this.onNext(it.next());
                }
                if (it.hasNext()) {
                    return;
                }
                this.completed = true;
                CallStreamObserver.this.onCompleted();
            }
        });
    }

    public static <V> void copyWithFlowControl(Iterable<V> iterable, CallStreamObserver<V> callStreamObserver) {
        Preconditions.checkNotNull(iterable, MapElement.Source.SOURCE);
        copyWithFlowControl(iterable.iterator(), callStreamObserver);
    }
}
