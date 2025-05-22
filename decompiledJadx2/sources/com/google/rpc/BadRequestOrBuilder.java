package com.google.rpc;

import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.BadRequest;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface BadRequestOrBuilder extends MessageOrBuilder {
    BadRequest.FieldViolation getFieldViolations(int i);

    int getFieldViolationsCount();

    List<BadRequest.FieldViolation> getFieldViolationsList();

    BadRequest.FieldViolationOrBuilder getFieldViolationsOrBuilder(int i);

    List<? extends BadRequest.FieldViolationOrBuilder> getFieldViolationsOrBuilderList();
}
