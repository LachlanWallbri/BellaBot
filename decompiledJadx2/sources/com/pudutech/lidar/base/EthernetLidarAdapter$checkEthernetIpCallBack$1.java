package com.pudutech.lidar.base;

import android.os.Handler;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.util.Tools;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: EthernetLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/lidar/base/EthernetLidarAdapter$checkEthernetIpCallBack$1", "Landroid/os/Handler$Callback;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class EthernetLidarAdapter$checkEthernetIpCallBack$1 implements Handler.Callback {
    final /* synthetic */ EthernetLidarAdapter this$0;

    EthernetLidarAdapter$checkEthernetIpCallBack$1(EthernetLidarAdapter ethernetLidarAdapter) {
        this.this$0 = ethernetLidarAdapter;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        if (msg == null) {
            return false;
        }
        if (msg.what == EthernetLidarAdapter.access$getRESET_ETHERNET_IP_MSG$p(this.this$0)) {
            String ethernetIp = EthernetLidarRootCmd.INSTANCE.getEthernetIp(EthernetLidarAdapter.access$getWiredNetworkPortNumber$p(this.this$0));
            Pdlog.m3273d(this.this$0.getTAG(), "checkEthernetIpHandler current ip = " + ethernetIp);
            if (StringsKt.isBlank(ethernetIp) || (!Intrinsics.areEqual(ethernetIp, EthernetLidarAdapter.access$getStaticIp$p(this.this$0)))) {
                Tools.rootCommand("ifconfig " + EthernetLidarAdapter.access$getWiredNetworkPortNumber$p(this.this$0) + ' ' + EthernetLidarAdapter.access$getStaticIp$p(this.this$0) + " netmask 255.255.255.0");
                this.this$0.getCheckEthernetIpHandler().sendEmptyMessageDelayed(EthernetLidarAdapter.access$getRESET_ETHERNET_IP_MSG$p(this.this$0), EthernetLidarAdapter.access$getRESET_ETHERNET_IP_TIME$p(this.this$0));
            }
        }
        return true;
    }
}
