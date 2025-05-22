package com.pudutech.bumblebee.business.core_devices_task.camera;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.business.base.SwitchListener;
import com.pudutech.bumblebee.business.core_devices_task.IDeviceListenerDispatcher;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: IRLEDController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/bumblebee/business/core_devices_task/IDeviceListenerDispatcher;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class IRLEDController$deviceListener$2 extends Lambda implements Function0<IDeviceListenerDispatcher> {
    final /* synthetic */ IRLEDController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IRLEDController$deviceListener$2(IRLEDController iRLEDController) {
        super(0);
        this.this$0 = iRLEDController;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final IDeviceListenerDispatcher invoke() {
        IDeviceListenerDispatcher iDeviceListenerDispatcher = new IDeviceListenerDispatcher();
        iDeviceListenerDispatcher.setOnIRLEDMethod(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.camera.IRLEDController$deviceListener$2$$special$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String str;
                final Boolean bool;
                ListenerList listeners;
                str = IRLEDController$deviceListener$2.this.this$0.TAG;
                Pdlog.m3275i(str, "onIRLED " + z);
                bool = IRLEDController$deviceListener$2.this.this$0.isRunning;
                IRLEDController$deviceListener$2.this.this$0.isRunning = Boolean.valueOf(z);
                listeners = IRLEDController$deviceListener$2.this.this$0.getListeners();
                listeners.forEach(new Function1<SwitchListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.camera.IRLEDController$deviceListener$2$$special$$inlined$apply$lambda$1.1
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
                        bool2 = IRLEDController$deviceListener$2.this.this$0.isRunning;
                        it.onValueSet("irLedPower", bool3, bool2);
                    }
                });
                IRLEDController$deviceListener$2.this.this$0.fsm();
            }
        });
        return iDeviceListenerDispatcher;
    }
}
