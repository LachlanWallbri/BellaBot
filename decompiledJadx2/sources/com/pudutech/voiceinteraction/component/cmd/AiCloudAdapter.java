package com.pudutech.voiceinteraction.component.cmd;

import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiCloudAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/cmd/AiCloudAdapter;", "Lcom/pudutech/voiceinteraction/component/cmd/ICmdAdatper;", "", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$RasaAnswer;", "()V", "TAG", "", "transformationCmd", "Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "data", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class AiCloudAdapter implements ICmdAdatper<List<? extends OkHttpUtils.RasaAnswer>> {
    private final String TAG = "AiCloudAdapter";

    @Override // com.pudutech.voiceinteraction.component.cmd.ICmdAdatper
    public /* bridge */ /* synthetic */ CmdBean transformationCmd(List<? extends OkHttpUtils.RasaAnswer> list) {
        return transformationCmd2((List<OkHttpUtils.RasaAnswer>) list);
    }

    /* renamed from: transformationCmd, reason: avoid collision after fix types in other method */
    public CmdBean transformationCmd2(List<OkHttpUtils.RasaAnswer> data) {
        TriggerIntent triggerIntent;
        List<OkHttpUtils.RasaAnswer> list = data;
        if (list == null || list.isEmpty()) {
            Pdlog.m3273d(this.TAG, "transformationCmd data is null or empty");
            return null;
        }
        OkHttpUtils.RasaAnswer rasaAnswer = data.get(0);
        Pdlog.m3273d(this.TAG, "transformationCmd answerBean:" + rasaAnswer);
        if (!Intrinsics.areEqual(rasaAnswer.getAction_type(), "action")) {
            Pdlog.m3273d(this.TAG, "transformationCmd answerBean.actionType is not action :" + rasaAnswer.getAction_type());
            return null;
        }
        HashMap hashMap = new HashMap();
        OkHttpUtils.Action action = rasaAnswer.getAction();
        String action2 = action != null ? action.getAction() : null;
        if (Intrinsics.areEqual(action2, TriggerIntent.TaskInput.getAction())) {
            HashMap hashMap2 = hashMap;
            String pallet = rasaAnswer.getAction().getPallet();
            if (pallet == null) {
                pallet = "0";
            }
            hashMap2.put("tray", pallet);
            String place = rasaAnswer.getAction().getPlace();
            if (place == null) {
                place = "";
            }
            hashMap2.put("destination", place);
            triggerIntent = TriggerIntent.TaskInput;
        } else if (Intrinsics.areEqual(action2, TriggerIntent.guide.getAction())) {
            HashMap hashMap3 = hashMap;
            String place2 = rasaAnswer.getAction().getPlace();
            if (place2 == null) {
                place2 = "";
            }
            hashMap3.put("destination", place2);
            triggerIntent = TriggerIntent.guide;
        } else if (Intrinsics.areEqual(action2, TriggerIntent.MissionStart.getAction())) {
            triggerIntent = TriggerIntent.MissionStart;
        } else if (Intrinsics.areEqual(action2, TriggerIntent.MissionAccomplished.getAction())) {
            triggerIntent = TriggerIntent.MissionAccomplished;
        } else if (Intrinsics.areEqual(action2, TriggerIntent.LookAtMe.getAction())) {
            triggerIntent = TriggerIntent.LookAtMe;
        } else {
            Pdlog.m3273d(this.TAG, "transformationCmd There is no corresponding intentional action");
            triggerIntent = null;
        }
        if (triggerIntent != null) {
            return new CmdBean(triggerIntent, hashMap);
        }
        return null;
    }
}
