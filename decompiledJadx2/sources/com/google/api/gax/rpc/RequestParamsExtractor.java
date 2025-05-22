package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For use by transport-specific implementations")
/* loaded from: classes2.dex */
public interface RequestParamsExtractor<RequestT> {
    Map<String, String> extract(RequestT requestt);
}
