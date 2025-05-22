package com.pudutech.bumblebee.robot_ui.manager;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudu.library.loracall.BaseMsg;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: lora_bean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0014\u0010\t\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaMsgCallRes;", "Lcom/pudu/library/loracall/BaseMsg;", "devAddr", "", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "", "distance", "", "([BID)V", "data", "getData", "()[B", "getDevAddr", "getDistance", "()D", "getRes", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class LoRaMsgCallRes extends BaseMsg {
    private final byte[] data;
    private final byte[] devAddr;
    private final double distance;
    private final int res;

    public static /* synthetic */ LoRaMsgCallRes copy$default(LoRaMsgCallRes loRaMsgCallRes, byte[] bArr, int i, double d, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bArr = loRaMsgCallRes.devAddr;
        }
        if ((i2 & 2) != 0) {
            i = loRaMsgCallRes.res;
        }
        if ((i2 & 4) != 0) {
            d = loRaMsgCallRes.distance;
        }
        return loRaMsgCallRes.copy(bArr, i, d);
    }

    /* renamed from: component1, reason: from getter */
    public final byte[] getDevAddr() {
        return this.devAddr;
    }

    /* renamed from: component2, reason: from getter */
    public final int getRes() {
        return this.res;
    }

    /* renamed from: component3, reason: from getter */
    public final double getDistance() {
        return this.distance;
    }

    public final LoRaMsgCallRes copy(byte[] devAddr, int res, double distance) {
        Intrinsics.checkParameterIsNotNull(devAddr, "devAddr");
        return new LoRaMsgCallRes(devAddr, res, distance);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoRaMsgCallRes)) {
            return false;
        }
        LoRaMsgCallRes loRaMsgCallRes = (LoRaMsgCallRes) other;
        return Intrinsics.areEqual(this.devAddr, loRaMsgCallRes.devAddr) && this.res == loRaMsgCallRes.res && Double.compare(this.distance, loRaMsgCallRes.distance) == 0;
    }

    public int hashCode() {
        byte[] bArr = this.devAddr;
        return ((((bArr != null ? Arrays.hashCode(bArr) : 0) * 31) + Integer.hashCode(this.res)) * 31) + Double.hashCode(this.distance);
    }

    public String toString() {
        return "LoRaMsgCallRes(devAddr=" + Arrays.toString(this.devAddr) + ", res=" + this.res + ", distance=" + this.distance + ")";
    }

    public /* synthetic */ LoRaMsgCallRes(byte[] bArr, int i, double d, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, i, (i2 & 4) != 0 ? 0.0d : d);
    }

    public final byte[] getDevAddr() {
        return this.devAddr;
    }

    public final double getDistance() {
        return this.distance;
    }

    public final int getRes() {
        return this.res;
    }

    public LoRaMsgCallRes(byte[] devAddr, int i, double d) {
        byte[] bArr;
        Intrinsics.checkParameterIsNotNull(devAddr, "devAddr");
        this.devAddr = devAddr;
        this.res = i;
        this.distance = d;
        int i2 = this.res;
        if (i2 == 2) {
            byte[] bArr2 = this.devAddr;
            double d2 = this.distance;
            bArr = new byte[]{(byte) 128, (byte) 2, bArr2[0], bArr2[1], bArr2[2], bArr2[3], (byte) i2, (byte) (((int) d2) & 255), (byte) ((((int) d2) >> 8) & 255)};
        } else {
            byte[] bArr3 = this.devAddr;
            bArr = new byte[]{(byte) 128, (byte) 2, bArr3[0], bArr3[1], bArr3[2], bArr3[3], (byte) i2};
        }
        this.data = bArr;
    }

    @Override // com.pudu.library.loracall.BaseMsg
    public byte[] getData() {
        return this.data;
    }
}
