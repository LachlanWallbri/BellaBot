package com.pudutech.peanut.robot_ui.viewmodel;

import com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: HeyPuduWakeViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\u0012\u0010\u0012\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0005H\u0016J\u001a\u0010\u0018\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0005H\u0016¨\u0006\u0019"}, m3961d2 = {"com/pudutech/peanut/robot_ui/viewmodel/HeyPuduWakeViewModel$mIVoiceReponseTextListener$1", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceReponseTextListener;", "dismissCountdown", "", "countdown", "", "finishVoice", "loading", "isShow", "", "palyCompelete", "state", "reWakeup", "requestText", "data", "", "isFinsh", "requestTimeout", "responseText", "showDialog", "random", "volumeChange", "volumeValue", ES6Iterator.VALUE_PROPERTY, "wakeup", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class HeyPuduWakeViewModel$mIVoiceReponseTextListener$1 implements IVoiceReponseTextListener {
    final /* synthetic */ HeyPuduWakeViewModel this$0;

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void dismissCountdown(int countdown) {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void finishVoice() {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void loading(boolean isShow) {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void palyCompelete(int state) {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void reWakeup(boolean isShow) {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void requestText(String data, boolean isFinsh) {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void requestTimeout() {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void responseText(String data) {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void showDialog(int random) {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void volumeChange(boolean isShow) {
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void volumeValue(int value) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HeyPuduWakeViewModel$mIVoiceReponseTextListener$1(HeyPuduWakeViewModel heyPuduWakeViewModel) {
        this.this$0 = heyPuduWakeViewModel;
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void wakeup(String data, int random) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new HeyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1(this, data, null), 2, null);
    }
}
