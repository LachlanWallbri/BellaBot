package com.google.rpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface RequestInfoOrBuilder extends MessageOrBuilder {
    String getRequestId();

    ByteString getRequestIdBytes();

    String getServingData();

    ByteString getServingDataBytes();
}
