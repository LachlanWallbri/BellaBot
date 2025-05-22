package com.pudutech.bumblebee.robot.utils;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.loc.C3898x;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LimitQueue.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010)\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0016\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\rJ\u0016\u0010\u0014\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0016J\r\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u000bH\u0016J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0096\u0002J\u0015\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\r\u0010\u001b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\r\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\r\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u0015\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\u0016\u0010\u001e\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0016J\u0016\u0010\u001f\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0016J\b\u0010 \u001a\u00020!H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\""}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/utils/LimitQueue;", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/util/Queue;", "size", "", "(I)V", "queue", "Ljava/util/LinkedList;", "getSize", "()I", TmpConstant.GROUP_OP_ADD, "", "element", "(Ljava/lang/Object;)Z", "addAll", MapElement.Key.ELEMENTS, "", "clear", "", "contains", "containsAll", "()Ljava/lang/Object;", "isEmpty", "iterator", "", "offer", C3898x.f4338g, "peek", "poll", "remove", "removeAll", "retainAll", "toString", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LimitQueue<T> implements Queue<T> {
    private final LinkedList<T> queue = new LinkedList<>();
    private final int size;

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public LimitQueue(int i) {
        this.size = i;
    }

    public int getSize() {
        return this.size;
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Queue, java.util.Collection
    public boolean add(T element) {
        return this.queue.add(element);
    }

    @Override // java.util.Queue
    public T remove() {
        return this.queue.remove();
    }

    @Override // java.util.Queue
    public boolean offer(T e) {
        if (this.queue.size() >= size()) {
            this.queue.poll();
        }
        return this.queue.offer(e);
    }

    @Override // java.util.Queue
    public T poll() {
        return this.queue.poll();
    }

    @Override // java.util.Queue
    public T element() {
        return this.queue.element();
    }

    @Override // java.util.Queue
    public T peek() {
        return this.queue.peek();
    }

    @Override // java.util.Collection
    public boolean remove(Object element) {
        return this.queue.remove(element);
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return this.queue.addAll(elements);
    }

    @Override // java.util.Collection
    public void clear() {
        this.queue.clear();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        Iterator<T> it = this.queue.iterator();
        Intrinsics.checkExpressionValueIsNotNull(it, "queue.iterator()");
        return it;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return this.queue.removeAll(elements);
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return this.queue.retainAll(elements);
    }

    @Override // java.util.Collection
    public boolean contains(Object element) {
        return this.queue.contains(element);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return this.queue.containsAll(elements);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.queue.size() == 0;
    }

    public String toString() {
        return "LimitQueue(size=" + size() + ", queue=" + this.queue + ')';
    }
}
