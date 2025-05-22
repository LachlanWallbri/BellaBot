package com.pudutech.lib_update;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.loc.C3898x;
import com.pudutech.lib_update.config.ConfigContant;
import com.pudutech.lib_update.listener.SystemInstallCallback;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.lib_update.util.SystemCmdUtils;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.bouncycastle.math.Primes;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.PuduSystemVersionManager$checkFile$1", m3970f = "PuduSystemVersionManager.kt", m3971i = {0, 1, 1, 1, 2, 2, 3, 3}, m3972l = {Primes.SMALL_FACTOR_LIMIT, 219, 243, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "versionCode", "isSuccess", "$this$launch", "isDelete", "$this$launch", C3898x.f4338g}, m3975s = {"L$0", "L$0", "L$1", "Z$0", "L$0", "Z$0", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class PuduSystemVersionManager$checkFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $outputFile;
    final /* synthetic */ Version $res;
    final /* synthetic */ SystemInstallCallback $sysInstallCallback;
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5390p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PuduSystemVersionManager$checkFile$1(File file, Version version, SystemInstallCallback systemInstallCallback, Continuation continuation) {
        super(2, continuation);
        this.$outputFile = file;
        this.$res = version;
        this.$sysInstallCallback = systemInstallCallback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PuduSystemVersionManager$checkFile$1 puduSystemVersionManager$checkFile$1 = new PuduSystemVersionManager$checkFile$1(this.$outputFile, this.$res, this.$sysInstallCallback, completion);
        puduSystemVersionManager$checkFile$1.f5390p$ = (CoroutineScope) obj;
        return puduSystemVersionManager$checkFile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PuduSystemVersionManager$checkFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01cc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f7 A[Catch: Exception -> 0x0054, TRY_ENTER, TryCatch #1 {Exception -> 0x0054, blocks: (B:28:0x004f, B:30:0x00f7, B:31:0x0136), top: B:27:0x004f }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String message;
        MainCoroutineDispatcher main;
        C46443 c46443;
        CoroutineScope coroutineScope2;
        Object checkMd5;
        String version_code;
        boolean rename;
        CoroutineScope coroutineScope3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception e) {
            e = e;
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope2 = this.f5390p$;
            PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
            File file = this.$outputFile;
            String md5 = this.$res.getMd5();
            this.L$0 = coroutineScope2;
            this.label = 1;
            checkMd5 = puduSystemVersionManager.checkMd5(file, md5, this);
            if (checkMd5 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    rename = this.Z$0;
                    version_code = (String) this.L$1;
                    coroutineScope3 = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        if (!rename) {
                            SystemCmdUtils systemCmdUtils = SystemCmdUtils.INSTANCE;
                            StringBuilder sb = new StringBuilder();
                            sb.append("mv ");
                            sb.append(this.$outputFile.getAbsolutePath());
                            sb.append(' ');
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Object[] objArr = {version_code};
                            String format = String.format(ConfigContant.SYSTEM_UPDATE_FILE_NAME_FORMAT, Arrays.copyOf(objArr, objArr.length));
                            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                            sb.append(new File("/data/media/0/", format).getAbsolutePath());
                            systemCmdUtils.execCommand(sb.toString(), true);
                        }
                        SystemCmdUtils.INSTANCE.execCommand(InvokeServiceData.CALL_TYPE_SYNC, false);
                    } catch (Exception e2) {
                        e = e2;
                        coroutineScope = coroutineScope3;
                        NetWorkLog.INSTANCE.mo3279e("PuduSystemVersionManager", "checkAndCopyDownloadFile error");
                        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                        message = e.getMessage();
                        if (message == null) {
                            message = "";
                        }
                        netWorkLog.mo3279e("PuduSystemVersionManager", message);
                        main = Dispatchers.getMain();
                        c46443 = new C46443(e, null);
                        this.L$0 = coroutineScope;
                        this.L$1 = e;
                        this.label = 4;
                        if (BuildersKt.withContext(main, c46443, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
                if (i == 3) {
                    boolean z = this.Z$0;
                    CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e3) {
                        e = e3;
                        coroutineScope = coroutineScope4;
                        NetWorkLog.INSTANCE.mo3279e("PuduSystemVersionManager", "checkAndCopyDownloadFile error");
                        NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                        message = e.getMessage();
                        if (message == null) {
                        }
                        netWorkLog2.mo3279e("PuduSystemVersionManager", message);
                        main = Dispatchers.getMain();
                        c46443 = new C46443(e, null);
                        this.L$0 = coroutineScope;
                        this.L$1 = e;
                        this.label = 4;
                        if (BuildersKt.withContext(main, c46443, this) == coroutine_suspended) {
                        }
                        return Unit.INSTANCE;
                    }
                } else {
                    if (i != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
            coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            checkMd5 = obj;
        }
        if (((Boolean) checkMd5).booleanValue()) {
            NetWorkLog.INSTANCE.mo3278d("PuduSystemVersionManager", "do remane " + this.$outputFile.getAbsolutePath() + ' ');
            version_code = this.$res.getVersion_code();
            File file2 = this.$outputFile;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Object[] objArr2 = {version_code};
            String format2 = String.format(ConfigContant.SYSTEM_UPDATE_FILE_NAME_FORMAT, Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
            rename = FileUtils.rename(file2, format2);
            NetWorkLog.INSTANCE.mo3278d("PuduSystemVersionManager", "checkAndCopyDownloadFile rename success = " + rename);
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            C46421 c46421 = new C46421(null);
            this.L$0 = coroutineScope2;
            this.L$1 = version_code;
            this.Z$0 = rename;
            this.label = 2;
            if (BuildersKt.withContext(main2, c46421, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope3 = coroutineScope2;
            if (!rename) {
            }
            SystemCmdUtils.INSTANCE.execCommand(InvokeServiceData.CALL_TYPE_SYNC, false);
            return Unit.INSTANCE;
        }
        NetWorkLog.INSTANCE.mo3279e("PuduSystemVersionManager", "checkAndCopyDownloadFile md5 is wrong , delete " + this.$outputFile);
        boolean delete = FileUtils.delete(this.$outputFile);
        if (!delete) {
            NetWorkLog.INSTANCE.mo3279e("PuduSystemVersionManager", "checkAndCopyDownloadFile md5 is wrong , delete file failed");
            SystemCmdUtils.INSTANCE.execCommand("rm " + this.$outputFile.getAbsolutePath(), true);
        }
        MainCoroutineDispatcher main3 = Dispatchers.getMain();
        C46432 c46432 = new C46432(null);
        this.L$0 = coroutineScope2;
        this.Z$0 = delete;
        this.label = 3;
        if (BuildersKt.withContext(main3, c46432, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PuduSystemVersionManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.PuduSystemVersionManager$checkFile$1$1", m3970f = "PuduSystemVersionManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.PuduSystemVersionManager$checkFile$1$1 */
    /* loaded from: classes5.dex */
    public static final class C46421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5391p$;

        C46421(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46421 c46421 = new C46421(completion);
            c46421.f5391p$ = (CoroutineScope) obj;
            return c46421;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5391p$;
            PuduSystemVersionManager$checkFile$1.this.$sysInstallCallback.onFinish();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PuduSystemVersionManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.PuduSystemVersionManager$checkFile$1$2", m3970f = "PuduSystemVersionManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.PuduSystemVersionManager$checkFile$1$2 */
    /* loaded from: classes5.dex */
    public static final class C46432 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5392p$;

        C46432(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46432 c46432 = new C46432(completion);
            c46432.f5392p$ = (CoroutineScope) obj;
            return c46432;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46432) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5392p$;
            PuduSystemVersionManager$checkFile$1.this.$sysInstallCallback.onFail(new Throwable("md5 is not same"));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PuduSystemVersionManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.PuduSystemVersionManager$checkFile$1$3", m3970f = "PuduSystemVersionManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.PuduSystemVersionManager$checkFile$1$3 */
    /* loaded from: classes5.dex */
    public static final class C46443 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $e */
        final /* synthetic */ Exception f5393$e;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5394p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C46443(Exception exc, Continuation continuation) {
            super(2, continuation);
            this.f5393$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46443 c46443 = new C46443(this.f5393$e, completion);
            c46443.f5394p$ = (CoroutineScope) obj;
            return c46443;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46443) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5394p$;
            PuduSystemVersionManager$checkFile$1.this.$sysInstallCallback.onFail(this.f5393$e);
            return Unit.INSTANCE;
        }
    }
}
