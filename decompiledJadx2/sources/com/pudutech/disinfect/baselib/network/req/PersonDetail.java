package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: PersonPortrait.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/PersonDetail;", "", "()V", "list", "", "", "getList", "()[[I", "setList", "([[I)V", "[[I", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PersonDetail {
    private int[][] list;

    public PersonDetail() {
        int[][] iArr = new int[10];
        for (int i = 0; i < 10; i++) {
            iArr[i] = new int[2];
        }
        this.list = iArr;
    }

    public final int[][] getList() {
        return this.list;
    }

    public final void setList(int[][] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "<set-?>");
        this.list = iArr;
    }
}
