package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.LockMotorListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$init$19 extends Lambda implements Function1<Boolean, Unit> {
    public static final SDKInterfaceStub$init$19 INSTANCE = new SDKInterfaceStub$init$19();

    SDKInterfaceStub$init$19() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
        invoke(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(final boolean z) {
        SDKInterfaceStub.access$getILockMotorListener$p(SDKInterfaceStub.INSTANCE).notify(new Function2<LockMotorListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$19.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(LockMotorListener lockMotorListener, String str) {
                invoke2(lockMotorListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LockMotorListener l, String str) {
                String str2;
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                str2 = SDKInterfaceStub.TAG;
                Pdlog.m3273d(str2, "LockMotor callback " + z);
                l.onLock(z);
            }
        });
    }
}
