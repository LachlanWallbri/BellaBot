package com.pudutech.bumblebee.robot_ui.util;

import android.content.Context;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.FullLoadDialog;
import com.pudutech.robotselfclean.CleanFactory;
import com.pudutech.robotselfclean.ICleanInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FileUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.util.FileUtil$cleanFactory$4", m3970f = "FileUtil.kt", m3971i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, m3972l = {149, 152, 158}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "clean", SpeechUtility.TAG_RESOURCE_RESULT, "$this$launch", "clean", SpeechUtility.TAG_RESOURCE_RESULT, "clear", "$this$launch", "clean", SpeechUtility.TAG_RESOURCE_RESULT}, m3975s = {"L$0", "L$1", "Z$0", "L$0", "L$1", "Z$0", "Z$1", "L$0", "L$1", "Z$0"})
/* loaded from: classes4.dex */
public final class FileUtil$cleanFactory$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ Context $context;
    final /* synthetic */ Ref.ObjectRef $waitDialog;
    Object L$0;
    Object L$1;
    boolean Z$0;
    boolean Z$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4968p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileUtil$cleanFactory$4(Function1 function1, Context context, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.$block = function1;
        this.$context = context;
        this.$waitDialog = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FileUtil$cleanFactory$4 fileUtil$cleanFactory$4 = new FileUtil$cleanFactory$4(this.$block, this.$context, this.$waitDialog, completion);
        fileUtil$cleanFactory$4.f4968p$ = (CoroutineScope) obj;
        return fileUtil$cleanFactory$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileUtil$cleanFactory$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ICleanInterface createClean;
        boolean clearFolderFilter$default;
        boolean clearSelfAppData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4968p$;
            createClean = CleanFactory.INSTANCE.createClean(CleanFactory.CleanType.DEF_CLEAN_TYPE);
            clearFolderFilter$default = ICleanInterface.DefaultImpls.clearFolderFilter$default(createClean, null, 1, null);
            SDK.INSTANCE.controlBatteryLevel(5);
            if (clearFolderFilter$default) {
                Function1 function1 = this.$block;
                this.L$0 = coroutineScope;
                this.L$1 = createClean;
                this.Z$0 = clearFolderFilter$default;
                this.label = 1;
                if (function1.invoke(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                clearSelfAppData = createClean.clearSelfAppData(this.$context);
                if (!clearSelfAppData) {
                }
            } else {
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C43952 c43952 = new C43952(null);
                this.L$0 = coroutineScope;
                this.L$1 = createClean;
                this.Z$0 = clearFolderFilter$default;
                this.label = 3;
                if (BuildersKt.withContext(main, c43952, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i != 1) {
            if (i == 2) {
                boolean z = this.Z$1;
            } else if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean z2 = this.Z$0;
            ResultKt.throwOnFailure(obj);
        } else {
            boolean z3 = this.Z$0;
            ICleanInterface iCleanInterface = (ICleanInterface) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            clearFolderFilter$default = z3;
            createClean = iCleanInterface;
            coroutineScope = coroutineScope2;
            clearSelfAppData = createClean.clearSelfAppData(this.$context);
            if (!clearSelfAppData) {
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                C43941 c43941 = new C43941(null);
                this.L$0 = coroutineScope;
                this.L$1 = createClean;
                this.Z$0 = clearFolderFilter$default;
                this.Z$1 = clearSelfAppData;
                this.label = 2;
                if (BuildersKt.withContext(main2, c43941, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FileUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.util.FileUtil$cleanFactory$4$1", m3970f = "FileUtil.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.util.FileUtil$cleanFactory$4$1 */
    /* loaded from: classes4.dex */
    public static final class C43941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4969p$;

        C43941(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C43941 c43941 = new C43941(completion);
            c43941.f4969p$ = (CoroutineScope) obj;
            return c43941;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C43941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4969p$;
            ((FullLoadDialog) FileUtil$cleanFactory$4.this.$waitDialog.element).dismiss();
            ToastUtils.show(FileUtil$cleanFactory$4.this.$context, C4188R.string.advance_clean_error_toast);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FileUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.util.FileUtil$cleanFactory$4$2", m3970f = "FileUtil.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.util.FileUtil$cleanFactory$4$2 */
    /* loaded from: classes4.dex */
    public static final class C43952 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4970p$;

        C43952(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C43952 c43952 = new C43952(completion);
            c43952.f4970p$ = (CoroutineScope) obj;
            return c43952;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C43952) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4970p$;
            ((FullLoadDialog) FileUtil$cleanFactory$4.this.$waitDialog.element).dismiss();
            ToastUtils.show(FileUtil$cleanFactory$4.this.$context, C4188R.string.advance_clean_error_toast);
            return Unit.INSTANCE;
        }
    }
}
