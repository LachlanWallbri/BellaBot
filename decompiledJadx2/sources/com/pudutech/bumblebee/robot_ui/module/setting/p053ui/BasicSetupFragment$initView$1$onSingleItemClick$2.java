package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectLanguageItem;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.TransparentLoadDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceManager;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.resources.language.LanguageUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BasicSetupFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.BasicSetupFragment$initView$1$onSingleItemClick$2", m3970f = "BasicSetupFragment.kt", m3971i = {0, 1, 1}, m3972l = {107, 135}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "item"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes3.dex */
public final class BasicSetupFragment$initView$1$onSingleItemClick$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BaseQuickAdapter $adapter;
    final /* synthetic */ Object $curItem;
    final /* synthetic */ int $position;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4873p$;
    final /* synthetic */ BasicSetupFragment$initView$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicSetupFragment$initView$1$onSingleItemClick$2(BasicSetupFragment$initView$1 basicSetupFragment$initView$1, Object obj, BaseQuickAdapter baseQuickAdapter, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = basicSetupFragment$initView$1;
        this.$curItem = obj;
        this.$adapter = baseQuickAdapter;
        this.$position = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BasicSetupFragment$initView$1$onSingleItemClick$2 basicSetupFragment$initView$1$onSingleItemClick$2 = new BasicSetupFragment$initView$1$onSingleItemClick$2(this.this$0, this.$curItem, this.$adapter, this.$position, completion);
        basicSetupFragment$initView$1$onSingleItemClick$2.f4873p$ = (CoroutineScope) obj;
        return basicSetupFragment$initView$1$onSingleItemClick$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BasicSetupFragment$initView$1$onSingleItemClick$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        String str2;
        TransparentLoadDialog transparentLoadDialog;
        int i;
        String str3;
        String str4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4873p$;
            str = this.this$0.this$0.TAG;
            Pdlog.m3273d(str, "curItem launch");
            C42241 c42241 = new C42241(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(SolicitService.CAMERA_OPEN_TIME_OUT, c42241, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                if (i2 == 2) {
                    Object obj2 = this.L$1;
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        str2 = this.this$0.this$0.TAG;
        Pdlog.m3273d(str2, "curItem end");
        transparentLoadDialog = this.this$0.this$0.mLoadDialog;
        if (transparentLoadDialog != null && transparentLoadDialog.isShowing()) {
            BaseQuickAdapter baseQuickAdapter = this.$adapter;
            i = this.this$0.this$0.mCurIndex;
            Object item = baseQuickAdapter.getItem(i);
            if (!(item instanceof SelectLanguageItem)) {
                str3 = this.this$0.this$0.TAG;
                Pdlog.m3273d(str3, "item is error");
            } else {
                this.this$0.resetLocalRes((SelectLanguageItem) item);
                str4 = this.this$0.this$0.TAG;
                Pdlog.m3273d(str4, "语言切换超时");
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C42252 c42252 = new C42252(null);
            this.L$0 = coroutineScope;
            this.L$1 = item;
            this.label = 2;
            if (BuildersKt.withContext(main, c42252, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BasicSetupFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.BasicSetupFragment$initView$1$onSingleItemClick$2$1", m3970f = "BasicSetupFragment.kt", m3971i = {0, 1}, m3972l = {110, 111}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull", "$this$withTimeoutOrNull"}, m3975s = {"L$0", "L$0"})
    /* renamed from: com.pudutech.bumblebee.robot_ui.module.setting.ui.BasicSetupFragment$initView$1$onSingleItemClick$2$1 */
    /* loaded from: classes3.dex */
    public static final class C42241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4874p$;

        C42241(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C42241 c42241 = new C42241(completion);
            c42241.f4874p$ = (CoroutineScope) obj;
            return c42241;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C42241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            String str;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f4874p$;
                str = BasicSetupFragment$initView$1$onSingleItemClick$2.this.this$0.this$0.TAG;
                Pdlog.m3273d(str, "curItem withTimeout");
                BasicSetupFragment$initView$1$onSingleItemClick$2.this.this$0.resetLocalRes((SelectLanguageItem) BasicSetupFragment$initView$1$onSingleItemClick$2.this.$curItem);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(800L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
            this.L$0 = coroutineScope;
            this.label = 2;
            if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: BasicSetupFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.BasicSetupFragment$initView$1$onSingleItemClick$2$1$1", m3970f = "BasicSetupFragment.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.bumblebee.robot_ui.module.setting.ui.BasicSetupFragment$initView$1$onSingleItemClick$2$1$1, reason: invalid class name */
        /* loaded from: classes3.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;

            /* renamed from: p$ */
            private CoroutineScope f4875p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f4875p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                LanguageUtils languageUtils;
                LanguageUtils languageUtils2;
                TransparentLoadDialog transparentLoadDialog;
                String str;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4875p$;
                languageUtils = BasicSetupFragment$initView$1$onSingleItemClick$2.this.this$0.this$0.languageUtils;
                languageUtils.updateLanguage(((SelectLanguageItem) BasicSetupFragment$initView$1$onSingleItemClick$2.this.$curItem).getOp().getLocale());
                languageUtils2 = BasicSetupFragment$initView$1$onSingleItemClick$2.this.this$0.this$0.languageUtils;
                languageUtils2.setSystemLanguage(((SelectLanguageItem) BasicSetupFragment$initView$1$onSingleItemClick$2.this.$curItem).getOp().getLocale());
                List data = BasicSetupFragment$initView$1$onSingleItemClick$2.this.$adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i = 0;
                for (Object obj2 : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Boxing.boxInt(i).intValue();
                    if (obj2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.SelectLanguageItem");
                    }
                    ((SelectLanguageItem) obj2).setSelect(false);
                    i = i2;
                }
                TtsVoiceManager.INSTANCE.resetMerchantTtsAllData();
                ((SelectLanguageItem) BasicSetupFragment$initView$1$onSingleItemClick$2.this.$curItem).setSelect(true);
                BasicSetupFragment$initView$1$onSingleItemClick$2.this.$adapter.notifyDataSetChanged();
                BasicSetupFragment$initView$1$onSingleItemClick$2.this.this$0.this$0.mCurIndex = BasicSetupFragment$initView$1$onSingleItemClick$2.this.$position;
                transparentLoadDialog = BasicSetupFragment$initView$1$onSingleItemClick$2.this.this$0.this$0.mLoadDialog;
                if (transparentLoadDialog != null) {
                    transparentLoadDialog.dismiss();
                }
                str = BasicSetupFragment$initView$1$onSingleItemClick$2.this.this$0.this$0.TAG;
                Pdlog.m3273d(str, "setSystemLanguage success");
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BasicSetupFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.BasicSetupFragment$initView$1$onSingleItemClick$2$2", m3970f = "BasicSetupFragment.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.module.setting.ui.BasicSetupFragment$initView$1$onSingleItemClick$2$2 */
    /* loaded from: classes3.dex */
    public static final class C42252 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4876p$;

        C42252(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C42252 c42252 = new C42252(completion);
            c42252.f4876p$ = (CoroutineScope) obj;
            return c42252;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C42252) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            TransparentLoadDialog transparentLoadDialog;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4876p$;
            transparentLoadDialog = BasicSetupFragment$initView$1$onSingleItemClick$2.this.this$0.this$0.mLoadDialog;
            if (transparentLoadDialog != null) {
                transparentLoadDialog.dismiss();
            }
            str = BasicSetupFragment$initView$1$onSingleItemClick$2.this.this$0.this$0.TAG;
            Pdlog.m3273d(str, "setSystemLanguage failure");
            return Unit.INSTANCE;
        }
    }
}
