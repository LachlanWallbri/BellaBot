package com.pudutech.mirsdk.activity;

import android.content.DialogInterface;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class MirSDKActivity$onCreate$14 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$onCreate$14(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        final List mutableListOf = CollectionsKt.mutableListOf(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "D", "I", ExifInterface.LONGITUDE_WEST);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.this$0);
        Object[] array = mutableListOf.toArray(new String[0]);
        if (array != null) {
            AlertDialog create = builder.setSingleChoiceItems((CharSequence[]) array, -1, new DialogInterface.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$14$dialog$1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    String str;
                    str = MirSDKActivity$onCreate$14.this.this$0.TAG;
                    Pdlog.m3275i(str, "select destination:" + ((String) mutableListOf.get(i)));
                    dialogInterface.dismiss();
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48601(i, null), 3, null);
                }

                /* compiled from: MirSDKActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$14$dialog$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$14$dialog$1$1 */
                /* loaded from: classes5.dex */
                static final class C48601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                    /* renamed from: $i */
                    final /* synthetic */ int f5677$i;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5678p$;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C48601(int i, Continuation continuation) {
                        super(2, continuation);
                        this.f5677$i = i;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C48601 c48601 = new C48601(this.f5677$i, completion);
                        c48601.f5678p$ = (CoroutineScope) obj;
                        return c48601;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C48601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5678p$;
                        String str = (String) mutableListOf.get(this.f5677$i);
                        int hashCode = str.hashCode();
                        int i = 2;
                        if (hashCode != 68) {
                            if (hashCode != 73) {
                                if (hashCode == 86) {
                                    str.equals(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
                                } else if (hashCode == 87 && str.equals(ExifInterface.LONGITUDE_WEST)) {
                                    i = 5;
                                }
                            } else if (str.equals("I")) {
                                i = 4;
                            }
                        } else if (str.equals("D")) {
                            i = 3;
                        }
                        Pdlog.setPuduLogHighestLevel(i);
                        return Unit.INSTANCE;
                    }
                }
            }).create();
            Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(this…               }.create()");
            create.show();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
