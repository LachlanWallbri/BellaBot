package com.pudutech.rgbdlib.config;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: RGBDJson.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B5\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/rgbdlib/config/RGBDJson;", "", "type", "", "serial", "shield", "", "model", "extrinsics", "Lcom/pudutech/rgbdlib/config/ExtrinsicsJson;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lcom/pudutech/rgbdlib/config/ExtrinsicsJson;)V", "getExtrinsics", "()Lcom/pudutech/rgbdlib/config/ExtrinsicsJson;", "setExtrinsics", "(Lcom/pudutech/rgbdlib/config/ExtrinsicsJson;)V", "getModel", "()Ljava/lang/String;", "setModel", "(Ljava/lang/String;)V", "getSerial", "setSerial", "getShield", "()Ljava/lang/Boolean;", "setShield", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getType", "setType", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class RGBDJson {
    private ExtrinsicsJson extrinsics;
    private String model;
    private String serial;
    private Boolean shield;
    private String type;

    public RGBDJson(String str, String serial, Boolean bool, String str2, ExtrinsicsJson extrinsicsJson) {
        Intrinsics.checkParameterIsNotNull(serial, "serial");
        this.type = str;
        this.serial = serial;
        this.shield = bool;
        this.model = str2;
        this.extrinsics = extrinsicsJson;
    }

    public final ExtrinsicsJson getExtrinsics() {
        return this.extrinsics;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getSerial() {
        return this.serial;
    }

    public final Boolean getShield() {
        return this.shield;
    }

    public final String getType() {
        return this.type;
    }

    public final void setExtrinsics(ExtrinsicsJson extrinsicsJson) {
        this.extrinsics = extrinsicsJson;
    }

    public final void setModel(String str) {
        this.model = str;
    }

    public final void setSerial(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.serial = str;
    }

    public final void setShield(Boolean bool) {
        this.shield = bool;
    }

    public final void setType(String str) {
        this.type = str;
    }
}
