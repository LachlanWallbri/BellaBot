package com.pudutech.lib_update;

import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.light_network.download.DownloadInfo;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Boolean;)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class PuduSystemVersionManager$checkAndCopyDownloadFile$p$2<T> implements Consumer<Boolean> {
    final /* synthetic */ IShowProgress $checkSystemListener;
    final /* synthetic */ DownloadInfo $downInfo;
    final /* synthetic */ int $versionCode;

    PuduSystemVersionManager$checkAndCopyDownloadFile$p$2(DownloadInfo downloadInfo, int i, IShowProgress iShowProgress) {
        this.$downInfo = downloadInfo;
        this.$versionCode = i;
        this.$checkSystemListener = iShowProgress;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Boolean it) {
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        if (it.booleanValue()) {
            File file = new File("/data/media/0/", this.$downInfo.getFileName());
            Pdlog.m3273d("PuduSystemVersionManager", "do remane " + file.getAbsolutePath() + ' ');
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Integer.valueOf(this.$versionCode)};
            String format = String.format("system_update_%d.pkg", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            boolean rename = FileUtils.rename(file, format);
            Pdlog.m3273d("PuduSystemVersionManager", "checkAndCopyDownloadFile rename success = " + rename);
            PuduSystemVersionManager.access$postOnUiThread(PuduSystemVersionManager.INSTANCE, new Runnable() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkAndCopyDownloadFile$p$2.1
                @Override // java.lang.Runnable
                public final void run() {
                    IShowProgress iShowProgress = PuduSystemVersionManager$checkAndCopyDownloadFile$p$2.this.$checkSystemListener;
                    if (iShowProgress != null) {
                        iShowProgress.onFinish();
                    }
                }
            });
            if (!rename) {
                StringBuilder sb = new StringBuilder();
                sb.append("mv ");
                sb.append(file.getAbsolutePath());
                sb.append(' ');
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr2 = {Integer.valueOf(this.$versionCode)};
                String format2 = String.format("system_update_%d.pkg", Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
                sb.append(new File("/data/media/0/", format2).getAbsolutePath());
                Tools.execCommand(sb.toString(), true);
            }
            Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, false);
            return;
        }
        File file2 = new File("/data/media/0/", this.$downInfo.getFileName());
        Pdlog.m3274e("PuduSystemVersionManager", "checkAndCopyDownloadFile md5 is wrong , delete " + file2);
        if (!FileUtils.delete(file2)) {
            Pdlog.m3274e("PuduSystemVersionManager", "checkAndCopyDownloadFile md5 is wrong , delete file failed");
            Tools.execCommand("rm " + file2.getAbsolutePath(), true);
        }
        PuduSystemVersionManager.access$postOnUiThread(PuduSystemVersionManager.INSTANCE, new Runnable() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkAndCopyDownloadFile$p$2.2
            @Override // java.lang.Runnable
            public final void run() {
                IShowProgress iShowProgress = PuduSystemVersionManager$checkAndCopyDownloadFile$p$2.this.$checkSystemListener;
                if (iShowProgress != null) {
                    iShowProgress.onFail(new Throwable("md5 is not same"));
                }
            }
        });
    }
}
