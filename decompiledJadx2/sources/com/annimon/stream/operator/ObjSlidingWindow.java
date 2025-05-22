package com.annimon.stream.operator;

import com.annimon.stream.internal.Compat;
import com.annimon.stream.iterator.LsaIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjSlidingWindow<T> extends LsaIterator<List<T>> {
    private final Iterator<? extends T> iterator;
    private final Queue<T> queue = Compat.queue();
    private final int stepWidth;
    private final int windowSize;

    public ObjSlidingWindow(Iterator<? extends T> it, int i, int i2) {
        this.iterator = it;
        this.windowSize = i;
        this.stepWidth = i2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public List<T> nextIteration() {
        for (int size = this.queue.size(); size < this.windowSize && this.iterator.hasNext(); size++) {
            this.queue.offer(this.iterator.next());
        }
        ArrayList arrayList = new ArrayList(this.queue);
        int min = Math.min(this.queue.size(), this.stepWidth);
        for (int i = 0; i < min; i++) {
            this.queue.poll();
        }
        for (int i2 = this.windowSize; i2 < this.stepWidth && this.iterator.hasNext(); i2++) {
            this.iterator.next();
        }
        return arrayList;
    }
}
