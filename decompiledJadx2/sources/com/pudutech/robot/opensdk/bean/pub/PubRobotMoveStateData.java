package com.pudutech.robot.opensdk.bean.pub;

import androidx.core.app.NotificationCompat;
import com.amitshekhar.utils.Constants;
import com.pudutech.bumblebee.presenter.robot_open_task.config.MqttConfig;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PubRobotMoveStateData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0003J1\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H\u0016J\t\u0010\u001e\u001a\u00020\u0016HÖ\u0001J\u0018\u0010\u001f\u001a\b\u0012\u0002\b\u0003\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u0003H\u0016J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R%\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006#"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubRobotMoveStateData;", "Lcom/pudutech/robot/opensdk/bean/pub/BaseNotifyPub;", "state", "", "errors", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/bean/pub/RobotError;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/util/ArrayList;)V", "getErrors", "()Ljava/util/ArrayList;", "getState", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "getMsgType", "getQos", "", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "hashCode", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PubRobotMoveStateData extends BaseNotifyPub {
    private final ArrayList<RobotError> errors;
    private final String state;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PubRobotMoveStateData copy$default(PubRobotMoveStateData pubRobotMoveStateData, String str, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pubRobotMoveStateData.state;
        }
        if ((i & 2) != 0) {
            arrayList = pubRobotMoveStateData.errors;
        }
        return pubRobotMoveStateData.copy(str, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final String getState() {
        return this.state;
    }

    public final ArrayList<RobotError> component2() {
        return this.errors;
    }

    public final PubRobotMoveStateData copy(String state, ArrayList<RobotError> errors) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        return new PubRobotMoveStateData(state, errors);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PubRobotMoveStateData)) {
            return false;
        }
        PubRobotMoveStateData pubRobotMoveStateData = (PubRobotMoveStateData) other;
        return Intrinsics.areEqual(this.state, pubRobotMoveStateData.state) && Intrinsics.areEqual(this.errors, pubRobotMoveStateData.errors);
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
        return MqttConfig.POINT_IS_EMPTY;
    }

    public int hashCode() {
        String str = this.state;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ArrayList<RobotError> arrayList = this.errors;
        return hashCode + (arrayList != null ? arrayList.hashCode() : 0);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public BaseMsgBean<?> parseObj(String msg) {
        return null;
    }

    public String toString() {
        return "PubRobotMoveStateData(state=" + this.state + ", errors=" + this.errors + ")";
    }

    public PubRobotMoveStateData(String state, ArrayList<RobotError> arrayList) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        this.state = state;
        this.errors = arrayList;
    }

    public /* synthetic */ PubRobotMoveStateData(String str, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? (ArrayList) null : arrayList);
    }

    public final ArrayList<RobotError> getErrors() {
        return this.errors;
    }

    public final String getState() {
        return this.state;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getMsgType() {
        return Constant.INSTANCE.getMSG_TYPE_NOTIFY_ROBOT_MOVE_STATE$robot_open_sdk_release();
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return Constant.INSTANCE.pubSdkTopicTemplate(pk, dn);
    }
}
