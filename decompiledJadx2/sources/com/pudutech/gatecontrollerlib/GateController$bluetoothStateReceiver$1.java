package com.pudutech.gatecontrollerlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.pudutech.base.Pdlog;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: GateController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/gatecontrollerlib/GateController$bluetoothStateReceiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GateController$bluetoothStateReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ GateController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GateController$bluetoothStateReceiver$1(GateController gateController) {
        this.this$0 = gateController;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        String str2;
        String str3;
        Set set;
        String str4;
        if (intent == null || !Intrinsics.areEqual(intent.getAction(), "android.bluetooth.adapter.action.STATE_CHANGED")) {
            return;
        }
        switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)) {
            case 10:
                str = this.this$0.TAG;
                Pdlog.m3273d(str, "bluetooth off");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C4593x4eac4680(null, this, intent), 3, null);
                return;
            case 11:
                str2 = this.this$0.TAG;
                Pdlog.m3273d(str2, "bluetooth turning on");
                return;
            case 12:
                str3 = this.this$0.TAG;
                Pdlog.m3273d(str3, "bluetooth on");
                set = this.this$0.waitConnectSet;
                if (!set.isEmpty()) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C4592x4eac467f(null, this, intent), 3, null);
                    return;
                }
                return;
            case 13:
                str4 = this.this$0.TAG;
                Pdlog.m3273d(str4, "bluetooth turning off");
                return;
            default:
                return;
        }
    }
}
