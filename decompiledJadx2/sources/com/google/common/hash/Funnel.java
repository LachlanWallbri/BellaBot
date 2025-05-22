package com.google.common.hash;

import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Implement with a lambda")
/* loaded from: classes3.dex */
public interface Funnel<T> extends Serializable {
    void funnel(T t, PrimitiveSink primitiveSink);
}
