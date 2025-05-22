package com.pudutech.voiceinteraction.component.config;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/config/UserInfo;", "", AIUIConstant.KEY_UID, "", "(Ljava/lang/String;)V", "getUid", "()Ljava/lang/String;", "setUid", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class UserInfo {
    private String uid;

    public static /* synthetic */ UserInfo copy$default(UserInfo userInfo, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userInfo.uid;
        }
        return userInfo.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUid() {
        return this.uid;
    }

    public final UserInfo copy(String uid) {
        Intrinsics.checkParameterIsNotNull(uid, "uid");
        return new UserInfo(uid);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof UserInfo) && Intrinsics.areEqual(this.uid, ((UserInfo) other).uid);
        }
        return true;
    }

    public int hashCode() {
        String str = this.uid;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "UserInfo(uid=" + this.uid + ")";
    }

    public UserInfo(String uid) {
        Intrinsics.checkParameterIsNotNull(uid, "uid");
        this.uid = uid;
    }

    public final String getUid() {
        return this.uid;
    }

    public final void setUid(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.uid = str;
    }
}
