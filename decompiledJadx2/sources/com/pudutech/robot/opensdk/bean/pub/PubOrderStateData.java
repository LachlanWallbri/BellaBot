package com.pudutech.robot.opensdk.bean.pub;

import androidx.core.app.NotificationCompat;
import com.amitshekhar.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import com.pudutech.robot.opensdk.bean.resp.RespResultBody;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PubOrderStateData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bHÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003JC\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u0003H\u0016J\b\u0010\"\u001a\u00020\u001eH\u0016J\b\u0010#\u001a\u00020$H\u0016J\u0018\u0010%\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u0003H\u0016J\t\u0010&\u001a\u00020\u001eHÖ\u0001J\u0018\u0010'\u001a\b\u0012\u0002\b\u0003\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010\u0003H\u0016J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u0012¨\u0006+"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubOrderStateData;", "Lcom/pudutech/robot/opensdk/bean/pub/BaseNotifyPub;", "role", "", "orderState", "ids", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/bean/pub/OrderIdData;", "Lkotlin/collections/ArrayList;", "employeeId", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;)V", "getEmployeeId", "()Ljava/lang/String;", "getIds", "()Ljava/util/ArrayList;", "getOrderState", "getRole", "setRole", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "getMsgType", "getQos", "", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "hashCode", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PubOrderStateData extends BaseNotifyPub {
    private final String employeeId;
    private final ArrayList<OrderIdData> ids;
    private final String orderState;
    private String role;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PubOrderStateData copy$default(PubOrderStateData pubOrderStateData, String str, String str2, ArrayList arrayList, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pubOrderStateData.role;
        }
        if ((i & 2) != 0) {
            str2 = pubOrderStateData.orderState;
        }
        if ((i & 4) != 0) {
            arrayList = pubOrderStateData.ids;
        }
        if ((i & 8) != 0) {
            str3 = pubOrderStateData.employeeId;
        }
        return pubOrderStateData.copy(str, str2, arrayList, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* renamed from: component2, reason: from getter */
    public final String getOrderState() {
        return this.orderState;
    }

    public final ArrayList<OrderIdData> component3() {
        return this.ids;
    }

    /* renamed from: component4, reason: from getter */
    public final String getEmployeeId() {
        return this.employeeId;
    }

    public final PubOrderStateData copy(String role, String orderState, ArrayList<OrderIdData> ids, String employeeId) {
        Intrinsics.checkParameterIsNotNull(role, "role");
        Intrinsics.checkParameterIsNotNull(orderState, "orderState");
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        return new PubOrderStateData(role, orderState, ids, employeeId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PubOrderStateData)) {
            return false;
        }
        PubOrderStateData pubOrderStateData = (PubOrderStateData) other;
        return Intrinsics.areEqual(this.role, pubOrderStateData.role) && Intrinsics.areEqual(this.orderState, pubOrderStateData.orderState) && Intrinsics.areEqual(this.ids, pubOrderStateData.ids) && Intrinsics.areEqual(this.employeeId, pubOrderStateData.employeeId);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public int getQos() {
        return 0;
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
        String str = this.role;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.orderState;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        ArrayList<OrderIdData> arrayList = this.ids;
        int hashCode3 = (hashCode2 + (arrayList != null ? arrayList.hashCode() : 0)) * 31;
        String str3 = this.employeeId;
        return hashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "PubOrderStateData(role=" + this.role + ", orderState=" + this.orderState + ", ids=" + this.ids + ", employeeId=" + this.employeeId + ")";
    }

    public final String getRole() {
        return this.role;
    }

    public final void setRole(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.role = str;
    }

    public final String getOrderState() {
        return this.orderState;
    }

    public final ArrayList<OrderIdData> getIds() {
        return this.ids;
    }

    public /* synthetic */ PubOrderStateData(String str, String str2, ArrayList arrayList, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, arrayList, (i & 8) != 0 ? (String) null : str3);
    }

    public final String getEmployeeId() {
        return this.employeeId;
    }

    public PubOrderStateData(String role, String orderState, ArrayList<OrderIdData> ids, String str) {
        Intrinsics.checkParameterIsNotNull(role, "role");
        Intrinsics.checkParameterIsNotNull(orderState, "orderState");
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        this.role = role;
        this.orderState = orderState;
        this.ids = ids;
        this.employeeId = str;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public BaseMsgBean<?> parseObj(String msg) {
        if (msg == null) {
            return null;
        }
        Object fromJson = new Gson().fromJson(msg, new TypeToken<BaseMsgBean<RespResultBody>>() { // from class: com.pudutech.robot.opensdk.bean.pub.PubOrderStateData$parseObj$1$type$1
        }.getType());
        if (fromJson != null) {
            return (BaseMsgBean) fromJson;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean<*>");
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

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getReplyTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        if (Intrinsics.areEqual(this.role, Constant.INSTANCE.getROLE_SDK())) {
            return Constant.INSTANCE.subSdkTopicTemplate(pk, dn);
        }
        if (Intrinsics.areEqual(this.role, Constant.INSTANCE.getROLE_BEEPER())) {
            return Constant.INSTANCE.subBeeperTopicTemplate(pk, dn);
        }
        return Constant.INSTANCE.subSdkTopicTemplate(pk, dn);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getMsgType() {
        return Constant.INSTANCE.getMSG_TYPE_NOTIFY_ROBOT_ORDER_STATE$robot_open_sdk_release();
    }
}
