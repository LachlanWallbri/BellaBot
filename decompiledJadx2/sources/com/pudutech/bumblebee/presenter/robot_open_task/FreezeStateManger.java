package com.pudutech.bumblebee.presenter.robot_open_task;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreenListener;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.FreezeStateListener;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: FreezeStateManger.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/FreezeStateManger;", "", "()V", "TAG", "", "_freezeStateListener", "Lcom/pudutech/bumblebee/business/base/ListenerList;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/FreezeStateListener;", "get_freezeStateListener", "()Lcom/pudutech/bumblebee/business/base/ListenerList;", "_freezeStateListener$delegate", "Lkotlin/Lazy;", "freezeStateListener", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "getFreezeStateListener", "()Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "freezeStateListener$delegate", "lCDScreenListener", "com/pudutech/bumblebee/presenter/robot_open_task/FreezeStateManger$lCDScreenListener$1", "Lcom/pudutech/bumblebee/presenter/robot_open_task/FreezeStateManger$lCDScreenListener$1;", ES6Iterator.VALUE_PROPERTY, "", "lastFinishTime", "getLastFinishTime", "()J", "setLastFinishTime", "(J)V", "updateFreezeState", "Ljava/lang/Runnable;", "isFreezeState", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FreezeStateManger {
    private static long lastFinishTime;
    public static final FreezeStateManger INSTANCE = new FreezeStateManger();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* renamed from: _freezeStateListener$delegate, reason: from kotlin metadata */
    private static final Lazy _freezeStateListener = LazyKt.lazy(new Function0<ListenerList<FreezeStateListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.FreezeStateManger$_freezeStateListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<FreezeStateListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: freezeStateListener$delegate, reason: from kotlin metadata */
    private static final Lazy freezeStateListener = LazyKt.lazy(new Function0<BaseMultiListenerImpl<FreezeStateListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.FreezeStateManger$freezeStateListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<FreezeStateListener> invoke() {
            ListenerList listenerList;
            listenerList = FreezeStateManger.INSTANCE.get_freezeStateListener();
            return new BaseMultiListenerImpl<>(listenerList);
        }
    });
    private static final Runnable updateFreezeState = new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.FreezeStateManger$updateFreezeState$1
        @Override // java.lang.Runnable
        public final void run() {
            ListenerList listenerList;
            listenerList = FreezeStateManger.INSTANCE.get_freezeStateListener();
            listenerList.forEach(new Function1<FreezeStateListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.FreezeStateManger$updateFreezeState$1.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FreezeStateListener freezeStateListener2) {
                    invoke2(freezeStateListener2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(FreezeStateListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onFreezeStateChange(FreezeStateManger.INSTANCE.isFreezeState());
                }
            });
        }
    };
    private static final FreezeStateManger$lCDScreenListener$1 lCDScreenListener = new LCDScreenListener() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.FreezeStateManger$lCDScreenListener$1
        @Override // com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreenListener
        public void onTouchEvent() {
            String str;
            FreezeStateManger.INSTANCE.setLastFinishTime(SystemClock.elapsedRealtime());
            FreezeStateManger freezeStateManger = FreezeStateManger.INSTANCE;
            str = FreezeStateManger.TAG;
            Pdlog.m3273d(str, "onTouchEvent lastFinishTime:" + FreezeStateManger.INSTANCE.getLastFinishTime() + ' ');
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<FreezeStateListener> get_freezeStateListener() {
        return (ListenerList) _freezeStateListener.getValue();
    }

    public final BaseMultiListenerImpl<FreezeStateListener> getFreezeStateListener() {
        return (BaseMultiListenerImpl) freezeStateListener.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [com.pudutech.bumblebee.presenter.robot_open_task.FreezeStateManger$lCDScreenListener$1] */
    static {
        Peripherals.INSTANCE.getLcd().addListener(lCDScreenListener);
    }

    private FreezeStateManger() {
    }

    public final long getLastFinishTime() {
        return lastFinishTime;
    }

    public final void setLastFinishTime(long j) {
        lastFinishTime = j;
        TimerThread.INSTANCE.remove(updateFreezeState);
        if (j == 0) {
            TimerThread.INSTANCE.post(updateFreezeState, 0L);
        } else {
            TimerThread.INSTANCE.post(updateFreezeState, 0L);
            TimerThread.INSTANCE.post(updateFreezeState, BusinessSetting.INSTANCE.getCallFreezeTime_ms() + 200);
        }
    }

    public final boolean isFreezeState() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = elapsedRealtime - lastFinishTime;
        Pdlog.m3273d(TAG, "isFreezeState elapsedRealtime:" + elapsedRealtime + ",lastFinishTime:" + lastFinishTime + ' ');
        return j < BusinessSetting.INSTANCE.getCallFreezeTime_ms() && lastFinishTime != 0;
    }
}
