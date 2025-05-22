package com.google.type;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class DateTimeProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001agoogle/type/datetime.proto\u0012\u000bgoogle.type\u001a\u001egoogle/protobuf/duration.proto\"à\u0001\n\bDateTime\u0012\f\n\u0004year\u0018\u0001 \u0001(\u0005\u0012\r\n\u0005month\u0018\u0002 \u0001(\u0005\u0012\u000b\n\u0003day\u0018\u0003 \u0001(\u0005\u0012\r\n\u0005hours\u0018\u0004 \u0001(\u0005\u0012\u000f\n\u0007minutes\u0018\u0005 \u0001(\u0005\u0012\u000f\n\u0007seconds\u0018\u0006 \u0001(\u0005\u0012\r\n\u0005nanos\u0018\u0007 \u0001(\u0005\u0012/\n\nutc_offset\u0018\b \u0001(\u000b2\u0019.google.protobuf.DurationH\u0000\u0012*\n\ttime_zone\u0018\t \u0001(\u000b2\u0015.google.type.TimeZoneH\u0000B\r\n\u000btime_offset\"'\n\bTimeZone\u0012\n\n\u0002id\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007version\u0018\u0002 \u0001(\tBi\n\u000fcom.google.typeB\rDateTimeProtoP\u0001Z<google.golang.org/genproto/googleapis/type/datetime;datetimeø\u0001\u0001¢\u0002\u0003GTPb\u0006proto3"}, new Descriptors.FileDescriptor[]{DurationProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_type_DateTime_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_DateTime_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_DateTime_descriptor, new String[]{"Year", "Month", "Day", "Hours", "Minutes", "Seconds", "Nanos", "UtcOffset", "TimeZone", "TimeOffset"});
    static final Descriptors.Descriptor internal_static_google_type_TimeZone_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_TimeZone_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_type_TimeZone_descriptor, new String[]{JsonDocumentFields.POLICY_ID, JsonDocumentFields.VERSION});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DateTimeProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        DurationProto.getDescriptor();
    }
}
