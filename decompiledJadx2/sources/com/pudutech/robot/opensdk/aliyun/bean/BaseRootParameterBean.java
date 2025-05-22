package com.pudutech.robot.opensdk.aliyun.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseRootParameterBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0016\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/BaseRootParameterBean;", "", "msgType", "", MapElement.Source.SOURCE, TypedValues.Attributes.S_TARGET, "msgId", "sourceType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMsgId", "()Ljava/lang/String;", "setMsgId", "(Ljava/lang/String;)V", "getMsgType", "setMsgType", "getSource", "setSource", "getSourceType", "setSourceType", "getTarget", "setTarget", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public class BaseRootParameterBean {
    private String msgId;
    private String msgType;
    private String source;
    private String sourceType;
    private String target;

    public BaseRootParameterBean() {
        this(null, null, null, null, null, 31, null);
    }

    public BaseRootParameterBean(String msgType, String source, String target, String msgId, String sourceType) {
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        Intrinsics.checkParameterIsNotNull(source, "source");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        Intrinsics.checkParameterIsNotNull(sourceType, "sourceType");
        this.msgType = msgType;
        this.source = source;
        this.target = target;
        this.msgId = msgId;
        this.sourceType = sourceType;
    }

    public final String getMsgType() {
        return this.msgType;
    }

    public final void setMsgType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.msgType = str;
    }

    public final String getSource() {
        return this.source;
    }

    public final void setSource(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.source = str;
    }

    public final String getTarget() {
        return this.target;
    }

    public final void setTarget(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.target = str;
    }

    public final String getMsgId() {
        return this.msgId;
    }

    public final void setMsgId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.msgId = str;
    }

    public /* synthetic */ BaseRootParameterBean(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5);
    }

    public final String getSourceType() {
        return this.sourceType;
    }

    public final void setSourceType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.sourceType = str;
    }
}
