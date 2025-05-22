package com.pudutech.bumblebee.presenter.utils;

import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LogUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\u00072\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006J\u0016\u0010\t\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0006\u0010\r\u001a\u00020\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/utils/LogUtil;", "", "()V", "TAG", "", "onClearDone", "Lkotlin/Function0;", "", "onDone", "removeFiles", "logFiles", "", "Ljava/io/File;", "removeLogBeforeLeastTwo", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LogUtil {
    private static boolean isRemoving;
    private final String TAG = "LogUtil";
    private Function0<Unit> onClearDone;

    public final LogUtil removeLogBeforeLeastTwo() {
        Log.i(this.TAG, "removeLogBeforeLeastTwo");
        if (isRemoving) {
            return this;
        }
        isRemoving = true;
        new Thread(new Runnable() { // from class: com.pudutech.bumblebee.presenter.utils.LogUtil$removeLogBeforeLeastTwo$1
            /* JADX WARN: Removed duplicated region for block: B:42:0x00fd  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0100 A[SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void run() {
                Function0 function0;
                boolean z;
                File[] logFiles = new File("/sdcard/pudu/log").listFiles();
                LogUtil logUtil = LogUtil.this;
                Intrinsics.checkExpressionValueIsNotNull(logFiles, "logFiles");
                ArrayList arrayList = new ArrayList();
                for (File it : logFiles) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    String name = it.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                    if (StringsKt.contains$default((CharSequence) name, (CharSequence) "function", false, 2, (Object) null)) {
                        arrayList.add(it);
                    }
                }
                logUtil.removeFiles(arrayList);
                LogUtil logUtil2 = LogUtil.this;
                ArrayList arrayList2 = new ArrayList();
                for (File it2 : logFiles) {
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    String name2 = it2.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name2, "it.name");
                    if (StringsKt.contains$default((CharSequence) name2, (CharSequence) "can_service", false, 2, (Object) null)) {
                        arrayList2.add(it2);
                    }
                }
                logUtil2.removeFiles(arrayList2);
                LogUtil logUtil3 = LogUtil.this;
                ArrayList arrayList3 = new ArrayList();
                int length = logFiles.length;
                int i = 0;
                while (true) {
                    boolean z2 = true;
                    if (i >= length) {
                        break;
                    }
                    File it3 = logFiles[i];
                    Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                    String name3 = it3.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name3, "it.name");
                    if (!StringsKt.contains$default((CharSequence) name3, (CharSequence) "robot", false, 2, (Object) null)) {
                        String name4 = it3.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name4, "it.name");
                        if (!StringsKt.contains$default((CharSequence) name4, (CharSequence) "Robot", false, 2, (Object) null)) {
                            z2 = false;
                        }
                    }
                    if (z2) {
                        arrayList3.add(it3);
                    }
                    i++;
                }
                logUtil3.removeFiles(arrayList3);
                LogUtil logUtil4 = LogUtil.this;
                ArrayList arrayList4 = new ArrayList();
                for (File it4 : logFiles) {
                    Intrinsics.checkExpressionValueIsNotNull(it4, "it");
                    String name5 = it4.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name5, "it.name");
                    if (!StringsKt.contains$default((CharSequence) name5, (CharSequence) "business", false, 2, (Object) null)) {
                        String name6 = it4.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name6, "it.name");
                        if (!StringsKt.contains$default((CharSequence) name6, (CharSequence) "Business", false, 2, (Object) null)) {
                            z = false;
                            if (!z) {
                                arrayList4.add(it4);
                            }
                        }
                    }
                    z = true;
                    if (!z) {
                    }
                }
                logUtil4.removeFiles(arrayList4);
                LogUtil logUtil5 = LogUtil.this;
                ArrayList arrayList5 = new ArrayList();
                for (File it5 : logFiles) {
                    Intrinsics.checkExpressionValueIsNotNull(it5, "it");
                    String name7 = it5.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name7, "it.name");
                    if (StringsKt.contains$default((CharSequence) name7, (CharSequence) "mirsdk", false, 2, (Object) null)) {
                        arrayList5.add(it5);
                    }
                }
                logUtil5.removeFiles(arrayList5);
                LogUtil logUtil6 = LogUtil.this;
                ArrayList arrayList6 = new ArrayList();
                for (File it6 : logFiles) {
                    Intrinsics.checkExpressionValueIsNotNull(it6, "it");
                    String name8 = it6.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name8, "it.name");
                    if (StringsKt.contains$default((CharSequence) name8, (CharSequence) "marker_map", false, 2, (Object) null)) {
                        arrayList6.add(it6);
                    }
                }
                logUtil6.removeFiles(arrayList6);
                function0 = LogUtil.this.onClearDone;
                if (function0 != null) {
                }
                LogUtil.this.onClearDone = (Function0) null;
                LogUtil.isRemoving = false;
            }
        }).start();
        return this;
    }

    public final void onDone(Function0<Unit> onClearDone) {
        this.onClearDone = onClearDone;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeFiles(List<? extends File> logFiles) {
        Log.d(this.TAG, "logFile num=" + logFiles.size());
        List<? extends File> list = logFiles;
        Iterator<T> it = list.iterator();
        long j = 0;
        long j2 = -1;
        while (it.hasNext()) {
            long lastModified = ((File) it.next()).lastModified();
            if (lastModified > j) {
                j2 = j;
                j = lastModified;
            } else if (lastModified > j2) {
                j2 = lastModified;
            }
        }
        Log.d(this.TAG, "lastModified time1=" + j + "  time2=" + j2);
        for (File file : list) {
            if (file.lastModified() < j2) {
                Log.d(this.TAG, "deleteFile " + file.getName());
                file.delete();
            }
        }
    }
}
