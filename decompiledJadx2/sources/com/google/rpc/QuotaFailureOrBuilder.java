package com.google.rpc;

import com.google.protobuf.MessageOrBuilder;
import com.google.rpc.QuotaFailure;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface QuotaFailureOrBuilder extends MessageOrBuilder {
    QuotaFailure.Violation getViolations(int i);

    int getViolationsCount();

    List<QuotaFailure.Violation> getViolationsList();

    QuotaFailure.ViolationOrBuilder getViolationsOrBuilder(int i);

    List<? extends QuotaFailure.ViolationOrBuilder> getViolationsOrBuilderList();
}
