package com.pudutech.jiagufix;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: JiaguFixUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\bJ@\u0010\u0011\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u00142\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0004j\b\u0012\u0004\u0012\u00020\u0013`\u0006H\u0002J(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0004j\b\u0012\u0004\u0012\u00020\u0013`\u0006H\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/jiagufix/JiaguFixUtil;", "", "()V", "filterPro", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", AUserTrack.UTKEY_START_TIME, "", "thread", "Ljava/lang/Thread;", "fix", "", "context", "Landroid/content/Context;", "checkInterval", "checkTime", "getRunningPIDFromProc", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "cmdContact", "isRight", "", "str", "list", "jiagufix_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class JiaguFixUtil {
    private final ArrayList<Integer> filterPro = new ArrayList<>();
    private long startTime = -1;
    private Thread thread;

    public static /* synthetic */ void fix$default(JiaguFixUtil jiaguFixUtil, Context context, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 500;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = 60000;
        }
        jiaguFixUtil.fix(context, j3, j2);
    }

    public final synchronized void fix(Context context, final long checkInterval, final long checkTime) {
        ArrayList arrayList;
        Intrinsics.checkParameterIsNotNull(context, "context");
        final String packageName = context.getPackageName();
        if (this.thread != null) {
            return;
        }
        ArrayList arrayListOf = CollectionsKt.arrayListOf("libjiagu.so", "libjiagu_a64.so");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        String[] list = resources.getAssets().list("");
        if (list != null) {
            ArrayList arrayList2 = new ArrayList();
            for (String str : list) {
                if (arrayListOf.contains(str)) {
                    arrayList2.add(str);
                }
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        ArrayList arrayList3 = arrayList;
        if (arrayList3 == null || arrayList3.isEmpty()) {
            System.out.println("FixJiagu : not 360 jiagu, do not need check");
            return;
        }
        this.thread = new Thread(new Runnable() { // from class: com.pudutech.jiagufix.JiaguFixUtil$fix$1
            @Override // java.lang.Runnable
            public final void run() {
                long elapsedRealtime;
                long j;
                HashMap runningPIDFromProc;
                JiaguFixUtil.this.startTime = SystemClock.elapsedRealtime();
                boolean z = false;
                while (!z) {
                    try {
                        elapsedRealtime = SystemClock.elapsedRealtime();
                        j = JiaguFixUtil.this.startTime;
                    } catch (Throwable unused) {
                    }
                    if (elapsedRealtime - j > checkTime) {
                        System.out.println("FixJiagu : timeout finish");
                        return;
                    }
                    Thread.sleep(checkInterval);
                    runningPIDFromProc = JiaguFixUtil.this.getRunningPIDFromProc(CollectionsKt.arrayListOf("/system/bin/dex2oat", "--dex-file=/data/data/" + packageName + "/.jiagu"));
                    if (runningPIDFromProc != null) {
                        for (Map.Entry entry : runningPIDFromProc.entrySet()) {
                            System.out.println("FixJiagu : need kill , pid = " + ((Number) entry.getKey()).intValue() + " ; cmd = " + ((String) entry.getValue()));
                            Runtime runtime = Runtime.getRuntime();
                            StringBuilder sb = new StringBuilder();
                            sb.append("kill -9 ");
                            sb.append(((Number) entry.getKey()).intValue());
                            runtime.exec(sb.toString());
                            z = true;
                        }
                    }
                }
            }
        });
        Thread thread = this.thread;
        if (thread != null) {
            thread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HashMap<Integer, String> getRunningPIDFromProc(ArrayList<String> cmdContact) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        try {
            File[] listFiles = new File("/proc").listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    Integer num = (Integer) null;
                    try {
                        String name = file.getName();
                        Intrinsics.checkExpressionValueIsNotNull(name, "temp.name");
                        num = Integer.valueOf(Integer.parseInt(name));
                    } catch (Exception unused) {
                    }
                    if (num != null && !this.filterPro.contains(num)) {
                        File[] listFiles2 = file.listFiles(new FilenameFilter() { // from class: com.pudutech.jiagufix.JiaguFixUtil$getRunningPIDFromProc$listFiles$1
                            @Override // java.io.FilenameFilter
                            public final boolean accept(File file2, String str) {
                                return Intrinsics.areEqual(str, "cmdline");
                            }
                        });
                        if (listFiles2 != null) {
                            if (!(listFiles2.length == 0)) {
                                File file1 = listFiles2[0];
                                if (file1.exists()) {
                                    try {
                                        Intrinsics.checkExpressionValueIsNotNull(file1, "file1");
                                        List readLines$default = FilesKt.readLines$default(file1, null, 1, null);
                                        if (!readLines$default.isEmpty()) {
                                            String str = (String) readLines$default.get(0);
                                            System.out.println("FixJiagu : pid = " + num + " , cmd = " + str);
                                            if (isRight(str, cmdContact)) {
                                                hashMap.put(num, str);
                                            } else {
                                                this.filterPro.add(num);
                                            }
                                        }
                                    } catch (Throwable unused2) {
                                    }
                                }
                            }
                        }
                        this.filterPro.add(num);
                    }
                }
            }
        } catch (Throwable unused3) {
        }
        return hashMap;
    }

    private final boolean isRight(String str, ArrayList<String> list) {
        if (list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) it.next(), false, 2, (Object) null)) {
                return false;
            }
        }
        return true;
    }
}
