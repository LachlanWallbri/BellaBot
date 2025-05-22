package com.pudutech.freeinstall_ui.utils;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: CommonDialogUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.utils.CommonDialogUtils$Companion$showDoubleCommonDialog$3", m3970f = "CommonDialogUtils.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
final class CommonDialogUtils$Companion$showDoubleCommonDialog$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0 $btLeftClick;
    final /* synthetic */ Function0 $btRightClick;
    final /* synthetic */ String $btnLeftString;
    final /* synthetic */ String $btnRightString;
    final /* synthetic */ String $content;
    final /* synthetic */ Context $context;
    final /* synthetic */ boolean $isCloseShow;
    final /* synthetic */ String $title;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5244p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommonDialogUtils$Companion$showDoubleCommonDialog$3(Context context, String str, String str2, String str3, String str4, boolean z, Function0 function0, Function0 function02, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
        this.$title = str;
        this.$content = str2;
        this.$btnRightString = str3;
        this.$btnLeftString = str4;
        this.$isCloseShow = z;
        this.$btLeftClick = function0;
        this.$btRightClick = function02;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CommonDialogUtils$Companion$showDoubleCommonDialog$3 commonDialogUtils$Companion$showDoubleCommonDialog$3 = new CommonDialogUtils$Companion$showDoubleCommonDialog$3(this.$context, this.$title, this.$content, this.$btnRightString, this.$btnLeftString, this.$isCloseShow, this.$btLeftClick, this.$btRightClick, completion);
        commonDialogUtils$Companion$showDoubleCommonDialog$3.f5244p$ = (CoroutineScope) obj;
        return commonDialogUtils$Companion$showDoubleCommonDialog$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CommonDialogUtils$Companion$showDoubleCommonDialog$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5244p$;
        final CommonDialog create = new CommonDialog.Builder(this.$context).setTitle(this.$title).setMinContent(this.$content).setBtRight(this.$btnRightString).setBtLeft(this.$btnLeftString).setClose(this.$isCloseShow).create();
        create.setBtLeftClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.utils.CommonDialogUtils$Companion$showDoubleCommonDialog$3$invokeSuspend$$inlined$let$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                CommonDialog.this.dismiss();
                Pdlog.m3273d("CommonDialogUtils: showDoubleCommonDialog " + this.$btLeftClick, new Object[0]);
                this.$btLeftClick.invoke();
            }
        });
        create.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.utils.CommonDialogUtils$Companion$showDoubleCommonDialog$3$invokeSuspend$$inlined$let$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                CommonDialog.this.dismiss();
                Pdlog.m3273d("CommonDialogUtils: showDoubleCommonDialog " + this.$btRightClick, new Object[0]);
                this.$btRightClick.invoke();
            }
        });
        create.show();
        return Unit.INSTANCE;
    }
}
