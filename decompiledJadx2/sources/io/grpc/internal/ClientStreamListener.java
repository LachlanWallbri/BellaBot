package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ClientStreamListener extends StreamListener {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum RpcProgress {
        PROCESSED,
        REFUSED,
        DROPPED
    }

    void closed(Status status, Metadata metadata);

    void closed(Status status, RpcProgress rpcProgress, Metadata metadata);

    void headersRead(Metadata metadata);
}
