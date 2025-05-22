package com.pudutech.rgbdlib;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: RGBDSensor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final class RGBDSensor$startDownRGBDCheckThread$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ RGBDSensor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RGBDSensor$startDownRGBDCheckThread$1(RGBDSensor rGBDSensor) {
        super(0);
        this.this$0 = rGBDSensor;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        while (true) {
            try {
                if (this.this$0.getMode() != ShowMode.DOWN_RGBD_CHECK) {
                    Thread.sleep(1000L);
                } else if (!this.this$0.getContinueRgbdCheck()) {
                    Thread.sleep(500L);
                } else {
                    byte[] nativeGetDownRgbdCheckData = this.this$0.nativeGetDownRgbdCheckData(this.this$0.getRobotType().getId());
                    if (nativeGetDownRgbdCheckData != null) {
                        boolean nativeGetDownRgbdCheckResult = this.this$0.nativeGetDownRgbdCheckResult();
                        Pdlog.m3273d(RGBDSensor.access$getTAG$p(this.this$0), "down rgbd callback size=" + nativeGetDownRgbdCheckData.length + ",res: " + nativeGetDownRgbdCheckResult);
                        if (this.this$0.getMode() == ShowMode.DOWN_RGBD_CHECK) {
                            this.this$0.getDownRGBDCheckResultCB().invoke(nativeGetDownRgbdCheckData, Boolean.valueOf(nativeGetDownRgbdCheckResult));
                        }
                    }
                }
            } catch (Exception e) {
                Pdlog.m3273d("sss", "exception: " + e.toString());
                return;
            }
        }
    }
}
