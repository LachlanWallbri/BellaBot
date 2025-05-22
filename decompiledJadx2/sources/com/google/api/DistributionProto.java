package com.google.api;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.TimestampProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class DistributionProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001dgoogle/api/distribution.proto\u0012\ngoogle.api\u001a\u0019google/protobuf/any.proto\u001a\u001fgoogle/protobuf/timestamp.proto\"Ù\u0006\n\fDistribution\u0012\r\n\u0005count\u0018\u0001 \u0001(\u0003\u0012\f\n\u0004mean\u0018\u0002 \u0001(\u0001\u0012 \n\u0018sum_of_squared_deviation\u0018\u0003 \u0001(\u0001\u0012-\n\u0005range\u0018\u0004 \u0001(\u000b2\u001e.google.api.Distribution.Range\u0012>\n\u000ebucket_options\u0018\u0006 \u0001(\u000b2&.google.api.Distribution.BucketOptions\u0012\u0015\n\rbucket_counts\u0018\u0007 \u0003(\u0003\u00124\n\texemplars\u0018\n \u0003(\u000b2!.google.api.Distribution.Exemplar\u001a!\n\u0005Range\u0012\u000b\n\u0003min\u0018\u0001 \u0001(\u0001\u0012\u000b\n\u0003max\u0018\u0002 \u0001(\u0001\u001aµ\u0003\n\rBucketOptions\u0012G\n\u000elinear_buckets\u0018\u0001 \u0001(\u000b2-.google.api.Distribution.BucketOptions.LinearH\u0000\u0012Q\n\u0013exponential_buckets\u0018\u0002 \u0001(\u000b22.google.api.Distribution.BucketOptions.ExponentialH\u0000\u0012K\n\u0010explicit_buckets\u0018\u0003 \u0001(\u000b2/.google.api.Distribution.BucketOptions.ExplicitH\u0000\u001aC\n\u0006Linear\u0012\u001a\n\u0012num_finite_buckets\u0018\u0001 \u0001(\u0005\u0012\r\n\u0005width\u0018\u0002 \u0001(\u0001\u0012\u000e\n\u0006offset\u0018\u0003 \u0001(\u0001\u001aO\n\u000bExponential\u0012\u001a\n\u0012num_finite_buckets\u0018\u0001 \u0001(\u0005\u0012\u0015\n\rgrowth_factor\u0018\u0002 \u0001(\u0001\u0012\r\n\u0005scale\u0018\u0003 \u0001(\u0001\u001a\u001a\n\bExplicit\u0012\u000e\n\u0006bounds\u0018\u0001 \u0003(\u0001B\t\n\u0007options\u001as\n\bExemplar\u0012\r\n\u0005value\u0018\u0001 \u0001(\u0001\u0012-\n\ttimestamp\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012)\n\u000battachments\u0018\u0003 \u0003(\u000b2\u0014.google.protobuf.AnyBq\n\u000ecom.google.apiB\u0011DistributionProtoP\u0001ZCgoogle.golang.org/genproto/googleapis/api/distribution;distribution¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[]{AnyProto.getDescriptor(), TimestampProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_api_Distribution_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Distribution_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Distribution_descriptor, new String[]{"Count", "Mean", "SumOfSquaredDeviation", "Range", "BucketOptions", "BucketCounts", "Exemplars"});
    static final Descriptors.Descriptor internal_static_google_api_Distribution_Range_descriptor = internal_static_google_api_Distribution_descriptor.getNestedTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Distribution_Range_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Distribution_Range_descriptor, new String[]{"Min", "Max"});
    static final Descriptors.Descriptor internal_static_google_api_Distribution_BucketOptions_descriptor = internal_static_google_api_Distribution_descriptor.getNestedTypes().get(1);

    /* renamed from: internal_static_google_api_Distribution_BucketOptions_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1321x433aff57 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Distribution_BucketOptions_descriptor, new String[]{"LinearBuckets", "ExponentialBuckets", "ExplicitBuckets", "Options"});

    /* renamed from: internal_static_google_api_Distribution_BucketOptions_Linear_descriptor */
    static final Descriptors.Descriptor f1319x65ad46ff = internal_static_google_api_Distribution_BucketOptions_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_api_Distribution_BucketOptions_Linear_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1320xf2a0fe7d = new GeneratedMessageV3.FieldAccessorTable(f1319x65ad46ff, new String[]{"NumFiniteBuckets", "Width", "Offset"});

    /* renamed from: internal_static_google_api_Distribution_BucketOptions_Exponential_descriptor */
    static final Descriptors.Descriptor f1317xdd8d7e33 = internal_static_google_api_Distribution_BucketOptions_descriptor.getNestedTypes().get(1);

    /* renamed from: internal_static_google_api_Distribution_BucketOptions_Exponential_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1318x5c40c1b1 = new GeneratedMessageV3.FieldAccessorTable(f1317xdd8d7e33, new String[]{"NumFiniteBuckets", "GrowthFactor", "Scale"});

    /* renamed from: internal_static_google_api_Distribution_BucketOptions_Explicit_descriptor */
    static final Descriptors.Descriptor f1315x47eb0b10 = internal_static_google_api_Distribution_BucketOptions_descriptor.getNestedTypes().get(2);

    /* renamed from: internal_static_google_api_Distribution_BucketOptions_Explicit_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1316x8566218e = new GeneratedMessageV3.FieldAccessorTable(f1315x47eb0b10, new String[]{"Bounds"});
    static final Descriptors.Descriptor internal_static_google_api_Distribution_Exemplar_descriptor = internal_static_google_api_Distribution_descriptor.getNestedTypes().get(2);

    /* renamed from: internal_static_google_api_Distribution_Exemplar_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1322xc3614745 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Distribution_Exemplar_descriptor, new String[]{"Value", "Timestamp", "Attachments"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DistributionProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        AnyProto.getDescriptor();
        TimestampProto.getDescriptor();
    }
}
