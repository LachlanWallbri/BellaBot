package com.pudutech.bumblebee.robot_ui.manager;

import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager;
import com.pudutech.bumblebee.presenter.robot_open_task.config.MqttConfig;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotStatusManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0012H\u0002R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\r¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/RobotStatusManager;", "", "()V", "busyListener", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/robot_ui/manager/BusyListener;", "getBusyListener", "()Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "busyListener$delegate", "Lkotlin/Lazy;", "busyListenerList", "Lcom/pudutech/bumblebee/business/base/ListenerList;", "getBusyListenerList", "()Lcom/pudutech/bumblebee/business/base/ListenerList;", "busyListenerList$delegate", "isBusyState", "", "boolean", "", "notifyBeeperRobotState", "b", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RobotStatusManager {
    public static final RobotStatusManager INSTANCE = new RobotStatusManager();

    /* renamed from: busyListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy busyListenerList = LazyKt.lazy(new Function0<ListenerList<BusyListener>>() { // from class: com.pudutech.bumblebee.robot_ui.manager.RobotStatusManager$busyListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<BusyListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: busyListener$delegate, reason: from kotlin metadata */
    private static final Lazy busyListener = LazyKt.lazy(new Function0<BaseMultiListenerImpl<BusyListener>>() { // from class: com.pudutech.bumblebee.robot_ui.manager.RobotStatusManager$busyListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<BusyListener> invoke() {
            ListenerList busyListenerList2;
            busyListenerList2 = RobotStatusManager.INSTANCE.getBusyListenerList();
            return new BaseMultiListenerImpl<>(busyListenerList2);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<BusyListener> getBusyListenerList() {
        return (ListenerList) busyListenerList.getValue();
    }

    public final BaseMultiListenerImpl<BusyListener> getBusyListener() {
        return (BaseMultiListenerImpl) busyListener.getValue();
    }

    private RobotStatusManager() {
    }

    public final void isBusyState(final boolean r3) {
        getBusyListenerList().forEach(new Function1<BusyListener, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.RobotStatusManager$isBusyState$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BusyListener busyListener2) {
                invoke2(busyListener2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BusyListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onBusyState(r3);
            }
        });
        notifyBeeperRobotState(r3);
    }

    private final void notifyBeeperRobotState(boolean b) {
        int i = b ? 1 : -1;
        RobotNewOpenManager robotNewOpenManager = RobotNewOpenManager.INSTANCE;
        String str = MqttConfig.INSTANCE.getWorkMap().get(Integer.valueOf(i));
        if (str == null) {
            str = "";
        }
        robotNewOpenManager.reportRobotWorkStatus(i, str);
    }
}
