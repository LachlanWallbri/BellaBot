package com.pudutech.bumblebee.robot_ui.p054ui;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.PalletTtsTaskNameAdapter;
import com.pudutech.bumblebee.robot_ui.roomdata.TtsDataBaseManager;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import java.util.List;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeliverSettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$loadPalletTtsScheme$1", m3970f = "DeliverSettingActivity.kt", m3971i = {0}, m3972l = {437}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes3.dex */
public final class DeliverSettingActivity$loadPalletTtsScheme$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4897p$;
    final /* synthetic */ DeliverSettingActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliverSettingActivity$loadPalletTtsScheme$1(DeliverSettingActivity deliverSettingActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deliverSettingActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DeliverSettingActivity$loadPalletTtsScheme$1 deliverSettingActivity$loadPalletTtsScheme$1 = new DeliverSettingActivity$loadPalletTtsScheme$1(this.this$0, completion);
        deliverSettingActivity$loadPalletTtsScheme$1.f4897p$ = (CoroutineScope) obj;
        return deliverSettingActivity$loadPalletTtsScheme$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DeliverSettingActivity$loadPalletTtsScheme$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List list;
        List list2;
        String str;
        PalletTtsScheme palletTtsScheme;
        List list3;
        List list4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4897p$;
            List<PalletTtsScheme> queryPalletTtsSchemeByLocale = TtsDataBaseManager.INSTANCE.getInstance().getMPalletTtsSchemeDao().queryPalletTtsSchemeByLocale(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null));
            if (queryPalletTtsSchemeByLocale != null) {
                list3 = this.this$0.palletTtsSchemeList;
                list3.clear();
                list4 = this.this$0.palletTtsSchemeList;
                Boxing.boxBoolean(list4.addAll(queryPalletTtsSchemeByLocale));
            }
            list = this.this$0.palletTtsSchemeList;
            if (!list.isEmpty()) {
                DeliverSettingActivity deliverSettingActivity = this.this$0;
                list2 = deliverSettingActivity.palletTtsSchemeList;
                deliverSettingActivity.currentPalletTtsScheme = (PalletTtsScheme) list2.get(0);
                str = this.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("loadPalletTtsScheme, currentPalletTtsScheme:");
                palletTtsScheme = this.this$0.currentPalletTtsScheme;
                sb.append(String.valueOf(palletTtsScheme));
                Pdlog.m3273d(str, sb.toString());
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C42912 c42912 = new C42912(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (BuildersKt.withContext(main, c42912, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DeliverSettingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$loadPalletTtsScheme$1$2", m3970f = "DeliverSettingActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$loadPalletTtsScheme$1$2 */
    /* loaded from: classes3.dex */
    public static final class C42912 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4898p$;

        C42912(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C42912 c42912 = new C42912(completion);
            c42912.f4898p$ = (CoroutineScope) obj;
            return c42912;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C42912) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            PalletTtsTaskNameAdapter palletTtsTaskNameAdapter;
            PalletTtsScheme palletTtsScheme;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4898p$;
            palletTtsTaskNameAdapter = DeliverSettingActivity$loadPalletTtsScheme$1.this.this$0.palletTtsTaskNameAdapter;
            if (palletTtsTaskNameAdapter != null) {
                palletTtsScheme = DeliverSettingActivity$loadPalletTtsScheme$1.this.this$0.currentPalletTtsScheme;
                palletTtsTaskNameAdapter.setSelectedPalletTtsScheme(palletTtsScheme);
                palletTtsTaskNameAdapter.notifyDataSetChanged();
            }
            DeliverSettingActivity$loadPalletTtsScheme$1.this.this$0.updateCurrentPalletTtsSchemeView();
            return Unit.INSTANCE;
        }
    }
}
