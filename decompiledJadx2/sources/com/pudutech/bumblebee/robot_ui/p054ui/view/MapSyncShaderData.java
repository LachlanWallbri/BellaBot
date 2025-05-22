package com.pudutech.bumblebee.robot_ui.p054ui.view;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapSyncStatusView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ>\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000f\u0010\n¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/MapSyncShaderData;", "", "bg", "", "textColor", "text", "", "src", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)V", "getBg", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSrc", "getText", "()Ljava/lang/String;", "getTextColor", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)Lcom/pudutech/bumblebee/robot_ui/ui/view/MapSyncShaderData;", "equals", "", "other", "hashCode", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class MapSyncShaderData {
    private final Integer bg;
    private final Integer src;
    private final String text;
    private final Integer textColor;

    public static /* synthetic */ MapSyncShaderData copy$default(MapSyncShaderData mapSyncShaderData, Integer num, Integer num2, String str, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = mapSyncShaderData.bg;
        }
        if ((i & 2) != 0) {
            num2 = mapSyncShaderData.textColor;
        }
        if ((i & 4) != 0) {
            str = mapSyncShaderData.text;
        }
        if ((i & 8) != 0) {
            num3 = mapSyncShaderData.src;
        }
        return mapSyncShaderData.copy(num, num2, str, num3);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getBg() {
        return this.bg;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getTextColor() {
        return this.textColor;
    }

    /* renamed from: component3, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getSrc() {
        return this.src;
    }

    public final MapSyncShaderData copy(Integer bg, Integer textColor, String text, Integer src) {
        return new MapSyncShaderData(bg, textColor, text, src);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapSyncShaderData)) {
            return false;
        }
        MapSyncShaderData mapSyncShaderData = (MapSyncShaderData) other;
        return Intrinsics.areEqual(this.bg, mapSyncShaderData.bg) && Intrinsics.areEqual(this.textColor, mapSyncShaderData.textColor) && Intrinsics.areEqual(this.text, mapSyncShaderData.text) && Intrinsics.areEqual(this.src, mapSyncShaderData.src);
    }

    public int hashCode() {
        Integer num = this.bg;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.textColor;
        int hashCode2 = (hashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        String str = this.text;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        Integer num3 = this.src;
        return hashCode3 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        return "MapSyncShaderData(bg=" + this.bg + ", textColor=" + this.textColor + ", text=" + this.text + ", src=" + this.src + ")";
    }

    public MapSyncShaderData(Integer num, Integer num2, String str, Integer num3) {
        this.bg = num;
        this.textColor = num2;
        this.text = str;
        this.src = num3;
    }

    public final Integer getBg() {
        return this.bg;
    }

    public final Integer getTextColor() {
        return this.textColor;
    }

    public final String getText() {
        return this.text;
    }

    public /* synthetic */ MapSyncShaderData(Integer num, Integer num2, String str, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, num2, str, (i & 8) != 0 ? (Integer) null : num3);
    }

    public final Integer getSrc() {
        return this.src;
    }
}
