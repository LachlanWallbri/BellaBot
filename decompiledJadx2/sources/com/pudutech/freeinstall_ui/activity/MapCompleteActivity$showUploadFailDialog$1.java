package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.module_freeinstall_ui.C5362R;
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

/* compiled from: MapCompleteActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.activity.MapCompleteActivity$showUploadFailDialog$1", m3970f = "MapCompleteActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes3.dex */
final class MapCompleteActivity$showUploadFailDialog$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5224p$;
    final /* synthetic */ MapCompleteActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapCompleteActivity$showUploadFailDialog$1(MapCompleteActivity mapCompleteActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapCompleteActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapCompleteActivity$showUploadFailDialog$1 mapCompleteActivity$showUploadFailDialog$1 = new MapCompleteActivity$showUploadFailDialog$1(this.this$0, completion);
        mapCompleteActivity$showUploadFailDialog$1.f5224p$ = (CoroutineScope) obj;
        return mapCompleteActivity$showUploadFailDialog$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapCompleteActivity$showUploadFailDialog$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5224p$;
        CommonDialog.Builder builder = new CommonDialog.Builder(this.this$0);
        String string = this.this$0.getString(C5362R.string.tips);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
        CommonDialog.Builder title = builder.setTitle(string);
        String string2 = this.this$0.getString(C5362R.string.upload_fail);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.upload_fail)");
        CommonDialog.Builder minContent = title.setMinContent(string2);
        String string3 = this.this$0.getString(C5362R.string.netware_set);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.netware_set)");
        final CommonDialog create = minContent.setBtRight(string3, CommonDialog.BtBg.GRAY, this.this$0.getColor(C5362R.color.white)).setClose(true).create();
        create.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$showUploadFailDialog$1$invokeSuspend$$inlined$let$lambda$1
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
                String str;
                CommonDialog.this.dismiss();
                str = this.this$0.TAG;
                Pdlog.m3273d(str, "showUploadFailDialog: btRightClick");
                this.this$0.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            }
        });
        create.setCloseClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$showUploadFailDialog$1$invokeSuspend$$inlined$let$lambda$2
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
                String str;
                CommonDialog.this.dismiss();
                str = this.this$0.TAG;
                Pdlog.m3273d(str, "showUploadFailDialog: closeClick");
            }
        });
        create.show();
        return Unit.INSTANCE;
    }
}
