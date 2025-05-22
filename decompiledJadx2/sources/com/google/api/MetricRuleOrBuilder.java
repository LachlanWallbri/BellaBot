package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface MetricRuleOrBuilder extends MessageOrBuilder {
    boolean containsMetricCosts(String str);

    @Deprecated
    Map<String, Long> getMetricCosts();

    int getMetricCostsCount();

    Map<String, Long> getMetricCostsMap();

    long getMetricCostsOrDefault(String str, long j);

    long getMetricCostsOrThrow(String str);

    String getSelector();

    ByteString getSelectorBytes();
}
