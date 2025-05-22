package com.pudutech.disinfect.baselib.image;

import kotlin.Metadata;

/* compiled from: DisplayConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/image/DisplayConfig;", "", "()V", "id_err_image", "", "id_holder_image", "DisplayConfig", "", "getId_err_image", "getId_holder_image", "setId_err_image", "setId_holder_image", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DisplayConfig {
    private int id_err_image;
    private int id_holder_image;

    public final void DisplayConfig() {
    }

    public final void DisplayConfig(int id_holder_image, int id_err_image) {
        this.id_holder_image = id_holder_image;
        this.id_err_image = id_err_image;
    }

    public final void DisplayConfig(int id_err_image) {
        this.id_err_image = id_err_image;
    }

    public final int getId_holder_image() {
        return this.id_holder_image;
    }

    public final void setId_holder_image(int id_holder_image) {
        this.id_holder_image = id_holder_image;
    }

    public final int getId_err_image() {
        return this.id_err_image;
    }

    public final void setId_err_image(int id_err_image) {
        this.id_err_image = id_err_image;
    }
}
