package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ShadowData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowErrorContent;", "", "errorcode", "", "errormessage", "(Ljava/lang/String;Ljava/lang/String;)V", "getErrorcode", "()Ljava/lang/String;", "getErrormessage", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowErrorContent {
    private final String errorcode;
    private final String errormessage;

    public static /* synthetic */ ShadowErrorContent copy$default(ShadowErrorContent shadowErrorContent, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = shadowErrorContent.errorcode;
        }
        if ((i & 2) != 0) {
            str2 = shadowErrorContent.errormessage;
        }
        return shadowErrorContent.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getErrorcode() {
        return this.errorcode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getErrormessage() {
        return this.errormessage;
    }

    public final ShadowErrorContent copy(String errorcode, String errormessage) {
        Intrinsics.checkParameterIsNotNull(errorcode, "errorcode");
        Intrinsics.checkParameterIsNotNull(errormessage, "errormessage");
        return new ShadowErrorContent(errorcode, errormessage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowErrorContent)) {
            return false;
        }
        ShadowErrorContent shadowErrorContent = (ShadowErrorContent) other;
        return Intrinsics.areEqual(this.errorcode, shadowErrorContent.errorcode) && Intrinsics.areEqual(this.errormessage, shadowErrorContent.errormessage);
    }

    public int hashCode() {
        String str = this.errorcode;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.errormessage;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ShadowErrorContent(errorcode=" + this.errorcode + ", errormessage=" + this.errormessage + ")";
    }

    public ShadowErrorContent(String errorcode, String errormessage) {
        Intrinsics.checkParameterIsNotNull(errorcode, "errorcode");
        Intrinsics.checkParameterIsNotNull(errormessage, "errormessage");
        this.errorcode = errorcode;
        this.errormessage = errormessage;
    }

    public final String getErrorcode() {
        return this.errorcode;
    }

    public final String getErrormessage() {
        return this.errormessage;
    }
}
