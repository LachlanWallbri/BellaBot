package com.pudutech.opengl_draw.gltext;

/* loaded from: classes5.dex */
class TextureRegion {

    /* renamed from: u1 */
    public float f6826u1;

    /* renamed from: u2 */
    public float f6827u2;

    /* renamed from: v1 */
    public float f6828v1;

    /* renamed from: v2 */
    public float f6829v2;

    public TextureRegion(float f, float f2, float f3, float f4, float f5, float f6) {
        this.f6826u1 = f3 / f;
        this.f6828v1 = f4 / f2;
        this.f6827u2 = this.f6826u1 + (f5 / f);
        this.f6829v2 = this.f6828v1 + (f6 / f2);
    }
}
