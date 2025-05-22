package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.Job;

/* compiled from: DmesgWorker.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/DmesgWorker;", "", "()V", "MAX_NUM_DMESG", "", "TAG", "", "kotlin.jvm.PlatformType", "dmesgDir", "dmesgJob", "Lkotlinx/coroutines/Job;", "dmesgJobFinished", "", "checkDmesgFile", "", "dmesgWork", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DmesgWorker {
    public static final DmesgWorker INSTANCE;
    private static final int MAX_NUM_DMESG;
    private static final String TAG;
    private static String dmesgDir;
    private static volatile Job dmesgJob;
    private static boolean dmesgJobFinished;

    static {
        DmesgWorker dmesgWorker = new DmesgWorker();
        INSTANCE = dmesgWorker;
        TAG = dmesgWorker.getClass().getSimpleName();
        dmesgJobFinished = true;
        dmesgDir = "/sdcard/pudu/dmesg";
        MAX_NUM_DMESG = 100;
    }

    private DmesgWorker() {
    }

    public final void dmesgWork() {
        Job launch$default;
        if (dmesgJob != null && !dmesgJobFinished) {
            Pdlog.m3273d(TAG, "SDKRobotState.Error happens, but dmesg job is already ongoing!");
            return;
        }
        Pdlog.m3273d(TAG, "SDKRobotState.Error happens, begin dmesg job.");
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, new CoroutineName("DmesgJob"), null, new DmesgWorker$dmesgWork$1(null), 2, null);
        dmesgJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkDmesgFile() throws Exception {
        File file = new File(dmesgDir);
        if (!file.exists()) {
            file.mkdir();
        }
        if (file.exists() && file.isFile()) {
            file.delete();
            file.mkdir();
        }
        File[] listFiles = file.listFiles();
        Intrinsics.checkExpressionValueIsNotNull(listFiles, "rootFile.listFiles()");
        List mutableList = ArraysKt.toMutableList(listFiles);
        if (mutableList == null || mutableList.size() < MAX_NUM_DMESG) {
            return;
        }
        Collections.sort(mutableList, new Comparator<File>() { // from class: com.pudutech.mirsdk.DmesgWorker$checkDmesgFile$1
            @Override // java.util.Comparator
            public final int compare(File file2, File file3) {
                if (file2.lastModified() < file3.lastModified()) {
                    return -1;
                }
                return file2.lastModified() > file3.lastModified() ? 1 : 0;
            }
        });
        Iterator it = mutableList.iterator();
        while (it.hasNext()) {
            File file2 = (File) it.next();
            Intrinsics.checkExpressionValueIsNotNull(file2, "file");
            if (file2.isFile()) {
                file2.delete();
                it.remove();
            }
            if (mutableList.size() < MAX_NUM_DMESG - 10) {
                return;
            }
        }
    }
}
