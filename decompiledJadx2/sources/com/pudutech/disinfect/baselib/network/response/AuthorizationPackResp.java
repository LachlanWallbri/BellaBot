package com.pudutech.disinfect.baselib.network.response;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AuthorizationPackResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005\u0012\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u0005¢\u0006\u0002\u0010\bJ\u001d\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J\u001d\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u0005HÆ\u0003JE\u0010\u000e\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00052\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u0005HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0007HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010HÖ\u0001R%\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR%\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/AuthorizationPackResp;", "Landroid/os/Parcelable;", "authorization", "Ljava/util/ArrayList;", "Lcom/pudutech/disinfect/baselib/network/response/AuthorizationPackData;", "Lkotlin/collections/ArrayList;", "positions", "", "(Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getAuthorization", "()Ljava/util/ArrayList;", "getPositions", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class AuthorizationPackResp implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final ArrayList<AuthorizationPackData> authorization;
    private final ArrayList<String> positions;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(in, "in");
            ArrayList arrayList2 = null;
            if (in.readInt() != 0) {
                int readInt = in.readInt();
                arrayList = new ArrayList(readInt);
                while (readInt != 0) {
                    arrayList.add((AuthorizationPackData) AuthorizationPackData.CREATOR.createFromParcel(in));
                    readInt--;
                }
            } else {
                arrayList = null;
            }
            if (in.readInt() != 0) {
                int readInt2 = in.readInt();
                arrayList2 = new ArrayList(readInt2);
                while (readInt2 != 0) {
                    arrayList2.add(in.readString());
                    readInt2--;
                }
            }
            return new AuthorizationPackResp(arrayList, arrayList2);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new AuthorizationPackResp[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AuthorizationPackResp copy$default(AuthorizationPackResp authorizationPackResp, ArrayList arrayList, ArrayList arrayList2, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = authorizationPackResp.authorization;
        }
        if ((i & 2) != 0) {
            arrayList2 = authorizationPackResp.positions;
        }
        return authorizationPackResp.copy(arrayList, arrayList2);
    }

    public final ArrayList<AuthorizationPackData> component1() {
        return this.authorization;
    }

    public final ArrayList<String> component2() {
        return this.positions;
    }

    public final AuthorizationPackResp copy(ArrayList<AuthorizationPackData> authorization, ArrayList<String> positions) {
        return new AuthorizationPackResp(authorization, positions);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthorizationPackResp)) {
            return false;
        }
        AuthorizationPackResp authorizationPackResp = (AuthorizationPackResp) other;
        return Intrinsics.areEqual(this.authorization, authorizationPackResp.authorization) && Intrinsics.areEqual(this.positions, authorizationPackResp.positions);
    }

    public int hashCode() {
        ArrayList<AuthorizationPackData> arrayList = this.authorization;
        int hashCode = (arrayList != null ? arrayList.hashCode() : 0) * 31;
        ArrayList<String> arrayList2 = this.positions;
        return hashCode + (arrayList2 != null ? arrayList2.hashCode() : 0);
    }

    public String toString() {
        return "AuthorizationPackResp(authorization=" + this.authorization + ", positions=" + this.positions + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        ArrayList<AuthorizationPackData> arrayList = this.authorization;
        if (arrayList != null) {
            parcel.writeInt(1);
            parcel.writeInt(arrayList.size());
            Iterator<AuthorizationPackData> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().writeToParcel(parcel, 0);
            }
        } else {
            parcel.writeInt(0);
        }
        ArrayList<String> arrayList2 = this.positions;
        if (arrayList2 == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(arrayList2.size());
        Iterator<String> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            parcel.writeString(it2.next());
        }
    }

    public AuthorizationPackResp(ArrayList<AuthorizationPackData> arrayList, ArrayList<String> arrayList2) {
        this.authorization = arrayList;
        this.positions = arrayList2;
    }

    public final ArrayList<AuthorizationPackData> getAuthorization() {
        return this.authorization;
    }

    public final ArrayList<String> getPositions() {
        return this.positions;
    }
}
