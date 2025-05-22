package com.pudutech.robot.opensdk;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: MsgContext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003BC\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u0015J^\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010%\u001a\u00020\u0005HÖ\u0001J\t\u0010&\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0015\u0010\f\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011¨\u0006'"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/MsgContext;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/robot/opensdk/interf/IBody;", "", "connectType", "", "role", "", "msgType", TypedValues.Attributes.S_TARGET, "msgId", "groupId", "reqData", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/robot/opensdk/interf/IBody;)V", "getConnectType", "()I", "getGroupId", "()Ljava/lang/String;", "getMsgId", "getMsgType", "getReqData", "()Lcom/pudutech/robot/opensdk/interf/IBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "getRole", "getTarget", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/robot/opensdk/interf/IBody;)Lcom/pudutech/robot/opensdk/MsgContext;", "equals", "", "other", "hashCode", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class MsgContext<T extends IBody> {
    private final int connectType;
    private final String groupId;
    private final String msgId;
    private final String msgType;
    private final T reqData;
    private final String role;
    private final String target;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MsgContext copy$default(MsgContext msgContext, int i, String str, String str2, String str3, String str4, String str5, IBody iBody, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = msgContext.connectType;
        }
        if ((i2 & 2) != 0) {
            str = msgContext.role;
        }
        String str6 = str;
        if ((i2 & 4) != 0) {
            str2 = msgContext.msgType;
        }
        String str7 = str2;
        if ((i2 & 8) != 0) {
            str3 = msgContext.target;
        }
        String str8 = str3;
        if ((i2 & 16) != 0) {
            str4 = msgContext.msgId;
        }
        String str9 = str4;
        if ((i2 & 32) != 0) {
            str5 = msgContext.groupId;
        }
        String str10 = str5;
        T t = iBody;
        if ((i2 & 64) != 0) {
            t = msgContext.reqData;
        }
        return msgContext.copy(i, str6, str7, str8, str9, str10, t);
    }

    /* renamed from: component1, reason: from getter */
    public final int getConnectType() {
        return this.connectType;
    }

    /* renamed from: component2, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMsgType() {
        return this.msgType;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTarget() {
        return this.target;
    }

    /* renamed from: component5, reason: from getter */
    public final String getMsgId() {
        return this.msgId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getGroupId() {
        return this.groupId;
    }

    public final T component7() {
        return this.reqData;
    }

    public final MsgContext<T> copy(int connectType, String role, String msgType, String target, String msgId, String groupId, T reqData) {
        Intrinsics.checkParameterIsNotNull(role, "role");
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        return new MsgContext<>(connectType, role, msgType, target, msgId, groupId, reqData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MsgContext)) {
            return false;
        }
        MsgContext msgContext = (MsgContext) other;
        return this.connectType == msgContext.connectType && Intrinsics.areEqual(this.role, msgContext.role) && Intrinsics.areEqual(this.msgType, msgContext.msgType) && Intrinsics.areEqual(this.target, msgContext.target) && Intrinsics.areEqual(this.msgId, msgContext.msgId) && Intrinsics.areEqual(this.groupId, msgContext.groupId) && Intrinsics.areEqual(this.reqData, msgContext.reqData);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.connectType) * 31;
        String str = this.role;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.msgType;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.target;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.msgId;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.groupId;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        T t = this.reqData;
        return hashCode6 + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "MsgContext(connectType=" + this.connectType + ", role=" + this.role + ", msgType=" + this.msgType + ", target=" + this.target + ", msgId=" + this.msgId + ", groupId=" + this.groupId + ", reqData=" + this.reqData + ")";
    }

    public MsgContext(int i, String role, String msgType, String target, String msgId, String str, T t) {
        Intrinsics.checkParameterIsNotNull(role, "role");
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        this.connectType = i;
        this.role = role;
        this.msgType = msgType;
        this.target = target;
        this.msgId = msgId;
        this.groupId = str;
        this.reqData = t;
    }

    public final int getConnectType() {
        return this.connectType;
    }

    public final String getRole() {
        return this.role;
    }

    public final String getMsgType() {
        return this.msgType;
    }

    public final String getTarget() {
        return this.target;
    }

    public final String getMsgId() {
        return this.msgId;
    }

    public final String getGroupId() {
        return this.groupId;
    }

    public /* synthetic */ MsgContext(int i, String str, String str2, String str3, String str4, String str5, IBody iBody, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, str3, str4, str5, (i2 & 64) != 0 ? (IBody) null : iBody);
    }

    public final T getReqData() {
        return this.reqData;
    }
}
