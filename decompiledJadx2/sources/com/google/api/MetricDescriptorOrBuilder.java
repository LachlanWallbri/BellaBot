package com.google.api;

import com.google.api.MetricDescriptor;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface MetricDescriptorOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getDisplayName();

    ByteString getDisplayNameBytes();

    LabelDescriptor getLabels(int i);

    int getLabelsCount();

    List<LabelDescriptor> getLabelsList();

    LabelDescriptorOrBuilder getLabelsOrBuilder(int i);

    List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList();

    LaunchStage getLaunchStage();

    int getLaunchStageValue();

    MetricDescriptor.MetricDescriptorMetadata getMetadata();

    MetricDescriptor.MetricDescriptorMetadataOrBuilder getMetadataOrBuilder();

    MetricDescriptor.MetricKind getMetricKind();

    int getMetricKindValue();

    String getMonitoredResourceTypes(int i);

    ByteString getMonitoredResourceTypesBytes(int i);

    int getMonitoredResourceTypesCount();

    List<String> getMonitoredResourceTypesList();

    String getName();

    ByteString getNameBytes();

    String getType();

    ByteString getTypeBytes();

    String getUnit();

    ByteString getUnitBytes();

    MetricDescriptor.ValueType getValueType();

    int getValueTypeValue();

    boolean hasMetadata();
}
