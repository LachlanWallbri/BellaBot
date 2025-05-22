package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.type.PhoneNumber;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface PhoneNumberOrBuilder extends MessageOrBuilder {
    String getE164Number();

    ByteString getE164NumberBytes();

    String getExtension();

    ByteString getExtensionBytes();

    PhoneNumber.KindCase getKindCase();

    PhoneNumber.ShortCode getShortCode();

    PhoneNumber.ShortCodeOrBuilder getShortCodeOrBuilder();

    boolean hasE164Number();

    boolean hasShortCode();
}
