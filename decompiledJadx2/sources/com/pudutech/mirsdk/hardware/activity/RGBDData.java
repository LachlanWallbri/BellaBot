package com.pudutech.mirsdk.hardware.activity;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: RGBDData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\"\u0018\u00002\u00020\u0001Bh\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\bø\u0001\u0000¢\u0006\u0002\u0010\u0012R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001f\u0010\u0004\u001a\u00020\u0003X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b!\u0010\u001e\"\u0004\b\"\u0010 R\u001f\u0010\u0002\u001a\u00020\u0003X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b$\u0010\u001e\"\u0004\b%\u0010 R\u001f\u0010\u000e\u001a\u00020\u0003X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b&\u0010\u001e\"\u0004\b'\u0010 R\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0018\"\u0004\b+\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0018\"\u0004\b-\u0010\u001aR\u001a\u0010\u0011\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001e\"\u0004\b/\u0010 R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001e\"\u0004\b1\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/RGBDData;", "", "llsecs", "Lkotlin/UInt;", "jlnsecs", "scale", "", "width", "", "height", "ppx", "ppy", "fx", "fy", "model", "coeffs", "", "serial_length", "(IIFIIFFFFI[FILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCoeffs", "()[F", "setCoeffs", "([F)V", "getFx", "()F", "setFx", "(F)V", "getFy", "setFy", "getHeight", "()I", "setHeight", "(I)V", "getJlnsecs", "setJlnsecs-WZ4Q5Ns", "I", "getLlsecs", "setLlsecs-WZ4Q5Ns", "getModel", "setModel-WZ4Q5Ns", "getPpx", "setPpx", "getPpy", "setPpy", "getScale", "setScale", "getSerial_length", "setSerial_length", "getWidth", "setWidth", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class RGBDData {
    private float[] coeffs;
    private float fx;
    private float fy;
    private int height;
    private int jlnsecs;
    private int llsecs;
    private int model;
    private float ppx;
    private float ppy;
    private float scale;
    private int serial_length;
    private int width;

    private RGBDData(int i, int i2, float f, int i3, int i4, float f2, float f3, float f4, float f5, int i5, float[] fArr, int i6) {
        this.llsecs = i;
        this.jlnsecs = i2;
        this.scale = f;
        this.width = i3;
        this.height = i4;
        this.ppx = f2;
        this.ppy = f3;
        this.fx = f4;
        this.fy = f5;
        this.model = i5;
        this.coeffs = fArr;
        this.serial_length = i6;
    }

    public /* synthetic */ RGBDData(int i, int i2, float f, int i3, int i4, float f2, float f3, float f4, float f5, int i5, float[] fArr, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, f, i3, i4, f2, f3, f4, f5, i5, fArr, i6);
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getJlnsecs() {
        return this.jlnsecs;
    }

    public final int getLlsecs() {
        return this.llsecs;
    }

    public final float getScale() {
        return this.scale;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    /* renamed from: setJlnsecs-WZ4Q5Ns, reason: not valid java name */
    public final void m4417setJlnsecsWZ4Q5Ns(int i) {
        this.jlnsecs = i;
    }

    /* renamed from: setLlsecs-WZ4Q5Ns, reason: not valid java name */
    public final void m4418setLlsecsWZ4Q5Ns(int i) {
        this.llsecs = i;
    }

    public final void setScale(float f) {
        this.scale = f;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final float[] getCoeffs() {
        return this.coeffs;
    }

    public final float getFx() {
        return this.fx;
    }

    public final float getFy() {
        return this.fy;
    }

    public final int getModel() {
        return this.model;
    }

    public final float getPpx() {
        return this.ppx;
    }

    public final float getPpy() {
        return this.ppy;
    }

    public final int getSerial_length() {
        return this.serial_length;
    }

    public final void setCoeffs(float[] fArr) {
        Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
        this.coeffs = fArr;
    }

    public final void setFx(float f) {
        this.fx = f;
    }

    public final void setFy(float f) {
        this.fy = f;
    }

    /* renamed from: setModel-WZ4Q5Ns, reason: not valid java name */
    public final void m4419setModelWZ4Q5Ns(int i) {
        this.model = i;
    }

    public final void setPpx(float f) {
        this.ppx = f;
    }

    public final void setPpy(float f) {
        this.ppy = f;
    }

    public final void setSerial_length(int i) {
        this.serial_length = i;
    }
}
