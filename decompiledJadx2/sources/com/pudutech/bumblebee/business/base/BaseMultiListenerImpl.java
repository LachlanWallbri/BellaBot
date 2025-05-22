package com.pudutech.bumblebee.business.base;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseMultiListenerImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0007\b\u0016¢\u0006\u0002\u0010\u0004B\u0015\b\u0016\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0010R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0007¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/bumblebee/business/base/BaseListener;", "", "()V", "listeners", "Lcom/pudutech/bumblebee/business/base/ListenerList;", "(Lcom/pudutech/bumblebee/business/base/ListenerList;)V", "TAG", "", "getListeners", "()Lcom/pudutech/bumblebee/business/base/ListenerList;", "setListeners", "addListener", "", "listener", "(Lcom/pudutech/bumblebee/business/base/BaseListener;)V", "addListenerAllowSameClassName", "removeListener", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class BaseMultiListenerImpl<T extends BaseListener> {
    private final String TAG;
    private ListenerList<T> listeners;

    public BaseMultiListenerImpl() {
        this.TAG = "BaseListener";
        this.listeners = new ListenerList<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ListenerList<T> getListeners() {
        return this.listeners;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setListeners(ListenerList<T> listenerList) {
        Intrinsics.checkParameterIsNotNull(listenerList, "<set-?>");
        this.listeners = listenerList;
    }

    public BaseMultiListenerImpl(ListenerList<T> listeners) {
        Intrinsics.checkParameterIsNotNull(listeners, "listeners");
        this.TAG = "BaseListener";
        this.listeners = new ListenerList<>();
        this.listeners = listeners;
    }

    public final void addListener(T listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3275i(this.TAG, "this=" + this + " \n add listener=" + listener + " \n listener classname=" + listener.getClass().getName());
        if (this.listeners.contains$module_bumblebee_business_robotRelease(listener)) {
            return;
        }
        this.listeners.removeSameClass$module_bumblebee_business_robotRelease(listener);
        this.listeners.add$module_bumblebee_business_robotRelease(listener);
    }

    public final void addListenerAllowSameClassName(T listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3275i(this.TAG, "addListenerAllowSameClassName this=" + this + " \n add listener=" + listener + " \n listener classname=" + listener.getClass().getName());
        if (this.listeners.contains$module_bumblebee_business_robotRelease(listener)) {
            return;
        }
        this.listeners.add$module_bumblebee_business_robotRelease(listener);
    }

    public final void removeListener(T listener) {
        Pdlog.m3275i("BaseListener", "this=" + this + " removeListener listener=" + listener);
        this.listeners.remove$module_bumblebee_business_robotRelease(listener);
    }
}
