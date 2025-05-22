package com.pudutech.bumblebee.robot_ui.bean;

import com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean;
import com.pudutech.disinfect.baselib.util.IgnoreAnnotation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReturnPointBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\b\u0010\u0019\u001a\u00020\u0003H\u0016J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\b\u0010\u001c\u001a\u00020\u0006H\u0016J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/bean/ReturnPointBean;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/radiobtntv/IRadioBtnTvBean;", "mapName", "", "pointName", "select", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getMapName", "()Ljava/lang/String;", "setMapName", "(Ljava/lang/String;)V", "getPointName", "setPointName", "getSelect", "()Z", "setSelect", "(Z)V", "component1", "component2", "component3", "copy", "equals", "other", "", "getName", "hashCode", "", "isCheck", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class ReturnPointBean implements IRadioBtnTvBean {
    private String mapName;
    private String pointName;

    @IgnoreAnnotation
    private boolean select;

    public static /* synthetic */ ReturnPointBean copy$default(ReturnPointBean returnPointBean, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = returnPointBean.mapName;
        }
        if ((i & 2) != 0) {
            str2 = returnPointBean.pointName;
        }
        if ((i & 4) != 0) {
            z = returnPointBean.select;
        }
        return returnPointBean.copy(str, str2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMapName() {
        return this.mapName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPointName() {
        return this.pointName;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getSelect() {
        return this.select;
    }

    public final ReturnPointBean copy(String mapName, String pointName, boolean select) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(pointName, "pointName");
        return new ReturnPointBean(mapName, pointName, select);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReturnPointBean)) {
            return false;
        }
        ReturnPointBean returnPointBean = (ReturnPointBean) other;
        return Intrinsics.areEqual(this.mapName, returnPointBean.mapName) && Intrinsics.areEqual(this.pointName, returnPointBean.pointName) && this.select == returnPointBean.select;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.mapName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.pointName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.select;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "ReturnPointBean(mapName=" + this.mapName + ", pointName=" + this.pointName + ", select=" + this.select + ")";
    }

    public ReturnPointBean(String mapName, String pointName, boolean z) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(pointName, "pointName");
        this.mapName = mapName;
        this.pointName = pointName;
        this.select = z;
    }

    public /* synthetic */ ReturnPointBean(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : z);
    }

    public final String getMapName() {
        return this.mapName;
    }

    public final String getPointName() {
        return this.pointName;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setMapName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mapName = str;
    }

    public final void setPointName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.pointName = str;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean
    public boolean isCheck() {
        return this.select;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean
    public String getName() {
        return this.pointName;
    }
}
