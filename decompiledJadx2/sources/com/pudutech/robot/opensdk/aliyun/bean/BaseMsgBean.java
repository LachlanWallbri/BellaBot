package com.pudutech.robot.opensdk.aliyun.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseMsgBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B;\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bJ\b\u0010\u0011\u001a\u00020\u0005H\u0016R\u0015\u0010\t\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/robot/opensdk/interf/IBody;", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseRootParameterBean;", "msgType", "", MapElement.Source.SOURCE, TypedValues.Attributes.S_TARGET, "msgId", "body", "groupId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/robot/opensdk/interf/IBody;Ljava/lang/String;)V", "getBody", "()Lcom/pudutech/robot/opensdk/interf/IBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "getGroupId", "()Ljava/lang/String;", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class BaseMsgBean<T extends IBody> extends BaseRootParameterBean {
    private final T body;
    private final String groupId;

    public /* synthetic */ BaseMsgBean(String str, String str2, String str3, String str4, IBody iBody, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? (IBody) null : iBody, str5);
    }

    public final T getBody() {
        return this.body;
    }

    public final String getGroupId() {
        return this.groupId;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseMsgBean(String msgType, String source, String target, String msgId, T t, String str) {
        super(msgType, source, target, msgId, null, 16, null);
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        Intrinsics.checkParameterIsNotNull(source, "source");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        this.body = t;
        this.groupId = str;
    }

    public String toString() {
        return "BaseMsgBean(msgType='" + getMsgType() + "', source='" + getSource() + "', target='" + getTarget() + "', msgId='" + getMsgId() + "', groupId='" + this.groupId + "', body=" + this.body + ')';
    }
}
