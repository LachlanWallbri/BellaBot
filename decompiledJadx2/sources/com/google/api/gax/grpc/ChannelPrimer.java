package com.google.api.gax.grpc;

import com.google.api.core.InternalApi;
import io.grpc.ManagedChannel;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For internal use by google-cloud-java clients only")
/* loaded from: classes2.dex */
public interface ChannelPrimer {
    void primeChannel(ManagedChannel managedChannel);
}
