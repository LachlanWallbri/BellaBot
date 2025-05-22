package com.pudutech.lib_update;

import android.util.Log;
import androidx.multidex.MultiDexExtractor;
import com.pudutech.lib_update.config.ConfigContant;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import java.io.FilenameFilter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.PuduSystemVersionManager$checkLocalSystemFile$1", m3970f = "PuduSystemVersionManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class PuduSystemVersionManager$checkLocalSystemFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $callback;
    final /* synthetic */ int $currentSysVersionCode;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5395p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PuduSystemVersionManager$checkLocalSystemFile$1(Function1 function1, int i, Continuation continuation) {
        super(2, continuation);
        this.$callback = function1;
        this.$currentSysVersionCode = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PuduSystemVersionManager$checkLocalSystemFile$1 puduSystemVersionManager$checkLocalSystemFile$1 = new PuduSystemVersionManager$checkLocalSystemFile$1(this.$callback, this.$currentSysVersionCode, completion);
        puduSystemVersionManager$checkLocalSystemFile$1.f5395p$ = (CoroutineScope) obj;
        return puduSystemVersionManager$checkLocalSystemFile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PuduSystemVersionManager$checkLocalSystemFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [T, java.io.File] */
    /* JADX WARN: Type inference failed for: r13v0, types: [T, java.lang.Object, java.io.File] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5395p$;
            File file = new File("/data/media/0/");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = "";
            File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkLocalSystemFile$1$listFiles$1
                @Override // java.io.FilenameFilter
                public boolean accept(File dir, String name) {
                    NetWorkLog.INSTANCE.mo3278d("PuduSystemVersionManager", "checkLocalSystemFile , update file is " + name);
                    return name != null && StringsKt.endsWith$default(name, ConfigContant.SYSTEM_FILE_SUFFIX, false, 2, (Object) null);
                }
            });
            if (listFiles != 0) {
                if (!(listFiles.length == 0)) {
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (File) 0;
                    int i2 = 0;
                    for (?? it : listFiles) {
                        PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        String name = it.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                        String versionCodeFormFileName = puduSystemVersionManager.getVersionCodeFormFileName(name);
                        NetWorkLog.INSTANCE.mo3278d("PuduSystemVersionManager", "path = " + it.getAbsolutePath() + " , code = " + versionCodeFormFileName);
                        String str = versionCodeFormFileName;
                        if (!(str == null || StringsKt.isBlank(str))) {
                            try {
                                i = Integer.parseInt(versionCodeFormFileName);
                            } catch (Exception e) {
                                e = e;
                                i = i2;
                            }
                            if (i > i2) {
                                try {
                                    objectRef2.element = it;
                                } catch (Exception e2) {
                                    e = e2;
                                    e.printStackTrace();
                                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                                    String message = e.getMessage();
                                    if (message == null) {
                                        message = "";
                                    }
                                    netWorkLog.mo3279e("PuduSystemVersionManager", message);
                                    i2 = i;
                                }
                                i2 = i;
                            }
                        }
                    }
                    if (((File) objectRef2.element) == null) {
                        NetWorkLog.INSTANCE.mo3279e("PuduSystemVersionManager", "checkLocalSystemFile file name can not read version");
                        PuduSystemVersionManager.INSTANCE.toMain(new Function0<Unit>() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkLocalSystemFile$1.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                PuduSystemVersionManager$checkLocalSystemFile$1.this.$callback.invoke((String) objectRef.element);
                            }
                        });
                        return Unit.INSTANCE;
                    }
                    NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("path = ");
                    File file2 = (File) objectRef2.element;
                    if (file2 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(file2.getAbsolutePath());
                    sb.append(" , code = ");
                    sb.append(i2);
                    netWorkLog2.mo3278d("PuduSystemVersionManager", sb.toString());
                    try {
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                        String message2 = e3.getMessage();
                        if (message2 == null) {
                            message2 = "";
                        }
                        netWorkLog3.mo3279e("PuduSystemVersionManager", message2);
                    }
                    if (this.$currentSysVersionCode < i2) {
                        PuduSystemVersionManager.INSTANCE.toMain(new Function0<Unit>() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkLocalSystemFile$1.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                Function1 function1 = PuduSystemVersionManager$checkLocalSystemFile$1.this.$callback;
                                File file3 = (File) objectRef2.element;
                                if (file3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                String absolutePath = file3.getAbsolutePath();
                                Intrinsics.checkExpressionValueIsNotNull(absolutePath, "sysUpdateFile!!.absolutePath");
                                function1.invoke(absolutePath);
                            }
                        });
                        return Unit.INSTANCE;
                    }
                    NetWorkLog.INSTANCE.mo3279e("PuduSystemVersionManager", "checkLocalSystemFile , local system update file < currentSysVersionCode");
                    try {
                        for (MultiDexExtractor.ExtractedDex extractedDex : listFiles) {
                            FileUtils.delete(extractedDex);
                        }
                    } catch (Exception e4) {
                        NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
                        String stackTraceString = Log.getStackTraceString(e4);
                        Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(e)");
                        netWorkLog4.mo3279e("PuduSystemVersionManager", stackTraceString);
                    }
                    NetWorkLog.INSTANCE.mo3278d("PuduSystemVersionManager", "checkLocalSystemFile not is new , need delete");
                    PuduSystemVersionManager.INSTANCE.toMain(new Function0<Unit>() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkLocalSystemFile$1.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            PuduSystemVersionManager$checkLocalSystemFile$1.this.$callback.invoke((String) objectRef.element);
                        }
                    });
                    return Unit.INSTANCE;
                }
            }
            NetWorkLog.INSTANCE.mo3278d("PuduSystemVersionManager", "checkLocalSystemFile not has file");
            PuduSystemVersionManager.INSTANCE.toMain(new Function0<Unit>() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkLocalSystemFile$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    PuduSystemVersionManager$checkLocalSystemFile$1.this.$callback.invoke((String) objectRef.element);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
