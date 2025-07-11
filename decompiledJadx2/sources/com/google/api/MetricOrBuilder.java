package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface MetricOrBuilder extends MessageOrBuilder {
    boolean containsLabels(String str);

    @Deprecated
    Map<String, String> getLabels();

    int getLabelsCount();

    Map<String, String> getLabelsMap();

    String getLabelsOrDefault(String str, String str2);

    String getLabelsOrThrow(String str);

    String getType();

    ByteString getTypeBytes();
}
