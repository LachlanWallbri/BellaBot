package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Application;
import android.widget.EditText;
import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CustomCruiseTtsDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog$generateTTs$2", m3970f = "CustomCruiseTtsDialog.kt", m3971i = {0, 0}, m3972l = {150}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "md5"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class CustomCruiseTtsDialog$generateTTs$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $content;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4908p$;
    final /* synthetic */ CustomCruiseTtsDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomCruiseTtsDialog$generateTTs$2(CustomCruiseTtsDialog customCruiseTtsDialog, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = customCruiseTtsDialog;
        this.$content = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CustomCruiseTtsDialog$generateTTs$2 customCruiseTtsDialog$generateTTs$2 = new CustomCruiseTtsDialog$generateTTs$2(this.this$0, this.$content, completion);
        customCruiseTtsDialog$generateTTs$2.f4908p$ = (CoroutineScope) obj;
        return customCruiseTtsDialog$generateTTs$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CustomCruiseTtsDialog$generateTTs$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4908p$;
            String str3 = this.$content;
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            str = this.this$0.mLocaleStr;
            sb.append(str);
            String md5 = MD5Util.toMD5(sb.toString() + this.this$0.getType().name());
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "input_done --gen md5 name = " + md5);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C43091 c43091 = new C43091(md5, null);
            this.L$0 = coroutineScope;
            this.L$1 = md5;
            this.label = 1;
            if (BuildersKt.withContext(main, c43091, this) == coroutine_suspended) {
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
    /* compiled from: CustomCruiseTtsDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog$generateTTs$2$1", m3970f = "CustomCruiseTtsDialog.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog$generateTTs$2$1 */
    /* loaded from: classes3.dex */
    public static final class C43091 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $md5;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4909p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C43091(String str, Continuation continuation) {
            super(2, continuation);
            this.$md5 = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C43091 c43091 = new C43091(this.$md5, completion);
            c43091.f4909p$ = (CoroutineScope) obj;
            return c43091;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C43091) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4909p$;
            TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
            String str = CustomCruiseTtsDialog$generateTTs$2.this.$content;
            String md5 = this.$md5;
            Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
            TtsVoiceHelper.addFlTtsVoice$default(ttsVoiceHelper, str, md5, CustomCruiseTtsDialog$generateTTs$2.this.this$0.getType(), false, null, new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog.generateTTs.2.1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                    invoke2(str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str2) {
                    String str3;
                    String str4;
                    if (CustomCruiseTtsDialog$generateTTs$2.this.this$0.isDetached()) {
                        return;
                    }
                    if (str2 == null) {
                        str4 = CustomCruiseTtsDialog$generateTTs$2.this.this$0.TAG;
                        Pdlog.m3273d(str4, "addFlTtsVoice 成功---content:" + CustomCruiseTtsDialog$generateTTs$2.this.$content + "---md5:" + C43091.this.$md5);
                        CustomCruiseTtsDialog$generateTTs$2.this.this$0.disLoadDialog();
                        KeyboardUtils.hideSoftInput((EditText) CustomCruiseTtsDialog$generateTTs$2.this.this$0._$_findCachedViewById(C4188R.id.etCustomVoice));
                        Function1<String, Unit> onTtsAddSuccessListener = CustomCruiseTtsDialog$generateTTs$2.this.this$0.getOnTtsAddSuccessListener();
                        if (onTtsAddSuccessListener != null) {
                            String str5 = CustomCruiseTtsDialog$generateTTs$2.this.$content;
                            if (str5 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                            }
                            onTtsAddSuccessListener.invoke(StringsKt.trim((CharSequence) str5).toString());
                        }
                        CustomCruiseTtsDialog$generateTTs$2.this.this$0.isGenerating = false;
                        CustomCruiseTtsDialog$generateTTs$2.this.this$0.dismissTTsDialog();
                        return;
                    }
                    str3 = CustomCruiseTtsDialog$generateTTs$2.this.this$0.TAG;
                    Pdlog.m3274e(str3, "addFlTtsVoice失败--content:" + CustomCruiseTtsDialog$generateTTs$2.this.$content + " ----  onError : " + str2);
                    Application companion = BaseApplication.INSTANCE.getInstance();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = CustomCruiseTtsDialog$generateTTs$2.this.this$0.getString(C4188R.string.pdStr7_74);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_74)");
                    Object[] objArr = {str2};
                    String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                    ToastUtils.show(companion, format, new Object[0]);
                    CustomCruiseTtsDialog$generateTTs$2.this.this$0.isGenerating = false;
                    CustomCruiseTtsDialog$generateTTs$2.this.this$0.disLoadDialog();
                }
            }, 24, null);
            return Unit.INSTANCE;
        }
    }
}
