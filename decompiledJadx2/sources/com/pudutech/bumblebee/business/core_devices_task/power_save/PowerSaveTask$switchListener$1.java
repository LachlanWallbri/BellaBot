package com.pudutech.bumblebee.business.core_devices_task.power_save;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.base.SwitchListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PowerSaveTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, m3961d2 = {"com/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveTask$switchListener$1", "Lcom/pudutech/bumblebee/business/base/SwitchListener;", "onValueSet", "", "describe", "", "oldValue", "", "newValue", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PowerSaveTask$switchListener$1 implements SwitchListener {
    final /* synthetic */ PowerSaveTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PowerSaveTask$switchListener$1(PowerSaveTask powerSaveTask) {
        this.this$0 = powerSaveTask;
    }

    @Override // com.pudutech.bumblebee.business.base.BaseValueListener
    public void onValueSet(String describe, Boolean oldValue, final Boolean newValue) {
        String str;
        Intrinsics.checkParameterIsNotNull(describe, "describe");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onValueSet describe=" + describe + "  oldValue=" + oldValue + "  newValue=" + newValue);
        TimerThread.INSTANCE.post(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveTask$switchListener$1$onValueSet$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                if (Intrinsics.areEqual((Object) newValue, (Object) true)) {
                    PowerSaveTask$switchListener$1.this.this$0.checkAllDeviceReady();
                }
            }
        });
    }
}
