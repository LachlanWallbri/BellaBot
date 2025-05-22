package com.google.common.collect;

import com.google.errorprone.annotations.DoNotMock;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Use Iterators.peekingIterator")
/* loaded from: classes3.dex */
public interface PeekingIterator<E> extends Iterator<E> {
    @Override // java.util.Iterator
    E next();

    E peek();

    @Override // java.util.Iterator
    void remove();
}
