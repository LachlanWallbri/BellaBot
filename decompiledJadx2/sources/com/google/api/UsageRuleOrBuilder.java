package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface UsageRuleOrBuilder extends MessageOrBuilder {
    boolean getAllowUnregisteredCalls();

    String getSelector();

    ByteString getSelectorBytes();

    boolean getSkipServiceControl();
}
