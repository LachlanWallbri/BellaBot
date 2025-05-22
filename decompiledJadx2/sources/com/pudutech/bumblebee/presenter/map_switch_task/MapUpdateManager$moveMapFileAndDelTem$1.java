package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.FileUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$moveMapFileAndDelTem$1", m3970f = "MapUpdateManager.kt", m3971i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, m3972l = {666, 673}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "tempFile", "mapFile", "mapFiles", "listFiles", "$this$forEach$iv", "element$iv", "it", "fileName", "$this$launch", "copyDir"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$0", "L$1"})
/* loaded from: classes4.dex */
public final class MapUpdateManager$moveMapFileAndDelTem$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $result;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4670p$;

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
        mapUpdateManager$moveMapFileAndDelTem$1.f4670p$ = (CoroutineScope) obj;
        return mapUpdateManager$moveMapFileAndDelTem$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdateManager$moveMapFileAndDelTem$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ea  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x01dd -> B:12:0x01e0). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        boolean z;
        CoroutineScope coroutineScope;
        File file;
        File file2;
        File[] fileArr;
        File[] fileArr2;
        int i;
        int length;
        Object obj2;
        MapUpdateManager$moveMapFileAndDelTem$1 mapUpdateManager$moveMapFileAndDelTem$1;
        File[] fileArr3;
        File[] fileArr4;
        String str6;
        int i2;
        String str7;
        String str8;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        int i4 = 0;
        int i5 = 1;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f4670p$;
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            str = MapUpdateManager.TAG;
            Pdlog.m3274e(str, FileUtils.getDirSize(MapUpdateManager.INSTANCE.getMAP_FILE_PATH()));
            if (!Intrinsics.areEqual(FileUtils.getDirSize(MapUpdateManager.INSTANCE.getMAP_FILE_PATH()), "0.000B")) {
                MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
                str4 = MapUpdateManager.DOWNLOAD_TEMP_FILE_PATH;
                File file3 = new File(str4);
                File file4 = new File(MapUpdateManager.INSTANCE.getMAP_FILE_PATH());
                File[] mapFiles = file4.listFiles();
                File[] listFiles = file3.listFiles();
                MapUpdateManager mapUpdateManager3 = MapUpdateManager.INSTANCE;
                str5 = MapUpdateManager.TAG;
                Pdlog.m3274e(str5, "moveMapFileAndDelTem " + listFiles + mapFiles + ' ');
                if (listFiles != null) {
                    if (!(listFiles.length == 0)) {
                        z = false;
                        if (!z) {
                            this.$result.invoke(Boxing.boxBoolean(false));
                            return Unit.INSTANCE;
                        }
                        int length2 = listFiles.length;
                        int i6 = 0;
                        while (i6 < length2) {
                            String fileName = FileUtils.getFileName(listFiles[i6]);
                            MapUpdateManager mapUpdateManager4 = MapUpdateManager.INSTANCE;
                            str6 = MapUpdateManager.TAG;
                            Object[] objArr = new Object[i5];
                            objArr[i4] = "moveMapFileAndDelTem filename=" + fileName;
                            Pdlog.m3274e(str6, objArr);
                            Intrinsics.checkExpressionValueIsNotNull(mapFiles, "mapFiles");
                            int length3 = mapFiles.length;
                            int i7 = i4;
                            while (i7 < length3) {
                                File file5 = mapFiles[i7];
                                if (Intrinsics.areEqual(fileName, FileUtils.getFileName(file5))) {
                                    boolean delete = FileUtils.delete(file5);
                                    MapUpdateManager mapUpdateManager5 = MapUpdateManager.INSTANCE;
                                    str7 = MapUpdateManager.TAG;
                                    Object[] objArr2 = new Object[i5];
                                    StringBuilder sb = new StringBuilder();
                                    i2 = length3;
                                    sb.append("moveMapFileAndDelTem delete=");
                                    sb.append(delete);
                                    objArr2[0] = sb.toString();
                                    Pdlog.m3274e(str7, objArr2);
                                } else {
                                    i2 = length3;
                                }
                                i7++;
                                length3 = i2;
                                i5 = 1;
                            }
                            i6++;
                            i4 = 0;
                            i5 = 1;
                        }
                        coroutineScope = coroutineScope2;
                        file = file3;
                        file2 = file4;
                        fileArr = listFiles;
                        fileArr2 = fileArr;
                        i = 0;
                        length = listFiles.length;
                        obj2 = coroutine_suspended;
                        mapUpdateManager$moveMapFileAndDelTem$1 = this;
                        fileArr3 = mapFiles;
                        fileArr4 = fileArr2;
                        if (i < length) {
                        }
                    }
                }
                z = true;
                if (!z) {
                }
            } else {
                MapUpdateManager mapUpdateManager6 = MapUpdateManager.INSTANCE;
                str2 = MapUpdateManager.TAG;
                Pdlog.m3274e(str2, "moveMapFileAndDelTem " + MapUpdateManager.INSTANCE.getMAP_FILE_PATH() + " is Empty");
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                MapUpdateManager mapUpdateManager7 = MapUpdateManager.INSTANCE;
                str3 = MapUpdateManager.DOWNLOAD_TEMP_FILE_PATH;
                booleanRef.element = FileUtils.copyDir(str3, MapUpdateManager.INSTANCE.getMAP_FILE_PATH());
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C40853 c40853 = new C40853(booleanRef, null);
                this.L$0 = coroutineScope2;
                this.L$1 = booleanRef;
                this.label = 2;
                if (BuildersKt.withContext(main, c40853, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i3 == 1) {
            i = this.I$1;
            length = this.I$0;
            fileArr = (File[]) this.L$6;
            fileArr2 = (File[]) this.L$5;
            fileArr4 = (File[]) this.L$4;
            fileArr3 = (File[]) this.L$3;
            file2 = (File) this.L$2;
            file = (File) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = coroutine_suspended;
            int i8 = 1;
            mapUpdateManager$moveMapFileAndDelTem$1 = this;
            i += i8;
            if (i < length) {
                File file6 = fileArr[i];
                String fileName2 = FileUtils.getFileName(file6);
                MapUpdateManager mapUpdateManager8 = MapUpdateManager.INSTANCE;
                str8 = MapUpdateManager.TAG;
                StringBuilder sb2 = new StringBuilder();
                Object obj3 = obj2;
                sb2.append("moveMapFileAndDelTem filename2=");
                sb2.append(fileName2);
                Pdlog.m3274e(str8, sb2.toString());
                FileUtils.moveFile(file6, new File(MapUpdateManager.INSTANCE.getMAP_FILE_PATH() + fileName2));
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                C4076xb851e1f1 c4076xb851e1f1 = new C4076xb851e1f1(null, mapUpdateManager$moveMapFileAndDelTem$1);
                mapUpdateManager$moveMapFileAndDelTem$1.L$0 = coroutineScope;
                mapUpdateManager$moveMapFileAndDelTem$1.L$1 = file;
                mapUpdateManager$moveMapFileAndDelTem$1.L$2 = file2;
                mapUpdateManager$moveMapFileAndDelTem$1.L$3 = fileArr3;
                mapUpdateManager$moveMapFileAndDelTem$1.L$4 = fileArr4;
                mapUpdateManager$moveMapFileAndDelTem$1.L$5 = fileArr2;
                mapUpdateManager$moveMapFileAndDelTem$1.L$6 = fileArr;
                mapUpdateManager$moveMapFileAndDelTem$1.I$0 = length;
                mapUpdateManager$moveMapFileAndDelTem$1.I$1 = i;
                mapUpdateManager$moveMapFileAndDelTem$1.L$7 = file6;
                mapUpdateManager$moveMapFileAndDelTem$1.L$8 = file6;
                mapUpdateManager$moveMapFileAndDelTem$1.L$9 = fileName2;
                i8 = 1;
                mapUpdateManager$moveMapFileAndDelTem$1.label = 1;
                Object withContext = BuildersKt.withContext(main2, c4076xb851e1f1, mapUpdateManager$moveMapFileAndDelTem$1);
                obj2 = obj3;
                if (withContext == obj2) {
                    return obj2;
                }
                i += i8;
                if (i < length) {
                }
            }
        } else {
            if (i3 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MapUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$moveMapFileAndDelTem$1$3", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$moveMapFileAndDelTem$1$3 */
    /* loaded from: classes4.dex */
    public static final class C40853 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $copyDir;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4671p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40853(Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$copyDir = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C40853 c40853 = new C40853(this.$copyDir, completion);
            c40853.f4671p$ = (CoroutineScope) obj;
            return c40853;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40853) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4671p$;
            Function1 function1 = MapUpdateManager$moveMapFileAndDelTem$1.this.$result;
            if (function1 != null) {
            }
            return Unit.INSTANCE;
        }
    }
}
