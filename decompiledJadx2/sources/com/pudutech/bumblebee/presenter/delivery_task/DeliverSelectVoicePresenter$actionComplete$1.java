package com.pudutech.bumblebee.presenter.delivery_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.delivery_task.DeliverSelectVoiceContract;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: DeliverSelectVoicePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class DeliverSelectVoicePresenter$actionComplete$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ long $select;
    final /* synthetic */ DeliverSelectVoicePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliverSelectVoicePresenter$actionComplete$1(DeliverSelectVoicePresenter deliverSelectVoicePresenter, long j) {
        super(0);
        this.this$0 = deliverSelectVoicePresenter;
        this.$select = j;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        List<VoicePackageInfo> list;
        list = this.this$0.voicePkgInfoList;
        for (final VoicePackageInfo voicePackageInfo : list) {
            Pdlog.m3273d(this.this$0.getTAG(), "actionComplete it id = " + voicePackageInfo.getId() + " , select = " + this.$select);
            if (this.$select == voicePackageInfo.getId()) {
                this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliverSelectVoicePresenter$actionComplete$1$$special$$inlined$forEach$lambda$1
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
                        DeliverSelectVoiceContract.ViewInterface theView;
                        theView = this.this$0.getTheView();
                        if (theView != null) {
                            theView.complete(VoicePackageInfo.this);
                        }
                    }
                });
            }
        }
    }
}
