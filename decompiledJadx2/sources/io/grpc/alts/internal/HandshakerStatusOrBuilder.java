package io.grpc.alts.internal;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface HandshakerStatusOrBuilder extends MessageOrBuilder {
    int getCode();

    String getDetails();

    ByteString getDetailsBytes();
}
