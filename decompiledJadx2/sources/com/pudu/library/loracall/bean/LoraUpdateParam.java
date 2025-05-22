package com.pudu.library.loracall.bean;

import com.pudu.library.loracall.KotlinUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.strategy.Name;

/* compiled from: LoraUpdateParam.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u0015"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/LoraUpdateParam;", "", "data", "", "([B)V", "getData", "()[B", "indexData", "", "getIndexData", "()I", "setIndexData", "(I)V", Name.LENGTH, "getLength", "setLength", "type", "getType", "setType", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoraUpdateParam {
    private final byte[] data;
    private int indexData;
    private int length;
    private int type;

    public LoraUpdateParam(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.data = data;
        byte[] bArr = this.data;
        if (bArr.length >= 7) {
            this.type = bArr[0];
            this.indexData = KotlinUtilsKt.bytesToInt(bArr, 1);
            byte[] bArr2 = this.data;
            this.length = ((bArr2[6] & 255) << 8) | (bArr2[5] & 255);
        }
    }

    public final byte[] getData() {
        return this.data;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final int getIndexData() {
        return this.indexData;
    }

    public final void setIndexData(int i) {
        this.indexData = i;
    }

    public final int getLength() {
        return this.length;
    }

    public final void setLength(int i) {
        this.length = i;
    }

    public String toString() {
        return "{\ntype:" + this.type + "\nindexData:" + this.indexData + "\nlength:" + this.length + "\n}";
    }
}
