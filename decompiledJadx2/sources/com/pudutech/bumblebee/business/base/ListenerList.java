package com.pudutech.bumblebee.business.base;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ListenerList.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0017\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0011\u0010\u0012J)\u0010\u0013\u001a\u00020\u000b2!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u000b0\u0015J\u0019\u0010\u0019\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00018\u0000H\u0000¢\u0006\u0004\b\u001a\u0010\u000eJ\u0017\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u001c\u0010\u000eJ\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/base/ListenerList;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/bumblebee/business/base/BaseListener;", "", "()V", "TAG", "", "list", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Ljava/lang/ref/WeakReference;", TmpConstant.GROUP_OP_ADD, "", "listener", "add$module_bumblebee_business_robotRelease", "(Lcom/pudutech/bumblebee/business/base/BaseListener;)V", "contains", "", "contains$module_bumblebee_business_robotRelease", "(Lcom/pudutech/bumblebee/business/base/BaseListener;)Z", "forEach", "method", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "it", "remove", "remove$module_bumblebee_business_robotRelease", "removeSameClass", "removeSameClass$module_bumblebee_business_robotRelease", "size", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ListenerList<T extends BaseListener> {
    private final String TAG = "BaseListener";
    private final CopyOnWriteArrayList<WeakReference<T>> list = new CopyOnWriteArrayList<>();

    public final void forEach(Function1<? super T, Unit> method) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Iterator<T> it = this.list.iterator();
        while (it.hasNext()) {
            BaseListener it2 = (BaseListener) ((WeakReference) it.next()).get();
            if (it2 != null) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                method.invoke(it2);
            }
        }
    }

    public final boolean contains$module_bumblebee_business_robotRelease(T listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Iterator<T> it = this.list.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual((BaseListener) ((WeakReference) it.next()).get(), listener)) {
                return true;
            }
        }
        return false;
    }

    public final void removeSameClass$module_bumblebee_business_robotRelease(final T listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3275i(this.TAG, "remove same class or null");
        Pdlog.m3275i(this.TAG, "before remove size=" + this.list.size());
        this.list.removeIf(new Predicate<WeakReference<T>>() { // from class: com.pudutech.bumblebee.business.base.ListenerList$removeSameClass$1
            @Override // java.util.function.Predicate
            public final boolean test(WeakReference<T> weakReference) {
                String str;
                BaseListener baseListener = (BaseListener) weakReference.get();
                if (baseListener == null) {
                    return true;
                }
                Intrinsics.checkExpressionValueIsNotNull(baseListener, "it.get() ?: return@removeIf true");
                if (!Intrinsics.areEqual(baseListener.getClass().getName(), listener.getClass().getName())) {
                    return false;
                }
                str = ListenerList.this.TAG;
                Pdlog.m3277w(str, "same name=" + listener.getClass().getName() + ". remove it");
                return true;
            }
        });
        Pdlog.m3275i(this.TAG, "after remove size=" + this.list.size());
    }

    public final void add$module_bumblebee_business_robotRelease(T listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3275i(this.TAG, "addListener listener=" + listener + " when size=" + this.list.size());
        this.list.removeIf(new Predicate<WeakReference<T>>() { // from class: com.pudutech.bumblebee.business.base.ListenerList$add$1
            @Override // java.util.function.Predicate
            public final boolean test(WeakReference<T> weakReference) {
                return weakReference.get() == null;
            }
        });
        Pdlog.m3275i(this.TAG, "after removeListener null size=" + this.list.size());
        this.list.add(new WeakReference<>(listener));
        Pdlog.m3275i(this.TAG, "after addListener new. size=" + this.list.size());
    }

    public final void remove$module_bumblebee_business_robotRelease(final T listener) {
        Pdlog.m3275i(this.TAG, "before removeListener size=" + this.list.size());
        if (listener == null) {
            this.list.clear();
            Pdlog.m3275i(this.TAG, "removeListener all");
        } else {
            this.list.removeIf(new Predicate<WeakReference<T>>() { // from class: com.pudutech.bumblebee.business.base.ListenerList$remove$1
                @Override // java.util.function.Predicate
                public final boolean test(WeakReference<T> weakReference) {
                    return Intrinsics.areEqual((BaseListener) weakReference.get(), BaseListener.this);
                }
            });
        }
        Pdlog.m3275i(this.TAG, "after removeListener size=" + this.list.size());
    }

    public final int size() {
        return this.list.size();
    }
}
