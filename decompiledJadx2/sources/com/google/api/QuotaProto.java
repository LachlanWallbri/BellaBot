package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class QuotaProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016google/api/quota.proto\u0012\ngoogle.api\"]\n\u0005Quota\u0012&\n\u0006limits\u0018\u0003 \u0003(\u000b2\u0016.google.api.QuotaLimit\u0012,\n\fmetric_rules\u0018\u0004 \u0003(\u000b2\u0016.google.api.MetricRule\"\u0091\u0001\n\nMetricRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012=\n\fmetric_costs\u0018\u0002 \u0003(\u000b2'.google.api.MetricRule.MetricCostsEntry\u001a2\n\u0010MetricCostsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\u0003:\u00028\u0001\"\u0095\u0002\n\nQuotaLimit\u0012\f\n\u0004name\u0018\u0006 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012\u0015\n\rdefault_limit\u0018\u0003 \u0001(\u0003\u0012\u0011\n\tmax_limit\u0018\u0004 \u0001(\u0003\u0012\u0011\n\tfree_tier\u0018\u0007 \u0001(\u0003\u0012\u0010\n\bduration\u0018\u0005 \u0001(\t\u0012\u000e\n\u0006metric\u0018\b \u0001(\t\u0012\f\n\u0004unit\u0018\t \u0001(\t\u00122\n\u0006values\u0018\n \u0003(\u000b2\".google.api.QuotaLimit.ValuesEntry\u0012\u0014\n\fdisplay_name\u0018\f \u0001(\t\u001a-\n\u000bValuesEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\u0003:\u00028\u0001Bl\n\u000ecom.google.apiB\nQuotaProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_Quota_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Quota_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Quota_descriptor, new String[]{"Limits", "MetricRules"});
    static final Descriptors.Descriptor internal_static_google_api_MetricRule_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_MetricRule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_MetricRule_descriptor, new String[]{"Selector", "MetricCosts"});

    /* renamed from: internal_static_google_api_MetricRule_MetricCostsEntry_descriptor */
    static final Descriptors.Descriptor f1336x75bbae3b = internal_static_google_api_MetricRule_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_api_MetricRule_MetricCostsEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1337x5d6269b9 = new GeneratedMessageV3.FieldAccessorTable(f1336x75bbae3b, new String[]{"Key", "Value"});
    static final Descriptors.Descriptor internal_static_google_api_QuotaLimit_descriptor = getDescriptor().getMessageTypes().get(2);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_QuotaLimit_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_QuotaLimit_descriptor, new String[]{"Name", "Description", "DefaultLimit", "MaxLimit", "FreeTier", "Duration", "Metric", "Unit", "Values", "DisplayName"});
    static final Descriptors.Descriptor internal_static_google_api_QuotaLimit_ValuesEntry_descriptor = internal_static_google_api_QuotaLimit_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_api_QuotaLimit_ValuesEntry_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1338x4bb371fc = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_QuotaLimit_ValuesEntry_descriptor, new String[]{"Key", "Value"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private QuotaProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
