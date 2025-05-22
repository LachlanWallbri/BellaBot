package com.pudutech.lidar.ld06;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: LD06DataStruct.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/lidar/ld06/LD06DataStruct;", "", "()V", "header", "", "getHeader", "()B", "len", "", "getLen", "()I", "structBytesSize", "getStructBytesSize", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LD06DataStruct {
    private final byte header = (byte) 84;
    private final int len = 12;
    private final int structBytesSize = 47;

    public final byte getHeader() {
        return this.header;
    }

    public final int getLen() {
        return this.len;
    }

    public final int getStructBytesSize() {
        return this.structBytesSize;
    }
}
