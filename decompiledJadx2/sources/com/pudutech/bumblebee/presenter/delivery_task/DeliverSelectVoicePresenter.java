package com.pudutech.bumblebee.presenter.delivery_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.delivery_task.DeliverSelectVoiceContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: DeliverSelectVoicePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0010\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSelectVoicePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSelectVoiceContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSelectVoiceContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "voicePkgInfoList", "", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "actionComplete", "", "select", "", "actionTransform", "data", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliverSelectVoicePresenter extends BaseOneViewPresenter<DeliverSelectVoiceContract.ViewInterface> implements DeliverSelectVoiceContract.PresenterInterface {
    private final String TAG = "DeliverSelectVoicePresenter";
    private final List<VoicePackageInfo> voicePkgInfoList = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliverSelectVoiceContract.PresenterInterface
    public void actionTransform(final List<VoicePackageInfo> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        final ArrayList arrayList = new ArrayList();
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = -1L;
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliverSelectVoicePresenter$actionTransform$1
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
                List list;
                List list2;
                list = DeliverSelectVoicePresenter.this.voicePkgInfoList;
                list.clear();
                list2 = DeliverSelectVoicePresenter.this.voicePkgInfoList;
                list2.addAll(data);
                for (VoicePackageInfo voicePackageInfo : data) {
                    Pdlog.m3273d(DeliverSelectVoicePresenter.this.getTAG(), "actionTransform it id = " + voicePackageInfo.getId() + ",isExist = " + voicePackageInfo.getIsExist() + ",selected = " + voicePackageInfo.getSelected());
                    if (voicePackageInfo.getIsExist()) {
                        if (voicePackageInfo.getSelected()) {
                            longRef.element = voicePackageInfo.getId();
                        }
                        arrayList.add(new DeliverSelectVoiceContract.SelectVoiceInfo(voicePackageInfo.getName(), voicePackageInfo.getSelected(), voicePackageInfo.getId()));
                    }
                }
                DeliverSelectVoicePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliverSelectVoicePresenter$actionTransform$1.2
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
                        DeliverSelectVoiceContract.ViewInterface theView;
                        theView = DeliverSelectVoicePresenter.this.getTheView();
                        if (theView != null) {
                            theView.showList(arrayList, longRef.element);
                        }
                    }
                });
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliverSelectVoiceContract.PresenterInterface
    public void actionComplete(long select) {
        runOnBusinessThread(new DeliverSelectVoicePresenter$actionComplete$1(this, select));
    }
}
