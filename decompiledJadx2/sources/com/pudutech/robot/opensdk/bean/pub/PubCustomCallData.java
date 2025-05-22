package com.pudutech.robot.opensdk.bean.pub;

import androidx.core.app.NotificationCompat;
import com.amitshekhar.utils.Constants;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PubCustomCallData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J3\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\b\u0010\u0016\u001a\u00020\u0003H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0003H\u0016J\b\u0010\u001c\u001a\u00020\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0003H\u0016J\t\u0010 \u001a\u00020\u0018HÖ\u0001J\u0018\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\u0003H\u0016J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006%"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubCustomCallData;", "Lcom/pudutech/robot/opensdk/bean/pub/BaseNotifyPub;", "state", "", MqttServiceConstants.DESTINATION_NAME, "operationType", "taskId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDestinationName", "()Ljava/lang/String;", "getOperationType", "getState", "getTaskId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "getMsgType", "getQos", "", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "hashCode", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PubCustomCallData extends BaseNotifyPub {
    private final String destinationName;
    private final String operationType;
    private final String state;
    private final String taskId;

    public static /* synthetic */ PubCustomCallData copy$default(PubCustomCallData pubCustomCallData, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pubCustomCallData.state;
        }
        if ((i & 2) != 0) {
            str2 = pubCustomCallData.destinationName;
        }
        if ((i & 4) != 0) {
            str3 = pubCustomCallData.operationType;
        }
        if ((i & 8) != 0) {
            str4 = pubCustomCallData.taskId;
        }
        return pubCustomCallData.copy(str, str2, str3, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getState() {
        return this.state;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDestinationName() {
        return this.destinationName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getOperationType() {
        return this.operationType;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTaskId() {
        return this.taskId;
    }

    public final PubCustomCallData copy(String state, String destinationName, String operationType, String taskId) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(destinationName, "destinationName");
        Intrinsics.checkParameterIsNotNull(taskId, "taskId");
        return new PubCustomCallData(state, destinationName, operationType, taskId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PubCustomCallData)) {
            return false;
        }
        PubCustomCallData pubCustomCallData = (PubCustomCallData) other;
        return Intrinsics.areEqual(this.state, pubCustomCallData.state) && Intrinsics.areEqual(this.destinationName, pubCustomCallData.destinationName) && Intrinsics.areEqual(this.operationType, pubCustomCallData.operationType) && Intrinsics.areEqual(this.taskId, pubCustomCallData.taskId);
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
        String str = this.state;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.destinationName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.operationType;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.taskId;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public BaseMsgBean<?> parseObj(String msg) {
        return null;
    }

    public String toString() {
        return "PubCustomCallData(state=" + this.state + ", destinationName=" + this.destinationName + ", operationType=" + this.operationType + ", taskId=" + this.taskId + ")";
    }

    public final String getState() {
        return this.state;
    }

    public final String getDestinationName() {
        return this.destinationName;
    }

    public final String getOperationType() {
        return this.operationType;
    }

    public final String getTaskId() {
        return this.taskId;
    }

    public PubCustomCallData(String state, String destinationName, String str, String taskId) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(destinationName, "destinationName");
        Intrinsics.checkParameterIsNotNull(taskId, "taskId");
        this.state = state;
        this.destinationName = destinationName;
        this.operationType = str;
        this.taskId = taskId;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getMsgType() {
        return Constant.INSTANCE.getMSG_TYPE_NOTIFY_CUSTOM_CALL$robot_open_sdk_release();
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return Constant.INSTANCE.pubSdkTopicTemplate(pk, dn);
    }
}
