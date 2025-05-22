package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.utils.SystemTool;
import com.pudutech.mirsdk.hardware.ICANData;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

/* compiled from: DisinfectionTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/factory_test/single_test/DisinfectionTestActivity$setListener$3", "Lcom/pudutech/mirsdk/hardware/ICANData$Stub;", "onData", "", "p0", "", "p1", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class DisinfectionTestActivity$setListener$3 extends ICANData.Stub {
    final /* synthetic */ DisinfectionTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DisinfectionTestActivity$setListener$3(DisinfectionTestActivity disinfectionTestActivity) {
        this.this$0 = disinfectionTestActivity;
    }

    @Override // com.pudutech.mirsdk.hardware.ICANData
    public void onData(int p0, byte[] p1) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("p0:");
        sb.append(Util.toHexString(p0));
        sb.append(",p1:");
        SystemTool systemTool = SystemTool.INSTANCE;
        if (p1 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(systemTool.toHexString(p1));
        objArr[0] = sb.toString();
        Pdlog.m3276v(str, objArr);
        byte[] copyOf = Arrays.copyOf(p1, p1.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(copyOf);
        if (p0 == -121) {
            str4 = this.this$0.TAG;
            Pdlog.m3273d(str4, "bytes[2].toInt() " + (UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255));
            if ((UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255) == 6) {
                str5 = this.this$0.TAG;
                Pdlog.m3273d(str5, "0x87u_bytes[2].toInt() == 6,current1 " + (((UByteArray.m4577getimpl(m4572constructorimpl, 4) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255)));
                this.this$0.setCurrent1(((UByteArray.m4577getimpl(m4572constructorimpl, 4) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255));
                this.this$0.setCurrent2(((UByteArray.m4577getimpl(m4572constructorimpl, 6) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 5) & 255));
            }
            if ((UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255) == 7) {
                this.this$0.setCurrent3(((UByteArray.m4577getimpl(m4572constructorimpl, 4) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255));
                this.this$0.setCurrent4((UByteArray.m4577getimpl(m4572constructorimpl, 5) & 255) | ((UByteArray.m4577getimpl(m4572constructorimpl, 6) & 255) << 8));
            }
        }
        str2 = this.this$0.TAG;
        Pdlog.m3276v(str2, "p0:" + p0 + ",byte1:" + UInt.m4632toStringimpl(UInt.m4595constructorimpl(UByteArray.m4577getimpl(m4572constructorimpl, 1) & 255)) + ",byte2:" + UInt.m4632toStringimpl(UInt.m4595constructorimpl(UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255)));
        if (p0 == -119 && (UByteArray.m4577getimpl(m4572constructorimpl, 1) & 255) == 9 && (UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255) == 10) {
            this.this$0.setFanSpeed((UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255) | ((UByteArray.m4577getimpl(m4572constructorimpl, 4) & 255) << 8));
            str3 = this.this$0.TAG;
            Pdlog.m3276v(str3, "fanSpeed: " + this.this$0.getFanSpeed());
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$setListener$3$onData$1
            @Override // java.lang.Runnable
            public final void run() {
                DisinfectionTestActivity$setListener$3.this.this$0.getKeyInfo().put("雾化片电流1", String.valueOf(DisinfectionTestActivity$setListener$3.this.this$0.getCurrent1()));
                DisinfectionTestActivity$setListener$3.this.this$0.getKeyInfo().put("雾化片电流2", String.valueOf(DisinfectionTestActivity$setListener$3.this.this$0.getCurrent2()));
                DisinfectionTestActivity$setListener$3.this.this$0.getKeyInfo().put("雾化片电流3", String.valueOf(DisinfectionTestActivity$setListener$3.this.this$0.getCurrent3()));
                DisinfectionTestActivity$setListener$3.this.this$0.getKeyInfo().put("雾化片电流4", String.valueOf(DisinfectionTestActivity$setListener$3.this.this$0.getCurrent4()));
                DisinfectionTestActivity$setListener$3.this.this$0.getKeyInfo().put("风机转速", String.valueOf(DisinfectionTestActivity$setListener$3.this.this$0.getFanSpeed()));
                DisinfectionTestActivity$setListener$3.this.this$0.showKeyInfo(DisinfectionTestActivity$setListener$3.this.this$0.toStr(DisinfectionTestActivity$setListener$3.this.this$0.getKeyInfo()));
            }
        });
    }
}
