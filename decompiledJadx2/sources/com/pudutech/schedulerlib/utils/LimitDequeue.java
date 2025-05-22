package com.pudutech.schedulerlib.utils;

import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
 */
/* compiled from: LimitDequeue.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fJ\r\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/schedulerlib/utils/LimitDequeue;", ExifInterface.LONGITUDE_EAST, "Ljava/util/concurrent/LinkedBlockingDeque;", "max_number", "", "(I)V", "item_count", "lock", "Ljava/lang/Object;", "putFirst", "", C3898x.f4338g, "(Ljava/lang/Object;)V", "takeFirst", "()Ljava/lang/Object;", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class LimitDequeue<E> extends LinkedBlockingDeque<E> {
    private int item_count;
    private final Object lock;
    private int max_number;

    public LimitDequeue() {
        this(0, 1, null);
    }

    public LimitDequeue(int i) {
        this.max_number = i;
        this.lock = new Object();
        if (this.max_number <= 0) {
            this.max_number = 10;
            Pdlog.m3275i("LimitDequeue", "Cannot set queue size less than 1");
        }
    }

    public /* synthetic */ LimitDequeue(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    @Override // java.util.concurrent.LinkedBlockingDeque, java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingDeque, java.util.Deque
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.concurrent.LinkedBlockingDeque, java.util.concurrent.BlockingDeque
    public void putFirst(E e) {
        synchronized (this.lock) {
            if (this.item_count == this.max_number) {
                super.takeLast();
                this.item_count--;
            }
            this.item_count++;
            super.putFirst(e);
            this.lock.notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [T, java.lang.Object] */
    @Override // java.util.concurrent.LinkedBlockingDeque, java.util.concurrent.BlockingDeque
    public E takeFirst() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        synchronized (this.lock) {
            if (this.item_count == 0) {
                this.lock.wait();
            }
            this.item_count--;
            objectRef.element = super.takeFirst();
            Unit unit = Unit.INSTANCE;
        }
        return (E) objectRef.element;
    }
}
