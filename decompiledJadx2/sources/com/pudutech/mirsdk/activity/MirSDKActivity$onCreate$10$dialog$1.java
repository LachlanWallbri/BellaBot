package com.pudutech.mirsdk.activity;

import android.content.DialogInterface;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, m3961d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "i", "", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MirSDKActivity$onCreate$10$dialog$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ List $logGradeArray;
    final /* synthetic */ MirSDKActivity$onCreate$10 this$0;

    MirSDKActivity$onCreate$10$dialog$1(MirSDKActivity$onCreate$10 mirSDKActivity$onCreate$10, List list) {
        this.this$0 = mirSDKActivity$onCreate$10;
        this.$logGradeArray = list;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        String str;
        str = this.this$0.this$0.TAG;
        Pdlog.m3275i(str, "select destination:" + ((String) this.$logGradeArray.get(i)));
        dialogInterface.dismiss();
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48551(i, null), 3, null);
    }

    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$10$dialog$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$10$dialog$1$1 */
    /* loaded from: classes4.dex */
    static final class C48551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $i */
        final /* synthetic */ int f5669$i;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5670p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C48551(int i, Continuation continuation) {
            super(2, continuation);
            this.f5669$i = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48551 c48551 = new C48551(this.f5669$i, completion);
            c48551.f5670p$ = (CoroutineScope) obj;
            return c48551;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5670p$;
            String str = (String) MirSDKActivity$onCreate$10$dialog$1.this.$logGradeArray.get(this.f5669$i);
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
}
