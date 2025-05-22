package com.pudutech.mirsdk.dance;

import android.os.SystemClock;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.WatchDog;
import com.pudutech.mirsdk.aidl.serialize.Dance;
import com.pudutech.mirsdk.aidl.serialize.DanceDirection;
import com.pudutech.mirsdk.aidl.serialize.DanceMode;
import com.pudutech.mirsdk.aidl.serialize.DanceStatus;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.Monitorable;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: BaseDance.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u0000 T2\u00020\u0001:\u0001TB~\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00126\u0010\t\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\n\u0012!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\u0010\u0015J\u0018\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?2\u0006\u0010A\u001a\u00020?H\u0002J\u0010\u0010B\u001a\u00020\u00112\u0006\u0010C\u001a\u00020(H\u0002J\u0010\u0010D\u001a\u00020\u00112\u0006\u0010C\u001a\u00020(H\u0002J\b\u0010E\u001a\u00020\u0011H\u0002J\b\u0010F\u001a\u00020\u0011H$J\b\u0010G\u001a\u00020\u0011H\u0002J\b\u0010H\u001a\u00020\u0011H\u0002J\u0018\u0010I\u001a\u00020/2\u000e\u0010J\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010KH\u0016J\b\u0010L\u001a\u00020/H\u0016J\b\u0010M\u001a\u00020\u0011H\u0002J\u001e\u0010N\u001a\u00020\u00112\u0014\u0010O\u001a\u0010\u0012\u0004\u0012\u00020Q\u0012\u0006\u0012\u0004\u0018\u00010\u000f0PH\u0016J\u0010\u0010R\u001a\u00020\u00112\u0006\u0010S\u001a\u00020\u000fH\u0016R\u001a\u0010\u0016\u001a\u00020\u000fX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R*\u0010&\u001a\u0012\u0012\u0004\u0012\u00020(0'j\b\u0012\u0004\u0012\u00020(`)X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u00020/X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00101\"\u0004\b6\u00103R\u001a\u00107\u001a\u00020\u001fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\t\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006U"}, m3961d2 = {"Lcom/pudutech/mirsdk/dance/BaseDance;", "Lcom/pudutech/mirsdk/dance/IDance;", "robotHardware", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "onStateChange", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "state", "", TmpConstant.SERVICE_DESC, "", "danceCallback", "Lkotlin/Function1;", "Lcom/pudutech/mirsdk/aidl/serialize/DanceStatus;", "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware;Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/WatchDog;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "TAG", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "actionStart", "Lkotlin/Function0;", "actionStop", "currentPos", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "danceJob", "Lkotlinx/coroutines/Job;", "getDanceJob", "()Lkotlinx/coroutines/Job;", "setDanceJob", "(Lkotlinx/coroutines/Job;)V", "danceList", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdk/aidl/serialize/Dance;", "Lkotlin/collections/ArrayList;", "getDanceList", "()Ljava/util/ArrayList;", "setDanceList", "(Ljava/util/ArrayList;)V", "mDanceStatus", "", "getMDanceStatus", "()I", "setMDanceStatus", "(I)V", "mLooperIndex", "getMLooperIndex", "setMLooperIndex", "mVector3d", "getMVector3d", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setMVector3d", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "moveHelper", "Lcom/pudutech/mirsdk/dance/MoveHelper;", "calAngularDistance", "", "from", TypedValues.Transition.S_TO, "danceRotate", "dance", "danceWalk", "dancing", "initDances", "realDancing", "resetPos", "startDance", "dances", "", "stopDance", "stopRobot", "suspendWarningWelfunction", "warning", "Lkotlin/Pair;", "", "triggerError", "error", "Companion", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class BaseDance implements IDance {
    public static final int DANCING = 1;
    public static final int ERROR = 3;
    public static final int IDLE = 0;
    public static final int STOPPING = 2;
    private String TAG;
    private Function0<Unit> actionStart;
    private Function0<Unit> actionStop;
    private final AIDLConnection<MirCoreInterface> coreService;
    private final Vector3d currentPos;
    private final Function1<DanceStatus, Unit> danceCallback;
    private Job danceJob;
    private ArrayList<Dance> danceList;
    private int mDanceStatus;
    private int mLooperIndex;
    private Vector3d mVector3d;
    private final MoveHelper moveHelper;
    private final Function2<RobotState, String, Unit> onStateChange;
    private final RobotHardware robotHardware;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DanceMode.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[DanceMode.ROTATE.ordinal()] = 1;
            $EnumSwitchMapping$0[DanceMode.WALK.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[DanceDirection.values().length];
            $EnumSwitchMapping$1[DanceDirection.BACK.ordinal()] = 1;
            $EnumSwitchMapping$1[DanceDirection.FRONT.ordinal()] = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double calAngularDistance(double from, double to) {
        double d = (((to - from) % 6.283185307179586d) + 6.283185307179586d) % 6.283185307179586d;
        return d > 3.141592653589793d ? d - 6.283185307179586d : d;
    }

    protected abstract void initDances();

    /* JADX WARN: Multi-variable type inference failed */
    public BaseDance(RobotHardware robotHardware, AIDLConnection<MirCoreInterface> coreService, WatchDog watchDog, Function2<? super RobotState, ? super String, Unit> onStateChange, Function1<? super DanceStatus, Unit> danceCallback) {
        Intrinsics.checkParameterIsNotNull(robotHardware, "robotHardware");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        Intrinsics.checkParameterIsNotNull(watchDog, "watchDog");
        Intrinsics.checkParameterIsNotNull(onStateChange, "onStateChange");
        Intrinsics.checkParameterIsNotNull(danceCallback, "danceCallback");
        this.robotHardware = robotHardware;
        this.coreService = coreService;
        this.onStateChange = onStateChange;
        this.danceCallback = danceCallback;
        this.TAG = "BaseDance";
        this.mVector3d = new Vector3d(0.0d, 0.0d, 0.0d);
        this.danceList = new ArrayList<>();
        this.actionStart = new Function0<Unit>() { // from class: com.pudutech.mirsdk.dance.BaseDance$actionStart$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                BaseDance.this.dancing();
            }
        };
        this.actionStop = new Function0<Unit>() { // from class: com.pudutech.mirsdk.dance.BaseDance$actionStop$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Pdlog.m3273d(BaseDance.this.getTAG(), "moveHelper watch error stop mDanceStatus=" + BaseDance.this.getMDanceStatus());
                if (BaseDance.this.getMDanceStatus() == 1) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48961(null), 3, null);
                }
                Pdlog.m3273d(BaseDance.this.getTAG(), "moveHelper watch error stop end");
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: BaseDance.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.mirsdk.dance.BaseDance$actionStop$1$1", m3970f = "BaseDance.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.mirsdk.dance.BaseDance$actionStop$1$1 */
            /* loaded from: classes4.dex */
            public static final class C48961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5772p$;

                C48961(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C48961 c48961 = new C48961(completion);
                    c48961.f5772p$ = (CoroutineScope) obj;
                    return c48961;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C48961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5772p$;
                    BaseDance.this.setMDanceStatus(3);
                    BaseDance.this.danceCallback.invoke(DanceStatus.STOPPING);
                    return Unit.INSTANCE;
                }
            }
        };
        RobotHardware robotHardware2 = this.robotHardware;
        this.moveHelper = new MoveHelper(robotHardware2, this.coreService, robotHardware2.getRobotStatus(), watchDog, this.onStateChange, this.actionStart, this.actionStop);
        this.currentPos = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        initDances();
        this.moveHelper.setStopNotify(new Function0<Unit>() { // from class: com.pudutech.mirsdk.dance.BaseDance.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Pdlog.m3273d(BaseDance.this.getTAG(), "notify stop mDanceStatus=" + BaseDance.this.getMDanceStatus());
                BaseDance.this.setMDanceStatus(0);
                BaseDance.this.danceCallback.invoke(DanceStatus.IDLE);
            }
        });
        this.moveHelper.setErrorClean(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.dance.BaseDance.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                Pdlog.m3273d(BaseDance.this.getTAG(), "notify error clean " + bool + " ,mDanceStatus=" + BaseDance.this.getMDanceStatus());
                if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                    BaseDance.this.danceCallback.invoke(DanceStatus.ERROR_CLEANED_SUCCESS);
                } else {
                    BaseDance.this.danceCallback.invoke(DanceStatus.IDLE);
                    BaseDance.this.setMDanceStatus(0);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getTAG() {
        return this.TAG;
    }

    protected void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Job getDanceJob() {
        return this.danceJob;
    }

    protected final void setDanceJob(Job job) {
        this.danceJob = job;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getMLooperIndex() {
        return this.mLooperIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMLooperIndex(int i) {
        this.mLooperIndex = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Vector3d getMVector3d() {
        return this.mVector3d;
    }

    protected final void setMVector3d(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.mVector3d = vector3d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getMDanceStatus() {
        return this.mDanceStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMDanceStatus(int i) {
        this.mDanceStatus = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ArrayList<Dance> getDanceList() {
        return this.danceList;
    }

    protected final void setDanceList(ArrayList<Dance> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.danceList = arrayList;
    }

    @Override // com.pudutech.mirsdk.dance.IDance
    public int startDance(List<Dance> dances) {
        MirCoreInterface mirCoreInterface;
        LocalizationInterface localizer;
        RobotStatus robotStatus;
        Monitorable<Vector3d> pose;
        Vector3d value;
        RobotStatus robotStatus2;
        Monitorable<Vector3d> pose2;
        Vector3d value2;
        RobotStatus robotStatus3;
        Monitorable<Vector3d> pose3;
        Vector3d value3;
        AIDLConnection<MirCoreInterface> aIDLConnection = this.coreService;
        if (aIDLConnection == null || (mirCoreInterface = aIDLConnection.getInterface()) == null || (localizer = mirCoreInterface.getLocalizer()) == null || !localizer.isLocalizationFinishInitialization()) {
            Pdlog.m3273d(getTAG(), "localization not finish init");
            Function2<RobotState, String, Unit> function2 = this.onStateChange;
            if (function2 != null) {
                function2.invoke(RobotState.Error, "{\"error_type\":\"TaskCannotStart\",\"detail\":\"1000\" ,\"level\":\"Error\"}");
            }
            return -1;
        }
        Pdlog.m3273d(getTAG(), "startDance mDanceStatus=" + this.mDanceStatus);
        int i = this.mDanceStatus;
        if (i == 1) {
            Pdlog.m3273d(getTAG(), "already started ");
            return 1;
        }
        if (i == 2 || i == 3) {
            Pdlog.m3273d(getTAG(), "startDancing failed ");
            return -1;
        }
        Vector3d vector3d = this.mVector3d;
        RobotHardware robotHardware = this.robotHardware;
        Double d = null;
        vector3d.setX(((robotHardware == null || (robotStatus3 = robotHardware.getRobotStatus()) == null || (pose3 = robotStatus3.getPose()) == null || (value3 = pose3.getValue()) == null) ? null : Double.valueOf(value3.getX())).doubleValue());
        Vector3d vector3d2 = this.mVector3d;
        RobotHardware robotHardware2 = this.robotHardware;
        vector3d2.setY(((robotHardware2 == null || (robotStatus2 = robotHardware2.getRobotStatus()) == null || (pose2 = robotStatus2.getPose()) == null || (value2 = pose2.getValue()) == null) ? null : Double.valueOf(value2.getY())).doubleValue());
        Vector3d vector3d3 = this.mVector3d;
        RobotHardware robotHardware3 = this.robotHardware;
        if (robotHardware3 != null && (robotStatus = robotHardware3.getRobotStatus()) != null && (pose = robotStatus.getPose()) != null && (value = pose.getValue()) != null) {
            d = Double.valueOf(value.getZ());
        }
        vector3d3.setZ(d.doubleValue());
        if (dances != null && (!dances.isEmpty())) {
            this.danceList.clear();
            Iterator<T> it = dances.iterator();
            while (it.hasNext()) {
                this.danceList.add((Dance) it.next());
            }
        }
        Pdlog.m3273d(getTAG(), "startDancing mVector3d.x= " + this.mVector3d.getX() + ",y=" + this.mVector3d.getY() + ",z=" + this.mVector3d.getZ());
        realDancing();
        return 0;
    }

    private final void realDancing() {
        this.mDanceStatus = 1;
        this.mLooperIndex = 0;
        BuildersKt__BuildersKt.runBlocking$default(null, new BaseDance$realDancing$1(this, null), 1, null);
        this.moveHelper.startJob();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetPos() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new BaseDance$resetPos$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopRobot() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new BaseDance$stopRobot$1(this, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.dance.IDance
    public int stopDance() {
        Pdlog.m3273d(getTAG(), "stopDance mDanceStatus=" + this.mDanceStatus);
        int i = this.mDanceStatus;
        if (i == 0) {
            Pdlog.m3273d(getTAG(), "dancing not start");
            return 0;
        }
        if (i == 1) {
            this.mDanceStatus = 2;
        } else if (i == 2 || i == 3) {
            Pdlog.m3273d(getTAG(), "dancing is stopping");
            return 1;
        }
        this.danceCallback.invoke(DanceStatus.STOPPING);
        this.moveHelper.stopJob();
        Pdlog.m3273d(getTAG(), "stopDancing");
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dancing() {
        Job launch$default;
        Pdlog.m3273d(getTAG(), "dancing start mDanceStatus=" + this.mDanceStatus);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new BaseDance$dancing$1(this, null), 3, null);
        this.danceJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [T, com.pudutech.mirsdk.hardware.serialize.Vector3d] */
    public final void danceWalk(Dance dance) {
        Vector3d vector3d = this.currentPos;
        RobotHardware robotHardware = this.robotHardware;
        if (robotHardware == null) {
            Intrinsics.throwNpe();
        }
        vector3d.setX(robotHardware.getRobotStatus().getPose().getValue().getX());
        Vector3d vector3d2 = this.currentPos;
        RobotHardware robotHardware2 = this.robotHardware;
        if (robotHardware2 == null) {
            Intrinsics.throwNpe();
        }
        vector3d2.setY(robotHardware2.getRobotStatus().getPose().getValue().getY());
        Vector3d vector3d3 = this.currentPos;
        RobotHardware robotHardware3 = this.robotHardware;
        if (robotHardware3 == null) {
            Intrinsics.throwNpe();
        }
        vector3d3.setZ(robotHardware3.getRobotStatus().getPose().getValue().getZ());
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        double z = this.currentPos.getZ();
        int i = WhenMappings.$EnumSwitchMapping$1[dance.getDirection().ordinal()];
        if (i == 1) {
            ((Vector3d) objectRef.element).setX(this.currentPos.getX() - (dance.getValue() * Math.cos(z)));
            ((Vector3d) objectRef.element).setY(this.currentPos.getY() - (dance.getValue() * Math.sin(z)));
        } else if (i == 2) {
            ((Vector3d) objectRef.element).setX(this.currentPos.getX() + (dance.getValue() * Math.cos(z)));
            ((Vector3d) objectRef.element).setY(this.currentPos.getY() + (dance.getValue() * Math.sin(z)));
        }
        ((Vector3d) objectRef.element).setZ(this.currentPos.getZ());
        Pdlog.m3273d(getTAG(), "currentPos=" + this.currentPos.getX() + ",currentPos=" + this.currentPos.getY() + ",z=" + this.currentPos.getZ());
        Pdlog.m3273d(getTAG(), "desPos=" + ((Vector3d) objectRef.element).getX() + ",desPos=" + ((Vector3d) objectRef.element).getY() + ",z=" + ((Vector3d) objectRef.element).getZ());
        BuildersKt__BuildersKt.runBlocking$default(null, new BaseDance$danceWalk$1(this, objectRef, dance, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v9, types: [T, com.pudutech.mirsdk.hardware.serialize.Vector3d] */
    public final void danceRotate(Dance dance) {
        Ref.DoubleRef doubleRef = new Ref.DoubleRef();
        doubleRef.element = dance.getValue();
        Pdlog.m3273d(getTAG(), "dancing angle=" + doubleRef.element);
        Ref.DoubleRef doubleRef2 = new Ref.DoubleRef();
        doubleRef2.element = ((doubleRef.element * 3.141592653589793d) / 180.0d) + this.mVector3d.getZ();
        Ref.DoubleRef doubleRef3 = new Ref.DoubleRef();
        doubleRef3.element = doubleRef2.element;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new Vector3d(Math.cos(doubleRef3.element), Math.sin(doubleRef3.element), 0.0d);
        Ref.DoubleRef doubleRef4 = new Ref.DoubleRef();
        doubleRef4.element = 0.0d;
        Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = SystemClock.elapsedRealtime();
        BuildersKt__BuildersKt.runBlocking$default(null, new BaseDance$danceRotate$1(this, doubleRef3, doubleRef, objectRef, dance, doubleRef4, doubleRef2, longRef, null), 1, null);
    }

    @Override // com.pudutech.mirsdk.dance.IDance
    public void suspendWarningWelfunction(Pair<Boolean, String> warning) {
        Intrinsics.checkParameterIsNotNull(warning, "warning");
        this.moveHelper.suspendWarningWelfunction(warning);
    }

    @Override // com.pudutech.mirsdk.dance.IDance
    public void triggerError(String error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        this.moveHelper.triggerError(error);
    }
}
