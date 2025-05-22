package com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task;

import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LCDScreen.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/lcd_screed_task/LCDScreen;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/peripherals_task/lcd_screed_task/LCDScreenListener;", "()V", "touchScreen", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LCDScreen extends BaseMultiListenerImpl<LCDScreenListener> {
    public final void touchScreen() {
        getListeners().forEach(new Function1<LCDScreenListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreen$touchScreen$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LCDScreenListener lCDScreenListener) {
                invoke2(lCDScreenListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LCDScreenListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onTouchEvent();
            }
        });
    }
}
