package com.google.rpc;

import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.PreconditionFailure;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface PreconditionFailureOrBuilder extends MessageOrBuilder {
    PreconditionFailure.Violation getViolations(int i);

    int getViolationsCount();

    List<PreconditionFailure.Violation> getViolationsList();

    PreconditionFailure.ViolationOrBuilder getViolationsOrBuilder(int i);

    List<? extends PreconditionFailure.ViolationOrBuilder> getViolationsOrBuilderList();
}
