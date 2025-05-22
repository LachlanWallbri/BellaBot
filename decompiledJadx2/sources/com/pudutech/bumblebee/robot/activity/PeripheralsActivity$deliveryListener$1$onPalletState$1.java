package com.pudutech.bumblebee.robot.activity;

import com.pudutech.bumblebee.robot.activity.PeripheralsActivity;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final class PeripheralsActivity$deliveryListener$1$onPalletState$1 implements Runnable {
    final /* synthetic */ List $pallets;
    final /* synthetic */ PeripheralsActivity$deliveryListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeripheralsActivity$deliveryListener$1$onPalletState$1(PeripheralsActivity$deliveryListener$1 peripheralsActivity$deliveryListener$1, List list) {
        this.this$0 = peripheralsActivity$deliveryListener$1;
        this.$pallets = list;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List<Pallet> list = this.$pallets;
        if (list != null) {
            for (Pallet pallet : list) {
                PeripheralsActivity.PalletView palletView = this.this$0.this$0.getPalletViews().get(Integer.valueOf(pallet.getPalletId()));
                if (palletView == null) {
                    palletView = this.this$0.this$0.createNewView(pallet);
                }
                Intrinsics.checkExpressionValueIsNotNull(palletView, "palletViews[it.palletId] ?: it.createNewView()");
                String str = pallet.getIsInstalled() ? "托盘已安装" : "托盘未安装";
                String str2 = pallet.getIsPowerOn() ? "电源打开（反馈）" : "电源关闭（反馈）";
                String str3 = pallet.getIsPlaced() ? "检测到有物体" : "没有物体";
                palletView.getInfo().setText((((str + "\n") + str2) + "\n") + str3);
            }
        }
    }
}
