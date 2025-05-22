package com.pudutech.bumblebee.presenter.delivery_task;

import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import kotlin.Metadata;

/* compiled from: DeliveryTaskSettingModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryTaskSettingModel;", "", "()V", "packageHelper", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageHelper;", "getCurrentVoiceName", "", "haveTableGroup", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliveryTaskSettingModel {
    private VoicePackageHelper packageHelper = new VoicePackageHelper();

    public final String getCurrentVoiceName() {
        return this.packageHelper.getCurrentVoiceName();
    }

    public final boolean haveTableGroup() {
        return RobotMapManager.INSTANCE.getAllTableGroup().size() > 0;
    }
}
