package com.google.rpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface ErrorInfoOrBuilder extends MessageOrBuilder {
    boolean containsMetadata(String str);

    String getDomain();

    ByteString getDomainBytes();

    @Deprecated
    Map<String, String> getMetadata();

    int getMetadataCount();

    Map<String, String> getMetadataMap();

    String getMetadataOrDefault(String str, String str2);

    String getMetadataOrThrow(String str);

    String getReason();

    ByteString getReasonBytes();
}
