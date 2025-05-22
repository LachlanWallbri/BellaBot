package io.grpc.alts.internal;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface HandshakerRespOrBuilder extends MessageOrBuilder {
    int getBytesConsumed();

    ByteString getOutFrames();

    HandshakerResult getResult();

    HandshakerResultOrBuilder getResultOrBuilder();

    HandshakerStatus getStatus();

    HandshakerStatusOrBuilder getStatusOrBuilder();

    boolean hasResult();

    boolean hasStatus();
}
