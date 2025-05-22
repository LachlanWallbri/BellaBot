package com.google.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.TimestampProto;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class IntervalProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001agoogle/type/interval.proto\u0012\u000bgoogle.type\u001a\u001fgoogle/protobuf/timestamp.proto\"h\n\bInterval\u0012.\n\nstart_time\u0018\u0001 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012,\n\bend_time\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.TimestampBi\n\u000fcom.google.typeB\rIntervalProtoP\u0001Z<google.golang.org/genproto/googleapis/type/interval;intervalø\u0001\u0001¢\u0002\u0003GTPb\u0006proto3"}, new Descriptors.FileDescriptor[]{TimestampProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_type_Interval_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_Interval_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_Interval_descriptor, new String[]{"StartTime", "EndTime"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private IntervalProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        TimestampProto.getDescriptor();
    }
}
