package io.reactivex;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface SingleTransformer<Upstream, Downstream> {
    SingleSource<Downstream> apply(Single<Upstream> single);
}
