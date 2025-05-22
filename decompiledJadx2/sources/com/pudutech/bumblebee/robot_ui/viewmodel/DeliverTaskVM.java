package com.pudutech.bumblebee.robot_ui.viewmodel;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: DeliverTaskVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\u000bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/DeliverTaskVM;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "getPlacePalletList", "", "", "getTrayDestinationWarnSwitch", "", "onCleared", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliverTaskVM extends BaseViewModel {
    private final String TAG = "DeliverTaskVM";

    public DeliverTaskVM() {
        Peripherals.INSTANCE.getPallet().requestPalletState();
    }

    public final List<Integer> getPlacePalletList() {
        ArrayList arrayList = new ArrayList();
        List<Pallet> lastPalletState = Peripherals.INSTANCE.getPallet().getLastPalletState();
        if (lastPalletState != null) {
            for (Pallet pallet : lastPalletState) {
                Pdlog.m3273d(this.TAG, "getPlacePalletList: " + pallet);
                if (pallet.getIsPowerOn() && pallet.getIsInstalled() && pallet.getIsPlaced()) {
                    arrayList.add(Integer.valueOf(pallet.getPalletId()));
                }
            }
        }
        return arrayList;
    }

    public final boolean getTrayDestinationWarnSwitch() {
        return Constans.INSTANCE.getTrayDestinationWarn();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
    }
}
