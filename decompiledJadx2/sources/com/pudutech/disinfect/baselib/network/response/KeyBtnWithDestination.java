package com.pudutech.disinfect.baselib.network.response;

import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: iot_call.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\tHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestination;", "", "dev_addr", "", "mac", MapElement.Key.MAP, "map_point", "pid", "shop_id", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getDev_addr", "()Ljava/lang/String;", "getMac", "getMap", "getMap_point", "getPid", "getShop_id", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class KeyBtnWithDestination {
    private final String dev_addr;
    private final String mac;
    private final String map;
    private final String map_point;
    private final String pid;
    private final int shop_id;

    public static /* synthetic */ KeyBtnWithDestination copy$default(KeyBtnWithDestination keyBtnWithDestination, String str, String str2, String str3, String str4, String str5, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = keyBtnWithDestination.dev_addr;
        }
        if ((i2 & 2) != 0) {
            str2 = keyBtnWithDestination.mac;
        }
        String str6 = str2;
        if ((i2 & 4) != 0) {
            str3 = keyBtnWithDestination.map;
        }
        String str7 = str3;
        if ((i2 & 8) != 0) {
            str4 = keyBtnWithDestination.map_point;
        }
        String str8 = str4;
        if ((i2 & 16) != 0) {
            str5 = keyBtnWithDestination.pid;
        }
        String str9 = str5;
        if ((i2 & 32) != 0) {
            i = keyBtnWithDestination.shop_id;
        }
        return keyBtnWithDestination.copy(str, str6, str7, str8, str9, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDev_addr() {
        return this.dev_addr;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMap() {
        return this.map;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMap_point() {
        return this.map_point;
    }

    /* renamed from: component5, reason: from getter */
    public final String getPid() {
        return this.pid;
    }

    /* renamed from: component6, reason: from getter */
    public final int getShop_id() {
        return this.shop_id;
    }

    public final KeyBtnWithDestination copy(String dev_addr, String mac, String map, String map_point, String pid, int shop_id) {
        Intrinsics.checkParameterIsNotNull(dev_addr, "dev_addr");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(map, "map");
        Intrinsics.checkParameterIsNotNull(map_point, "map_point");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        return new KeyBtnWithDestination(dev_addr, mac, map, map_point, pid, shop_id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KeyBtnWithDestination)) {
            return false;
        }
        KeyBtnWithDestination keyBtnWithDestination = (KeyBtnWithDestination) other;
        return Intrinsics.areEqual(this.dev_addr, keyBtnWithDestination.dev_addr) && Intrinsics.areEqual(this.mac, keyBtnWithDestination.mac) && Intrinsics.areEqual(this.map, keyBtnWithDestination.map) && Intrinsics.areEqual(this.map_point, keyBtnWithDestination.map_point) && Intrinsics.areEqual(this.pid, keyBtnWithDestination.pid) && this.shop_id == keyBtnWithDestination.shop_id;
    }

    public int hashCode() {
        String str = this.dev_addr;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mac;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.map;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.map_point;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.pid;
        return ((hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31) + Integer.hashCode(this.shop_id);
    }

    public String toString() {
        return "KeyBtnWithDestination(dev_addr=" + this.dev_addr + ", mac=" + this.mac + ", map=" + this.map + ", map_point=" + this.map_point + ", pid=" + this.pid + ", shop_id=" + this.shop_id + ")";
    }

    public KeyBtnWithDestination(String dev_addr, String mac, String map, String map_point, String pid, int i) {
        Intrinsics.checkParameterIsNotNull(dev_addr, "dev_addr");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(map, "map");
        Intrinsics.checkParameterIsNotNull(map_point, "map_point");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        this.dev_addr = dev_addr;
        this.mac = mac;
        this.map = map;
        this.map_point = map_point;
        this.pid = pid;
        this.shop_id = i;
    }

    public final String getDev_addr() {
        return this.dev_addr;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getMap() {
        return this.map;
    }

    public final String getMap_point() {
        return this.map_point;
    }

    public final String getPid() {
        return this.pid;
    }

    public final int getShop_id() {
        return this.shop_id;
    }
}
