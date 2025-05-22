package com.pudutech.bumblebee.robot.nfc;

import android.content.Context;
import com.iflytek.cloud.SpeechEvent;
import com.pudutech.base.Pdlog;
import com.pudutech.recyclebot.robot.nfc.RFIDReader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: NFC.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/nfc/NFC;", "Lcom/pudutech/recyclebot/robot/nfc/RFIDReader$OnUIDListener;", "()V", "TAG", "", "listener", "Lcom/pudutech/bumblebee/robot/nfc/NFCListener;", "reader", "Lcom/pudutech/recyclebot/robot/nfc/RFIDReader;", "onSwipe", "", SpeechEvent.KEY_EVENT_TTS_BUFFER, "", "p1", "", "open", "context", "Landroid/content/Context;", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class NFC implements RFIDReader.OnUIDListener {
    private NFCListener listener;
    private final String TAG = "NFC";
    private final RFIDReader reader = new RFIDReader();

    @Override // com.pudutech.recyclebot.robot.nfc.RFIDReader.OnUIDListener
    public void onSwipe(byte[] buffer, int p1) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        Pdlog.m3273d(this.TAG, "onSwipe len=" + p1);
        if (p1 < 4) {
            Pdlog.m3277w(this.TAG, "illegal len. no parse.");
            return;
        }
        long j = (buffer[3] & 255) | ((buffer[1] & 255) << 16) | ((buffer[0] & 255) << 24) | ((buffer[2] & 255) << 8);
        NFCListener nFCListener = this.listener;
        if (nFCListener != null) {
            nFCListener.onSignDetected(String.valueOf(j));
        }
    }

    public final void open(Context context, NFCListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listener = listener;
        this.reader.open(context, this);
    }
}
