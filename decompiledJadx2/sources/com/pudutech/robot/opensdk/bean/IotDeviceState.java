package com.pudutech.robot.opensdk.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DeviceOnlineBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/IotDeviceState;", "", "(Ljava/lang/String;I)V", "ONLINE", "OFFLINE", "UNACTIVE", "DISABLE", "NULL", "Companion", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public enum IotDeviceState {
    ONLINE,
    OFFLINE,
    UNACTIVE,
    DISABLE,
    NULL;


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: DeviceOnlineBody.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/IotDeviceState$Companion;", "", "()V", "parse", "Lcom/pudutech/robot/opensdk/bean/IotDeviceState;", "str", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IotDeviceState parse(String str) {
            Intrinsics.checkParameterIsNotNull(str, "str");
            if (Intrinsics.areEqual(str, IotDeviceState.OFFLINE.name())) {
                return IotDeviceState.OFFLINE;
            }
            if (Intrinsics.areEqual(str, IotDeviceState.ONLINE.name())) {
                return IotDeviceState.ONLINE;
            }
            if (Intrinsics.areEqual(str, IotDeviceState.UNACTIVE.name())) {
                return IotDeviceState.UNACTIVE;
            }
            if (Intrinsics.areEqual(str, IotDeviceState.DISABLE.name())) {
                return IotDeviceState.DISABLE;
            }
            if (Intrinsics.areEqual(str, IotDeviceState.NULL.name())) {
                return IotDeviceState.NULL;
            }
            return null;
        }
    }
}
