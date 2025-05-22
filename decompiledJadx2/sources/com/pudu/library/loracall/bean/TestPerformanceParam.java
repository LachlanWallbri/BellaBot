package com.pudu.library.loracall.bean;

import kotlin.Metadata;

/* compiled from: TestPerformanceParam.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/TestPerformanceParam;", "", "number", "", "isJoin", "", "(BZ)V", "data", "", "getData", "()[B", "()Z", "getNumber", "()B", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class TestPerformanceParam {
    private final byte[] data;
    private final boolean isJoin;
    private final byte number;

    public static /* synthetic */ TestPerformanceParam copy$default(TestPerformanceParam testPerformanceParam, byte b, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            b = testPerformanceParam.number;
        }
        if ((i & 2) != 0) {
            z = testPerformanceParam.isJoin;
        }
        return testPerformanceParam.copy(b, z);
    }

    /* renamed from: component1, reason: from getter */
    public final byte getNumber() {
        return this.number;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsJoin() {
        return this.isJoin;
    }

    public final TestPerformanceParam copy(byte number, boolean isJoin) {
        return new TestPerformanceParam(number, isJoin);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TestPerformanceParam)) {
            return false;
        }
        TestPerformanceParam testPerformanceParam = (TestPerformanceParam) other;
        return this.number == testPerformanceParam.number && this.isJoin == testPerformanceParam.isJoin;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = Byte.hashCode(this.number) * 31;
        boolean z = this.isJoin;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "TestPerformanceParam(number=" + ((int) this.number) + ", isJoin=" + this.isJoin + ")";
    }

    public TestPerformanceParam(byte b, boolean z) {
        this.number = b;
        this.isJoin = z;
        this.data = new byte[]{this.number, this.isJoin};
    }

    public final byte getNumber() {
        return this.number;
    }

    public final boolean isJoin() {
        return this.isJoin;
    }

    public final byte[] getData() {
        return this.data;
    }
}
