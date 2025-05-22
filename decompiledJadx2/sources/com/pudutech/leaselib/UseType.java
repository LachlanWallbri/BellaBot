package com.pudutech.leaselib;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LeaseSdk.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/leaselib/UseType;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "BUY", "TRIAL", "LEASE", "UNKNOW", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public enum UseType {
    BUY(1),
    TRIAL(2),
    LEASE(3),
    UNKNOW(0);

    private final int type;

    UseType(int i) {
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }
}
