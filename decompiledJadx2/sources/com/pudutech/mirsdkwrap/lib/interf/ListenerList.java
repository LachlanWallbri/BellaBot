package com.pudutech.mirsdkwrap.lib.interf;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import defpackage.C$r8$backportedMethods$utility$Boolean$1$hashCode;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ListenerList.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0017\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u000f\u0010\rJ\r\u0010\u0010\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0011J\u0017\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0014\u0010\u0015J)\u0010\u0016\u001a\u00020\n2!\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\n0\u0018J1\u0010\u0016\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2!\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\n0\u0018J\u0019\u0010\u001e\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00018\u0000H\u0000¢\u0006\u0004\b\u001f\u0010\rJ\u0006\u0010 \u001a\u00020!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "TAG", "", "list", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Ljava/lang/ref/WeakReference;", TmpConstant.GROUP_OP_ADD, "", "listener", "add$module_robot_mirsdk_wrapper_release", "(Ljava/lang/Object;)V", "addNotSame", "addNotSame$module_robot_mirsdk_wrapper_release", "clear", "clear$module_robot_mirsdk_wrapper_release", "contains", "", "contains$module_robot_mirsdk_wrapper_release", "(Ljava/lang/Object;)Z", "forEach", "method", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "it", "c", "Lkotlin/coroutines/CoroutineContext;", "remove", "remove$module_robot_mirsdk_wrapper_release", "size", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ListenerList<T> {
    private final String TAG = "BaseListener";
    private final CopyOnWriteArrayList<WeakReference<T>> list = new CopyOnWriteArrayList<>();

    public final void forEach(Function1<? super T, Unit> method) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Iterator<T> it = this.list.iterator();
        while (it.hasNext()) {
            C$r8$backportedMethods$utility$Boolean$1$hashCode c$r8$backportedMethods$utility$Boolean$1$hashCode = (Object) ((WeakReference) it.next()).get();
            if (c$r8$backportedMethods$utility$Boolean$1$hashCode != null) {
                method.invoke(c$r8$backportedMethods$utility$Boolean$1$hashCode);
            }
        }
    }

    public final void forEach(CoroutineContext c, Function1<? super T, Unit> method) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(method, "method");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, c, null, new ListenerList$forEach$2(this, method, null), 2, null);
    }

    public final boolean contains$module_robot_mirsdk_wrapper_release(T listener) {
        Iterator<T> it = this.list.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((WeakReference) it.next()).get(), listener)) {
                return true;
            }
        }
        return false;
    }

    public final void add$module_robot_mirsdk_wrapper_release(T listener) {
        Pdlog.m3273d(this.TAG, "addListener listener=" + listener + " when size=" + this.list.size());
        this.list.removeIf(new Predicate<WeakReference<T>>() { // from class: com.pudutech.mirsdkwrap.lib.interf.ListenerList$add$1
            @Override // java.util.function.Predicate
            public final boolean test(WeakReference<T> weakReference) {
                return weakReference.get() == null;
            }
        });
        Pdlog.m3273d(this.TAG, "after removeListener null size=" + this.list.size());
        this.list.add(new WeakReference<>(listener));
        Pdlog.m3273d(this.TAG, "after addListener new. size=" + this.list.size());
    }

    public final void addNotSame$module_robot_mirsdk_wrapper_release(T listener) {
        Pdlog.m3273d(this.TAG, "addListener listener=" + listener + " when size=" + this.list.size());
        this.list.removeIf(new Predicate<WeakReference<T>>() { // from class: com.pudutech.mirsdkwrap.lib.interf.ListenerList$addNotSame$1
            @Override // java.util.function.Predicate
            public final boolean test(WeakReference<T> weakReference) {
                return weakReference.get() == null;
            }
        });
        CopyOnWriteArrayList<WeakReference<T>> copyOnWriteArrayList = this.list;
        ArrayList arrayList = new ArrayList();
        for (T t : copyOnWriteArrayList) {
            if (Intrinsics.areEqual(((WeakReference) t).get(), listener)) {
                arrayList.add(t);
            }
        }
        if (!arrayList.isEmpty()) {
            Pdlog.m3274e(this.TAG, "addNotSame : has same listener");
            return;
        }
        Pdlog.m3273d(this.TAG, "after removeListener null size=" + this.list.size());
        this.list.add(new WeakReference<>(listener));
        Pdlog.m3273d(this.TAG, "after addListener new. size=" + this.list.size());
    }

    public final void remove$module_robot_mirsdk_wrapper_release(final T listener) {
        Pdlog.m3273d(this.TAG, "before removeListener size=" + this.list.size());
        if (listener == null) {
            this.list.clear();
            Pdlog.m3273d(this.TAG, "removeListener all");
        } else {
            this.list.removeIf(new Predicate<WeakReference<T>>() { // from class: com.pudutech.mirsdkwrap.lib.interf.ListenerList$remove$1
                @Override // java.util.function.Predicate
                public final boolean test(WeakReference<T> weakReference) {
                    return Intrinsics.areEqual(weakReference.get(), listener);
                }
            });
        }
        Pdlog.m3273d(this.TAG, "after removeListener size=" + this.list.size());
    }

    public final void clear$module_robot_mirsdk_wrapper_release() {
        this.list.clear();
    }

    public final int size() {
        return this.list.size();
    }
}
