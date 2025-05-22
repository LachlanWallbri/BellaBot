package com.pudutech.robot.opensdk.bean.pub;

import androidx.core.app.NotificationCompat;
import com.amitshekhar.utils.Constants;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import com.pudutech.robot.opensdk.bean.Destination;
import com.pudutech.robot.opensdk.interf.IPubMsg;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PubRobotGoStateData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\b\u0010\u001a\u001a\u00020\u0003H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003H\u0016J\b\u0010 \u001a\u00020\u001cH\u0016J\b\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003H\u0016J\t\u0010$\u001a\u00020\u001cHÖ\u0001J\u0018\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010\u0003H\u0016J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006)"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubRobotGoStateData;", "Lcom/pudutech/robot/opensdk/interf/IPubMsg;", "role", "", "robotGoState", "destination", "Lcom/pudutech/robot/opensdk/bean/Destination;", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/robot/opensdk/bean/Destination;)V", "getDestination", "()Lcom/pudutech/robot/opensdk/bean/Destination;", "setDestination", "(Lcom/pudutech/robot/opensdk/bean/Destination;)V", "getRobotGoState", "()Ljava/lang/String;", "setRobotGoState", "(Ljava/lang/String;)V", "getRole", "setRole", "component1", "component2", "component3", "copy", "equals", "", "other", "", "getMsgType", "getQos", "", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "hashCode", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PubRobotGoStateData implements IPubMsg {
    private Destination destination;
    private String robotGoState;
    private String role;

    public static /* synthetic */ PubRobotGoStateData copy$default(PubRobotGoStateData pubRobotGoStateData, String str, String str2, Destination destination, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pubRobotGoStateData.role;
        }
        if ((i & 2) != 0) {
            str2 = pubRobotGoStateData.robotGoState;
        }
        if ((i & 4) != 0) {
            destination = pubRobotGoStateData.destination;
        }
        return pubRobotGoStateData.copy(str, str2, destination);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* renamed from: component2, reason: from getter */
    public final String getRobotGoState() {
        return this.robotGoState;
    }

    /* renamed from: component3, reason: from getter */
    public final Destination getDestination() {
        return this.destination;
    }

    public final PubRobotGoStateData copy(String role, String robotGoState, Destination destination) {
        Intrinsics.checkParameterIsNotNull(role, "role");
        Intrinsics.checkParameterIsNotNull(robotGoState, "robotGoState");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        return new PubRobotGoStateData(role, robotGoState, destination);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PubRobotGoStateData)) {
            return false;
        }
        PubRobotGoStateData pubRobotGoStateData = (PubRobotGoStateData) other;
        return Intrinsics.areEqual(this.role, pubRobotGoStateData.role) && Intrinsics.areEqual(this.robotGoState, pubRobotGoStateData.robotGoState) && Intrinsics.areEqual(this.destination, pubRobotGoStateData.destination);
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
        String str = this.role;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.robotGoState;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Destination destination = this.destination;
        return hashCode2 + (destination != null ? destination.hashCode() : 0);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public BaseMsgBean<?> parseObj(String msg) {
        return null;
    }

    public String toString() {
        return "PubRobotGoStateData(role=" + this.role + ", robotGoState=" + this.robotGoState + ", destination=" + this.destination + ")";
    }

    public PubRobotGoStateData(String role, String robotGoState, Destination destination) {
        Intrinsics.checkParameterIsNotNull(role, "role");
        Intrinsics.checkParameterIsNotNull(robotGoState, "robotGoState");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        this.role = role;
        this.robotGoState = robotGoState;
        this.destination = destination;
    }

    public final String getRole() {
        return this.role;
    }

    public final void setRole(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.role = str;
    }

    public final String getRobotGoState() {
        return this.robotGoState;
    }

    public final void setRobotGoState(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.robotGoState = str;
    }

    public final Destination getDestination() {
        return this.destination;
    }

    public final void setDestination(Destination destination) {
        Intrinsics.checkParameterIsNotNull(destination, "<set-?>");
        this.destination = destination;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getMsgType() {
        return Constant.INSTANCE.getMSG_TYPE_NOTIFY_GO_STATE$robot_open_sdk_release();
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        if (Intrinsics.areEqual(this.role, Constant.INSTANCE.getROLE_SDK())) {
            return Constant.INSTANCE.pubSdkTopicTemplate(pk, dn);
        }
        return Intrinsics.areEqual(this.role, Constant.INSTANCE.getROLE_BEEPER()) ? Constant.INSTANCE.pubBeeperTopicTemplate(pk, dn) : "";
    }
}
