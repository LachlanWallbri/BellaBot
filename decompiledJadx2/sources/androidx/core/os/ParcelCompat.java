package androidx.core.os;

import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class ParcelCompat {
    public static boolean readBoolean(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    private ParcelCompat() {
    }
}
