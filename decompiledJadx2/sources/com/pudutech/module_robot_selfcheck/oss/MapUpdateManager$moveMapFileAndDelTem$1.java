package com.pudutech.module_robot_selfcheck.oss;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.FileUtils;
import java.io.File;
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
/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$moveMapFileAndDelTem$1", m3970f = "MapUpdateManager.kt", m3971i = {0, 0, 0, 0, 0, 1, 1}, m3972l = {559, 565}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "tempFile", "mapFile", "mapFiles", "listFiles", "$this$launch", "copyDir"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class MapUpdateManager$moveMapFileAndDelTem$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $result;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6757p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdateManager$moveMapFileAndDelTem$1(Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$result = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdateManager$moveMapFileAndDelTem$1 mapUpdateManager$moveMapFileAndDelTem$1 = new MapUpdateManager$moveMapFileAndDelTem$1(this.$result, completion);
        mapUpdateManager$moveMapFileAndDelTem$1.f6757p$ = (CoroutineScope) obj;
        return mapUpdateManager$moveMapFileAndDelTem$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdateManager$moveMapFileAndDelTem$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        int i2 = 1;
        if (i != 0) {
            if (i == 1) {
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6757p$;
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            str = MapUpdateManager.TAG;
            int i3 = 0;
            Pdlog.m3274e(str, FileUtils.getDirSize(MapUpdateManager.INSTANCE.getMAP_FILE_PATH()));
            if (!Intrinsics.areEqual(FileUtils.getDirSize(MapUpdateManager.INSTANCE.getMAP_FILE_PATH()), "0.000B")) {
                MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
                str4 = MapUpdateManager.DOWNLOAD_TEMP_FILE_PATH;
                File file = new File(str4);
                File file2 = new File(MapUpdateManager.INSTANCE.getMAP_FILE_PATH());
                File[] mapFiles = file2.listFiles();
                File[] listFiles = file.listFiles();
                MapUpdateManager mapUpdateManager3 = MapUpdateManager.INSTANCE;
                str5 = MapUpdateManager.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append(listFiles);
                sb.append(mapFiles);
                sb.append(' ');
                Pdlog.m3274e(str5, sb.toString());
                Intrinsics.checkExpressionValueIsNotNull(listFiles, "listFiles");
                int length = listFiles.length;
                int i4 = 0;
                while (i4 < length) {
                    String fileName = FileUtils.getFileName(listFiles[i4]);
                    MapUpdateManager mapUpdateManager4 = MapUpdateManager.INSTANCE;
                    str7 = MapUpdateManager.TAG;
                    Object[] objArr = new Object[i2];
                    objArr[i3] = "filename=" + fileName;
                    Pdlog.m3274e(str7, objArr);
                    Intrinsics.checkExpressionValueIsNotNull(mapFiles, "mapFiles");
                    int length2 = mapFiles.length;
                    int i5 = i3;
                    while (i5 < length2) {
                        File file3 = mapFiles[i5];
                        if (Intrinsics.areEqual(fileName, FileUtils.getFileName(file3))) {
                            Object[] objArr2 = new Object[i2];
                            objArr2[0] = "delete=" + FileUtils.delete(file3);
                            Pdlog.m3274e(RequestParameters.SUBRESOURCE_DELETE, objArr2);
                        }
                        i5++;
                        i2 = 1;
                    }
                    i4++;
                    i2 = 1;
                    i3 = 0;
                }
                for (File file4 : listFiles) {
                    String fileName2 = FileUtils.getFileName(file4);
                    MapUpdateManager mapUpdateManager5 = MapUpdateManager.INSTANCE;
                    str6 = MapUpdateManager.TAG;
                    Pdlog.m3274e(str6, "filename2=" + fileName2);
                    FileUtils.moveFile(file4, new File(MapUpdateManager.INSTANCE.getMAP_FILE_PATH() + fileName2));
                }
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C53823 c53823 = new C53823(null);
                this.L$0 = coroutineScope;
                this.L$1 = file;
                this.L$2 = file2;
                this.L$3 = mapFiles;
                this.L$4 = listFiles;
                this.label = 1;
                if (BuildersKt.withContext(main, c53823, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                MapUpdateManager mapUpdateManager6 = MapUpdateManager.INSTANCE;
                str2 = MapUpdateManager.TAG;
                Pdlog.m3274e(str2, MapUpdateManager.INSTANCE.getMAP_FILE_PATH() + " is Empty");
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                MapUpdateManager mapUpdateManager7 = MapUpdateManager.INSTANCE;
                str3 = MapUpdateManager.DOWNLOAD_TEMP_FILE_PATH;
                booleanRef.element = FileUtils.copyDir(str3, MapUpdateManager.INSTANCE.getMAP_FILE_PATH());
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                C53834 c53834 = new C53834(booleanRef, null);
                this.L$0 = coroutineScope;
                this.L$1 = booleanRef;
                this.label = 2;
                if (BuildersKt.withContext(main2, c53834, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MapUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$moveMapFileAndDelTem$1$3", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$moveMapFileAndDelTem$1$3 */
    /* loaded from: classes5.dex */
    public static final class C53823 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6758p$;

        C53823(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53823 c53823 = new C53823(completion);
            c53823.f6758p$ = (CoroutineScope) obj;
            return c53823;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53823) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6758p$;
            Function1 function1 = MapUpdateManager$moveMapFileAndDelTem$1.this.$result;
            if (function1 != null) {
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MapUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$moveMapFileAndDelTem$1$4", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.module_robot_selfcheck.oss.MapUpdateManager$moveMapFileAndDelTem$1$4 */
    /* loaded from: classes5.dex */
    public static final class C53834 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $copyDir;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6759p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C53834(Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$copyDir = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53834 c53834 = new C53834(this.$copyDir, completion);
            c53834.f6759p$ = (CoroutineScope) obj;
            return c53834;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53834) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6759p$;
            Function1 function1 = MapUpdateManager$moveMapFileAndDelTem$1.this.$result;
            if (function1 != null) {
            }
            return Unit.INSTANCE;
        }
    }
}
