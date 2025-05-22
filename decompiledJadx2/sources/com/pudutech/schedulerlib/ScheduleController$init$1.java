package com.pudutech.schedulerlib;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.SchCommunicateInfoListener;
import com.pudutech.mirsdk.hardware.serialize.ScheduleCommunicateInfo;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.connection.BaseEspConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ScheduleController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ScheduleController$init$1", m3970f = "ScheduleController.kt", m3971i = {0}, m3972l = {135}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class ScheduleController$init$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7426p$;
    final /* synthetic */ ScheduleController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScheduleController$init$1(ScheduleController scheduleController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = scheduleController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ScheduleController$init$1 scheduleController$init$1 = new ScheduleController$init$1(this.this$0, completion);
        scheduleController$init$1.f7426p$ = (CoroutineScope) obj;
        return scheduleController$init$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ScheduleController$init$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0281 A[EDGE_INSN: B:83:0x0281->B:81:0x0281 BREAK  A[LOOP:0: B:6:0x0022->B:20:?], SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ScheduleCommunicateInfo scheduleCommunicateInfo;
        String str;
        LinkedHashMap linkedHashMap;
        ScheduleCommunicateInfo scheduleCommunicateInfo2;
        LinkedHashMap linkedHashMap2;
        ScheduleCommunicateInfo scheduleCommunicateInfo3;
        LinkedHashMap linkedHashMap3;
        AtomicBoolean atomicBoolean;
        LinkedHashMap linkedHashMap4;
        LinkedHashMap linkedHashMap5;
        ScheduleCommunicateInfo scheduleCommunicateInfo4;
        LinkedHashMap linkedHashMap6;
        ScheduleCommunicateInfo scheduleCommunicateInfo5;
        LinkedHashMap linkedHashMap7;
        AtomicBoolean atomicBoolean2;
        LinkedHashMap linkedHashMap8;
        String str2;
        ScheduleCommunicateInfo scheduleCommunicateInfo6;
        ScheduleCommunicateInfo scheduleCommunicateInfo7;
        ScheduleCommunicateInfo scheduleCommunicateInfo8;
        String str3;
        String str4;
        ScheduleCommunicateInfo scheduleCommunicateInfo9;
        ScheduleCommunicateInfo scheduleCommunicateInfo10;
        ThreadSafeListener threadSafeListener;
        AtomicBoolean atomicBoolean3;
        LinkedHashMap linkedHashMap9;
        LinkedHashMap linkedHashMap10;
        LinkedHashMap linkedHashMap11;
        LinkedHashMap linkedHashMap12;
        LinkedHashMap linkedHashMap13;
        LinkedHashMap linkedHashMap14;
        ScheduleCommunicateInfo scheduleCommunicateInfo11;
        boolean checkWifi;
        BaseEspConnection baseEspConnection;
        LinkedHashMap linkedHashMap15;
        LinkedHashMap linkedHashMap16;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7426p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            Boolean espIsConnected = this.this$0.espIsConnected();
            if (espIsConnected == null) {
                Intrinsics.throwNpe();
            }
            if (espIsConnected.booleanValue() || this.this$0.udpIsConnected()) {
                final LinkedHashMap linkedHashMap17 = new LinkedHashMap();
                final LinkedHashMap linkedHashMap18 = new LinkedHashMap();
                scheduleCommunicateInfo = this.this$0.commInfo;
                str = this.this$0.map_md5;
                if (str == null) {
                    str = "";
                }
                scheduleCommunicateInfo.setMap_flag(str);
                linkedHashMap = this.this$0.espFps;
                synchronized (linkedHashMap) {
                    scheduleCommunicateInfo2 = this.this$0.commInfo;
                    linkedHashMap2 = this.this$0.espFps;
                    scheduleCommunicateInfo2.setEsp_count(linkedHashMap2.size());
                    scheduleCommunicateInfo3 = this.this$0.commInfo;
                    linkedHashMap3 = this.this$0.espFps;
                    scheduleCommunicateInfo3.setEsp_linker(new ArrayList<>(linkedHashMap3.keySet()));
                    atomicBoolean = this.this$0.debuger;
                    if (atomicBoolean.get()) {
                        linkedHashMap16 = this.this$0.espFps;
                        linkedHashMap17.put("esp", MapsKt.toMutableMap(linkedHashMap16));
                    }
                    linkedHashMap4 = this.this$0.espFps;
                    linkedHashMap4.clear();
                    Unit unit = Unit.INSTANCE;
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    break;
                }
                linkedHashMap5 = this.this$0.udpFps;
                synchronized (linkedHashMap5) {
                    scheduleCommunicateInfo4 = this.this$0.commInfo;
                    linkedHashMap6 = this.this$0.udpFps;
                    scheduleCommunicateInfo4.setUdp_count(linkedHashMap6.size());
                    scheduleCommunicateInfo5 = this.this$0.commInfo;
                    linkedHashMap7 = this.this$0.udpFps;
                    scheduleCommunicateInfo5.setUdp_linker(new ArrayList<>(linkedHashMap7.keySet()));
                    atomicBoolean2 = this.this$0.debuger;
                    if (atomicBoolean2.get()) {
                        linkedHashMap15 = this.this$0.udpFps;
                        linkedHashMap17.put("udp", MapsKt.toMutableMap(linkedHashMap15));
                    }
                    linkedHashMap8 = this.this$0.udpFps;
                    linkedHashMap8.clear();
                    Unit unit2 = Unit.INSTANCE;
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    break;
                }
                str2 = this.this$0.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("esp count ");
                scheduleCommunicateInfo6 = this.this$0.commInfo;
                sb.append(scheduleCommunicateInfo6.getEsp_count() < 1);
                sb.append(" udp count ");
                scheduleCommunicateInfo7 = this.this$0.commInfo;
                sb.append(scheduleCommunicateInfo7.getUdp_count() < 1);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str2, objArr);
                scheduleCommunicateInfo8 = this.this$0.commInfo;
                if (scheduleCommunicateInfo8.getEsp_count() < 1) {
                    scheduleCommunicateInfo11 = this.this$0.commInfo;
                    if (scheduleCommunicateInfo11.getUdp_count() < 1) {
                        checkWifi = this.this$0.checkWifi();
                        if (checkWifi) {
                            baseEspConnection = this.this$0.espConnection;
                            str3 = baseEspConnection != null ? baseEspConnection.getESPErrorCode() : null;
                        } else {
                            str3 = "Wifi Cannot be used";
                        }
                        str4 = this.this$0.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("esp count ");
                        scheduleCommunicateInfo9 = this.this$0.commInfo;
                        sb2.append(scheduleCommunicateInfo9.getEsp_count());
                        sb2.append(" udp count ");
                        scheduleCommunicateInfo10 = this.this$0.commInfo;
                        sb2.append(scheduleCommunicateInfo10.getUdp_count());
                        sb2.append(" error: ");
                        sb2.append(str3);
                        Pdlog.m3276v(str4, sb2.toString());
                        threadSafeListener = this.this$0.commListener;
                        threadSafeListener.notify(new Function2<SchCommunicateInfoListener, String, Unit>() { // from class: com.pudutech.schedulerlib.ScheduleController$init$1.3
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(SchCommunicateInfoListener schCommunicateInfoListener, String str5) {
                                invoke2(schCommunicateInfoListener, str5);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SchCommunicateInfoListener it, String str5) {
                                ScheduleCommunicateInfo scheduleCommunicateInfo12;
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                                scheduleCommunicateInfo12 = ScheduleController$init$1.this.this$0.commInfo;
                                it.scheduleCommunicateInfo(scheduleCommunicateInfo12);
                            }
                        });
                        if (!CoroutineScopeKt.isActive(coroutineScope)) {
                            atomicBoolean3 = this.this$0.debuger;
                            if (atomicBoolean3.get()) {
                                linkedHashMap9 = this.this$0.errESP;
                                synchronized (linkedHashMap9) {
                                    linkedHashMap10 = this.this$0.errESP;
                                    linkedHashMap18.put("esp", MapsKt.toMutableMap(linkedHashMap10));
                                    linkedHashMap11 = this.this$0.errESP;
                                    linkedHashMap11.clear();
                                    Unit unit3 = Unit.INSTANCE;
                                }
                                linkedHashMap12 = this.this$0.errUDP;
                                synchronized (linkedHashMap12) {
                                    linkedHashMap13 = this.this$0.errUDP;
                                    linkedHashMap18.put("udp", MapsKt.toMutableMap(linkedHashMap13));
                                    linkedHashMap14 = this.this$0.errUDP;
                                    linkedHashMap14.clear();
                                    Unit unit4 = Unit.INSTANCE;
                                }
                                this.this$0.getFspListener$schedulerlib_release().notify(new Function2<ScheduleController.FPSCallback, String, Unit>() { // from class: com.pudutech.schedulerlib.ScheduleController$init$1.6
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(ScheduleController.FPSCallback fPSCallback, String str5) {
                                        invoke2(fPSCallback, str5);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(ScheduleController.FPSCallback it, String str5) {
                                        Intrinsics.checkParameterIsNotNull(it, "it");
                                        Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                                        it.updateFPS(linkedHashMap17, linkedHashMap18);
                                    }
                                });
                            }
                            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                str3 = "";
                str4 = this.this$0.TAG;
                StringBuilder sb22 = new StringBuilder();
                sb22.append("esp count ");
                scheduleCommunicateInfo9 = this.this$0.commInfo;
                sb22.append(scheduleCommunicateInfo9.getEsp_count());
                sb22.append(" udp count ");
                scheduleCommunicateInfo10 = this.this$0.commInfo;
                sb22.append(scheduleCommunicateInfo10.getUdp_count());
                sb22.append(" error: ");
                sb22.append(str3);
                Pdlog.m3276v(str4, sb22.toString());
                threadSafeListener = this.this$0.commListener;
                threadSafeListener.notify(new Function2<SchCommunicateInfoListener, String, Unit>() { // from class: com.pudutech.schedulerlib.ScheduleController$init$1.3
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(SchCommunicateInfoListener schCommunicateInfoListener, String str5) {
                        invoke2(schCommunicateInfoListener, str5);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SchCommunicateInfoListener it, String str5) {
                        ScheduleCommunicateInfo scheduleCommunicateInfo12;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                        scheduleCommunicateInfo12 = ScheduleController$init$1.this.this$0.commInfo;
                        it.scheduleCommunicateInfo(scheduleCommunicateInfo12);
                    }
                });
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
            }
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
