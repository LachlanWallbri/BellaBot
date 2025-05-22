package com.pudutech.disinfect.baselib.network.response.ad;

import android.os.Parcel;
import android.os.Parcelable;
import com.iflytek.cloud.SpeechConstant;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdverListResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B¡\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f\u0012\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\u001a\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000f\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0014J\u0006\u0010)\u001a\u00020\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\bHÆ\u0003J\u001d\u0010,\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000fHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\bHÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\u0019\u00104\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fHÆ\u0003J\u001d\u00105\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000fHÆ\u0003J½\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f2\u001c\b\u0002\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b2\u001c\b\u0002\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0001J\t\u00107\u001a\u00020\u0003HÖ\u0001J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;HÖ\u0003J\t\u0010<\u001a\u00020\u0003HÖ\u0001J\u0006\u0010=\u001a\u00020\u0003J\t\u0010>\u001a\u00020\bHÖ\u0001J\u0019\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0003HÖ\u0001R!\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR%\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR%\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001a\"\u0004\b&\u0010'R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001a¨\u0006D"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/ad/AdInfo;", "Landroid/os/Parcelable;", "id", "", "end_time", "", "start_time", "name", "", "second", "times", "template", "ad_list", "Ljava/util/ArrayList;", "Lcom/pudutech/disinfect/baselib/network/response/ad/Material;", "Lkotlin/collections/ArrayList;", SpeechConstant.MFV_SCENES, "map_name", "map_points", "kind", "(IJJLjava/lang/String;IIILjava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;)V", "getAd_list", "()Ljava/util/ArrayList;", "getEnd_time", "()J", "getId", "()I", "getKind", "()Ljava/lang/String;", "setKind", "(Ljava/lang/String;)V", "getMap_name", "getMap_points", "getName", "getScenes", "getSecond", "getStart_time", "getTemplate", "setTemplate", "(I)V", "getTimes", "adType", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "hashCode", "mediaType", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class AdInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final ArrayList<Material> ad_list;
    private final long end_time;
    private final int id;
    private String kind;
    private final String map_name;
    private final ArrayList<String> map_points;
    private final String name;
    private final ArrayList<String> scenes;
    private final int second;
    private final long start_time;
    private int template;
    private final int times;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(in, "in");
            int readInt = in.readInt();
            long readLong = in.readLong();
            long readLong2 = in.readLong();
            String readString = in.readString();
            int readInt2 = in.readInt();
            int readInt3 = in.readInt();
            int readInt4 = in.readInt();
            int readInt5 = in.readInt();
            ArrayList arrayList2 = new ArrayList(readInt5);
            while (readInt5 != 0) {
                arrayList2.add((Material) Material.CREATOR.createFromParcel(in));
                readInt5--;
            }
            ArrayList arrayList3 = null;
            if (in.readInt() != 0) {
                int readInt6 = in.readInt();
                arrayList = new ArrayList(readInt6);
                while (readInt6 != 0) {
                    arrayList.add(in.readString());
                    readInt6--;
                }
            } else {
                arrayList = null;
            }
            String readString2 = in.readString();
            if (in.readInt() != 0) {
                int readInt7 = in.readInt();
                arrayList3 = new ArrayList(readInt7);
                while (readInt7 != 0) {
                    arrayList3.add(in.readString());
                    readInt7--;
                }
            }
            return new AdInfo(readInt, readLong, readLong2, readString, readInt2, readInt3, readInt4, arrayList2, arrayList, readString2, arrayList3, in.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new AdInfo[i];
        }
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getMap_name() {
        return this.map_name;
    }

    public final ArrayList<String> component11() {
        return this.map_points;
    }

    /* renamed from: component12, reason: from getter */
    public final String getKind() {
        return this.kind;
    }

    /* renamed from: component2, reason: from getter */
    public final long getEnd_time() {
        return this.end_time;
    }

    /* renamed from: component3, reason: from getter */
    public final long getStart_time() {
        return this.start_time;
    }

    /* renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component5, reason: from getter */
    public final int getSecond() {
        return this.second;
    }

    /* renamed from: component6, reason: from getter */
    public final int getTimes() {
        return this.times;
    }

    /* renamed from: component7, reason: from getter */
    public final int getTemplate() {
        return this.template;
    }

    public final ArrayList<Material> component8() {
        return this.ad_list;
    }

    public final ArrayList<String> component9() {
        return this.scenes;
    }

    public final AdInfo copy(int id, long end_time, long start_time, String name, int second, int times, int template, ArrayList<Material> ad_list, ArrayList<String> scenes, String map_name, ArrayList<String> map_points, String kind) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(ad_list, "ad_list");
        return new AdInfo(id, end_time, start_time, name, second, times, template, ad_list, scenes, map_name, map_points, kind);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdInfo)) {
            return false;
        }
        AdInfo adInfo = (AdInfo) other;
        return this.id == adInfo.id && this.end_time == adInfo.end_time && this.start_time == adInfo.start_time && Intrinsics.areEqual(this.name, adInfo.name) && this.second == adInfo.second && this.times == adInfo.times && this.template == adInfo.template && Intrinsics.areEqual(this.ad_list, adInfo.ad_list) && Intrinsics.areEqual(this.scenes, adInfo.scenes) && Intrinsics.areEqual(this.map_name, adInfo.map_name) && Intrinsics.areEqual(this.map_points, adInfo.map_points) && Intrinsics.areEqual(this.kind, adInfo.kind);
    }

    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.id) * 31) + Long.hashCode(this.end_time)) * 31) + Long.hashCode(this.start_time)) * 31;
        String str = this.name;
        int hashCode2 = (((((((hashCode + (str != null ? str.hashCode() : 0)) * 31) + Integer.hashCode(this.second)) * 31) + Integer.hashCode(this.times)) * 31) + Integer.hashCode(this.template)) * 31;
        ArrayList<Material> arrayList = this.ad_list;
        int hashCode3 = (hashCode2 + (arrayList != null ? arrayList.hashCode() : 0)) * 31;
        ArrayList<String> arrayList2 = this.scenes;
        int hashCode4 = (hashCode3 + (arrayList2 != null ? arrayList2.hashCode() : 0)) * 31;
        String str2 = this.map_name;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        ArrayList<String> arrayList3 = this.map_points;
        int hashCode6 = (hashCode5 + (arrayList3 != null ? arrayList3.hashCode() : 0)) * 31;
        String str3 = this.kind;
        return hashCode6 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "AdInfo(id=" + this.id + ", end_time=" + this.end_time + ", start_time=" + this.start_time + ", name=" + this.name + ", second=" + this.second + ", times=" + this.times + ", template=" + this.template + ", ad_list=" + this.ad_list + ", scenes=" + this.scenes + ", map_name=" + this.map_name + ", map_points=" + this.map_points + ", kind=" + this.kind + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeInt(this.id);
        parcel.writeLong(this.end_time);
        parcel.writeLong(this.start_time);
        parcel.writeString(this.name);
        parcel.writeInt(this.second);
        parcel.writeInt(this.times);
        parcel.writeInt(this.template);
        ArrayList<Material> arrayList = this.ad_list;
        parcel.writeInt(arrayList.size());
        Iterator<Material> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(parcel, 0);
        }
        ArrayList<String> arrayList2 = this.scenes;
        if (arrayList2 != null) {
            parcel.writeInt(1);
            parcel.writeInt(arrayList2.size());
            Iterator<String> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                parcel.writeString(it2.next());
            }
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.map_name);
        ArrayList<String> arrayList3 = this.map_points;
        if (arrayList3 != null) {
            parcel.writeInt(1);
            parcel.writeInt(arrayList3.size());
            Iterator<String> it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                parcel.writeString(it3.next());
            }
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.kind);
    }

    public AdInfo(int i, long j, long j2, String name, int i2, int i3, int i4, ArrayList<Material> ad_list, ArrayList<String> arrayList, String str, ArrayList<String> arrayList2, String str2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(ad_list, "ad_list");
        this.id = i;
        this.end_time = j;
        this.start_time = j2;
        this.name = name;
        this.second = i2;
        this.times = i3;
        this.template = i4;
        this.ad_list = ad_list;
        this.scenes = arrayList;
        this.map_name = str;
        this.map_points = arrayList2;
        this.kind = str2;
    }

    public final int getId() {
        return this.id;
    }

    public final long getEnd_time() {
        return this.end_time;
    }

    public final long getStart_time() {
        return this.start_time;
    }

    public final String getName() {
        return this.name;
    }

    public final int getSecond() {
        return this.second;
    }

    public final int getTimes() {
        return this.times;
    }

    public final int getTemplate() {
        return this.template;
    }

    public final void setTemplate(int i) {
        this.template = i;
    }

    public final ArrayList<Material> getAd_list() {
        return this.ad_list;
    }

    public final ArrayList<String> getScenes() {
        return this.scenes;
    }

    public final String getMap_name() {
        return this.map_name;
    }

    public final ArrayList<String> getMap_points() {
        return this.map_points;
    }

    public final String getKind() {
        return this.kind;
    }

    public final void setKind(String str) {
        this.kind = str;
    }

    public final int adType() {
        String str = this.kind;
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -1899802323) {
                if (hashCode == -17240885) {
                    if (str.equals("kind_tiny")) {
                        return 1;
                    }
                } else if (hashCode == 1953806039 && str.equals("kind_advance")) {
                    return 2;
                }
            } else if (str.equals("kind_ordinary")) {
                return 3;
            }
        }
        return -1;
    }

    public final int mediaType() {
        int i = this.template;
        if (i == 0 || i == 1) {
            return this.template;
        }
        if (i != 2 || this.ad_list.size() == 0) {
            return 2;
        }
        ArrayList<Material> arrayList = this.ad_list;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((Material) next).getType() == 0) {
                arrayList2.add(next);
            }
        }
        if (arrayList2.size() > 0) {
            return 0;
        }
        ArrayList<Material> arrayList3 = this.ad_list;
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : arrayList3) {
            if (((Material) obj).getType() == 1) {
                arrayList4.add(obj);
            }
        }
        return arrayList4.size() > 0 ? 1 : 2;
    }
}
