package com.pudutech.mirsdkwrap.lib.robot;

import android.view.MotionEvent;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.robot.TouchListenerManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TouchListenerManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/TouchListenerManager;", "", "()V", "TAG", "", "onTouchListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lcom/pudutech/mirsdkwrap/lib/robot/TouchListenerManager$OnTouchListener;", "getOnTouchListeners", "()Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "onTouchListeners$delegate", "Lkotlin/Lazy;", "addTouchListener", "", "l", "getTouchListeners", "removeTouchEventListener", "OnTouchListener", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TouchListenerManager {
    public static final TouchListenerManager INSTANCE = new TouchListenerManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* renamed from: onTouchListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onTouchListeners = LazyKt.lazy(new Function0<ListenerList<OnTouchListener>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.TouchListenerManager$onTouchListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<TouchListenerManager.OnTouchListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* compiled from: TouchListenerManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/TouchListenerManager$OnTouchListener;", "", "onTouchListener", "", "event", "Landroid/view/MotionEvent;", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnTouchListener {
        void onTouchListener(MotionEvent event);
    }

    private final ListenerList<OnTouchListener> getOnTouchListeners() {
        return (ListenerList) onTouchListeners.getValue();
    }

    private TouchListenerManager() {
    }

    public final ListenerList<OnTouchListener> getTouchListeners() {
        return getOnTouchListeners();
    }

    public final void addTouchListener(OnTouchListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnTouchListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
        Pdlog.m3273d(TAG, "addTouchListener : l = " + l + "; size = " + getOnTouchListeners().size());
    }

    public final void removeTouchEventListener(OnTouchListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnTouchListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }
}
