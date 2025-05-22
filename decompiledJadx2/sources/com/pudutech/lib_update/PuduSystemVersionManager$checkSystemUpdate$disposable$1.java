package com.pudutech.lib_update;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$disposable$1;
import com.pudutech.lib_update.base.net.HttpResult;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.light_network.download.DownLoadObserver;
import com.pudutech.light_network.download.DownloadInfo;
import com.pudutech.light_network.download.DownloadManager;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/lib_update/base/net/HttpResult;", "Lcom/pudutech/lib_update/module/model/VerionResult;", "kotlin.jvm.PlatformType", "accept"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PuduSystemVersionManager$checkSystemUpdate$disposable$1<T> implements Consumer<HttpResult<VerionResult>> {
    final /* synthetic */ IShowProgress $checkSystemListener;

    PuduSystemVersionManager$checkSystemUpdate$disposable$1(IShowProgress iShowProgress) {
        this.$checkSystemListener = iShowProgress;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(HttpResult<VerionResult> httpResult) {
        Pdlog.m3273d("PuduSystemVersionManager", "checkSystemUpdate result = " + httpResult);
        if (httpResult.getCode() == 200) {
            if (httpResult.getData().getAvailable() && httpResult.getData().getVersion() != null) {
                Version version = httpResult.getData().getVersion();
                if (version == null) {
                    Intrinsics.throwNpe();
                }
                if (!StringsKt.isBlank(version.getMd5())) {
                    DownloadManager downloadManager = DownloadManager.getInstance();
                    Version version2 = httpResult.getData().getVersion();
                    if (version2 == null) {
                        Intrinsics.throwNpe();
                    }
                    downloadManager.cancel(version2.getUrl());
                    DownloadManager downloadManager2 = DownloadManager.getInstance();
                    Version version3 = httpResult.getData().getVersion();
                    if (version3 == null) {
                        Intrinsics.throwNpe();
                    }
                    downloadManager2.download("/data/media/0/", version3.getUrl(), new C46521(httpResult));
                    return;
                }
            }
            IShowProgress iShowProgress = this.$checkSystemListener;
            if (iShowProgress != null) {
                iShowProgress.onFinish();
                return;
            }
            return;
        }
        IShowProgress iShowProgress2 = this.$checkSystemListener;
        if (iShowProgress2 != null) {
            iShowProgress2.onFinish();
        }
    }

    /* compiled from: PuduSystemVersionManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/lib_update/PuduSystemVersionManager$checkSystemUpdate$disposable$1$1", "Lcom/pudutech/light_network/download/DownLoadObserver;", "onComplete", "", "onError", C3898x.f4338g, "", "onNext", "downloadInfo", "Lcom/pudutech/light_network/download/DownloadInfo;", "onSubscribe", LinkFormat.DOMAIN, "Lio/reactivex/disposables/Disposable;", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$disposable$1$1 */
    /* loaded from: classes4.dex */
    public static final class C46521 extends DownLoadObserver {
        final /* synthetic */ HttpResult $it;

        C46521(HttpResult httpResult) {
            this.$it = httpResult;
        }

        @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
        public void onComplete() {
            super.onComplete();
            PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
            Version version = ((VerionResult) this.$it.getData()).getVersion();
            if (version == null) {
                Intrinsics.throwNpe();
            }
            int parseInt = Integer.parseInt(version.getVersion_code());
            DownloadInfo downloadInfo = this.downloadInfo;
            Version version2 = ((VerionResult) this.$it.getData()).getVersion();
            if (version2 == null) {
                Intrinsics.throwNpe();
            }
            PuduSystemVersionManager.access$checkAndCopyDownloadFile(puduSystemVersionManager, parseInt, downloadInfo, version2.getMd5(), PuduSystemVersionManager$checkSystemUpdate$disposable$1.this.$checkSystemListener);
        }

        @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
        public void onError(Throwable e) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            super.onError(e);
            Pdlog.m3274e("PuduSystemVersionManager", "DownloadManager onError");
            Pdlog.m3274e("PuduSystemVersionManager", e.getLocalizedMessage());
            Observable.fromCallable(new Callable<T>() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$disposable$1$1$onError$temp$1
                @Override // java.util.concurrent.Callable
                public /* bridge */ /* synthetic */ Object call() {
                    return Boolean.valueOf(call());
                }

                @Override // java.util.concurrent.Callable
                public final boolean call() {
                    DownloadInfo downloadInfo;
                    DownloadInfo downloadInfo2;
                    downloadInfo = PuduSystemVersionManager$checkSystemUpdate$disposable$1.C46521.this.downloadInfo;
                    if (downloadInfo != null) {
                        downloadInfo2 = PuduSystemVersionManager$checkSystemUpdate$disposable$1.C46521.this.downloadInfo;
                        Intrinsics.checkExpressionValueIsNotNull(downloadInfo2, "downloadInfo");
                        File file = new File("/data/media/0/", downloadInfo2.getFileName());
                        boolean delete = FileUtils.delete(file);
                        Pdlog.m3274e("PuduSystemVersionManager", "onError delete " + file.getAbsoluteFile() + " = " + delete);
                        if (!delete) {
                            Tools.execCommand("rm " + file.getAbsoluteFile(), true);
                        }
                    }
                    return true;
                }
            }).observeOn(Schedulers.m3958io()).subscribeOn(Schedulers.m3958io()).subscribe(new Consumer<Boolean>() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$disposable$1$1$onError$temp$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Boolean bool) {
                }
            }, new Consumer<Throwable>() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$disposable$1$1$onError$temp$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                }
            });
            IShowProgress iShowProgress = PuduSystemVersionManager$checkSystemUpdate$disposable$1.this.$checkSystemListener;
            if (iShowProgress != null) {
                iShowProgress.onFail(e);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
        public void onNext(DownloadInfo downloadInfo) {
            Intrinsics.checkParameterIsNotNull(downloadInfo, "downloadInfo");
            super.onNext(downloadInfo);
            IShowProgress iShowProgress = PuduSystemVersionManager$checkSystemUpdate$disposable$1.this.$checkSystemListener;
            if (iShowProgress != null) {
                iShowProgress.onProgress(downloadInfo);
            }
        }

        @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
        public void onSubscribe(Disposable d) {
            Intrinsics.checkParameterIsNotNull(d, "d");
            super.onSubscribe(d);
            IShowProgress iShowProgress = PuduSystemVersionManager$checkSystemUpdate$disposable$1.this.$checkSystemListener;
            if (iShowProgress != null) {
                iShowProgress.onStartProgress();
            }
        }
    }
}
