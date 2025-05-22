package com.google.common.collect;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ImmutableMapEntry<K, V> extends ImmutableEntry<K, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableMapEntry<K, V> getNextInKeyBucket() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableMapEntry<K, V> getNextInValueBucket() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isReusable() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> ImmutableMapEntry<K, V>[] createEntryArray(int i) {
        return new ImmutableMapEntry[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableMapEntry(K k, V v) {
        super(k, v);
        CollectPreconditions.checkEntryNotNull(k, v);
    }

    ImmutableMapEntry(ImmutableMapEntry<K, V> immutableMapEntry) {
        super(immutableMapEntry.getKey(), immutableMapEntry.getValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class NonTerminalImmutableMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final transient ImmutableMapEntry<K, V> nextInKeyBucket;

        @Override // com.google.common.collect.ImmutableMapEntry
        final boolean isReusable() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public NonTerminalImmutableMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
        }

        @Override // com.google.common.collect.ImmutableMapEntry
        final ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }
    }

    /* loaded from: classes3.dex */
    static final class NonTerminalImmutableBiMapEntry<K, V> extends NonTerminalImmutableMapEntry<K, V> {
        private final transient ImmutableMapEntry<K, V> nextInValueBucket;

        /* JADX INFO: Access modifiers changed from: package-private */
        public NonTerminalImmutableBiMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(k, v, immutableMapEntry);
            this.nextInValueBucket = immutableMapEntry2;
        }

        @Override // com.google.common.collect.ImmutableMapEntry
        ImmutableMapEntry<K, V> getNextInValueBucket() {
            return this.nextInValueBucket;
        }
    }
}
