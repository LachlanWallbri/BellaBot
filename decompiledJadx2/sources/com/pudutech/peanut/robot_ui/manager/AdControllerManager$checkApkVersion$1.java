package com.pudutech.peanut.robot_ui.manager;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.RespApkVersionData;
import com.pudutech.lib_update.UpdateManager;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.peanut.presenter.utils.WifiUtil;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.RobotInfo;
import com.pudutech.peanut.robot_ui.util.AppUtil;
import com.pudutech.resources.language.LanguageUtils;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AdControllerManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkApkVersion$1", m3970f = "AdControllerManager.kt", m3971i = {0}, m3972l = {109}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class AdControllerManager$checkApkVersion$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6987p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdControllerManager$checkApkVersion$1(Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AdControllerManager$checkApkVersion$1 adControllerManager$checkApkVersion$1 = new AdControllerManager$checkApkVersion$1(this.$cb, completion);
        adControllerManager$checkApkVersion$1.f6987p$ = (CoroutineScope) obj;
        return adControllerManager$checkApkVersion$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdControllerManager$checkApkVersion$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f6987p$;
                NetWorkApiManager.AdPlatformService adControl = NetWorkApiManager.INSTANCE.getAdControl();
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = adControl.getApkVersion(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            String adUpdateProductName = RobotInfo.INSTANCE.getAdUpdateProductName();
            LanguageUtils languageUtils = new LanguageUtils(RobotContext.INSTANCE.getContext());
            String versionName = AppUtil.getVersionName(RobotContext.INSTANCE.getContext());
            Intrinsics.checkExpressionValueIsNotNull(versionName, "AppUtil.getVersionName(RobotContext.context)");
            String valueOf = String.valueOf(((RespApkVersionData) ((ApiResponse) obj).getData()).getVersionCode());
            String mac = WifiUtil.INSTANCE.getMac();
            if (mac == null) {
                mac = "";
            }
            UpdateManager.checkSoftWareVersion(new CheckUpdateRequestParams(versionName, valueOf, mac, adUpdateProductName, null, null, null, (languageUtils.getCurrent().getLocale().getLanguage() + "-") + languageUtils.getCurrent().getLocale().getCountry(), 112, null), new Function<VerionResult, Void>() { // from class: com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkApkVersion$1.1

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: AdControllerManager.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkApkVersion$1$1$1", m3970f = "AdControllerManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkApkVersion$1$1$1, reason: invalid class name */
                /* loaded from: classes5.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ VerionResult $versionResult;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f6988p$;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(VerionResult verionResult, Continuation continuation) {
                        super(2, continuation);
                        this.$versionResult = verionResult;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$versionResult, completion);
                        anonymousClass1.f6988p$ = (CoroutineScope) obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f6988p$;
                        AdControllerManager$checkApkVersion$1.this.$cb.invoke(this.$versionResult);
                        return Unit.INSTANCE;
                    }
                }

                @Override // io.reactivex.functions.Function
                public final Void apply(VerionResult versionResult) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(versionResult, "versionResult");
                    AdControllerManager adControllerManager = AdControllerManager.INSTANCE;
                    str2 = AdControllerManager.TAG;
                    Pdlog.m3273d(str2, "startCheckFormServer : versionResult = " + versionResult + "; ");
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(versionResult, null), 2, null);
                    return null;
                }
            }, new Function<Throwable, Void>() { // from class: com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkApkVersion$1.2

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: AdControllerManager.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkApkVersion$1$2$1", m3970f = "AdControllerManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkApkVersion$1$2$1, reason: invalid class name */
                /* loaded from: classes5.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f6989p$;

                    AnonymousClass1(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                        anonymousClass1.f6989p$ = (CoroutineScope) obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f6989p$;
                        AdControllerManager$checkApkVersion$1.this.$cb.invoke(null);
                        return Unit.INSTANCE;
                    }
                }

                @Override // io.reactivex.functions.Function
                public final Void apply(Throwable it) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    AdControllerManager adControllerManager = AdControllerManager.INSTANCE;
                    str2 = AdControllerManager.TAG;
                    Pdlog.m3274e(str2, "startCheckFormServer " + Log.getStackTraceString(it));
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
                    return null;
                }
            });
        } catch (Exception e) {
            AdControllerManager adControllerManager = AdControllerManager.INSTANCE;
            str = AdControllerManager.TAG;
            Pdlog.m3274e(str, "checkApkVersion : " + Log.getStackTraceString(e));
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C55143(null), 2, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AdControllerManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkApkVersion$1$3", m3970f = "AdControllerManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.manager.AdControllerManager$checkApkVersion$1$3 */
    /* loaded from: classes5.dex */
    public static final class C55143 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6990p$;

        C55143(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C55143 c55143 = new C55143(completion);
            c55143.f6990p$ = (CoroutineScope) obj;
            return c55143;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C55143) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6990p$;
            AdControllerManager$checkApkVersion$1.this.$cb.invoke(null);
            return Unit.INSTANCE;
        }
    }
}
