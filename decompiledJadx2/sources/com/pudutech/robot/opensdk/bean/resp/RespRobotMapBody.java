package com.pudutech.robot.opensdk.bean.resp;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RespRobotMapBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/resp/RespRobotMapBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "data", "", "(Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "setData", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class RespRobotMapBody implements IBody {
    private String data;

    public static /* synthetic */ RespRobotMapBody copy$default(RespRobotMapBody respRobotMapBody, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = respRobotMapBody.data;
        }
        return respRobotMapBody.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getData() {
        return this.data;
    }

    public final RespRobotMapBody copy(String data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return new RespRobotMapBody(data);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof RespRobotMapBody) && Intrinsics.areEqual(this.data, ((RespRobotMapBody) other).data);
        }
        return true;
    }

    public int hashCode() {
        String str = this.data;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "RespRobotMapBody(data=" + this.data + ")";
    }

    public RespRobotMapBody(String data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.data = data;
    }

    public final String getData() {
        return this.data;
    }

    public final void setData(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.data = str;
    }
}
