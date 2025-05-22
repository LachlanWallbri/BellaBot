package com.pudutech.rgbdlib.config;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: DataJson.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/rgbdlib/config/DataJson;", "", "path", "", "record", "", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getPath", "()Ljava/lang/String;", "setPath", "(Ljava/lang/String;)V", "getRecord", "()Ljava/lang/Integer;", "setRecord", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DataJson {
    private String path;
    private Integer record;

    public DataJson(String str, Integer num) {
        this.path = str;
        this.record = num;
    }

    public final String getPath() {
        return this.path;
    }

    public final Integer getRecord() {
        return this.record;
    }

    public final void setPath(String str) {
        this.path = str;
    }

    public final void setRecord(Integer num) {
        this.record = num;
    }
}
