package com.pudutech.robot.opensdk.bean.pub;

import androidx.core.app.NotificationCompat;
import com.amitshekhar.utils.Constants;
import com.pudutech.base.StringUtils;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import com.pudutech.robot.opensdk.interf.IPubMsg;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PubUnbindDeviceData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\t\u0010\u0017\u001a\u00020\u000fHÖ\u0001J\u0018\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u0016J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubUnbindDeviceData;", "Lcom/pudutech/robot/opensdk/interf/IPubMsg;", "deviceName", "", "(Ljava/lang/String;)V", "getDeviceName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getMsgType", "getQos", "", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "hashCode", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PubUnbindDeviceData implements IPubMsg {
    private final String deviceName;

    public PubUnbindDeviceData() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ PubUnbindDeviceData copy$default(PubUnbindDeviceData pubUnbindDeviceData, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pubUnbindDeviceData.deviceName;
        }
        return pubUnbindDeviceData.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    public final PubUnbindDeviceData copy(String deviceName) {
        return new PubUnbindDeviceData(deviceName);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof PubUnbindDeviceData) && Intrinsics.areEqual(this.deviceName, ((PubUnbindDeviceData) other).deviceName);
        }
        return true;
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
        return 0;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public long getTimeout() {
        return 5000L;
    }

    public int hashCode() {
        String str = this.deviceName;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public BaseMsgBean<?> parseObj(String msg) {
        return null;
    }

    public String toString() {
        return "PubUnbindDeviceData(deviceName=" + this.deviceName + ")";
    }

    public PubUnbindDeviceData(String str) {
        this.deviceName = str;
    }

    public /* synthetic */ PubUnbindDeviceData(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getMsgType() {
        if (StringUtils.isEmpty(this.deviceName)) {
            return Constant.INSTANCE.getMSG_TYPE_UNBIND_ALL$robot_open_sdk_release();
        }
        return Constant.INSTANCE.getMSG_TYPE_UNBIND$robot_open_sdk_release();
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return Constant.INSTANCE.unbindTopicTemplate(pk, dn);
    }
}
