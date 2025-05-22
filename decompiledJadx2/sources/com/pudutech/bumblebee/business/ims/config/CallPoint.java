package com.pudutech.bumblebee.business.ims.config;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CallPoint.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/config/CallPoint;", "Ljava/io/Serializable;", "type", "", "name", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getType", "setType", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CallPoint implements Serializable {
    public static final String KEY_CALL_POINT = "key_call_point";
    private String name;
    private String type;

    public CallPoint(String type, String name) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.type = type;
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    public final String getType() {
        return this.type;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final void setType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.type = str;
    }

    public String toString() {
        return "CallPoint(type='" + this.type + "', name='" + this.name + "')";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            CallPoint callPoint = (CallPoint) other;
            return ((Intrinsics.areEqual(this.type, callPoint.type) ^ true) || (Intrinsics.areEqual(this.name, callPoint.name) ^ true)) ? false : true;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.ims.config.CallPoint");
    }

    public int hashCode() {
        return (this.type.hashCode() * 31) + this.name.hashCode();
    }
}
