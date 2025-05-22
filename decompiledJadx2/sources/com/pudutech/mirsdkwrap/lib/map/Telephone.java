package com.pudutech.mirsdkwrap.lib.map;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: TelephoneConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\b\u0010\u0014\u001a\u00020\u0003H\u0016R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/Telephone;", "", MqttServiceConstants.DESTINATION_NAME, "", "phoneNumber", "(Ljava/lang/String;Ljava/lang/String;)V", "getDestinationName", "()Ljava/lang/String;", "setDestinationName", "(Ljava/lang/String;)V", "getPhoneNumber", "setPhoneNumber", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class Telephone {

    @SerializedName("id")
    private String destinationName;

    @SerializedName("number")
    private String phoneNumber;

    public static /* synthetic */ Telephone copy$default(Telephone telephone, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = telephone.destinationName;
        }
        if ((i & 2) != 0) {
            str2 = telephone.phoneNumber;
        }
        return telephone.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDestinationName() {
        return this.destinationName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final Telephone copy(String destinationName, String phoneNumber) {
        Intrinsics.checkParameterIsNotNull(destinationName, "destinationName");
        Intrinsics.checkParameterIsNotNull(phoneNumber, "phoneNumber");
        return new Telephone(destinationName, phoneNumber);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Telephone)) {
            return false;
        }
        Telephone telephone = (Telephone) other;
        return Intrinsics.areEqual(this.destinationName, telephone.destinationName) && Intrinsics.areEqual(this.phoneNumber, telephone.phoneNumber);
    }

    public int hashCode() {
        String str = this.destinationName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.phoneNumber;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public Telephone(String destinationName, String phoneNumber) {
        Intrinsics.checkParameterIsNotNull(destinationName, "destinationName");
        Intrinsics.checkParameterIsNotNull(phoneNumber, "phoneNumber");
        this.destinationName = destinationName;
        this.phoneNumber = phoneNumber;
    }

    public final String getDestinationName() {
        return this.destinationName;
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final void setDestinationName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.destinationName = str;
    }

    public final void setPhoneNumber(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.phoneNumber = str;
    }

    public String toString() {
        return "Telephone(destinationName='" + this.destinationName + "', phoneNumber='" + this.phoneNumber + "')";
    }
}
