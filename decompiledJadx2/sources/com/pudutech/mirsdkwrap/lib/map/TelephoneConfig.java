package com.pudutech.mirsdkwrap.lib.map;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: TelephoneConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R.\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/TelephoneConfig;", "", "phoneBook", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/Telephone;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getPhoneBook", "()Ljava/util/ArrayList;", "setPhoneBook", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class TelephoneConfig {

    @SerializedName("phone_book")
    private ArrayList<Telephone> phoneBook;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TelephoneConfig copy$default(TelephoneConfig telephoneConfig, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = telephoneConfig.phoneBook;
        }
        return telephoneConfig.copy(arrayList);
    }

    public final ArrayList<Telephone> component1() {
        return this.phoneBook;
    }

    public final TelephoneConfig copy(ArrayList<Telephone> phoneBook) {
        Intrinsics.checkParameterIsNotNull(phoneBook, "phoneBook");
        return new TelephoneConfig(phoneBook);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof TelephoneConfig) && Intrinsics.areEqual(this.phoneBook, ((TelephoneConfig) other).phoneBook);
        }
        return true;
    }

    public int hashCode() {
        ArrayList<Telephone> arrayList = this.phoneBook;
        if (arrayList != null) {
            return arrayList.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "TelephoneConfig(phoneBook=" + this.phoneBook + ")";
    }

    public TelephoneConfig(ArrayList<Telephone> phoneBook) {
        Intrinsics.checkParameterIsNotNull(phoneBook, "phoneBook");
        this.phoneBook = phoneBook;
    }

    public final ArrayList<Telephone> getPhoneBook() {
        return this.phoneBook;
    }

    public final void setPhoneBook(ArrayList<Telephone> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.phoneBook = arrayList;
    }
}
