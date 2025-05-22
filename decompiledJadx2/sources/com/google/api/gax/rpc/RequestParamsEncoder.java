package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For use by transport-specific implementations")
/* loaded from: classes2.dex */
public interface RequestParamsEncoder<RequestT> {
    String encode(RequestT requestt);
}
