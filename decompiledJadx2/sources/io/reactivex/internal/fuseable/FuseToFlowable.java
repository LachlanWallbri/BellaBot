package io.reactivex.internal.fuseable;

import io.reactivex.Flowable;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface FuseToFlowable<T> {
    Flowable<T> fuseToFlowable();
}
