package com.pudutech.lib_update.util;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.light_network.download.DownLoadObserver;
import com.pudutech.light_network.download.DownloadInfo;
import io.reactivex.disposables.Disposable;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: DownloadUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u000f2\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u000f2\b\b\u0001\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u000f2\b\b\u0001\u0010\u0017\u001a\u00020\u0018H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0019"}, m3961d2 = {"com/pudutech/lib_update/util/DownloadUtil$Companion$downloadApp$1", "Lcom/pudutech/light_network/download/DownLoadObserver;", "fileName", "", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "last_progress", "", "getLast_progress", "()F", "setLast_progress", "(F)V", "onComplete", "", "onError", C3898x.f4338g, "", "onNext", "downloadInfo", "Lcom/pudutech/light_network/download/DownloadInfo;", "onSubscribe", LinkFormat.DOMAIN, "Lio/reactivex/disposables/Disposable;", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DownloadUtil$Companion$downloadApp$1 extends DownLoadObserver {
    final /* synthetic */ DownLoadObserver $delegate;
    final /* synthetic */ String $md5;
    final /* synthetic */ Version $version;
    private String fileName = "";
    private float last_progress;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadUtil$Companion$downloadApp$1(DownLoadObserver downLoadObserver, Version version, String str) {
        this.$delegate = downLoadObserver;
        this.$version = version;
        this.$md5 = str;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final void setFileName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.fileName = str;
    }

    public final float getLast_progress() {
        return this.last_progress;
    }

    public final void setLast_progress(float f) {
        this.last_progress = f;
    }

    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        Intrinsics.checkParameterIsNotNull(d, "d");
        super.onSubscribe(d);
        this.$delegate.onSubscribe(d);
        Pdlog.m3275i(DownloadUtil.INSTANCE.getTAG(), "downloadApp------->onSubscribe" + this.$version.getUrl());
    }

    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onError(Throwable e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        super.onError(e);
        this.$delegate.onError(e);
        Pdlog.m3275i(DownloadUtil.INSTANCE.getTAG(), "ex:" + e);
    }

    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onComplete() {
        super.onComplete();
        if (this.$md5.length() > 0) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DownloadUtil$Companion$downloadApp$1$onComplete$1(this, null), 3, null);
        } else {
            this.$delegate.onError(new IOException("md5 is null"));
        }
        Pdlog.m3275i(DownloadUtil.INSTANCE.getTAG(), "........onComplete........." + this.fileName);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onNext(DownloadInfo downloadInfo) {
        Intrinsics.checkParameterIsNotNull(downloadInfo, "downloadInfo");
        super.onNext(downloadInfo);
        this.$delegate.onNext(downloadInfo);
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
}
