package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import io.grpc.ClientInterceptor;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for adding custom interceptors is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface GrpcInterceptorProvider {
    List<ClientInterceptor> getInterceptors();
}
