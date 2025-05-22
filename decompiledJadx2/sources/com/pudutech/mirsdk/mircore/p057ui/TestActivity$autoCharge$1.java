package com.pudutech.mirsdk.mircore.p057ui;

import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationResult;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationStatus;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.TestActivity$autoCharge$1", m3970f = "TestActivity.kt", m3971i = {0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, m3972l = {204, 222, 246}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "alignPoint", "$this$launch", "alignPoint", "start", SpeechUtility.TAG_RESOURCE_RESULT, "pura", "$this$launch", "alignPoint", "start", SpeechUtility.TAG_RESOURCE_RESULT, "pura"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "J$0", "L$2", "J$1", "L$0", "L$1", "J$0", "L$2", "J$1"})
/* loaded from: classes6.dex */
public final class TestActivity$autoCharge$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6279p$;
    final /* synthetic */ TestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TestActivity$autoCharge$1(TestActivity testActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = testActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TestActivity$autoCharge$1 testActivity$autoCharge$1 = new TestActivity$autoCharge$1(this.this$0, completion);
        testActivity$autoCharge$1.f6279p$ = (CoroutineScope) obj;
        return testActivity$autoCharge$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TestActivity$autoCharge$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:152:0x02bc, code lost:
    
        r1.this$0.getChargeSignal = false;
        r4 = r1.this$0.coreService;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x02c7, code lost:
    
        if (r4 == null) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x02c9, code lost:
    
        r4 = (com.pudutech.mirsdk.mircore.MirCoreInterface) r4.getInterface();
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x02cf, code lost:
    
        if (r4 == null) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x02d1, code lost:
    
        r4 = r4.getNavigator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x02d5, code lost:
    
        if (r4 == null) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x02d7, code lost:
    
        r4.resetNavigationFlag();
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x02da, code lost:
    
        r4 = r1.this$0.coreService;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x02e0, code lost:
    
        if (r4 == null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x02e2, code lost:
    
        r4 = (com.pudutech.mirsdk.mircore.MirCoreInterface) r4.getInterface();
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x02e8, code lost:
    
        if (r4 == null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x02ea, code lost:
    
        r4 = r4.getNavigator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x02ee, code lost:
    
        if (r4 == null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x02f0, code lost:
    
        r23 = r1.this$0.chargeX;
        r25 = r1.this$0.chargeY;
        r27 = r1.this$0.chargeZ;
        kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4.prepareAutoChargeTask(new com.pudutech.mirsdk.hardware.serialize.Vector3d(r23, r25, r27)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0310, code lost:
    
        r4 = r1.this$0.TAG;
        com.pudutech.base.Pdlog.m3273d(r4, "prepareAutoChargeTask");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02bc A[EDGE_INSN: B:151:0x02bc->B:152:0x02bc BREAK  A[LOOP:1: B:107:0x021a->B:143:0x021a], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0414 A[EDGE_INSN: B:175:0x0414->B:66:0x0414 BREAK  A[LOOP:0: B:8:0x031f->B:56:0x031f], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x019b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:142:0x0310 -> B:7:0x031f). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        boolean z;
        double d;
        double d2;
        double d3;
        double d4;
        String str;
        double d5;
        double d6;
        double d7;
        Object obj2;
        CoroutineScope coroutineScope;
        Vector3d vector3d;
        TestActivity$autoCharge$1 testActivity$autoCharge$1;
        Ref.ObjectRef objectRef;
        AIDLConnection aIDLConnection;
        AIDLConnection aIDLConnection2;
        NavigationResult navigationResult;
        HardwareInterface hardwareInterface;
        Double boxDouble;
        Double boxDouble2;
        MirCoreInterface mirCoreInterface;
        NavigationInterface navigator;
        int i;
        AIDLConnection aIDLConnection3;
        AIDLConnection aIDLConnection4;
        AIDLConnection aIDLConnection5;
        int i2;
        AIDLConnection aIDLConnection6;
        HardwareInterface hardwareInterface2;
        MirCoreInterface mirCoreInterface2;
        NavigationInterface navigator2;
        MirCoreInterface mirCoreInterface3;
        NavigationInterface navigator3;
        MirCoreInterface mirCoreInterface4;
        ScheduleInterface scheduler;
        Ref.ObjectRef objectRef2;
        AIDLConnection aIDLConnection7;
        AIDLConnection aIDLConnection8;
        NavigationResult navigationResult2;
        boolean z2;
        AIDLConnection aIDLConnection9;
        String str2;
        int i3;
        String str3;
        int i4;
        HardwareInterface hardwareInterface3;
        boolean z3;
        HardwareInterface hardwareInterface4;
        Double boxDouble3;
        Double boxDouble4;
        MirCoreInterface mirCoreInterface5;
        NavigationInterface navigator4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = this.label;
        if (i5 != 0) {
            if (i5 == 1) {
                vector3d = (Vector3d) this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
            } else if (i5 == 2) {
                long j = this.J$1;
                long j2 = this.J$0;
                vector3d = (Vector3d) this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
            } else if (i5 == 3) {
                long j3 = this.J$1;
                long j4 = this.J$0;
                vector3d = (Vector3d) this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                testActivity$autoCharge$1 = this;
                while (true) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    objectRef2 = new Ref.ObjectRef();
                    aIDLConnection7 = testActivity$autoCharge$1.this$0.coreService;
                    objectRef2.element = (aIDLConnection7 != null || (mirCoreInterface5 = (MirCoreInterface) aIDLConnection7.getInterface()) == null || (navigator4 = mirCoreInterface5.getNavigator()) == null) ? 0 : navigator4.navigation(NavigationMode.Normal, true);
                    aIDLConnection8 = testActivity$autoCharge$1.this$0.hardwareService;
                    if (aIDLConnection8 != null && (hardwareInterface4 = (HardwareInterface) aIDLConnection8.getInterface()) != null) {
                        NavigationResult navigationResult3 = (NavigationResult) objectRef2.element;
                        double doubleValue = (navigationResult3 != null || (boxDouble4 = Boxing.boxDouble(navigationResult3.getLinear_vel())) == null) ? 0.0d : boxDouble4.doubleValue();
                        NavigationResult navigationResult4 = (NavigationResult) objectRef2.element;
                        hardwareInterface4.controlWheel(doubleValue, (navigationResult4 != null || (boxDouble3 = Boxing.boxDouble(navigationResult4.getAngular_vel())) == null) ? 0.0d : boxDouble3.doubleValue(), true);
                    }
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C52622(objectRef2, null), 2, null);
                    navigationResult2 = (NavigationResult) objectRef2.element;
                    if ((navigationResult2 != null ? navigationResult2.getNavigation_status() : null) != NavigationStatus.FailStuck) {
                        NavigationResult navigationResult5 = (NavigationResult) objectRef2.element;
                        if ((navigationResult5 != null ? navigationResult5.getNavigation_status() : null) != NavigationStatus.FailOverTime) {
                            NavigationResult navigationResult6 = (NavigationResult) objectRef2.element;
                            if ((navigationResult6 != null ? navigationResult6.getNavigation_status() : null) == NavigationStatus.FailTrack) {
                                break;
                            }
                            z3 = testActivity$autoCharge$1.this$0.getChargeSignal;
                            if (z3) {
                                break;
                            }
                            long j5 = 99;
                            long elapsedRealtime2 = j5 - (SystemClock.elapsedRealtime() - elapsedRealtime);
                            if (0 <= elapsedRealtime2 && j5 >= elapsedRealtime2) {
                                testActivity$autoCharge$1.L$0 = coroutineScope;
                                testActivity$autoCharge$1.L$1 = vector3d;
                                testActivity$autoCharge$1.J$0 = elapsedRealtime;
                                testActivity$autoCharge$1.L$2 = objectRef2;
                                testActivity$autoCharge$1.J$1 = elapsedRealtime2;
                                testActivity$autoCharge$1.label = 3;
                                if (DelayKt.delay(elapsedRealtime2, testActivity$autoCharge$1) == obj2) {
                                    return obj2;
                                }
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                z2 = testActivity$autoCharge$1.this$0.getChargeSignal;
                if (z2) {
                    testActivity$autoCharge$1.this$0.getChargeSignal = false;
                }
                aIDLConnection9 = testActivity$autoCharge$1.this$0.hardwareService;
                if (aIDLConnection9 != null && (hardwareInterface3 = (HardwareInterface) aIDLConnection9.getInterface()) != null) {
                    hardwareInterface3.controlWheel(0.0d, 0.0d, false);
                }
                str2 = testActivity$autoCharge$1.this$0.TAG;
                Pdlog.m3273d(str2, "finish AutoChargeTask");
                TestActivity testActivity = testActivity$autoCharge$1.this$0;
                i3 = testActivity.runTimes;
                testActivity.runTimes = i3 - 1;
                str3 = testActivity$autoCharge$1.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                i4 = testActivity$autoCharge$1.this$0.runTimes;
                sb.append(i4);
                sb.append(" times left to be executed");
                Pdlog.m3273d(str3, sb.toString());
                i = testActivity$autoCharge$1.this$0.runTimes;
                if (i <= 0) {
                    testActivity$autoCharge$1.this$0.getRunTimes = false;
                    return Unit.INSTANCE;
                }
                aIDLConnection3 = testActivity$autoCharge$1.this$0.coreService;
                if (aIDLConnection3 != null && (mirCoreInterface4 = (MirCoreInterface) aIDLConnection3.getInterface()) != null && (scheduler = mirCoreInterface4.getScheduler()) != null) {
                    Boxing.boxBoolean(scheduler.prepareDeliverTask(vector3d));
                }
                aIDLConnection4 = testActivity$autoCharge$1.this$0.coreService;
                if (aIDLConnection4 != null && (mirCoreInterface3 = (MirCoreInterface) aIDLConnection4.getInterface()) != null && (navigator3 = mirCoreInterface3.getNavigator()) != null) {
                    navigator3.resetNavigationFlag();
                }
                aIDLConnection5 = testActivity$autoCharge$1.this$0.coreService;
                if (aIDLConnection5 != null && (mirCoreInterface2 = (MirCoreInterface) aIDLConnection5.getInterface()) != null && (navigator2 = mirCoreInterface2.getNavigator()) != null) {
                    Boxing.boxBoolean(navigator2.prepareMoveToChargeTask());
                }
                TestActivity testActivity2 = testActivity$autoCharge$1.this$0;
                i2 = testActivity2.cntTime;
                testActivity2.cntTime = i2 + 1;
                aIDLConnection6 = testActivity$autoCharge$1.this$0.hardwareService;
                if (aIDLConnection6 != null && (hardwareInterface2 = (HardwareInterface) aIDLConnection6.getInterface()) != null) {
                    hardwareInterface2.clearWheelError();
                }
                testActivity$autoCharge$1.L$0 = coroutineScope;
                testActivity$autoCharge$1.L$1 = vector3d;
                testActivity$autoCharge$1.label = 1;
                if (DelayKt.delay(500L, testActivity$autoCharge$1) == obj2) {
                    return obj2;
                }
                while (true) {
                    long elapsedRealtime3 = SystemClock.elapsedRealtime();
                    objectRef = new Ref.ObjectRef();
                    aIDLConnection = testActivity$autoCharge$1.this$0.coreService;
                    objectRef.element = (aIDLConnection != null || (mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface()) == null || (navigator = mirCoreInterface.getNavigator()) == null) ? 0 : navigator.navigation(NavigationMode.Normal, true);
                    aIDLConnection2 = testActivity$autoCharge$1.this$0.hardwareService;
                    if (aIDLConnection2 != null && (hardwareInterface = (HardwareInterface) aIDLConnection2.getInterface()) != null) {
                        NavigationResult navigationResult7 = (NavigationResult) objectRef.element;
                        double doubleValue2 = (navigationResult7 != null || (boxDouble2 = Boxing.boxDouble(navigationResult7.getLinear_vel())) == null) ? 0.0d : boxDouble2.doubleValue();
                        NavigationResult navigationResult8 = (NavigationResult) objectRef.element;
                        hardwareInterface.controlWheel(doubleValue2, (navigationResult8 != null || (boxDouble = Boxing.boxDouble(navigationResult8.getAngular_vel())) == null) ? 0.0d : boxDouble.doubleValue(), true);
                    }
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C52611(objectRef, null), 2, null);
                    navigationResult = (NavigationResult) objectRef.element;
                    if ((navigationResult == null ? navigationResult.getNavigation_status() : null) != NavigationStatus.Finished) {
                        break;
                    }
                    long j6 = 99;
                    long elapsedRealtime4 = j6 - (SystemClock.elapsedRealtime() - elapsedRealtime3);
                    if (0 <= elapsedRealtime4 && j6 >= elapsedRealtime4) {
                        testActivity$autoCharge$1.L$0 = coroutineScope;
                        testActivity$autoCharge$1.L$1 = vector3d;
                        testActivity$autoCharge$1.J$0 = elapsedRealtime3;
                        testActivity$autoCharge$1.L$2 = objectRef;
                        testActivity$autoCharge$1.J$1 = elapsedRealtime4;
                        testActivity$autoCharge$1.label = 2;
                        if (DelayKt.delay(elapsedRealtime4, testActivity$autoCharge$1) == obj2) {
                            return obj2;
                        }
                    }
                }
                while (true) {
                    long elapsedRealtime5 = SystemClock.elapsedRealtime();
                    objectRef2 = new Ref.ObjectRef();
                    aIDLConnection7 = testActivity$autoCharge$1.this$0.coreService;
                    objectRef2.element = (aIDLConnection7 != null || (mirCoreInterface5 = (MirCoreInterface) aIDLConnection7.getInterface()) == null || (navigator4 = mirCoreInterface5.getNavigator()) == null) ? 0 : navigator4.navigation(NavigationMode.Normal, true);
                    aIDLConnection8 = testActivity$autoCharge$1.this$0.hardwareService;
                    if (aIDLConnection8 != null) {
                        NavigationResult navigationResult32 = (NavigationResult) objectRef2.element;
                        if (navigationResult32 != null) {
                        }
                        NavigationResult navigationResult42 = (NavigationResult) objectRef2.element;
                        hardwareInterface4.controlWheel(doubleValue, (navigationResult42 != null || (boxDouble3 = Boxing.boxDouble(navigationResult42.getAngular_vel())) == null) ? 0.0d : boxDouble3.doubleValue(), true);
                    }
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C52622(objectRef2, null), 2, null);
                    navigationResult2 = (NavigationResult) objectRef2.element;
                    if ((navigationResult2 != null ? navigationResult2.getNavigation_status() : null) != NavigationStatus.FailStuck) {
                    }
                }
                z2 = testActivity$autoCharge$1.this$0.getChargeSignal;
                if (z2) {
                }
                aIDLConnection9 = testActivity$autoCharge$1.this$0.hardwareService;
                if (aIDLConnection9 != null) {
                    hardwareInterface3.controlWheel(0.0d, 0.0d, false);
                }
                str2 = testActivity$autoCharge$1.this$0.TAG;
                Pdlog.m3273d(str2, "finish AutoChargeTask");
                TestActivity testActivity3 = testActivity$autoCharge$1.this$0;
                i3 = testActivity3.runTimes;
                testActivity3.runTimes = i3 - 1;
                str3 = testActivity$autoCharge$1.this$0.TAG;
                StringBuilder sb2 = new StringBuilder();
                i4 = testActivity$autoCharge$1.this$0.runTimes;
                sb2.append(i4);
                sb2.append(" times left to be executed");
                Pdlog.m3273d(str3, sb2.toString());
                i = testActivity$autoCharge$1.this$0.runTimes;
                if (i <= 0) {
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            testActivity$autoCharge$1 = this;
            while (true) {
                long elapsedRealtime32 = SystemClock.elapsedRealtime();
                objectRef = new Ref.ObjectRef();
                aIDLConnection = testActivity$autoCharge$1.this$0.coreService;
                objectRef.element = (aIDLConnection != null || (mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface()) == null || (navigator = mirCoreInterface.getNavigator()) == null) ? 0 : navigator.navigation(NavigationMode.Normal, true);
                aIDLConnection2 = testActivity$autoCharge$1.this$0.hardwareService;
                if (aIDLConnection2 != null) {
                    NavigationResult navigationResult72 = (NavigationResult) objectRef.element;
                    if (navigationResult72 != null) {
                    }
                    NavigationResult navigationResult82 = (NavigationResult) objectRef.element;
                    hardwareInterface.controlWheel(doubleValue2, (navigationResult82 != null || (boxDouble = Boxing.boxDouble(navigationResult82.getAngular_vel())) == null) ? 0.0d : boxDouble.doubleValue(), true);
                }
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C52611(objectRef, null), 2, null);
                navigationResult = (NavigationResult) objectRef.element;
                if ((navigationResult == null ? navigationResult.getNavigation_status() : null) != NavigationStatus.Finished) {
                }
            }
            while (true) {
                long elapsedRealtime52 = SystemClock.elapsedRealtime();
                objectRef2 = new Ref.ObjectRef();
                aIDLConnection7 = testActivity$autoCharge$1.this$0.coreService;
                objectRef2.element = (aIDLConnection7 != null || (mirCoreInterface5 = (MirCoreInterface) aIDLConnection7.getInterface()) == null || (navigator4 = mirCoreInterface5.getNavigator()) == null) ? 0 : navigator4.navigation(NavigationMode.Normal, true);
                aIDLConnection8 = testActivity$autoCharge$1.this$0.hardwareService;
                if (aIDLConnection8 != null) {
                }
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C52622(objectRef2, null), 2, null);
                navigationResult2 = (NavigationResult) objectRef2.element;
                if ((navigationResult2 != null ? navigationResult2.getNavigation_status() : null) != NavigationStatus.FailStuck) {
                }
            }
            z2 = testActivity$autoCharge$1.this$0.getChargeSignal;
            if (z2) {
            }
            aIDLConnection9 = testActivity$autoCharge$1.this$0.hardwareService;
            if (aIDLConnection9 != null) {
            }
            str2 = testActivity$autoCharge$1.this$0.TAG;
            Pdlog.m3273d(str2, "finish AutoChargeTask");
            TestActivity testActivity32 = testActivity$autoCharge$1.this$0;
            i3 = testActivity32.runTimes;
            testActivity32.runTimes = i3 - 1;
            str3 = testActivity$autoCharge$1.this$0.TAG;
            StringBuilder sb22 = new StringBuilder();
            i4 = testActivity$autoCharge$1.this$0.runTimes;
            sb22.append(i4);
            sb22.append(" times left to be executed");
            Pdlog.m3273d(str3, sb22.toString());
            i = testActivity$autoCharge$1.this$0.runTimes;
            if (i <= 0) {
            }
        } else {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6279p$;
            TestActivity testActivity4 = this.this$0;
            View findViewById = testActivity4.findViewById(C5224R.id.charge_x);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<EditText>(R.id.charge_x)");
            testActivity4.chargeX = Double.parseDouble(((EditText) findViewById).getText().toString());
            TestActivity testActivity5 = this.this$0;
            View findViewById2 = testActivity5.findViewById(C5224R.id.charge_y);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById<EditText>(R.id.charge_y)");
            testActivity5.chargeY = Double.parseDouble(((EditText) findViewById2).getText().toString());
            TestActivity testActivity6 = this.this$0;
            View findViewById3 = testActivity6.findViewById(C5224R.id.charge_z);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById<EditText>(R.id.charge_z)");
            testActivity6.chargeZ = Double.parseDouble(((EditText) findViewById3).getText().toString());
            TestActivity testActivity7 = this.this$0;
            View findViewById4 = testActivity7.findViewById(C5224R.id.charge_dist);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, "findViewById<EditText>(R.id.charge_dist)");
            testActivity7.chargeDist = Double.parseDouble(((EditText) findViewById4).getText().toString());
            z = this.this$0.getRunTimes;
            if (!z) {
                TestActivity testActivity8 = this.this$0;
                View findViewById5 = testActivity8.findViewById(C5224R.id.run_times);
                Intrinsics.checkExpressionValueIsNotNull(findViewById5, "findViewById<EditText>(R.id.run_times)");
                testActivity8.runTimes = Integer.parseInt(((EditText) findViewById5).getText().toString());
                this.this$0.getRunTimes = true;
            }
            TestActivity testActivity9 = this.this$0;
            d = testActivity9.chargeX;
            d2 = this.this$0.chargeY;
            d3 = this.this$0.chargeZ;
            Vector3d vector3d2 = new Vector3d(d, d2, d3);
            d4 = this.this$0.chargeDist;
            Vector3d calAlignPoint = testActivity9.calAlignPoint(vector3d2, d4);
            str = this.this$0.TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("begin to charge ");
            d5 = this.this$0.chargeX;
            sb3.append(d5);
            sb3.append(", ");
            d6 = this.this$0.chargeY;
            sb3.append(d6);
            sb3.append(", ");
            d7 = this.this$0.chargeZ;
            sb3.append(d7);
            sb3.append(", mv to ");
            sb3.append(calAlignPoint.getX());
            sb3.append(',');
            sb3.append(calAlignPoint.getY());
            sb3.append(',');
            sb3.append(calAlignPoint.getZ());
            Pdlog.m3273d(str, sb3.toString());
            obj2 = coroutine_suspended;
            coroutineScope = coroutineScope2;
            vector3d = calAlignPoint;
            testActivity$autoCharge$1 = this;
            i = testActivity$autoCharge$1.this$0.runTimes;
            if (i <= 0) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: TestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.TestActivity$autoCharge$1$1", m3970f = "TestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.TestActivity$autoCharge$1$1 */
    /* loaded from: classes6.dex */
    public static final class C52611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $result;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6280p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C52611(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$result = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52611 c52611 = new C52611(this.$result, completion);
            c52611.f6280p$ = (CoroutineScope) obj;
            return c52611;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6280p$;
            TextView tx_navigation_status = (TextView) TestActivity$autoCharge$1.this.this$0._$_findCachedViewById(C5224R.id.tx_navigation_status);
            Intrinsics.checkExpressionValueIsNotNull(tx_navigation_status, "tx_navigation_status");
            NavigationResult navigationResult = (NavigationResult) this.$result.element;
            tx_navigation_status.setText(String.valueOf(navigationResult != null ? navigationResult.getNavigation_status() : null));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.TestActivity$autoCharge$1$2", m3970f = "TestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.TestActivity$autoCharge$1$2 */
    /* loaded from: classes6.dex */
    public static final class C52622 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $result;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6281p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C52622(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$result = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52622 c52622 = new C52622(this.$result, completion);
            c52622.f6281p$ = (CoroutineScope) obj;
            return c52622;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52622) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6281p$;
            TextView tx_navigation_status = (TextView) TestActivity$autoCharge$1.this.this$0._$_findCachedViewById(C5224R.id.tx_navigation_status);
            Intrinsics.checkExpressionValueIsNotNull(tx_navigation_status, "tx_navigation_status");
            NavigationResult navigationResult = (NavigationResult) this.$result.element;
            tx_navigation_status.setText(String.valueOf(navigationResult != null ? navigationResult.getNavigation_status() : null));
            return Unit.INSTANCE;
        }
    }
}
