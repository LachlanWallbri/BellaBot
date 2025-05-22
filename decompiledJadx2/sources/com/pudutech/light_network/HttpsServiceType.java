package com.pudutech.light_network;

import kotlin.Metadata;
import org.apache.http.HttpVersion;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HttpsServiceType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/light_network/HttpsServiceType;", "", "id", "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getId", "()B", "B", HttpVersion.HTTP, "Upgrade", "Cloud", "IOT", "IOTSR", "DEBUGSRV", "lib_network_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public enum HttpsServiceType {
    HTTP((byte) 0),
    Upgrade((byte) 1),
    Cloud((byte) 2),
    IOT((byte) 3),
    IOTSR((byte) 4),
    DEBUGSRV((byte) 5);

    private final byte id;

    HttpsServiceType(byte b) {
        this.id = b;
    }

    public final byte getId() {
        return this.id;
    }
}
