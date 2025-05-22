package com.pudutech.peanut.robot_ui.ttsvoices.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TtsVoicePk.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsVoiceItem;", "", "noName", "", ES6Iterator.VALUE_PROPERTY, "index", "", "md5", "path", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getIndex", "()I", "getMd5", "()Ljava/lang/String;", "setMd5", "(Ljava/lang/String;)V", "getNoName", "getPath", "setPath", "getValue", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TtsVoiceItem {
    private final int index;
    private String md5;
    private final String noName;
    private String path;
    private final String value;

    public static /* synthetic */ TtsVoiceItem copy$default(TtsVoiceItem ttsVoiceItem, String str, String str2, int i, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ttsVoiceItem.noName;
        }
        if ((i2 & 2) != 0) {
            str2 = ttsVoiceItem.value;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            i = ttsVoiceItem.index;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str3 = ttsVoiceItem.md5;
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            str4 = ttsVoiceItem.path;
        }
        return ttsVoiceItem.copy(str, str5, i3, str6, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getNoName() {
        return this.noName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    /* renamed from: component3, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMd5() {
        return this.md5;
    }

    /* renamed from: component5, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    public final TtsVoiceItem copy(String noName, String value, int index, String md5, String path) {
        Intrinsics.checkParameterIsNotNull(noName, "noName");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(path, "path");
        return new TtsVoiceItem(noName, value, index, md5, path);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsVoiceItem)) {
            return false;
        }
        TtsVoiceItem ttsVoiceItem = (TtsVoiceItem) other;
        return Intrinsics.areEqual(this.noName, ttsVoiceItem.noName) && Intrinsics.areEqual(this.value, ttsVoiceItem.value) && this.index == ttsVoiceItem.index && Intrinsics.areEqual(this.md5, ttsVoiceItem.md5) && Intrinsics.areEqual(this.path, ttsVoiceItem.path);
    }

    public int hashCode() {
        String str = this.noName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.value;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.index)) * 31;
        String str3 = this.md5;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.path;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "TtsVoiceItem(noName=" + this.noName + ", value=" + this.value + ", index=" + this.index + ", md5=" + this.md5 + ", path=" + this.path + ")";
    }

    public TtsVoiceItem(String noName, String value, int i, String md5, String path) {
        Intrinsics.checkParameterIsNotNull(noName, "noName");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(path, "path");
        this.noName = noName;
        this.value = value;
        this.index = i;
        this.md5 = md5;
        this.path = path;
    }

    public final String getNoName() {
        return this.noName;
    }

    public final String getValue() {
        return this.value;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.md5 = str;
    }

    public /* synthetic */ TtsVoiceItem(String str, String str2, int i, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? 1 : i, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4);
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.path = str;
    }
}
