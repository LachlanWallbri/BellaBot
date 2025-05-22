package com.pudutech.mirsdk.dance;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.WatchDog;
import com.pudutech.mirsdk.aidl.dance.DanceCallback;
import com.pudutech.mirsdk.aidl.dance.IDanceService;
import com.pudutech.mirsdk.aidl.serialize.Dance;
import com.pudutech.mirsdk.aidl.serialize.DanceStatus;
import com.pudutech.mirsdk.aidl.serialize.DanceType;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DanceService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00126\u0010\t\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\n¢\u0006\u0002\u0010\u0012J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0018H\u0016J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000fH\u0016J \u0010 \u001a\u00020!2\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#2\u0006\u0010%\u001a\u00020\u001bH\u0016J\b\u0010&\u001a\u00020!H\u0016J\u001c\u0010'\u001a\u00020\u00112\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020*\u0012\u0006\u0012\u0004\u0018\u00010\u000f0)J\u000e\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u000fR\u000e\u0010\u0013\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u0019\u001a\"\u0012\u0004\u0012\u00020\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001aj\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u0015`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\t\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, m3961d2 = {"Lcom/pudutech/mirsdk/dance/DanceService;", "Lcom/pudutech/mirsdk/aidl/dance/IDanceService$Stub;", "robotHardware", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "onStateChange", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "state", "", TmpConstant.SERVICE_DESC, "", "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware;Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/WatchDog;Lkotlin/jvm/functions/Function2;)V", "TAG", "dance", "Lcom/pudutech/mirsdk/dance/IDance;", "danceCallbacks", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/dance/DanceCallback;", "danceInterfaceMap", "Ljava/util/HashMap;", "Lcom/pudutech/mirsdk/aidl/serialize/DanceType;", "Lkotlin/collections/HashMap;", "addDanceCallback", "callback", "removeDanceCallback", "startDancing", "", "dances", "", "Lcom/pudutech/mirsdk/aidl/serialize/Dance;", "danceType", "stopDancing", "suspendWarningWelfunction", "warning", "Lkotlin/Pair;", "", "triggerError", "error", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DanceService extends IDanceService.Stub {
    private final String TAG;
    private final AIDLConnection<MirCoreInterface> coreService;
    private IDance dance;
    private ThreadSafeListener<DanceCallback> danceCallbacks;
    private final HashMap<DanceType, IDance> danceInterfaceMap;
    private final Function2<RobotState, String, Unit> onStateChange;
    private final RobotHardware robotHardware;
    private final WatchDog watchDog;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DanceType.values().length];

        static {
            $EnumSwitchMapping$0[DanceType.SIMPLE.ordinal()] = 1;
            $EnumSwitchMapping$0[DanceType.WALTZ.ordinal()] = 2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DanceService(RobotHardware robotHardware, AIDLConnection<MirCoreInterface> coreService, WatchDog watchDog, Function2<? super RobotState, ? super String, Unit> onStateChange) {
        Intrinsics.checkParameterIsNotNull(robotHardware, "robotHardware");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        Intrinsics.checkParameterIsNotNull(watchDog, "watchDog");
        Intrinsics.checkParameterIsNotNull(onStateChange, "onStateChange");
        this.robotHardware = robotHardware;
        this.coreService = coreService;
        this.watchDog = watchDog;
        this.onStateChange = onStateChange;
        this.TAG = "DanceService";
        this.danceInterfaceMap = new HashMap<>();
        this.danceCallbacks = new ThreadSafeListener<>();
    }

    @Override // com.pudutech.mirsdk.aidl.dance.IDanceService
    public int startDancing(List<Dance> dances, DanceType danceType) {
        SimpleDance simpleDance;
        Intrinsics.checkParameterIsNotNull(danceType, "danceType");
        this.dance = this.danceInterfaceMap.get(danceType);
        if (this.dance == null) {
            int i = WhenMappings.$EnumSwitchMapping$0[danceType.ordinal()];
            if (i == 1) {
                simpleDance = new SimpleDance(this.robotHardware, this.coreService, this.watchDog, this.onStateChange, new Function1<DanceStatus, Unit>() { // from class: com.pudutech.mirsdk.dance.DanceService$startDancing$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DanceStatus danceStatus) {
                        invoke2(danceStatus);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final DanceStatus status) {
                        String str;
                        ThreadSafeListener threadSafeListener;
                        Intrinsics.checkParameterIsNotNull(status, "status");
                        str = DanceService.this.TAG;
                        Pdlog.m3273d(str, "SimpleDance dance status = " + status);
                        threadSafeListener = DanceService.this.danceCallbacks;
                        threadSafeListener.notify(new Function2<DanceCallback, String, Unit>() { // from class: com.pudutech.mirsdk.dance.DanceService$startDancing$1.1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(DanceCallback danceCallback, String str2) {
                                invoke2(danceCallback, str2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DanceCallback it, String str2) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                                it.onCallback(DanceStatus.this);
                            }
                        });
                    }
                });
            } else {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                simpleDance = new Waltz(this.robotHardware, this.coreService, this.watchDog, this.onStateChange, new Function1<DanceStatus, Unit>() { // from class: com.pudutech.mirsdk.dance.DanceService$startDancing$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DanceStatus danceStatus) {
                        invoke2(danceStatus);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final DanceStatus status) {
                        String str;
                        ThreadSafeListener threadSafeListener;
                        Intrinsics.checkParameterIsNotNull(status, "status");
                        str = DanceService.this.TAG;
                        Pdlog.m3273d(str, "SimpleDance dance status = " + status);
                        threadSafeListener = DanceService.this.danceCallbacks;
                        threadSafeListener.notify(new Function2<DanceCallback, String, Unit>() { // from class: com.pudutech.mirsdk.dance.DanceService$startDancing$2.1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(DanceCallback danceCallback, String str2) {
                                invoke2(danceCallback, str2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(DanceCallback it, String str2) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                                it.onCallback(DanceStatus.this);
                            }
                        });
                    }
                });
            }
            this.dance = simpleDance;
            this.danceInterfaceMap.put(danceType, this.dance);
        }
        IDance iDance = this.dance;
        if (iDance != null) {
            return iDance.startDance(dances);
        }
        return -1;
    }

    @Override // com.pudutech.mirsdk.aidl.dance.IDanceService
    public int stopDancing() {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("stopDancing ");
        sb.append(this.dance == null ? " interface is null" : "");
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        IDance iDance = this.dance;
        if (iDance != null) {
            return iDance.stopDance();
        }
        return -1;
    }

    @Override // com.pudutech.mirsdk.aidl.dance.IDanceService
    public void addDanceCallback(String name, DanceCallback callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(this.TAG, "addDanceCallback " + name);
        this.danceCallbacks.add(name, callback);
    }

    @Override // com.pudutech.mirsdk.aidl.dance.IDanceService
    public void removeDanceCallback(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(this.TAG, "removeDanceCallback " + name);
        this.danceCallbacks.remove(name);
    }

    public final void triggerError(String error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        Pdlog.m3273d(this.TAG, "triggerError " + error);
        IDance iDance = this.dance;
        if (iDance != null) {
            iDance.triggerError(error);
        }
    }

    public final void suspendWarningWelfunction(Pair<Boolean, String> warning) {
        Intrinsics.checkParameterIsNotNull(warning, "warning");
        Pdlog.m3273d(this.TAG, "suspendWarningWelfunction " + warning.getSecond());
        IDance iDance = this.dance;
        if (iDance != null) {
            iDance.suspendWarningWelfunction(warning);
        }
    }
}
