package com.pudutech.bumblebee.business.peripherals_task.led_task;

import java.util.Objects;

/* loaded from: classes4.dex */
public class Color {

    /* renamed from: B */
    public int f4601B;

    /* renamed from: G */
    public int f4602G;

    /* renamed from: R */
    public int f4603R;
    public boolean sameAsLast;

    public Color() {
        this.sameAsLast = false;
        this.f4603R = 0;
        this.f4602G = 0;
        this.f4601B = 0;
    }

    public Color(int i, int i2, int i3) {
        this.sameAsLast = false;
        this.f4603R = 0;
        this.f4602G = 0;
        this.f4601B = 0;
        this.f4603R = i;
        this.f4602G = i2;
        this.f4601B = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Color color = (Color) obj;
        return this.f4603R == color.f4603R && this.f4602G == color.f4602G && this.f4601B == color.f4601B;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.f4603R), Integer.valueOf(this.f4602G), Integer.valueOf(this.f4601B));
    }

    public String toString() {
        return "Color{R=" + this.f4603R + ", G=" + this.f4602G + ", B=" + this.f4601B + '}';
    }
}
