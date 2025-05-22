package com.pudutech.voiceinteraction.component.listener;

import com.pudutech.voiceinteraction.component.cmd.CmdBean;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IVoiceInteractionListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0007H&J\u001a\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH&J$\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0010H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u000eH&J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0014H&¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/listener/IVoiceInteractionListener;", "", "offlineCmd", "", "data", "", "onCmdResponse", "Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "onResultRequest", "boolean", "", "onResultResponse", "json", "state", "", "onStatusChanged", "Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState;", "onVolumeChanged", "volume", "onWakeup", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface IVoiceInteractionListener {
    void offlineCmd(String data);

    void onCmdResponse(CmdBean data);

    void onResultRequest(String data, boolean r2);

    void onResultResponse(String data, String json, int state);

    void onStatusChanged(VoiceInteractionState state);

    void onVolumeChanged(int volume);

    void onWakeup(WakeupInfo data);
}
