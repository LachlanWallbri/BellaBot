package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RegisterReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\tJ2\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\u0011\u0010\t\"\u0004\b\u0012\u0010\u000b¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/RegisterReq;", "", "deviceName", "", "productType", "", "clientType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getClientType", "()Ljava/lang/Integer;", "setClientType", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getDeviceName", "()Ljava/lang/String;", "setDeviceName", "(Ljava/lang/String;)V", "getProductType", "setProductType", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/RegisterReq;", "equals", "", "other", "hashCode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class RegisterReq {
    private Integer clientType;
    private String deviceName;
    private Integer productType;

    public static /* synthetic */ RegisterReq copy$default(RegisterReq registerReq, String str, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = registerReq.deviceName;
        }
        if ((i & 2) != 0) {
            num = registerReq.productType;
        }
        if ((i & 4) != 0) {
            num2 = registerReq.clientType;
        }
        return registerReq.copy(str, num, num2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getProductType() {
        return this.productType;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getClientType() {
        return this.clientType;
    }

    public final RegisterReq copy(String deviceName, Integer productType, Integer clientType) {
        return new RegisterReq(deviceName, productType, clientType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RegisterReq)) {
            return false;
        }
        RegisterReq registerReq = (RegisterReq) other;
        return Intrinsics.areEqual(this.deviceName, registerReq.deviceName) && Intrinsics.areEqual(this.productType, registerReq.productType) && Intrinsics.areEqual(this.clientType, registerReq.clientType);
    }

    public int hashCode() {
        String str = this.deviceName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.productType;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.clientType;
        return hashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "RegisterReq(deviceName=" + this.deviceName + ", productType=" + this.productType + ", clientType=" + this.clientType + ")";
    }

    public RegisterReq(String str, Integer num, Integer num2) {
        this.deviceName = str;
        this.productType = num;
        this.clientType = num2;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final void setDeviceName(String str) {
        this.deviceName = str;
    }

    public final Integer getProductType() {
        return this.productType;
    }

    public final void setProductType(Integer num) {
        this.productType = num;
    }

    public final Integer getClientType() {
        return this.clientType;
    }

    public final void setClientType(Integer num) {
        this.clientType = num;
    }
}
