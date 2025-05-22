package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener;
import kotlin.Metadata;

/* compiled from: HeyPuduWakeViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/HeyPuduWakeViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "mIVoiceReponseTextListener", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceReponseTextListener;", "wakeUpAngle", "Landroidx/lifecycle/MutableLiveData;", "", "getWakeUpAngle", "()Landroidx/lifecycle/MutableLiveData;", "startListening", "", "stopListening", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class HeyPuduWakeViewModel extends BaseViewModel {
    private final String TAG = "HeyPuduWakeViewModel";
    private final MutableLiveData<Double> wakeUpAngle = new MutableLiveData<>();
    private final IVoiceReponseTextListener mIVoiceReponseTextListener = new HeyPuduWakeViewModel$mIVoiceReponseTextListener$1(this);

    public final MutableLiveData<Double> getWakeUpAngle() {
        return this.wakeUpAngle;
    }

    public final void startListening() {
        AiVoiceManager.INSTANCE.attachNullActivity(this.mIVoiceReponseTextListener);
        AiVoiceManager.INSTANCE.setAiUiEnable(false);
        AiVoiceManager.INSTANCE.startAiRecording();
    }

    public final void stopListening() {
        AiVoiceManager.INSTANCE.detachNullActivity();
        AiVoiceManager.INSTANCE.setAiUiEnable(true);
        AiVoiceManager.INSTANCE.stopAiRecording();
    }
}
