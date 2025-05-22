package com.pudutech.bumblebee.business.core_devices_task.camera;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.business.base.SwitchListener;
import com.pudutech.bumblebee.business.core_devices_task.IDeviceListenerDispatcher;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MarkerCameraController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/bumblebee/business/core_devices_task/IDeviceListenerDispatcher;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MarkerCameraController$deviceListener$2 extends Lambda implements Function0<IDeviceListenerDispatcher> {
    final /* synthetic */ MarkerCameraController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MarkerCameraController$deviceListener$2(MarkerCameraController markerCameraController) {
        super(0);
        this.this$0 = markerCameraController;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final IDeviceListenerDispatcher invoke() {
        IDeviceListenerDispatcher iDeviceListenerDispatcher = new IDeviceListenerDispatcher();
        iDeviceListenerDispatcher.setOnMarkerCameraMethod(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.camera.MarkerCameraController$deviceListener$2$$special$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                String str;
                final Boolean bool;
                ListenerList listeners;
                String str2;
                String str3;
                str = MarkerCameraController$deviceListener$2.this.this$0.TAG;
                Pdlog.m3275i(str, "onMarkerCamera " + z);
                bool = MarkerCameraController$deviceListener$2.this.this$0.isRunning;
                MarkerCameraController$deviceListener$2.this.this$0.isRunning = Boolean.valueOf(z);
                listeners = MarkerCameraController$deviceListener$2.this.this$0.getListeners();
                listeners.forEach(new Function1<SwitchListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.camera.MarkerCameraController$deviceListener$2$$special$$inlined$apply$lambda$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SwitchListener switchListener) {
                        invoke2(switchListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SwitchListener it) {
                        Boolean bool2;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Boolean bool3 = bool;
                        bool2 = MarkerCameraController$deviceListener$2.this.this$0.isRunning;
                        it.onValueSet("MarkerCamera", bool3, bool2);
                    }
                });
                TimerThread timerThread = TimerThread.INSTANCE;
                str2 = MarkerCameraController$deviceListener$2.this.this$0.objSting;
                str3 = MarkerCameraController$deviceListener$2.this.this$0.TAG;
                timerThread.rePost(str2, str3, (r21 & 4) != 0 ? -1L : 0L, (r21 & 8) != 0 ? -1L : MarkerCameraController$deviceListener$2.this.this$0.getRetryTime_ms(), (r21 & 16) != 0 ? false : false, new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.camera.MarkerCameraController$deviceListener$2$$special$$inlined$apply$lambda$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        boolean z2;
                        MarkerCameraController markerCameraController = MarkerCameraController$deviceListener$2.this.this$0;
                        z2 = MarkerCameraController$deviceListener$2.this.this$0.isSwitchOn;
                        markerCameraController.fsm(z2, Boolean.valueOf(z));
                    }
                });
            }
        });
        return iDeviceListenerDispatcher;
    }
}
