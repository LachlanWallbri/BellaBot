package com.pudutech.bumblebee.presenter.general_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletInstallListener;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletInstallTask;
import com.pudutech.bumblebee.presenter.general_task.PalletContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: PalletPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/presenter/general_task/PalletPresenter$mListener$1", "Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletInstallListener;", "onInstallSatesChange", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PalletPresenter$mListener$1 implements PalletInstallListener {
    final /* synthetic */ PalletPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PalletPresenter$mListener$1(PalletPresenter palletPresenter) {
        this.this$0 = palletPresenter;
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletInstallListener
    public void onInstallSatesChange() {
        ArrayList arrayList;
        int i;
        ArrayList arrayList2;
        ArrayList arrayList3;
        int i2;
        HashMap<Integer, Boolean> installStates = PalletInstallTask.INSTANCE.getInstallStates();
        arrayList = this.this$0.mPallets;
        arrayList.clear();
        this.this$0.mVisibleNum = 0;
        for (Map.Entry<Integer, Boolean> entry : installStates.entrySet()) {
            arrayList3 = this.this$0.mPallets;
            arrayList3.add(new PalletContract.PalletModel(entry.getKey().intValue(), entry.getValue().booleanValue()));
            if (entry.getValue().booleanValue()) {
                PalletPresenter palletPresenter = this.this$0;
                i2 = palletPresenter.mVisibleNum;
                palletPresenter.mVisibleNum = i2 + 1;
            }
        }
        String tag = this.this$0.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("onInstallSatesChange mVisibleNum=");
        i = this.this$0.mVisibleNum;
        sb.append(i);
        sb.append(" mPallets=");
        arrayList2 = this.this$0.mPallets;
        sb.append(arrayList2);
        Pdlog.m3275i(tag, sb.toString());
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.general_task.PalletPresenter$mListener$1$onInstallSatesChange$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PalletContract.ViewInterface theView;
                int i3;
                ArrayList<PalletContract.PalletModel> arrayList4;
                theView = PalletPresenter$mListener$1.this.this$0.getTheView();
                if (theView != null) {
                    i3 = PalletPresenter$mListener$1.this.this$0.mVisibleNum;
                    arrayList4 = PalletPresenter$mListener$1.this.this$0.mPallets;
                    theView.showPallets(i3, arrayList4);
                }
            }
        });
    }
}
