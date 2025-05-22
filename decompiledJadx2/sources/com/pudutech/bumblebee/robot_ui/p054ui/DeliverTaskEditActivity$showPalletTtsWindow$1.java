package com.pudutech.bumblebee.robot_ui.p054ui;

import android.view.View;
import com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme;
import com.pudutech.bumblebee.robot_ui.roomdata.TtsDataBaseManager;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeliverTaskEditActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showPalletTtsWindow$1", m3970f = "DeliverTaskEditActivity.kt", m3971i = {0, 0}, m3972l = {1800}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "list"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class DeliverTaskEditActivity$showPalletTtsWindow$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $pwContentView;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4900p$;
    final /* synthetic */ DeliverTaskEditActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliverTaskEditActivity$showPalletTtsWindow$1(DeliverTaskEditActivity deliverTaskEditActivity, View view, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deliverTaskEditActivity;
        this.$pwContentView = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DeliverTaskEditActivity$showPalletTtsWindow$1 deliverTaskEditActivity$showPalletTtsWindow$1 = new DeliverTaskEditActivity$showPalletTtsWindow$1(this.this$0, this.$pwContentView, completion);
        deliverTaskEditActivity$showPalletTtsWindow$1.f4900p$ = (CoroutineScope) obj;
        return deliverTaskEditActivity$showPalletTtsWindow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DeliverTaskEditActivity$showPalletTtsWindow$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4900p$;
            List<PalletTtsScheme> queryPalletTtsSchemeByLocale = TtsDataBaseManager.INSTANCE.getInstance().getMPalletTtsSchemeDao().queryPalletTtsSchemeByLocale(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null));
            if (queryPalletTtsSchemeByLocale != null) {
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C4293x698a3188 c4293x698a3188 = new C4293x698a3188(queryPalletTtsSchemeByLocale, null, this);
                this.L$0 = coroutineScope;
                this.L$1 = queryPalletTtsSchemeByLocale;
                this.label = 1;
                obj = BuildersKt.withContext(main, c4293x698a3188, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Unit.INSTANCE;
    }
}
