package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface QuotaLimitOrBuilder extends MessageOrBuilder {
    boolean containsValues(String str);

    long getDefaultLimit();

    String getDescription();

    ByteString getDescriptionBytes();

    String getDisplayName();

    ByteString getDisplayNameBytes();

    String getDuration();

    ByteString getDurationBytes();

    long getFreeTier();

    long getMaxLimit();

    String getMetric();

    ByteString getMetricBytes();

    String getName();

    ByteString getNameBytes();

    String getUnit();

    ByteString getUnitBytes();

    @Deprecated
    Map<String, Long> getValues();

    int getValuesCount();

    Map<String, Long> getValuesMap();

    long getValuesOrDefault(String str, long j);

    long getValuesOrThrow(String str);
}
