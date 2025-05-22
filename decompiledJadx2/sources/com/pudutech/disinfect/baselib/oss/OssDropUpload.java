package com.pudutech.disinfect.baselib.oss;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: OssDropUpload.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\"B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u0002J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0006H\u0002J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ#\u0010\u001d\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/oss/OssDropUpload;", "", "builder", "Lcom/pudutech/disinfect/baselib/oss/OssDropUpload$Builder;", "(Lcom/pudutech/disinfect/baselib/oss/OssDropUpload$Builder;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "mAK", "mBucket", "mOSSClient", "Lcom/alibaba/sdk/android/oss/OSSClient;", "mOssEndpoint", "mRegion", "mSK", "getOSSAuthCredentialsProvider", "Lcom/alibaba/sdk/android/oss/common/auth/OSSCredentialProvider;", "getOSSConfig", "Lcom/alibaba/sdk/android/oss/ClientConfiguration;", "getTime", "time", "getWIFIMac", "upLoadCliffIrImg", "", "p0", "Landroid/os/ParcelFileDescriptor;", "size", "", "updata", "objectKey", "byteArray", "", "(Ljava/lang/String;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Builder", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class OssDropUpload {
    private final String TAG;
    private String mAK;
    private String mBucket;
    private OSSClient mOSSClient;
    private String mOssEndpoint;
    private String mRegion;
    private String mSK;

    public /* synthetic */ OssDropUpload(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    public final String getTAG() {
        return this.TAG;
    }

    private OssDropUpload(Builder builder) {
        this.TAG = "OssDropUpload";
        String mRegion = builder.getMRegion();
        this.mRegion = mRegion == null ? "oss-cn-shenzhen" : mRegion;
        this.mOssEndpoint = "http://" + this.mRegion + ".aliyuncs.com";
        String mBucket = builder.getMBucket();
        this.mBucket = mBucket == null ? "bella-fall" : mBucket;
        String mak = builder.getMAK();
        this.mAK = mak == null ? "LTAI5tG6HXkpujVcMV6PSUGw" : mak;
        String msk = builder.getMSK();
        this.mSK = msk == null ? "TB1BB3MolAeUJj6aHnXhnGW7Xq31CP" : msk;
        Pdlog.m3273d(this.TAG, "init()##mRegion:" + this.mRegion + "##mOssEndpoint:" + this.mOssEndpoint + "##mBucket:" + this.mBucket + "##mAK:" + this.mAK + "##mSK:" + this.mSK);
        this.mOSSClient = new OSSClient(BaseApp.INSTANCE.getINSTANCE(), this.mOssEndpoint, getOSSAuthCredentialsProvider(), getOSSConfig());
    }

    /* compiled from: OssDropUpload.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0016\u001a\u00020\u00002\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\u00002\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/oss/OssDropUpload$Builder;", "", "()V", "mAK", "", "getMAK", "()Ljava/lang/String;", "setMAK", "(Ljava/lang/String;)V", "mBucket", "getMBucket", "setMBucket", "mRegion", "getMRegion", "setMRegion", "mSK", "getMSK", "setMSK", "mStsServerUrl", "getMStsServerUrl", "setMStsServerUrl", "ak", OSSConfig.PARAM_BUCKET, JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/pudutech/disinfect/baselib/oss/OssDropUpload;", OSSConfig.PARAM_REGION, "sk", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Builder {
        private String mAK;
        private String mBucket;
        private String mRegion;
        private String mSK;
        private String mStsServerUrl;

        public final String getMRegion() {
            return this.mRegion;
        }

        public final void setMRegion(String str) {
            this.mRegion = str;
        }

        public final String getMBucket() {
            return this.mBucket;
        }

        public final void setMBucket(String str) {
            this.mBucket = str;
        }

        public final String getMAK() {
            return this.mAK;
        }

        public final void setMAK(String str) {
            this.mAK = str;
        }

        public final String getMSK() {
            return this.mSK;
        }

        public final void setMSK(String str) {
            this.mSK = str;
        }

        public final String getMStsServerUrl() {
            return this.mStsServerUrl;
        }

        public final void setMStsServerUrl(String str) {
            this.mStsServerUrl = str;
        }

        public final Builder region(String region) {
            this.mRegion = region;
            return this;
        }

        public final Builder bucket(String bucket) {
            this.mBucket = bucket;
            return this;
        }

        /* renamed from: ak */
        public final Builder m3287ak(String ak) {
            this.mAK = ak;
            return this;
        }

        /* renamed from: sk */
        public final Builder m3288sk(String sk) {
            this.mSK = sk;
            return this;
        }

        public final OssDropUpload build() {
            return new OssDropUpload(this, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v8, types: [T, java.lang.String] */
    public final void upLoadCliffIrImg(ParcelFileDescriptor p0, int size) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        try {
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(p0);
            byte[] bArr = new byte[size];
            autoCloseInputStream.read(bArr, 0, size);
            Pdlog.m3273d(this.TAG, "upLoadCliffIrImg()##buf:" + bArr.length);
            String wIFIMac = getWIFIMac();
            if (wIFIMac == null) {
                wIFIMac = "dropMacEmpty";
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = wIFIMac + '/' + System.currentTimeMillis() + "_fangdieluo_beila_ir.png";
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("upLoadCliffIrImg() ---objectKey:");
            sb.append((String) objectRef.element);
            Pdlog.m3273d(str, sb.toString());
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new OssDropUpload$upLoadCliffIrImg$1(this, objectRef, bArr, null), 3, null);
        } catch (Exception e) {
            Pdlog.m3273d(this.TAG, "upLoadCliffIrImg()--error: " + e.getMessage());
        }
    }

    public final Object updata(String str, byte[] bArr, Continuation<? super String> continuation) {
        Pdlog.m3273d(this.TAG, "updata()###objectKey:" + str);
        try {
            PutObjectResult putObject = this.mOSSClient.putObject(new PutObjectRequest(this.mBucket, str, bArr));
            Intrinsics.checkExpressionValueIsNotNull(putObject, "mOSSClient.putObject(put)");
            String presignPublicObjectURL = this.mOSSClient.presignPublicObjectURL(this.mBucket, str);
            Log.d(this.TAG, "UploadSuccess = " + presignPublicObjectURL + "###ETag=" + putObject.getETag() + "###RequestId=" + putObject.getRequestId());
            return presignPublicObjectURL;
        } catch (ClientException e) {
            String str2 = this.TAG;
            Object[] objArr = new Object[2];
            objArr[0] = "updata()";
            String message = e.getMessage();
            if (message == null) {
                message = "本地异常，如网络异常";
            }
            objArr[1] = message;
            Pdlog.m3274e(str2, objArr);
            return null;
        } catch (ServiceException e2) {
            String str3 = this.TAG;
            Object[] objArr2 = new Object[2];
            objArr2[0] = "updata()";
            String message2 = e2.getMessage();
            if (message2 == null) {
                message2 = "服务异常";
            }
            objArr2[1] = message2;
            Pdlog.m3274e(str3, objArr2);
            return null;
        } catch (Exception e3) {
            String str4 = this.TAG;
            Object[] objArr3 = new Object[2];
            objArr3[0] = "updata()";
            String message3 = e3.getMessage();
            if (message3 == null) {
                message3 = "其他异常";
            }
            objArr3[1] = message3;
            Pdlog.m3274e(str4, objArr3);
            return null;
        }
    }

    private final ClientConfiguration getOSSConfig() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(15000);
        clientConfiguration.setSocketTimeout(15000);
        clientConfiguration.setMaxConcurrentRequest(2);
        clientConfiguration.setMaxErrorRetry(2);
        return clientConfiguration;
    }

    public final OSSCredentialProvider getOSSAuthCredentialsProvider() {
        return new OSSFederationCredentialProvider() { // from class: com.pudutech.disinfect.baselib.oss.OssDropUpload$getOSSAuthCredentialsProvider$1
            @Override // com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider, com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
            public OSSFederationToken getFederationToken() {
                String str;
                String str2;
                String time;
                str = OssDropUpload.this.mAK;
                str2 = OssDropUpload.this.mSK;
                time = OssDropUpload.this.getTime("");
                OSSFederationToken oSSFederationToken = new OSSFederationToken(str, str2, "", time);
                Pdlog.m3274e(OssDropUpload.this.getTAG(), "getOSSAuthCredentialsProvider():" + String.valueOf(oSSFederationToken));
                return oSSFederationToken;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTime(String time) {
        long currentTimeMillis = System.currentTimeMillis();
        Pdlog.m3274e("time", Long.valueOf(currentTimeMillis));
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = simpleDateFormat.format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "simpleDateFormat.format(data)");
        return format;
    }

    private final String getWIFIMac() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X:", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String sb2 = sb.toString();
                    if (sb2 != null) {
                        return StringsKt.replace$default(sb2, ":", "_", false, 4, (Object) null);
                    }
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
