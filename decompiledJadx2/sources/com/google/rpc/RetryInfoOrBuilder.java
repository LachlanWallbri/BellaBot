package com.google.rpc;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface RetryInfoOrBuilder extends MessageOrBuilder {
    Duration getRetryDelay();

    DurationOrBuilder getRetryDelayOrBuilder();

    boolean hasRetryDelay();
}
