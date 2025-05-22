package com.pudutech.robot.module.report.protocol.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseCardInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0017JF\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0007HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006'"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/bean/CruiseCardInfo;", "", "trigger_text", "", "id", "name", "serial", "", "type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getName", "setName", "getSerial", "()I", "setSerial", "(I)V", "getTrigger_text", "setTrigger_text", "getType", "()Ljava/lang/Integer;", "setType", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;)Lcom/pudutech/robot/module/report/protocol/bean/CruiseCardInfo;", "equals", "", "other", "hashCode", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class CruiseCardInfo {
    private String id;
    private String name;
    private int serial;
    private String trigger_text;
    private Integer type;

    public static /* synthetic */ CruiseCardInfo copy$default(CruiseCardInfo cruiseCardInfo, String str, String str2, String str3, int i, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = cruiseCardInfo.trigger_text;
        }
        if ((i2 & 2) != 0) {
            str2 = cruiseCardInfo.id;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            str3 = cruiseCardInfo.name;
        }
        String str5 = str3;
        if ((i2 & 8) != 0) {
            i = cruiseCardInfo.serial;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            num = cruiseCardInfo.type;
        }
        return cruiseCardInfo.copy(str, str4, str5, i3, num);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTrigger_text() {
        return this.trigger_text;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final int getSerial() {
        return this.serial;
    }

    /* renamed from: component5, reason: from getter */
    public final Integer getType() {
        return this.type;
    }

    public final CruiseCardInfo copy(String trigger_text, String id, String name, int serial, Integer type) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new CruiseCardInfo(trigger_text, id, name, serial, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CruiseCardInfo)) {
            return false;
        }
        CruiseCardInfo cruiseCardInfo = (CruiseCardInfo) other;
        return Intrinsics.areEqual(this.trigger_text, cruiseCardInfo.trigger_text) && Intrinsics.areEqual(this.id, cruiseCardInfo.id) && Intrinsics.areEqual(this.name, cruiseCardInfo.name) && this.serial == cruiseCardInfo.serial && Intrinsics.areEqual(this.type, cruiseCardInfo.type);
    }

    public int hashCode() {
        String str = this.trigger_text;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.id;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.name;
        int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.serial) * 31;
        Integer num = this.type;
        return hashCode3 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "CruiseCardInfo(trigger_text=" + this.trigger_text + ", id=" + this.id + ", name=" + this.name + ", serial=" + this.serial + ", type=" + this.type + ")";
    }

    public CruiseCardInfo(String str, String str2, String name, int i, Integer num) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.trigger_text = str;
        this.id = str2;
        this.name = name;
        this.serial = i;
        this.type = num;
    }

    public final String getTrigger_text() {
        return this.trigger_text;
    }

    public final void setTrigger_text(String str) {
        this.trigger_text = str;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final int getSerial() {
        return this.serial;
    }

    public final void setSerial(int i) {
        this.serial = i;
    }

    public /* synthetic */ CruiseCardInfo(String str, String str2, String str3, int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i, (i2 & 16) != 0 ? -1 : num);
    }

    public final Integer getType() {
        return this.type;
    }

    public final void setType(Integer num) {
        this.type = num;
    }
}
