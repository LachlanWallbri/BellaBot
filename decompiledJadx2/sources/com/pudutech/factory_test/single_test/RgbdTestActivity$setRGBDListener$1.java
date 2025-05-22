package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IRgbdStatus;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: RgbdTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/factory_test/single_test/RgbdTestActivity$setRGBDListener$1", "Lcom/pudutech/mirsdk/hardware/IRgbdStatus$Stub;", "onParkingMode", "", "p0", "", "onRGBDFrameTick", "onRGBDOpened", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RgbdTestActivity$setRGBDListener$1 extends IRgbdStatus.Stub {
    final /* synthetic */ RgbdTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RgbdTestActivity$setRGBDListener$1(RgbdTestActivity rgbdTestActivity) {
        this.this$0 = rgbdTestActivity;
    }

    @Override // com.pudutech.mirsdk.hardware.IRgbdStatus
    public void onRGBDOpened(final boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onRGBDOpened p0=" + p0);
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$setRGBDListener$1$onRGBDOpened$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1<Boolean, Unit> onOpen = RgbdTestActivity$setRGBDListener$1.this.this$0.getOnOpen();
                if (onOpen != null) {
                    onOpen.invoke(Boolean.valueOf(p0));
                }
            }
        });
    }

    @Override // com.pudutech.mirsdk.hardware.IRgbdStatus
    public void onRGBDFrameTick() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3276v(str, "onRGBDFrameTick ");
    }

    @Override // com.pudutech.mirsdk.hardware.IRgbdStatus
    public void onParkingMode(boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3276v(str, "onParkingMode p0=" + p0);
    }
}
