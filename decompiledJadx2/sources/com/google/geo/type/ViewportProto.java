package com.google.geo.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.type.LatLngProto;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class ViewportProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001egoogle/geo/type/viewport.proto\u0012\u000fgoogle.geo.type\u001a\u0018google/type/latlng.proto\"O\n\bViewport\u0012 \n\u0003low\u0018\u0001 \u0001(\u000b2\u0013.google.type.LatLng\u0012!\n\u0004high\u0018\u0002 \u0001(\u000b2\u0013.google.type.LatLngBo\n\u0013com.google.geo.typeB\rViewportProtoP\u0001Z@google.golang.org/genproto/googleapis/geo/type/viewport;viewport¢\u0002\u0004GGTPb\u0006proto3"}, new Descriptors.FileDescriptor[]{LatLngProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_geo_type_Viewport_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_geo_type_Viewport_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_geo_type_Viewport_descriptor, new String[]{"Low", "High"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ViewportProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        LatLngProto.getDescriptor();
    }
}
