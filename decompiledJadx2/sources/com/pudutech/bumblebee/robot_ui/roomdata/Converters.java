package com.pudutech.bumblebee.robot_ui.roomdata;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Converters.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0014\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/roomdata/Converters;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "stringToTtsConfigData", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "name", "ttsConfigDataToString", "data", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class Converters {
    private final String TAG = getClass().getSimpleName();

    public final TtsVoiceHelper.TtsConfigData stringToTtsConfigData(String name) {
        Object obj;
        Pdlog.m3273d(this.TAG, "name:" + name);
        Iterator<T> it = TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(name, ((TtsVoiceHelper.TtsConfigData) obj).getName())) {
                break;
            }
        }
        return (TtsVoiceHelper.TtsConfigData) obj;
    }

    public final String ttsConfigDataToString(TtsVoiceHelper.TtsConfigData data) {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("data:");
        sb.append(data != null ? data.toString() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (data != null) {
            return data.getName();
        }
        return null;
    }
}
