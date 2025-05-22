package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface HeaderProvider {
    Map<String, String> getHeaders();
}
