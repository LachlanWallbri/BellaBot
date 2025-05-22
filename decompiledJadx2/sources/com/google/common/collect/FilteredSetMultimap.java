package com.google.common.collect;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
interface FilteredSetMultimap<K, V> extends FilteredMultimap<K, V>, SetMultimap<K, V> {
    @Override // com.google.common.collect.FilteredMultimap
    SetMultimap<K, V> unfiltered();
}
