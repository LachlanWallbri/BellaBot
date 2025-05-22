package com.google.common.base;

import com.google.errorprone.annotations.DoNotMock;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Use an instance of one of the Finalizable*Reference classes")
/* loaded from: classes3.dex */
public interface FinalizableReference {
    void finalizeReferent();
}
