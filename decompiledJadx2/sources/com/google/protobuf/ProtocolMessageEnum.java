package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public interface ProtocolMessageEnum extends Internal.EnumLite {
    Descriptors.EnumDescriptor getDescriptorForType();

    @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    int getNumber();

    Descriptors.EnumValueDescriptor getValueDescriptor();
}
