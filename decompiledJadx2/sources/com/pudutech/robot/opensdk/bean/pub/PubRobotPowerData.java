package com.pudutech.robot.opensdk.bean.pub;

import androidx.core.app.NotificationCompat;
import com.amitshekhar.utils.Constants;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PubRobotPowerData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\b\u0010\u0012\u001a\u00020\u0005H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\b\u0010\u0017\u001a\u00020\u0003H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\u0018\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u0016J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006 "}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubRobotPowerData;", "Lcom/pudutech/robot/opensdk/bean/pub/BaseNotifyPub;", "power", "", "chargeStage", "", "(ILjava/lang/String;)V", "getChargeStage", "()Ljava/lang/String;", "getPower", "()I", "component1", "component2", "copy", "equals", "", "other", "", "getMsgType", "getQos", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "hashCode", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PubRobotPowerData extends BaseNotifyPub {
    private final String chargeStage;
    private final int power;

    public static /* synthetic */ PubRobotPowerData copy$default(PubRobotPowerData pubRobotPowerData, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = pubRobotPowerData.power;
        }
        if ((i2 & 2) != 0) {
            str = pubRobotPowerData.chargeStage;
        }
        return pubRobotPowerData.copy(i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getPower() {
        return this.power;
    }

    /* renamed from: component2, reason: from getter */
    public final String getChargeStage() {
        return this.chargeStage;
    }

    public final PubRobotPowerData copy(int power, String chargeStage) {
        Intrinsics.checkParameterIsNotNull(chargeStage, "chargeStage");
        return new PubRobotPowerData(power, chargeStage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PubRobotPowerData)) {
            return false;
        }
        PubRobotPowerData pubRobotPowerData = (PubRobotPowerData) other;
        return this.power == pubRobotPowerData.power && Intrinsics.areEqual(this.chargeStage, pubRobotPowerData.chargeStage);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public int getQos() {
        return 0;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getReplyTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return "";
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public int getRetryCount() {
        return 3;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public long getTimeout() {
        return 5000L;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.power) * 31;
        String str = this.chargeStage;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public BaseMsgBean<?> parseObj(String msg) {
        return null;
    }

    public String toString() {
        return "PubRobotPowerData(power=" + this.power + ", chargeStage=" + this.chargeStage + ")";
    }

    public PubRobotPowerData(int i, String chargeStage) {
        Intrinsics.checkParameterIsNotNull(chargeStage, "chargeStage");
        this.power = i;
        this.chargeStage = chargeStage;
    }

    public final String getChargeStage() {
        return this.chargeStage;
    }

    public final int getPower() {
        return this.power;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getMsgType() {
        return Constant.INSTANCE.getMSG_TYPE_NOTIFY_ROBOT_POWER$robot_open_sdk_release();
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return Constant.INSTANCE.pubSdkTopicTemplate(pk, dn);
    }
}
