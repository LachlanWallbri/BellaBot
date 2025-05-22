package io.reactivex.internal.fuseable;

import org.reactivestreams.Publisher;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface HasUpstreamPublisher<T> {
    Publisher<T> source();
}
