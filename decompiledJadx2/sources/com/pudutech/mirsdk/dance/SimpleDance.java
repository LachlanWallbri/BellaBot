package com.pudutech.mirsdk.dance;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.WatchDog;
import com.pudutech.mirsdk.aidl.serialize.Dance;
import com.pudutech.mirsdk.aidl.serialize.DanceDirection;
import com.pudutech.mirsdk.aidl.serialize.DanceMode;
import com.pudutech.mirsdk.aidl.serialize.DanceStatus;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: SimpleDance.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B~\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00126\u0010\t\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\n\u0012!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\u0010\u0015J\b\u0010\u001c\u001a\u00020\u0011H\u0014R$\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000f8T@TX\u0094\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/mirsdk/dance/SimpleDance;", "Lcom/pudutech/mirsdk/dance/BaseDance;", "robotHardware", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "onStateChange", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "state", "", TmpConstant.SERVICE_DESC, "", "danceCallbacker", "Lkotlin/Function1;", "Lcom/pudutech/mirsdk/aidl/serialize/DanceStatus;", "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware;Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/WatchDog;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", ES6Iterator.VALUE_PROPERTY, "TAG", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "initDances", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SimpleDance extends BaseDance {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdk.dance.BaseDance
    public String getTAG() {
        return "SimpleDance";
    }

    @Override // com.pudutech.mirsdk.dance.BaseDance
    protected void setTAG(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleDance(RobotHardware robotHardware, AIDLConnection<MirCoreInterface> coreService, WatchDog watchDog, Function2<? super RobotState, ? super String, Unit> onStateChange, Function1<? super DanceStatus, Unit> danceCallbacker) {
        super(robotHardware, coreService, watchDog, onStateChange, danceCallbacker);
        Intrinsics.checkParameterIsNotNull(robotHardware, "robotHardware");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        Intrinsics.checkParameterIsNotNull(watchDog, "watchDog");
        Intrinsics.checkParameterIsNotNull(onStateChange, "onStateChange");
        Intrinsics.checkParameterIsNotNull(danceCallbacker, "danceCallbacker");
    }

    @Override // com.pudutech.mirsdk.dance.BaseDance
    protected void initDances() {
        getDanceList().add(new Dance(45.0d, 1.5d, DanceDirection.RIGHT, DanceMode.ROTATE));
        getDanceList().add(new Dance(-45.0d, 1.5d, DanceDirection.LEFT, DanceMode.ROTATE));
        getDanceList().add(new Dance(45.0d, 1.5d, DanceDirection.RIGHT, DanceMode.ROTATE));
        getDanceList().add(new Dance(-45.0d, 1.5d, DanceDirection.LEFT, DanceMode.ROTATE));
        getDanceList().add(new Dance(180.0d, 1.5d, DanceDirection.RIGHT, DanceMode.ROTATE));
        getDanceList().add(new Dance(360.0d, 1.5d, DanceDirection.RIGHT, DanceMode.ROTATE));
    }
}
