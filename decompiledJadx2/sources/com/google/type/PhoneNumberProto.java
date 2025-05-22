package com.google.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class PhoneNumberProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/type/phone_number.proto\u0012\u000bgoogle.type\"«\u0001\n\u000bPhoneNumber\u0012\u0015\n\u000be164_number\u0018\u0001 \u0001(\tH\u0000\u00128\n\nshort_code\u0018\u0002 \u0001(\u000b2\".google.type.PhoneNumber.ShortCodeH\u0000\u0012\u0011\n\textension\u0018\u0003 \u0001(\t\u001a0\n\tShortCode\u0012\u0013\n\u000bregion_code\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0002 \u0001(\tB\u0006\n\u0004kindBt\n\u000fcom.google.typeB\u0010PhoneNumberProtoP\u0001ZDgoogle.golang.org/genproto/googleapis/type/phone_number;phone_numberø\u0001\u0001¢\u0002\u0003GTPb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_type_PhoneNumber_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_PhoneNumber_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_PhoneNumber_descriptor, new String[]{"E164Number", "ShortCode", "Extension", "Kind"});
    static final Descriptors.Descriptor internal_static_google_type_PhoneNumber_ShortCode_descriptor = internal_static_google_type_PhoneNumber_descriptor.getNestedTypes().get(0);

    /* renamed from: internal_static_google_type_PhoneNumber_ShortCode_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f2089x5586e619 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_PhoneNumber_ShortCode_descriptor, new String[]{"RegionCode", "Number"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private PhoneNumberProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
