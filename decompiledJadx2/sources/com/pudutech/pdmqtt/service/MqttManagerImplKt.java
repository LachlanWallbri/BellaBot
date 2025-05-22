package com.pudutech.pdmqtt.service;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.pdmqtt.MqttLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttManagerImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003¨\u0006\u0005"}, m3961d2 = {"remoteLog", "", AIUIConstant.KEY_TAG, "", NotificationCompat.CATEGORY_MESSAGE, "pdmqtt_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MqttManagerImplKt {
    public static final void remoteLog(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (MqttManagerImpl.INSTANCE.getLog() == null) {
            Log.i(tag, msg);
            return;
        }
        try {
            MqttLogger log = MqttManagerImpl.INSTANCE.getLog();
            if (log != null) {
                log.log(tag, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
