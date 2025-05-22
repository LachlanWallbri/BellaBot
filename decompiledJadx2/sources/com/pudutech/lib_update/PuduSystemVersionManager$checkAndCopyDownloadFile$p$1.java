package com.pudutech.lib_update;

import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.util.FileUtil;
import java.io.File;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", NotificationCompat.CATEGORY_CALL}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class PuduSystemVersionManager$checkAndCopyDownloadFile$p$1<V, T> implements Callable<T> {
    final /* synthetic */ IShowProgress $checkSystemListener;
    final /* synthetic */ File $file;
    final /* synthetic */ String $md5;

    PuduSystemVersionManager$checkAndCopyDownloadFile$p$1(File file, IShowProgress iShowProgress, String str) {
        this.$file = file;
        this.$checkSystemListener = iShowProgress;
        this.$md5 = str;
    }

    @Override // java.util.concurrent.Callable
    public /* bridge */ /* synthetic */ Object call() {
        return Boolean.valueOf(call());
    }

    @Override // java.util.concurrent.Callable
    public final boolean call() {
        if (!this.$file.exists()) {
            Pdlog.m3273d("PuduSystemVersionManager", "checkDownloadInfo downInfo file not exists ,try wait and check ");
            Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, false);
            Thread.sleep(2000L);
            if (!this.$file.exists()) {
                Pdlog.m3274e("PuduSystemVersionManager", "checkDownloadInfo downInfo file not exists ??");
                IShowProgress iShowProgress = this.$checkSystemListener;
                if (iShowProgress != null) {
                    iShowProgress.onFinish();
                }
                return false;
            }
        }
        String calculateFileMD5 = FileUtil.INSTANCE.calculateFileMD5(this.$file);
        Pdlog.m3273d("PuduSystemVersionManager", "checkDownInfo start check md5 , file md5 = " + this.$md5 + " , fileMd5 = " + calculateFileMD5);
        String str = calculateFileMD5;
        return ((str == null || StringsKt.isBlank(str)) || (Intrinsics.areEqual(this.$md5, calculateFileMD5) ^ true)) ? false : true;
    }
}
