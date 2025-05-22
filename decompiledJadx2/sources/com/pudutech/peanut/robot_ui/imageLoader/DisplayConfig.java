package com.pudutech.peanut.robot_ui.imageLoader;

/* loaded from: classes5.dex */
public class DisplayConfig {
    private int id_err_image;
    private int id_holder_image;

    public DisplayConfig() {
    }

    public DisplayConfig(int i, int i2) {
        this.id_holder_image = i;
        this.id_err_image = i2;
    }

    public DisplayConfig(int i) {
        this.id_err_image = i;
    }

    public int getId_holder_image() {
        return this.id_holder_image;
    }

    public void setId_holder_image(int i) {
        this.id_holder_image = i;
    }

    public int getId_err_image() {
        return this.id_err_image;
    }

    public void setId_err_image(int i) {
        this.id_err_image = i;
    }
}
