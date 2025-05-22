package com.google.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class ColorProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0017google/type/color.proto\u0012\u000bgoogle.type\u001a\u001egoogle/protobuf/wrappers.proto\"]\n\u0005Color\u0012\u000b\n\u0003red\u0018\u0001 \u0001(\u0002\u0012\r\n\u0005green\u0018\u0002 \u0001(\u0002\u0012\f\n\u0004blue\u0018\u0003 \u0001(\u0002\u0012*\n\u0005alpha\u0018\u0004 \u0001(\u000b2\u001b.google.protobuf.FloatValueB`\n\u000fcom.google.typeB\nColorProtoP\u0001Z6google.golang.org/genproto/googleapis/type/color;colorø\u0001\u0001¢\u0002\u0003GTPb\u0006proto3"}, new Descriptors.FileDescriptor[]{WrappersProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_type_Color_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_Color_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_Color_descriptor, new String[]{"Red", "Green", "Blue", "Alpha"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ColorProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        WrappersProto.getDescriptor();
    }
}
