package com.pudutech.bumblebee.robot.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: TouchState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0012B\u0012\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0016R\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u0010j\u0002\b\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState;", "", "Landroid/os/Parcelable;", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getValue", "()B", "B", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "UP", "DOWN", "CREATOR", "RobotAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public enum TouchState implements Parcelable {
    UP((byte) 0),
    DOWN((byte) 1);


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final byte value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    TouchState(byte b) {
        this.value = b;
    }

    public final byte getValue() {
        return this.value;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(name());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: TouchState.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "fromValue", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "fromValue-7apg3OU", "(B)Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState;", "newArray", "", "size", "", "(I)[Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState;", "RobotAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.robot.aidl.serialize.TouchState$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes.dex */
    public static final class Companion implements Parcelable.Creator<TouchState> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TouchState createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            String readString = parcel.readString();
            if (readString == null) {
                Intrinsics.throwNpe();
            }
            return TouchState.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TouchState[] newArray(int size) {
            return new TouchState[size];
        }

        /* renamed from: fromValue-7apg3OU, reason: not valid java name */
        public final TouchState m4318fromValue7apg3OU(byte value) {
            for (TouchState touchState : TouchState.values()) {
                if (value == touchState.getValue()) {
                    return touchState;
                }
            }
            return TouchState.DOWN;
        }
    }
}
