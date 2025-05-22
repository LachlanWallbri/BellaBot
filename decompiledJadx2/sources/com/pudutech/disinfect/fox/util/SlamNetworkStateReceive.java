package com.pudutech.disinfect.fox.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.pudutech.bumblebee.robot_ui.util.NetStatusUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SlamNetworkStateReceive.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B*\u0012#\u0010\u0002\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\u001c\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R7\u0010\u0002\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/disinfect/fox/util/SlamNetworkStateReceive;", "Landroid/content/BroadcastReceiver;", "onEvent", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "b", "", "(Lkotlin/jvm/functions/Function1;)V", "getOnEvent", "()Lkotlin/jvm/functions/Function1;", "setOnEvent", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class SlamNetworkStateReceive extends BroadcastReceiver {
    private Function1<? super Boolean, Unit> onEvent;

    public SlamNetworkStateReceive(Function1<? super Boolean, Unit> function1) {
        this.onEvent = function1;
    }

    public final Function1<Boolean, Unit> getOnEvent() {
        return this.onEvent;
    }

    public final void setOnEvent(Function1<? super Boolean, Unit> function1) {
        this.onEvent = function1;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null) {
            return;
        }
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "android.net.conn.CONNECTIVITY_CHANGE")) {
            if (NetStatusUtil.isWifi(context)) {
                Function1<? super Boolean, Unit> function1 = this.onEvent;
                if (function1 != null) {
                    function1.invoke(true);
                    return;
                }
                return;
            }
            Function1<? super Boolean, Unit> function12 = this.onEvent;
            if (function12 != null) {
                function12.invoke(false);
            }
        }
    }
}
