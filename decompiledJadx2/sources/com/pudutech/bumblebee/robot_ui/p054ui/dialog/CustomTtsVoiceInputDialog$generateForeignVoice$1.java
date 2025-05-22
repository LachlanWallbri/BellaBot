package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Application;
import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
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
/* compiled from: CustomTtsVoiceInputDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$generateForeignVoice$1", m3970f = "CustomTtsVoiceInputDialog.kt", m3971i = {0, 0}, m3972l = {224}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "md5"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class CustomTtsVoiceInputDialog$generateForeignVoice$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4910p$;
    final /* synthetic */ CustomTtsVoiceInputDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomTtsVoiceInputDialog$generateForeignVoice$1(CustomTtsVoiceInputDialog customTtsVoiceInputDialog, Continuation continuation) {
        super(2, continuation);
        this.this$0 = customTtsVoiceInputDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CustomTtsVoiceInputDialog$generateForeignVoice$1 customTtsVoiceInputDialog$generateForeignVoice$1 = new CustomTtsVoiceInputDialog$generateForeignVoice$1(this.this$0, completion);
        customTtsVoiceInputDialog$generateForeignVoice$1.f4910p$ = (CoroutineScope) obj;
        return customTtsVoiceInputDialog$generateForeignVoice$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CustomTtsVoiceInputDialog$generateForeignVoice$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4910p$;
            String content = this.this$0.getContent();
            StringBuilder sb = new StringBuilder();
            sb.append(content);
            str = this.this$0.mLocaleStr;
            sb.append(str);
            String md5 = MD5Util.toMD5(sb.toString() + this.this$0.getVoiceType().name());
            str2 = this.this$0.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("input_done --gen md5 name = ");
            sb2.append(md5);
            sb2.append("---localStr:");
            str3 = this.this$0.mLocaleStr;
            sb2.append(str3);
            sb2.append("---nameType:");
            sb2.append(this.this$0.getVoiceType().name());
            sb2.append("--content:");
            sb2.append(this.this$0.getContent());
            Pdlog.m3273d(str2, sb2.toString());
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C43101 c43101 = new C43101(md5, null);
            this.L$0 = coroutineScope;
            this.L$1 = md5;
            this.label = 1;
            if (BuildersKt.withContext(main, c43101, this) == coroutine_suspended) {
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
    /* compiled from: CustomTtsVoiceInputDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$generateForeignVoice$1$1", m3970f = "CustomTtsVoiceInputDialog.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$generateForeignVoice$1$1 */
    /* loaded from: classes3.dex */
    public static final class C43101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $md5;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4911p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C43101(String str, Continuation continuation) {
            super(2, continuation);
            this.$md5 = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C43101 c43101 = new C43101(this.$md5, completion);
            c43101.f4911p$ = (CoroutineScope) obj;
            return c43101;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C43101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4911p$;
            TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
            String content = CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.getContent();
            String md5 = this.$md5;
            Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
            TtsVoiceHelper.addFlTtsVoice$default(ttsVoiceHelper, content, md5, CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.getVoiceType(), false, null, new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog.generateForeignVoice.1.1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str) {
                    String str2;
                    String str3;
                    if (CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.isDetached()) {
                        return;
                    }
                    if (str == null) {
                        CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.disLoadDialog();
                        CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.isGenerating = false;
                        Function1<String, Unit> onContentChange = CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.getOnContentChange();
                        if (onContentChange != null) {
                            String content2 = CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.getContent();
                            if (content2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                            }
                            onContentChange.invoke(StringsKt.trim((CharSequence) content2).toString());
                        }
                        CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.dismissAllowingStateLoss();
                        str3 = CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.TAG;
                        Pdlog.m3274e(str3, "addFlTtsVoice 成功 ---- content：" + CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.getContent() + "---md5:" + C43101.this.$md5);
                        return;
                    }
                    str2 = CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.TAG;
                    Pdlog.m3274e(str2, "addFlTtsVoice 失败 content：" + CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.getContent() + " ----  onError : " + str);
                    Application companion = BaseApplication.INSTANCE.getInstance();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.getString(C4188R.string.pdStr7_74);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_74)");
                    Object[] objArr = {str};
                    String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                    ToastUtils.show(companion, format, new Object[0]);
                    CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.isGenerating = false;
                    CustomTtsVoiceInputDialog$generateForeignVoice$1.this.this$0.disLoadDialog();
                }
            }, 24, null);
            return Unit.INSTANCE;
        }
    }
}
