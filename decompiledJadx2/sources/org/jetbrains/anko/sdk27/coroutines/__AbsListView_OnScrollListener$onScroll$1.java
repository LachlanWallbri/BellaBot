package org.jetbrains.anko.sdk27.coroutines;

import android.widget.AbsListView;
import com.aliyun.alink.dm.api.DMErrorCode;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ListenersWithCoroutines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 13})
@DebugMetadata(m3969c = "org/jetbrains/anko/sdk27/coroutines/__AbsListView_OnScrollListener$onScroll$1", m3970f = "ListenersWithCoroutines.kt", m3971i = {}, m3972l = {526, DMErrorCode.ERROR_API_CLIENT_SEND_FAIL}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes9.dex */
final class __AbsListView_OnScrollListener$onScroll$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $firstVisibleItem;
    final /* synthetic */ Function6 $handler;
    final /* synthetic */ int $totalItemCount;
    final /* synthetic */ AbsListView $view;
    final /* synthetic */ int $visibleItemCount;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f10164p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public __AbsListView_OnScrollListener$onScroll$1(Function6 function6, AbsListView absListView, int i, int i2, int i3, Continuation continuation) {
        super(2, continuation);
        this.$handler = function6;
        this.$view = absListView;
        this.$firstVisibleItem = i;
        this.$visibleItemCount = i2;
        this.$totalItemCount = i3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        __AbsListView_OnScrollListener$onScroll$1 __abslistview_onscrolllistener_onscroll_1 = new __AbsListView_OnScrollListener$onScroll$1(this.$handler, this.$view, this.$firstVisibleItem, this.$visibleItemCount, this.$totalItemCount, completion);
        __abslistview_onscrolllistener_onscroll_1.f10164p$ = (CoroutineScope) obj;
        return __abslistview_onscrolllistener_onscroll_1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((__AbsListView_OnScrollListener$onScroll$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else {
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
            CoroutineScope coroutineScope = this.f10164p$;
            Function6 function6 = this.$handler;
            AbsListView absListView = this.$view;
            Integer boxInt = Boxing.boxInt(this.$firstVisibleItem);
            Integer boxInt2 = Boxing.boxInt(this.$visibleItemCount);
            Integer boxInt3 = Boxing.boxInt(this.$totalItemCount);
            this.label = 1;
            if (function6.invoke(coroutineScope, absListView, boxInt, boxInt2, boxInt3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
