package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TtsInfoResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J?\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000b¨\u0006 "}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/TtsVoiceInfo;", "", "id", "", "isProduced", "", "text", "md5", "path", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "()Z", "setProduced", "(Z)V", "getMd5", "setMd5", "(Ljava/lang/String;)V", "getPath", "setPath", "getText", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TtsVoiceInfo {
    private final String id;
    private boolean isProduced;
    private String md5;
    private String path;
    private final String text;

    public static /* synthetic */ TtsVoiceInfo copy$default(TtsVoiceInfo ttsVoiceInfo, String str, boolean z, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = ttsVoiceInfo.id;
        }
        if ((i & 2) != 0) {
            z = ttsVoiceInfo.isProduced;
        }
        boolean z2 = z;
        if ((i & 4) != 0) {
            str2 = ttsVoiceInfo.text;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = ttsVoiceInfo.md5;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            str4 = ttsVoiceInfo.path;
        }
        return ttsVoiceInfo.copy(str, z2, str5, str6, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsProduced() {
        return this.isProduced;
    }

    /* renamed from: component3, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMd5() {
        return this.md5;
    }

    /* renamed from: component5, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    public final TtsVoiceInfo copy(String id, boolean isProduced, String text, String md5, String path) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return new TtsVoiceInfo(id, isProduced, text, md5, path);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsVoiceInfo)) {
            return false;
        }
        TtsVoiceInfo ttsVoiceInfo = (TtsVoiceInfo) other;
        return Intrinsics.areEqual(this.id, ttsVoiceInfo.id) && this.isProduced == ttsVoiceInfo.isProduced && Intrinsics.areEqual(this.text, ttsVoiceInfo.text) && Intrinsics.areEqual(this.md5, ttsVoiceInfo.md5) && Intrinsics.areEqual(this.path, ttsVoiceInfo.path);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isProduced;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        String str2 = this.text;
        int hashCode2 = (i2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.md5;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.path;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "TtsVoiceInfo(id=" + this.id + ", isProduced=" + this.isProduced + ", text=" + this.text + ", md5=" + this.md5 + ", path=" + this.path + ")";
    }

    public TtsVoiceInfo(String id, boolean z, String text, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.id = id;
        this.isProduced = z;
        this.text = text;
        this.md5 = str;
        this.path = str2;
    }

    public final String getId() {
        return this.id;
    }

    public final boolean isProduced() {
        return this.isProduced;
    }

    public final void setProduced(boolean z) {
        this.isProduced = z;
    }

    public final String getText() {
        return this.text;
    }

    public /* synthetic */ TtsVoiceInfo(String str, boolean z, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z, str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4);
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        this.md5 = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        this.path = str;
    }
}
