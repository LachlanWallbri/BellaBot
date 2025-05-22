package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class StreamingCallSettings<RequestT, ResponseT> {
    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newBuilder() {
        return new Builder<>();
    }

    public Builder<RequestT, ResponseT> toBuilder() {
        return new Builder<>(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder<RequestT, ResponseT> {
        public Builder() {
        }

        public Builder(StreamingCallSettings<RequestT, ResponseT> streamingCallSettings) {
        }

        public StreamingCallSettings<RequestT, ResponseT> build() {
            return new StreamingCallSettings<>();
        }
    }
}
