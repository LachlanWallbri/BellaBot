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
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PubDeliveryTaskStateData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0019\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0007HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\b\u0010\u0014\u001a\u00020\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0016J\t\u0010\u001e\u001a\u00020\u0016HÖ\u0001J\u0018\u0010\u001f\u001a\b\u0012\u0002\b\u0003\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u0007H\u0016J\t\u0010\"\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006#"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubDeliveryTaskStateData;", "Lcom/pudutech/robot/opensdk/bean/pub/BaseNotifyPub;", "trays", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/bean/pub/DeliveryTraysTaskState;", "Lkotlin/collections/ArrayList;", "deliveryMode", "", "(Ljava/util/ArrayList;Ljava/lang/String;)V", "getDeliveryMode", "()Ljava/lang/String;", "getTrays", "()Ljava/util/ArrayList;", "component1", "component2", "copy", "equals", "", "other", "", "getMsgType", "getQos", "", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "hashCode", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PubDeliveryTaskStateData extends BaseNotifyPub {
    private final String deliveryMode;
    private final ArrayList<DeliveryTraysTaskState> trays;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PubDeliveryTaskStateData copy$default(PubDeliveryTaskStateData pubDeliveryTaskStateData, ArrayList arrayList, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = pubDeliveryTaskStateData.trays;
        }
        if ((i & 2) != 0) {
            str = pubDeliveryTaskStateData.deliveryMode;
        }
        return pubDeliveryTaskStateData.copy(arrayList, str);
    }

    public final ArrayList<DeliveryTraysTaskState> component1() {
        return this.trays;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeliveryMode() {
        return this.deliveryMode;
    }

    public final PubDeliveryTaskStateData copy(ArrayList<DeliveryTraysTaskState> trays, String deliveryMode) {
        Intrinsics.checkParameterIsNotNull(trays, "trays");
        Intrinsics.checkParameterIsNotNull(deliveryMode, "deliveryMode");
        return new PubDeliveryTaskStateData(trays, deliveryMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PubDeliveryTaskStateData)) {
            return false;
        }
        PubDeliveryTaskStateData pubDeliveryTaskStateData = (PubDeliveryTaskStateData) other;
        return Intrinsics.areEqual(this.trays, pubDeliveryTaskStateData.trays) && Intrinsics.areEqual(this.deliveryMode, pubDeliveryTaskStateData.deliveryMode);
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
        ArrayList<DeliveryTraysTaskState> arrayList = this.trays;
        int hashCode = (arrayList != null ? arrayList.hashCode() : 0) * 31;
        String str = this.deliveryMode;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "PubDeliveryTaskStateData(trays=" + this.trays + ", deliveryMode=" + this.deliveryMode + ")";
    }

    public final ArrayList<DeliveryTraysTaskState> getTrays() {
        return this.trays;
    }

    public final String getDeliveryMode() {
        return this.deliveryMode;
    }

    public PubDeliveryTaskStateData(ArrayList<DeliveryTraysTaskState> trays, String deliveryMode) {
        Intrinsics.checkParameterIsNotNull(trays, "trays");
        Intrinsics.checkParameterIsNotNull(deliveryMode, "deliveryMode");
        this.trays = trays;
        this.deliveryMode = deliveryMode;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getMsgType() {
        return Constant.INSTANCE.getMSG_TYPE_NOTIFY_DELIVERY_TASK$robot_open_sdk_release();
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return Constant.INSTANCE.pubSdkTopicTemplate(pk, dn);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getReplyTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return Constant.INSTANCE.subSdkTopicTemplate(pk, dn);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public BaseMsgBean<?> parseObj(String msg) {
        if (msg == null) {
            return null;
        }
        Object fromJson = new Gson().fromJson(msg, new TypeToken<BaseMsgBean<RespResultBody>>() { // from class: com.pudutech.robot.opensdk.bean.pub.PubDeliveryTaskStateData$parseObj$1$type$1
        }.getType());
        if (fromJson != null) {
            return (BaseMsgBean) fromJson;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean<*>");
    }
}
