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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$moveMapFileAndDelTemp$2", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class MapUpdateManager$moveMapFileAndDelTemp$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mapName;
    final /* synthetic */ Function1 $result;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4672p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdateManager$moveMapFileAndDelTemp$2(String str, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$mapName = str;
        this.$result = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdateManager$moveMapFileAndDelTemp$2 mapUpdateManager$moveMapFileAndDelTemp$2 = new MapUpdateManager$moveMapFileAndDelTemp$2(this.$mapName, this.$result, completion);
        mapUpdateManager$moveMapFileAndDelTemp$2.f4672p$ = (CoroutineScope) obj;
        return mapUpdateManager$moveMapFileAndDelTemp$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdateManager$moveMapFileAndDelTemp$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        File it;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4672p$;
        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
        str = MapUpdateManager.TAG;
        Pdlog.m3273d(str, "moveMapFileAndDelTem");
        MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
        str2 = MapUpdateManager.DOWNLOAD_TEMP_FILE_PATH;
        if (!FileUtils.isFileExists(str2)) {
            MapUpdateManager mapUpdateManager3 = MapUpdateManager.INSTANCE;
            str11 = MapUpdateManager.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("moveMapFileAndDelTemp: ");
            MapUpdateManager mapUpdateManager4 = MapUpdateManager.INSTANCE;
            str12 = MapUpdateManager.DOWNLOAD_TEMP_FILE_PATH;
            sb.append(str12);
            sb.append(" is not exists");
            Pdlog.m3273d(str11, sb.toString());
            return Unit.INSTANCE;
        }
        if (!FileUtils.isFileExists(MapUpdateManager.INSTANCE.getMAP_FILE_PATH())) {
            boolean createOrExistsDir = FileUtils.createOrExistsDir(MapUpdateManager.INSTANCE.getMAP_FILE_PATH());
            MapUpdateManager mapUpdateManager5 = MapUpdateManager.INSTANCE;
            str10 = MapUpdateManager.TAG;
            Pdlog.m3273d(str10, "moveMapFileAndDelTem", Boxing.boxBoolean(createOrExistsDir));
        }
        MapUpdateManager mapUpdateManager6 = MapUpdateManager.INSTANCE;
        str3 = MapUpdateManager.TAG;
        Pdlog.m3274e(str3, "moveMapFileAndDelTem Dir Size : " + FileUtils.getDirSize(MapUpdateManager.INSTANCE.getMAP_FILE_PATH()));
        if (!Intrinsics.areEqual(FileUtils.getDirSize(MapUpdateManager.INSTANCE.getMAP_FILE_PATH()), "0.000B")) {
            MapUpdateManager mapUpdateManager7 = MapUpdateManager.INSTANCE;
            str6 = MapUpdateManager.DOWNLOAD_TEMP_FILE_PATH;
            File file = new File(str6, this.$mapName);
            File[] listFiles = new File(MapUpdateManager.INSTANCE.getMAP_FILE_PATH()).listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "mapDir.listFiles()");
            int length = listFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    it = null;
                    break;
                }
                it = listFiles[i];
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (Boxing.boxBoolean(Intrinsics.areEqual(it.getName(), file.getName())).booleanValue()) {
                    break;
                }
                i++;
            }
            MapUpdateManager mapUpdateManager8 = MapUpdateManager.INSTANCE;
            str7 = MapUpdateManager.TAG;
            Pdlog.m3273d(str7, "moveMapFileAndDelTemp tempFile:" + it + ' ');
            if (it != null) {
                BackupMapManager.INSTANCE.backup(it);
                FileUtils.delete(it);
            }
            StringBuilder sb2 = new StringBuilder();
            MapUpdateManager mapUpdateManager9 = MapUpdateManager.INSTANCE;
            str8 = MapUpdateManager.DOWNLOAD_TEMP_FILE_PATH;
            sb2.append(str8);
            sb2.append('/');
            sb2.append(this.$mapName);
            boolean moveFile = FileUtils.moveFile(sb2.toString(), MapUpdateManager.INSTANCE.getMAP_FILE_PATH() + '/' + this.$mapName);
            MapUpdateManager mapUpdateManager10 = MapUpdateManager.INSTANCE;
            str9 = MapUpdateManager.TAG;
            Pdlog.m3273d(str9, "moveMapFileAndDelTemp copyFile: " + moveFile);
            this.$result.invoke(Boxing.boxBoolean(moveFile));
        } else {
            StringBuilder sb3 = new StringBuilder();
            MapUpdateManager mapUpdateManager11 = MapUpdateManager.INSTANCE;
            str4 = MapUpdateManager.DOWNLOAD_TEMP_FILE_PATH;
            sb3.append(str4);
            sb3.append('/');
            sb3.append(this.$mapName);
            boolean moveFile2 = FileUtils.moveFile(sb3.toString(), MapUpdateManager.INSTANCE.getMAP_FILE_PATH() + '/' + this.$mapName);
            MapUpdateManager mapUpdateManager12 = MapUpdateManager.INSTANCE;
            str5 = MapUpdateManager.TAG;
            Pdlog.m3273d(str5, "moveMapFileAndDelTemp copyFile: " + moveFile2);
            this.$result.invoke(Boxing.boxBoolean(moveFile2));
        }
        return Unit.INSTANCE;
    }
}
