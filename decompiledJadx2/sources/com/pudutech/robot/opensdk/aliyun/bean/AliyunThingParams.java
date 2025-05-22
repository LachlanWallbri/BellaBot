package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AliyunThingProData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingParams;", "", "bind_code", "Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingValue;", "(Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingValue;)V", "getBind_code", "()Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingValue;", "setBind_code", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class AliyunThingParams {
    private AliyunThingValue bind_code;

    public static /* synthetic */ AliyunThingParams copy$default(AliyunThingParams aliyunThingParams, AliyunThingValue aliyunThingValue, int i, Object obj) {
        if ((i & 1) != 0) {
            aliyunThingValue = aliyunThingParams.bind_code;
        }
        return aliyunThingParams.copy(aliyunThingValue);
    }

    /* renamed from: component1, reason: from getter */
    public final AliyunThingValue getBind_code() {
        return this.bind_code;
    }

    public final AliyunThingParams copy(AliyunThingValue bind_code) {
        Intrinsics.checkParameterIsNotNull(bind_code, "bind_code");
        return new AliyunThingParams(bind_code);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof AliyunThingParams) && Intrinsics.areEqual(this.bind_code, ((AliyunThingParams) other).bind_code);
        }
        return true;
    }

    public int hashCode() {
        AliyunThingValue aliyunThingValue = this.bind_code;
        if (aliyunThingValue != null) {
            return aliyunThingValue.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "AliyunThingParams(bind_code=" + this.bind_code + ")";
    }

    public AliyunThingParams(AliyunThingValue bind_code) {
        Intrinsics.checkParameterIsNotNull(bind_code, "bind_code");
        this.bind_code = bind_code;
    }

    public final AliyunThingValue getBind_code() {
        return this.bind_code;
    }

    public final void setBind_code(AliyunThingValue aliyunThingValue) {
        Intrinsics.checkParameterIsNotNull(aliyunThingValue, "<set-?>");
        this.bind_code = aliyunThingValue;
    }
}
