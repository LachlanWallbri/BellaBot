package com.google.type;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface IntervalOrBuilder extends MessageOrBuilder {
    Timestamp getEndTime();

    TimestampOrBuilder getEndTimeOrBuilder();

    Timestamp getStartTime();

    TimestampOrBuilder getStartTimeOrBuilder();

    boolean hasEndTime();

    boolean hasStartTime();
}
