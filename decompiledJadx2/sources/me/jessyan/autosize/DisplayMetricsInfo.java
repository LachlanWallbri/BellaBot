package me.jessyan.autosize;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes8.dex */
public class DisplayMetricsInfo implements Parcelable {
    public static final Parcelable.Creator<DisplayMetricsInfo> CREATOR = new Parcelable.Creator<DisplayMetricsInfo>() { // from class: me.jessyan.autosize.DisplayMetricsInfo.1
        @Override // android.os.Parcelable.Creator
        public DisplayMetricsInfo createFromParcel(Parcel parcel) {
            return new DisplayMetricsInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DisplayMetricsInfo[] newArray(int i) {
            return new DisplayMetricsInfo[i];
        }
    };
    private float density;
    private int densityDpi;
    private float scaledDensity;
    private float xdpi;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DisplayMetricsInfo(float f, int i, float f2, float f3) {
        this.density = f;
        this.densityDpi = i;
        this.scaledDensity = f2;
        this.xdpi = f3;
    }

    public float getDensity() {
        return this.density;
    }

    public void setDensity(float f) {
        this.density = f;
    }

    public int getDensityDpi() {
        return this.densityDpi;
    }

    public void setDensityDpi(int i) {
        this.densityDpi = i;
    }

    public float getScaledDensity() {
        return this.scaledDensity;
    }

    public void setScaledDensity(float f) {
        this.scaledDensity = f;
    }

    public float getXdpi() {
        return this.xdpi;
    }

    public void setXdpi(float f) {
        this.xdpi = f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.density);
        parcel.writeInt(this.densityDpi);
        parcel.writeFloat(this.scaledDensity);
        parcel.writeFloat(this.xdpi);
    }

    protected DisplayMetricsInfo(Parcel parcel) {
        this.density = parcel.readFloat();
        this.densityDpi = parcel.readInt();
        this.scaledDensity = parcel.readFloat();
        this.xdpi = parcel.readFloat();
    }

    public String toString() {
        return "DisplayMetricsInfo{density=" + this.density + ", densityDpi=" + this.densityDpi + ", scaledDensity=" + this.scaledDensity + ", xdpi=" + this.xdpi + '}';
    }
}
