package com.google.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class DateProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016google/type/date.proto\u0012\u000bgoogle.type\"0\n\u0004Date\u0012\f\n\u0004year\u0018\u0001 \u0001(\u0005\u0012\r\n\u0005month\u0018\u0002 \u0001(\u0005\u0012\u000b\n\u0003day\u0018\u0003 \u0001(\u0005B]\n\u000fcom.google.typeB\tDateProtoP\u0001Z4google.golang.org/genproto/googleapis/type/date;dateø\u0001\u0001¢\u0002\u0003GTPb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_type_Date_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_Date_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_Date_descriptor, new String[]{"Year", "Month", "Day"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DateProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
