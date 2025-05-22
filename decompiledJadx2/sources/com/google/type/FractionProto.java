package com.google.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class FractionProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001agoogle/type/fraction.proto\u0012\u000bgoogle.type\"2\n\bFraction\u0012\u0011\n\tnumerator\u0018\u0001 \u0001(\u0003\u0012\u0013\n\u000bdenominator\u0018\u0002 \u0001(\u0003Bf\n\u000fcom.google.typeB\rFractionProtoP\u0001Z<google.golang.org/genproto/googleapis/type/fraction;fraction¢\u0002\u0003GTPb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_type_Fraction_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_Fraction_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_Fraction_descriptor, new String[]{"Numerator", "Denominator"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private FractionProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
