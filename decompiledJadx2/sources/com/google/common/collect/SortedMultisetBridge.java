package com.google.common.collect;

import java.util.SortedSet;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
interface SortedMultisetBridge<E> extends Multiset<E> {
    @Override // com.google.common.collect.Multiset
    SortedSet<E> elementSet();
}
