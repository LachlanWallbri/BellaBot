package com.pudutech.pd_network.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: verify.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GetSecretRes;", "", "secret", "", "(Ljava/lang/String;)V", "getSecret", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class GetSecretRes {
    private final String secret;

    public static /* synthetic */ GetSecretRes copy$default(GetSecretRes getSecretRes, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = getSecretRes.secret;
        }
        return getSecretRes.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSecret() {
        return this.secret;
    }

    public final GetSecretRes copy(String secret) {
        Intrinsics.checkParameterIsNotNull(secret, "secret");
        return new GetSecretRes(secret);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof GetSecretRes) && Intrinsics.areEqual(this.secret, ((GetSecretRes) other).secret);
        }
        return true;
    }

    public int hashCode() {
        String str = this.secret;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "GetSecretRes(secret=" + this.secret + ")";
    }

    public GetSecretRes(String secret) {
        Intrinsics.checkParameterIsNotNull(secret, "secret");
        this.secret = secret;
    }

    public final String getSecret() {
        return this.secret;
    }
}
