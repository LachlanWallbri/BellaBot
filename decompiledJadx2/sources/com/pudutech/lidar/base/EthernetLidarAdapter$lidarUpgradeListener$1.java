package com.pudutech.lidar.base;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarUpgradeListener;
import com.pudutech.lidar.util.Tools;
import kotlin.Metadata;

/* compiled from: EthernetLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\t"}, m3961d2 = {"com/pudutech/lidar/base/EthernetLidarAdapter$lidarUpgradeListener$1", "Lcom/pudutech/lidar/LidarUpgradeListener;", "onLidarUpgradeComplete", "", "isComplete", "", "onLidarUpgradeResult", "isSuccess", "onLidarUpgradeStart", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class EthernetLidarAdapter$lidarUpgradeListener$1 implements LidarUpgradeListener {
    final /* synthetic */ EthernetLidarAdapter this$0;

    EthernetLidarAdapter$lidarUpgradeListener$1(EthernetLidarAdapter ethernetLidarAdapter) {
        this.this$0 = ethernetLidarAdapter;
    }

    @Override // com.pudutech.lidar.LidarUpgradeListener
    public void onLidarUpgradeStart() {
        Pdlog.m3273d("EchoxUpgrade", "onLidarUpgradeStart");
    }

    @Override // com.pudutech.lidar.LidarUpgradeListener
    public void onLidarUpgradeComplete(boolean isComplete) {
        this.this$0.getLidar().isDifopInfoGetComplete().set(false);
        EthernetLidarAdapter.access$isPointCloudReceived$p(this.this$0).set(false);
        while (isComplete && !EthernetLidarAdapter.access$isPointCloudReceived$p(this.this$0).get()) {
            Log.d("EchoxUpgrade", "ifconfig " + EthernetLidarAdapter.access$getWiredNetworkPortNumber$p(this.this$0) + ' ' + this.this$0.getLidar().getEth0IP() + ". result " + Tools.rootCommand("ifconfig " + EthernetLidarAdapter.access$getWiredNetworkPortNumber$p(this.this$0) + ' ' + this.this$0.getLidar().getEth0IP() + " netmask 255.255.255.0"));
            Thread.sleep(1000L);
        }
        EthernetLidarAdapter.access$receiveStatusInfo(this.this$0);
    }

    @Override // com.pudutech.lidar.LidarUpgradeListener
    public void onLidarUpgradeResult(boolean isSuccess) {
        this.this$0.getLidar().isDifopInfoGetComplete().set(true);
        Pdlog.m3273d("EchoxUpgrade", "onLidarUpgradeSuccess: " + isSuccess);
    }
}
