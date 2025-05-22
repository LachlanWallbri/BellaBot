package com.pudutech.peanut.presenter.net.resp;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AuthorizationPackResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R.\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/net/resp/AuthorizationPackResp;", "", "()V", "authorization", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/net/resp/AuthorizationPackResp$AuthorizationPack;", "Lkotlin/collections/ArrayList;", "getAuthorization", "()Ljava/util/ArrayList;", "setAuthorization", "(Ljava/util/ArrayList;)V", "toString", "", "AuthorizationPack", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AuthorizationPackResp {
    private ArrayList<AuthorizationPack> authorization;

    public final ArrayList<AuthorizationPack> getAuthorization() {
        return this.authorization;
    }

    public final void setAuthorization(ArrayList<AuthorizationPack> arrayList) {
        this.authorization = arrayList;
    }

    /* compiled from: AuthorizationPackResp.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/net/resp/AuthorizationPackResp$AuthorizationPack;", "", "name", "", "status", "", "(Ljava/lang/String;I)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getStatus", "()I", "setStatus", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class AuthorizationPack {
        private String name;
        private int status;

        public static /* synthetic */ AuthorizationPack copy$default(AuthorizationPack authorizationPack, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = authorizationPack.name;
            }
            if ((i2 & 2) != 0) {
                i = authorizationPack.status;
            }
            return authorizationPack.copy(str, i);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        public final AuthorizationPack copy(String name, int status) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new AuthorizationPack(name, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AuthorizationPack)) {
                return false;
            }
            AuthorizationPack authorizationPack = (AuthorizationPack) other;
            return Intrinsics.areEqual(this.name, authorizationPack.name) && this.status == authorizationPack.status;
        }

        public int hashCode() {
            String str = this.name;
            return ((str != null ? str.hashCode() : 0) * 31) + this.status;
        }

        public String toString() {
            return "AuthorizationPack(name=" + this.name + ", status=" + this.status + ")";
        }

        public AuthorizationPack(String name, int i) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.name = name;
            this.status = i;
        }

        public final String getName() {
            return this.name;
        }

        public final int getStatus() {
            return this.status;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }

        public final void setStatus(int i) {
            this.status = i;
        }
    }

    public String toString() {
        return "AuthorizationPackResp(authorization=" + this.authorization + ')';
    }
}
