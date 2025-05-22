package com.pudutech.robot.peripherals.activity;

import com.pudutech.robot.peripherals.disinfection.UvcLampDeviceError;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: NineTailsDebugActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\bJ\u001e\u0010\t\u001a\u00020\u00072\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0096\u0002¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m3961d2 = {"com/pudutech/robot/peripherals/activity/NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1", "Lkotlin/Function1;", "", "Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;", "Lkotlin/ParameterName;", "name", "l", "", "Lcom/pudutech/robot/peripherals/disinfection/device/OnUvLampDeviceErrorChangeListener;", "invoke", "([Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;)V", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1 implements Function1<UvcLampDeviceError[], Unit> {
    final /* synthetic */ NineTailsDebugActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1(NineTailsDebugActivity nineTailsDebugActivity) {
        this.this$0 = nineTailsDebugActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(UvcLampDeviceError[] uvcLampDeviceErrorArr) {
        invoke2(uvcLampDeviceErrorArr);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(UvcLampDeviceError[] l) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C5710x287e4031(this, l, null), 2, null);
    }
}
