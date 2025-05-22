package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface ExprOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getExpression();

    ByteString getExpressionBytes();

    String getLocation();

    ByteString getLocationBytes();

    String getTitle();

    ByteString getTitleBytes();
}
