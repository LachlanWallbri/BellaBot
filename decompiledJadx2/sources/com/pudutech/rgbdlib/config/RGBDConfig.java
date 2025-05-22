package com.pudutech.rgbdlib.config;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: RGBDConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000f¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/rgbdlib/config/RGBDConfig;", "", "left_rgbd", "Lcom/pudutech/rgbdlib/config/RGBDJson;", "right_rgbd", "center_rgbd", "down_rgbd", "data", "Lcom/pudutech/rgbdlib/config/DataJson;", "reset", "", "(Lcom/pudutech/rgbdlib/config/RGBDJson;Lcom/pudutech/rgbdlib/config/RGBDJson;Lcom/pudutech/rgbdlib/config/RGBDJson;Lcom/pudutech/rgbdlib/config/RGBDJson;Lcom/pudutech/rgbdlib/config/DataJson;Ljava/lang/Boolean;)V", "getCenter_rgbd", "()Lcom/pudutech/rgbdlib/config/RGBDJson;", "setCenter_rgbd", "(Lcom/pudutech/rgbdlib/config/RGBDJson;)V", "getData", "()Lcom/pudutech/rgbdlib/config/DataJson;", "setData", "(Lcom/pudutech/rgbdlib/config/DataJson;)V", "getDown_rgbd", "setDown_rgbd", "getLeft_rgbd", "setLeft_rgbd", "getReset", "()Ljava/lang/Boolean;", "setReset", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getRight_rgbd", "setRight_rgbd", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class RGBDConfig {
    private RGBDJson center_rgbd;
    private DataJson data;
    private RGBDJson down_rgbd;
    private RGBDJson left_rgbd;
    private Boolean reset;
    private RGBDJson right_rgbd;

    public RGBDConfig(RGBDJson rGBDJson, RGBDJson rGBDJson2, RGBDJson rGBDJson3, RGBDJson rGBDJson4, DataJson dataJson, Boolean bool) {
        this.left_rgbd = rGBDJson;
        this.right_rgbd = rGBDJson2;
        this.center_rgbd = rGBDJson3;
        this.down_rgbd = rGBDJson4;
        this.data = dataJson;
        this.reset = bool;
    }

    public final RGBDJson getLeft_rgbd() {
        return this.left_rgbd;
    }

    public final void setLeft_rgbd(RGBDJson rGBDJson) {
        this.left_rgbd = rGBDJson;
    }

    public final RGBDJson getRight_rgbd() {
        return this.right_rgbd;
    }

    public final void setRight_rgbd(RGBDJson rGBDJson) {
        this.right_rgbd = rGBDJson;
    }

    public final RGBDJson getCenter_rgbd() {
        return this.center_rgbd;
    }

    public final void setCenter_rgbd(RGBDJson rGBDJson) {
        this.center_rgbd = rGBDJson;
    }

    public final RGBDJson getDown_rgbd() {
        return this.down_rgbd;
    }

    public final void setDown_rgbd(RGBDJson rGBDJson) {
        this.down_rgbd = rGBDJson;
    }

    public final DataJson getData() {
        return this.data;
    }

    public final void setData(DataJson dataJson) {
        this.data = dataJson;
    }

    public final Boolean getReset() {
        return this.reset;
    }

    public final void setReset(Boolean bool) {
        this.reset = bool;
    }
}
