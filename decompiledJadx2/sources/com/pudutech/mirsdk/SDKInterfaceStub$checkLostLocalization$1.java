package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.aidl.LostLocalizationListener;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$checkLostLocalization$1", m3970f = "SDKService.kt", m3971i = {0}, m3972l = {967}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$checkLostLocalization$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5566p$;

    SDKInterfaceStub$checkLostLocalization$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKInterfaceStub$checkLostLocalization$1 sDKInterfaceStub$checkLostLocalization$1 = new SDKInterfaceStub$checkLostLocalization$1(completion);
        sDKInterfaceStub$checkLostLocalization$1.f5566p$ = (CoroutineScope) obj;
        return sDKInterfaceStub$checkLostLocalization$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKInterfaceStub$checkLostLocalization$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x009a, code lost:
    
        if ((r3 != null ? r3.getStatus_level() : null) == com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel.Error) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0035 -> B:5:0x0038). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        SDKInterfaceStub$checkLostLocalization$1 sDKInterfaceStub$checkLostLocalization$1;
        AIDLConnection aIDLConnection;
        LocalizationInterface localizer;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5566p$;
            sDKInterfaceStub$checkLostLocalization$1 = this;
            if (SDKInterfaceStub.access$getCheckLocateFlag$p(SDKInterfaceStub.INSTANCE)) {
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            sDKInterfaceStub$checkLostLocalization$1 = this;
            SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
            aIDLConnection = SDKInterfaceStub.coreService;
            MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
            LocalizationStatus localizationStatus = (mirCoreInterface != null || (localizer = mirCoreInterface.getLocalizer()) == null) ? null : localizer.getLocalizationStatus();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("locaStatus level");
            sb.append(localizationStatus == null ? localizationStatus.getStatus_level() : null);
            sb.append(" and ");
            sb.append(localizationStatus == null ? localizationStatus.getStatus_info() : null);
            objArr[0] = sb.toString();
            Pdlog.m3273d("localizationStatus", objArr);
            if ((localizationStatus == null ? localizationStatus.getStatus_level() : null) != LocalizationStatusLevel.Warning) {
            }
            SDKInterfaceStub.access$initCameraScheme(SDKInterfaceStub.INSTANCE);
            SDKInterfaceStub.access$getLostLocalizationListener$p(SDKInterfaceStub.INSTANCE).notify(new Function2<LostLocalizationListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$checkLostLocalization$1.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LostLocalizationListener lostLocalizationListener, String str) {
                    invoke2(lostLocalizationListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LostLocalizationListener it, String name) {
                    AIDLConnection aIDLConnection2;
                    LocalizationInterface localizer2;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                    aIDLConnection2 = SDKInterfaceStub.coreService;
                    MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection2.getInterface();
                    it.LostLocalization((mirCoreInterface2 == null || (localizer2 = mirCoreInterface2.getLocalizer()) == null) ? null : localizer2.getLocalizationStatus());
                }
            });
            if (SDKInterfaceStub.access$getCheckLocateFlag$p(SDKInterfaceStub.INSTANCE)) {
                sDKInterfaceStub$checkLostLocalization$1.L$0 = coroutineScope;
                sDKInterfaceStub$checkLostLocalization$1.label = 1;
                if (DelayKt.delay(200L, sDKInterfaceStub$checkLostLocalization$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                aIDLConnection = SDKInterfaceStub.coreService;
                MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection.getInterface();
                if (mirCoreInterface2 != null) {
                }
                Object[] objArr2 = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append("locaStatus level");
                sb2.append(localizationStatus == null ? localizationStatus.getStatus_level() : null);
                sb2.append(" and ");
                sb2.append(localizationStatus == null ? localizationStatus.getStatus_info() : null);
                objArr2[0] = sb2.toString();
                Pdlog.m3273d("localizationStatus", objArr2);
                if ((localizationStatus == null ? localizationStatus.getStatus_level() : null) != LocalizationStatusLevel.Warning) {
                }
                SDKInterfaceStub.access$initCameraScheme(SDKInterfaceStub.INSTANCE);
                SDKInterfaceStub.access$getLostLocalizationListener$p(SDKInterfaceStub.INSTANCE).notify(new Function2<LostLocalizationListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$checkLostLocalization$1.1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(LostLocalizationListener lostLocalizationListener, String str) {
                        invoke2(lostLocalizationListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LostLocalizationListener it, String name) {
                        AIDLConnection aIDLConnection2;
                        LocalizationInterface localizer2;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        SDKInterfaceStub sDKInterfaceStub22 = SDKInterfaceStub.INSTANCE;
                        aIDLConnection2 = SDKInterfaceStub.coreService;
                        MirCoreInterface mirCoreInterface22 = (MirCoreInterface) aIDLConnection2.getInterface();
                        it.LostLocalization((mirCoreInterface22 == null || (localizer2 = mirCoreInterface22.getLocalizer()) == null) ? null : localizer2.getLocalizationStatus());
                    }
                });
                if (SDKInterfaceStub.access$getCheckLocateFlag$p(SDKInterfaceStub.INSTANCE)) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
