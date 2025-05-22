package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import io.grpc.Metadata;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for response metadata is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface ResponseMetadataHandler {
    void onHeaders(Metadata metadata);

    void onTrailers(Metadata metadata);
}
