package com.pudutech.bumblebee.robot_ui.manager;

import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.bean.LoraReceiveCall;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;

/* compiled from: LoRaManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, m3961d2 = {"addrHexStr", "", "Lcom/pudu/library/loracall/bean/LoraReceiveCall;", "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LoRaManagerKt {
    public static final /* synthetic */ String access$addrHexStr(LoraReceiveCall loraReceiveCall) {
        return addrHexStr(loraReceiveCall);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String addrHexStr(LoraReceiveCall loraReceiveCall) {
        return KotlinUtilsKt.toHexString(ArraysKt.reversedArray(loraReceiveCall.getDevAddr()));
    }
}
