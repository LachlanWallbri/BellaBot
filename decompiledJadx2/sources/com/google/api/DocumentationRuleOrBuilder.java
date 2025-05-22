package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface DocumentationRuleOrBuilder extends MessageOrBuilder {
    String getDeprecationDescription();

    ByteString getDeprecationDescriptionBytes();

    String getDescription();

    ByteString getDescriptionBytes();

    String getSelector();

    ByteString getSelectorBytes();
}
