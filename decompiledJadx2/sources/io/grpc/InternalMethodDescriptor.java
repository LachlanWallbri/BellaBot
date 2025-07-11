package io.grpc;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalMethodDescriptor {
    private final InternalKnownTransport transport;

    public InternalMethodDescriptor(InternalKnownTransport internalKnownTransport) {
        this.transport = (InternalKnownTransport) Preconditions.checkNotNull(internalKnownTransport, NotificationCompat.CATEGORY_TRANSPORT);
    }

    public Object geRawMethodName(MethodDescriptor<?, ?> methodDescriptor) {
        return methodDescriptor.getRawMethodName(this.transport.ordinal());
    }

    public void setRawMethodName(MethodDescriptor<?, ?> methodDescriptor, Object obj) {
        methodDescriptor.setRawMethodName(this.transport.ordinal(), obj);
    }
}
