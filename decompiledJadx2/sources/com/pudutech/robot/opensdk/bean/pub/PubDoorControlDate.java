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
/* compiled from: PubDoorControlDate.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\t\u0010\u001d\u001a\u00020\u0015HÖ\u0001J\u0018\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0003H\u0016J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\""}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubDoorControlDate;", "Lcom/pudutech/robot/opensdk/bean/pub/BaseNotifyPub;", "action", "", "id", "ext", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAction", "()Ljava/lang/String;", "getExt", "getId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "getMsgType", "getQos", "", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "hashCode", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PubDoorControlDate extends BaseNotifyPub {
    private final String action;
    private final String ext;
    private final String id;

    public static /* synthetic */ PubDoorControlDate copy$default(PubDoorControlDate pubDoorControlDate, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pubDoorControlDate.action;
        }
        if ((i & 2) != 0) {
            str2 = pubDoorControlDate.id;
        }
        if ((i & 4) != 0) {
            str3 = pubDoorControlDate.ext;
        }
        return pubDoorControlDate.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAction() {
        return this.action;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getExt() {
        return this.ext;
    }

    public final PubDoorControlDate copy(String action, String id, String ext) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return new PubDoorControlDate(action, id, ext);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PubDoorControlDate)) {
            return false;
        }
        PubDoorControlDate pubDoorControlDate = (PubDoorControlDate) other;
        return Intrinsics.areEqual(this.action, pubDoorControlDate.action) && Intrinsics.areEqual(this.id, pubDoorControlDate.id) && Intrinsics.areEqual(this.ext, pubDoorControlDate.ext);
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
        String str = this.action;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.id;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.ext;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public BaseMsgBean<?> parseObj(String msg) {
        return null;
    }

    public String toString() {
        return "PubDoorControlDate(action=" + this.action + ", id=" + this.id + ", ext=" + this.ext + ")";
    }

    public final String getAction() {
        return this.action;
    }

    public final String getExt() {
        return this.ext;
    }

    public final String getId() {
        return this.id;
    }

    public PubDoorControlDate(String action, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        this.action = action;
        this.id = str;
        this.ext = str2;
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getMsgType() {
        return Constant.INSTANCE.getMSG_TYPE_REQUEST_DOOR_CONTROL$robot_open_sdk_release();
    }

    @Override // com.pudutech.robot.opensdk.interf.IPubMsg
    public String getTopic(String pk, String dn) {
        Intrinsics.checkParameterIsNotNull(pk, "pk");
        Intrinsics.checkParameterIsNotNull(dn, "dn");
        return Constant.INSTANCE.pubSdkTopicTemplate(pk, dn);
    }
}
