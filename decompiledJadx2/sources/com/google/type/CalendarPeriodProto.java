package com.google.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class CalendarPeriodProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n!google/type/calendar_period.proto\u0012\u000bgoogle.type*\u007f\n\u000eCalendarPeriod\u0012\u001f\n\u001bCALENDAR_PERIOD_UNSPECIFIED\u0010\u0000\u0012\u0007\n\u0003DAY\u0010\u0001\u0012\b\n\u0004WEEK\u0010\u0002\u0012\r\n\tFORTNIGHT\u0010\u0003\u0012\t\n\u0005MONTH\u0010\u0004\u0012\u000b\n\u0007QUARTER\u0010\u0005\u0012\b\n\u0004HALF\u0010\u0006\u0012\b\n\u0004YEAR\u0010\u0007Bx\n\u000fcom.google.typeB\u0013CalendarPeriodProtoP\u0001ZHgoogle.golang.org/genproto/googleapis/type/calendarperiod;calendarperiod¢\u0002\u0003GTPb\u0006proto3"}, new Descriptors.FileDescriptor[0]);

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private CalendarPeriodProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
