package com.pudutech.pd_network;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.pd_network.IStorage;
import com.pudutech.pd_network.bean.NetEnvironment;
import com.pudutech.pd_network.bean.PdNetworkManagerBuilder;
import com.pudutech.pd_network.report.IReportClient;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007J!\u0010\b\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH&¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH&J\u0011\u0010\u000f\u001a\u00020\u0010H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J)\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00130\u0017¢\u0006\u0002\b\u0019H&J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0010H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/pd_network/IPdNetworkManager;", "Lcom/pudutech/pd_network/IGateway;", "Lcom/pudutech/pd_network/IAutoVerify;", "Lcom/pudutech/pd_network/IStorage;", "Lcom/pudutech/pd_network/report/IReportClient;", "Lcom/pudutech/pd_network/ISupportAbility;", "Lcom/pudutech/pd_network/INetworkType;", "Lcom/pudutech/pd_network/ISN;", "createService", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "crtEnvironment", "Lcom/pudutech/pd_network/bean/NetEnvironment;", "hardID", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "init", "", "context", "Landroid/content/Context;", "block", "Lkotlin/Function1;", "Lcom/pudutech/pd_network/bean/PdNetworkManagerBuilder;", "Lkotlin/ExtensionFunctionType;", "resetBaseUrl", "url", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IPdNetworkManager extends IGateway, IAutoVerify, IStorage, IReportClient, ISupportAbility, INetworkType, ISN {

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static IOssTaskController download(IPdNetworkManager iPdNetworkManager, String url, File downloadFile) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(downloadFile, "downloadFile");
            return IStorage.DefaultImpls.download(iPdNetworkManager, url, downloadFile);
        }
    }

    <T> T createService(Class<T> clazz);

    NetEnvironment crtEnvironment();

    Object hardID(Continuation<? super String> continuation);

    void init(Context context, Function1<? super PdNetworkManagerBuilder, Unit> block);

    void resetBaseUrl(String url);
}
