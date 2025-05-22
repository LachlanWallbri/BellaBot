package com.pudutech.lib_update.util;

import android.util.Log;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.config.ConfigContant;
import java.io.File;
import java.io.IOException;
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
/* compiled from: DownloadUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.util.DownloadUtil$Companion$downloadApp$1$onComplete$1", m3970f = "DownloadUtil.kt", m3971i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2}, m3972l = {184, 202, 208}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "downloadFile", "fileMd5", "$this$launch", "downloadFile", "fileMd5", "upgradeFile", C3898x.f4338g, "$this$launch", "downloadFile", "fileMd5", "upgradeFile"}, m3975s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes4.dex */
public final class DownloadUtil$Companion$downloadApp$1$onComplete$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5438p$;
    final /* synthetic */ DownloadUtil$Companion$downloadApp$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadUtil$Companion$downloadApp$1$onComplete$1(DownloadUtil$Companion$downloadApp$1 downloadUtil$Companion$downloadApp$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = downloadUtil$Companion$downloadApp$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DownloadUtil$Companion$downloadApp$1$onComplete$1 downloadUtil$Companion$downloadApp$1$onComplete$1 = new DownloadUtil$Companion$downloadApp$1$onComplete$1(this.this$0, completion);
        downloadUtil$Companion$downloadApp$1$onComplete$1.f5438p$ = (CoroutineScope) obj;
        return downloadUtil$Companion$downloadApp$1$onComplete$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DownloadUtil$Companion$downloadApp$1$onComplete$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            if (i == 3) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5438p$;
        Pdlog.m3273d(DownloadUtil.INSTANCE.getTAG(), "http md5: " + this.this$0.$md5);
        File file = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, this.this$0.getFileName());
        String calculateFileMD5 = FileUtil.INSTANCE.calculateFileMD5(file);
        if (calculateFileMD5 != null) {
            Pdlog.m3273d(DownloadUtil.INSTANCE.getTAG(), "file md5: " + calculateFileMD5);
            if (!Intrinsics.areEqual(this.this$0.$md5, calculateFileMD5)) {
                file.deleteOnExit();
                Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), "download error file check http md5: " + this.this$0.$md5 + " & file md5 : " + calculateFileMD5);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C46691 c46691 = new C46691(null);
                this.L$0 = coroutineScope;
                this.L$1 = file;
                this.L$2 = calculateFileMD5;
                this.label = 1;
                if (BuildersKt.withContext(main, c46691, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        File file2 = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, "update.apk");
        try {
            if (file2.exists()) {
                file2.delete();
            }
            FileUtils.copyFile(file, file2);
            file.delete();
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            C46713 c46713 = new C46713(null);
            this.L$0 = coroutineScope;
            this.L$1 = file;
            this.L$2 = calculateFileMD5;
            this.L$3 = file2;
            this.label = 3;
            if (BuildersKt.withContext(main2, c46713, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        } catch (Exception e) {
            Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), Log.getStackTraceString(e));
            MainCoroutineDispatcher main3 = Dispatchers.getMain();
            C46702 c46702 = new C46702(e, null);
            this.L$0 = coroutineScope;
            this.L$1 = file;
            this.L$2 = calculateFileMD5;
            this.L$3 = file2;
            this.L$4 = e;
            this.label = 2;
            if (BuildersKt.withContext(main3, c46702, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.util.DownloadUtil$Companion$downloadApp$1$onComplete$1$1", m3970f = "DownloadUtil.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.util.DownloadUtil$Companion$downloadApp$1$onComplete$1$1 */
    /* loaded from: classes4.dex */
    public static final class C46691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5439p$;

        C46691(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46691 c46691 = new C46691(completion);
            c46691.f5439p$ = (CoroutineScope) obj;
            return c46691;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46691) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5439p$;
            DownloadUtil$Companion$downloadApp$1$onComplete$1.this.this$0.$delegate.onError(new IOException("down file md5 check failed"));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.util.DownloadUtil$Companion$downloadApp$1$onComplete$1$2", m3970f = "DownloadUtil.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.util.DownloadUtil$Companion$downloadApp$1$onComplete$1$2 */
    /* loaded from: classes4.dex */
    public static final class C46702 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $e */
        final /* synthetic */ Exception f5440$e;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5441p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C46702(Exception exc, Continuation continuation) {
            super(2, continuation);
            this.f5440$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46702 c46702 = new C46702(this.f5440$e, completion);
            c46702.f5441p$ = (CoroutineScope) obj;
            return c46702;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46702) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5441p$;
            DownloadUtil$Companion$downloadApp$1$onComplete$1.this.this$0.$delegate.onError(this.f5440$e);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.util.DownloadUtil$Companion$downloadApp$1$onComplete$1$3", m3970f = "DownloadUtil.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.util.DownloadUtil$Companion$downloadApp$1$onComplete$1$3 */
    /* loaded from: classes4.dex */
    public static final class C46713 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5442p$;

        C46713(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46713 c46713 = new C46713(completion);
            c46713.f5442p$ = (CoroutineScope) obj;
            return c46713;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46713) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5442p$;
            DownloadUtil$Companion$downloadApp$1$onComplete$1.this.this$0.$delegate.onComplete();
            return Unit.INSTANCE;
        }
    }
}
