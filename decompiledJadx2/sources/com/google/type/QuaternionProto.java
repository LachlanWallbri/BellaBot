package com.google.type;

import androidx.exifinterface.media.ExifInterface;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class QuaternionProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001cgoogle/type/quaternion.proto\u0012\u000bgoogle.type\"8\n\nQuaternion\u0012\t\n\u0001x\u0018\u0001 \u0001(\u0001\u0012\t\n\u0001y\u0018\u0002 \u0001(\u0001\u0012\t\n\u0001z\u0018\u0003 \u0001(\u0001\u0012\t\n\u0001w\u0018\u0004 \u0001(\u0001Bo\n\u000fcom.google.typeB\u000fQuaternionProtoP\u0001Z@google.golang.org/genproto/googleapis/type/quaternion;quaternionø\u0001\u0001¢\u0002\u0003GTPb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_type_Quaternion_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_Quaternion_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_Quaternion_descriptor, new String[]{"X", "Y", "Z", ExifInterface.LONGITUDE_WEST});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private QuaternionProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
