package com.pudutech.lib_update.util;

import android.content.Context;
import android.os.Build;
import android.os.RecoverySystem;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.config.ConfigContant;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.light_network.download.DownLoadObserver;
import com.pudutech.light_network.download.DownloadInfo;
import com.pudutech.light_network.download.DownloadManager;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/lib_update/util/DownloadUtil;", "", "()V", "Companion", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DownloadUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* compiled from: DownloadUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/lib_update/util/DownloadUtil$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "downloadApp", "", "context", "Landroid/content/Context;", "version", "Lcom/pudutech/lib_update/module/model/Version;", "delegate", "Lcom/pudutech/light_network/download/DownLoadObserver;", "downloadSystem", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return DownloadUtil.TAG;
        }

        public final void downloadSystem(final Context context, Version version, final DownLoadObserver delegate) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(version, "version");
            Intrinsics.checkParameterIsNotNull(delegate, "delegate");
            final String md5 = version.getMd5();
            DownloadManager.getInstance().cancel(version.getUrl());
            DownloadManager.getInstance().download(ConfigContant.DEFAULT_SAVE_FILE_PATH, version.getUrl(), new DownLoadObserver() { // from class: com.pudutech.lib_update.util.DownloadUtil$Companion$downloadSystem$1
                private String fileName = "";
                private float last_progress;

                /* renamed from: getFileName$lib_update_packRelease, reason: from getter */
                public final String getFileName() {
                    return this.fileName;
                }

                public final void setFileName$lib_update_packRelease(String str) {
                    Intrinsics.checkParameterIsNotNull(str, "<set-?>");
                    this.fileName = str;
                }

                /* renamed from: getLast_progress$lib_update_packRelease, reason: from getter */
                public final float getLast_progress() {
                    return this.last_progress;
                }

                public final void setLast_progress$lib_update_packRelease(float f) {
                    this.last_progress = f;
                }

                @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                    Intrinsics.checkParameterIsNotNull(d, "d");
                    super.onSubscribe(d);
                    DownLoadObserver.this.onSubscribe(d);
                    Pdlog.m3275i(DownloadUtil.INSTANCE.getTAG(), "onSubscribe");
                }

                @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
                public void onError(Throwable e) {
                    Intrinsics.checkParameterIsNotNull(e, "e");
                    super.onError(e);
                    DownLoadObserver.this.onError(e);
                    Pdlog.m3275i(DownloadUtil.INSTANCE.getTAG(), "ex:" + e);
                }

                @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
                public void onComplete() {
                    super.onComplete();
                    DownLoadObserver.this.onComplete();
                    String str = md5;
                    if (str != null) {
                        if (!(str.length() == 0)) {
                            Pdlog.m3273d(DownloadUtil.INSTANCE.getTAG(), "http md5: " + md5);
                            File file = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, this.fileName);
                            String calculateFileMD5 = FileUtil.INSTANCE.calculateFileMD5(file);
                            if (calculateFileMD5 != null) {
                                Pdlog.m3273d(DownloadUtil.INSTANCE.getTAG(), "file md5: " + calculateFileMD5);
                                if (!Intrinsics.areEqual(md5, calculateFileMD5)) {
                                    file.deleteOnExit();
                                    Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), "download error file check http md5: " + md5 + " & file md5 : " + calculateFileMD5);
                                    return;
                                }
                            }
                            File file2 = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, "update.pkg");
                            if (Build.VERSION.SDK_INT > 26) {
                                try {
                                    if (file2.exists()) {
                                        Files.delete(file2.toPath());
                                    }
                                } catch (Exception e) {
                                    Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), e.getLocalizedMessage());
                                }
                                try {
                                    Files.copy(file.toPath(), file2.toPath(), new CopyOption[0]);
                                } catch (UnsupportedOperationException e2) {
                                    Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), e2.getLocalizedMessage());
                                }
                                try {
                                    Files.delete(file.toPath());
                                } catch (Exception e3) {
                                    Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), e3.getLocalizedMessage());
                                }
                            } else {
                                if (file2.exists()) {
                                    try {
                                        file2.delete();
                                    } catch (SecurityException e4) {
                                        Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), e4.getLocalizedMessage());
                                    }
                                }
                                try {
                                    FileUtils.copyFile(file, file2);
                                } catch (Exception e5) {
                                    Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), e5.getLocalizedMessage());
                                }
                                try {
                                    file.delete();
                                } catch (Exception e6) {
                                    Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), e6.getLocalizedMessage());
                                }
                            }
                            try {
                                RecoverySystem.installPackage(context, file2);
                            } catch (IOException e7) {
                                Pdlog.m3274e(DownloadUtil.INSTANCE.getTAG(), e7.getLocalizedMessage());
                            }
                        }
                    }
                    Pdlog.m3275i(DownloadUtil.INSTANCE.getTAG(), "........onComplete........." + this.fileName);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
                public void onNext(DownloadInfo downloadInfo) {
                    Intrinsics.checkParameterIsNotNull(downloadInfo, "downloadInfo");
                    super.onNext(downloadInfo);
                    DownLoadObserver.this.onNext(downloadInfo);
                    if (Intrinsics.areEqual("", this.fileName)) {
                        String fileName = downloadInfo.getFileName();
                        Intrinsics.checkExpressionValueIsNotNull(fileName, "downloadInfo.fileName");
                        this.fileName = fileName;
                    }
                    float progress = (((float) downloadInfo.getProgress()) * 1.0f) / ((float) downloadInfo.getTotal());
                    if (progress == 1.0f) {
                        Pdlog.m3275i(DownloadUtil.INSTANCE.getTAG(), "........onNext download 100%.....");
                    }
                    if (progress - this.last_progress > 0.05d) {
                        Pdlog.m3275i(DownloadUtil.INSTANCE.getTAG(), "downloading, progress " + (100 * progress) + "%");
                        this.last_progress = progress;
                    }
                }
            });
        }

        public final void downloadApp(Context context, Version version, DownLoadObserver delegate) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(version, "version");
            Intrinsics.checkParameterIsNotNull(delegate, "delegate");
            String md5 = version.getMd5();
            DownloadManager.getInstance().cancel(version.getUrl());
            DownloadManager.getInstance().download(ConfigContant.DEFAULT_SAVE_FILE_PATH, version.getUrl(), new DownloadUtil$Companion$downloadApp$1(delegate, version, md5));
        }
    }
}
