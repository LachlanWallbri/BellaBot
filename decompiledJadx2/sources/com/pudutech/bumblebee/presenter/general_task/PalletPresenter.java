package com.pudutech.bumblebee.presenter.general_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletInstallTask;
import com.pudutech.bumblebee.presenter.general_task.PalletContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: PalletPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000I\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u000e\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0015H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/general_task/PalletPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "checkSupportManualConfig", "", "getCheckSupportManualConfig", "()Z", "mListener", "com/pudutech/bumblebee/presenter/general_task/PalletPresenter$mListener$1", "Lcom/pudutech/bumblebee/presenter/general_task/PalletPresenter$mListener$1;", "mPallets", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$PalletModel;", "Lkotlin/collections/ArrayList;", "mVisibleNum", "", "pallets", "getPallets", "()Ljava/util/ArrayList;", "visibleNum", "getVisibleNum", "()I", "setPalletNum", "", "num", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PalletPresenter extends BaseOneViewPresenter<PalletContract.ViewInterface> implements PalletContract.PresenterInterface {
    private int mVisibleNum;
    private final String TAG = "PalletPresenter";
    private ArrayList<PalletContract.PalletModel> mPallets = new ArrayList<>();
    private PalletPresenter$mListener$1 mListener = new PalletPresenter$mListener$1(this);

    public PalletPresenter() {
        HashMap<Integer, Boolean> installStates = PalletInstallTask.INSTANCE.getInstallStates();
        this.mPallets.clear();
        this.mVisibleNum = 0;
        for (Map.Entry<Integer, Boolean> entry : installStates.entrySet()) {
            this.mPallets.add(new PalletContract.PalletModel(entry.getKey().intValue(), entry.getValue().booleanValue()));
            if (entry.getValue().booleanValue()) {
                this.mVisibleNum++;
            }
        }
        Peripherals.INSTANCE.getPalletInstallTask().addListener(this.mListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.PalletContract.PresenterInterface
    public boolean getCheckSupportManualConfig() {
        return MachineInfoHelper.INSTANCE.getRobotType() == MachineModel.Hls;
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.PalletContract.PresenterInterface
    /* renamed from: getVisibleNum, reason: from getter */
    public int getMVisibleNum() {
        return this.mVisibleNum;
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.PalletContract.PresenterInterface
    public ArrayList<PalletContract.PalletModel> getPallets() {
        return this.mPallets;
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.PalletContract.PresenterInterface
    public void setPalletNum(final int num) {
        Pdlog.m3273d(getTAG(), "setPalletNum " + num);
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.general_task.PalletPresenter$setPalletNum$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                HashMap<Integer, Boolean> installStates = PalletInstallTask.INSTANCE.getInstallStates();
                for (Map.Entry<Integer, Boolean> entry : installStates.entrySet()) {
                    Integer key = entry.getKey();
                    boolean z = true;
                    if (num + 1 <= entry.getKey().intValue()) {
                        z = false;
                    }
                    installStates.put(key, Boolean.valueOf(z));
                }
            }
        });
    }
}
