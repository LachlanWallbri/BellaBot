package com.google.type;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.type.DateTime;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface DateTimeOrBuilder extends MessageOrBuilder {
    int getDay();

    int getHours();

    int getMinutes();

    int getMonth();

    int getNanos();

    int getSeconds();

    DateTime.TimeOffsetCase getTimeOffsetCase();

    TimeZone getTimeZone();

    TimeZoneOrBuilder getTimeZoneOrBuilder();

    Duration getUtcOffset();

    DurationOrBuilder getUtcOffsetOrBuilder();

    int getYear();

    boolean hasTimeZone();

    boolean hasUtcOffset();
}
