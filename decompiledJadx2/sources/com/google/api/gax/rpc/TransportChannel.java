package com.google.api.gax.rpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.core.BackgroundResource;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
/* loaded from: classes2.dex */
public interface TransportChannel extends BackgroundResource {
    ApiCallContext getEmptyCallContext();

    String getTransportName();
}
