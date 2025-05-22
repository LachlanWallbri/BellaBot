package com.annimon.stream.operator;

import com.annimon.stream.function.BiFunction;
import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjMerge<T> extends LsaIterator<T> {
    private final Queue<T> buffer1 = new LinkedList();
    private final Queue<T> buffer2 = new LinkedList();
    private final Iterator<? extends T> iterator1;
    private final Iterator<? extends T> iterator2;
    private final BiFunction<? super T, ? super T, MergeResult> selector;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum MergeResult {
        TAKE_FIRST,
        TAKE_SECOND
    }

    public ObjMerge(Iterator<? extends T> it, Iterator<? extends T> it2, BiFunction<? super T, ? super T, MergeResult> biFunction) {
        this.iterator1 = it;
        this.iterator2 = it2;
        this.selector = biFunction;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return !this.buffer1.isEmpty() || !this.buffer2.isEmpty() || this.iterator1.hasNext() || this.iterator2.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public T nextIteration() {
        if (!this.buffer1.isEmpty()) {
            T poll = this.buffer1.poll();
            return this.iterator2.hasNext() ? select(poll, this.iterator2.next()) : poll;
        }
        if (!this.buffer2.isEmpty()) {
            T poll2 = this.buffer2.poll();
            return this.iterator1.hasNext() ? select(this.iterator1.next(), poll2) : poll2;
        }
        if (!this.iterator1.hasNext()) {
            return this.iterator2.next();
        }
        if (!this.iterator2.hasNext()) {
            return this.iterator1.next();
        }
        return select(this.iterator1.next(), this.iterator2.next());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.annimon.stream.operator.ObjMerge$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C14921 {
        static final /* synthetic */ int[] $SwitchMap$com$annimon$stream$operator$ObjMerge$MergeResult = new int[MergeResult.values().length];

        static {
            try {
                $SwitchMap$com$annimon$stream$operator$ObjMerge$MergeResult[MergeResult.TAKE_FIRST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$annimon$stream$operator$ObjMerge$MergeResult[MergeResult.TAKE_SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private T select(T t, T t2) {
        if (C14921.$SwitchMap$com$annimon$stream$operator$ObjMerge$MergeResult[this.selector.apply(t, t2).ordinal()] == 1) {
            this.buffer2.add(t2);
            return t;
        }
        this.buffer1.add(t);
        return t2;
    }
}
