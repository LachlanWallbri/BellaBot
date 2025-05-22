package com.pudutech.voiceinteraction.component.listener;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: IVoiceReponseTextListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u001a\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\tH&J\b\u0010\u0011\u001a\u00020\u0003H&J\u0012\u0010\u0012\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0005H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0005H&J\u001a\u0010\u0018\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0005H&¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/listener/IVoiceReponseTextListener;", "", "dismissCountdown", "", "countdown", "", "finishVoice", "loading", "isShow", "", "palyCompelete", "state", "reWakeup", "requestText", "data", "", "isFinsh", "requestTimeout", "responseText", "showDialog", "random", "volumeChange", "volumeValue", ES6Iterator.VALUE_PROPERTY, "wakeup", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IVoiceReponseTextListener {
    void dismissCountdown(int countdown);

    void finishVoice();

    void loading(boolean isShow);

    void palyCompelete(int state);

    void reWakeup(boolean isShow);

    void requestText(String data, boolean isFinsh);

    void requestTimeout();

    void responseText(String data);

    void showDialog(int random);

    void volumeChange(boolean isShow);

    void volumeValue(int value);

    void wakeup(String data, int random);
}
