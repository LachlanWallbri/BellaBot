package com.pudutech.pd_network.bean;

import android.util.Log;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.pd_network.bean.StorageBucketType;
import com.pudutech.pd_network.utils.ExtKt;
import com.pudutech.pd_network.utils.NetDataUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: storage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \r2\u00020\u0001:\u0002\f\rB/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0005J\b\u0010\u000b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/PdUploadConfig;", "", "file", "Ljava/io/File;", "fileName", "", "fileType", "bucketType", "Lcom/pudutech/pd_network/bean/StorageBucketType;", TransferTable.COLUMN_KEY, "(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/pd_network/bean/StorageBucketType;Ljava/lang/String;)V", "toString", "Builder", "Companion", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PdUploadConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private StorageBucketType bucketType;
    private File file;
    private String fileName;
    private String fileType;
    private String key;

    private PdUploadConfig(File file, String str, String str2, StorageBucketType storageBucketType, String str3) {
        this.fileType = str2;
        this.bucketType = storageBucketType;
        this.key = str3;
        this.file = file;
        this.fileName = str;
    }

    public /* synthetic */ PdUploadConfig(File file, String str, String str2, StorageBucketType storageBucketType, String str3, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, str, str2, storageBucketType, str3);
    }

    /* renamed from: file, reason: from getter */
    public final File getFile() {
        return this.file;
    }

    /* renamed from: fileName, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* renamed from: fileType, reason: from getter */
    public final String getFileType() {
        return this.fileType;
    }

    /* renamed from: bucketType, reason: from getter */
    public final StorageBucketType getBucketType() {
        return this.bucketType;
    }

    /* renamed from: key, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    public String toString() {
        return "PdUploadConfig(file=" + this.file + ", fileName=" + this.fileName + ", fileType='" + this.fileType + "', key='" + this.key + "', bucketType=" + this.bucketType + ')';
    }

    /* compiled from: storage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/PdUploadConfig$Companion;", "", "()V", "mapFile", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "file", "Ljava/io/File;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PdUploadConfig mapFile(File file) {
            Intrinsics.checkParameterIsNotNull(file, "file");
            return new Builder().file(file).fileType(MapElement.Key.MAP).builder();
        }
    }

    /* compiled from: storage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/PdUploadConfig$Builder;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "bucketType", "Lcom/pudutech/pd_network/bean/StorageBucketType;", "file", "Ljava/io/File;", "fileName", "fileType", "builder", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "handlerFileName", "", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Builder {
        private File file;
        private final String TAG = getClass().getSimpleName();
        private String fileType = "default";
        private StorageBucketType bucketType = StorageBucketType.Default.INSTANCE;
        private String fileName = "";

        public final Builder bucketType(StorageBucketType bucketType) {
            Intrinsics.checkParameterIsNotNull(bucketType, "bucketType");
            this.bucketType = bucketType;
            return this;
        }

        public final Builder fileType(String fileType) {
            Intrinsics.checkParameterIsNotNull(fileType, "fileType");
            this.fileType = fileType;
            return this;
        }

        public final Builder file(File file) {
            Intrinsics.checkParameterIsNotNull(file, "file");
            this.file = file;
            return this;
        }

        public final PdUploadConfig builder() {
            File file = this.file;
            if (file == null) {
                throw new Exception("file cant = null");
            }
            if (file != null && (!file.exists())) {
                StringBuilder sb = new StringBuilder();
                sb.append("file:");
                File file2 = this.file;
                sb.append(file2 != null ? file2.getPath() : null);
                sb.append(" 不存在");
                throw new Exception(sb.toString());
            }
            if (this.file != null && (!r0.isFile())) {
                throw new Exception("file 不是文件");
            }
            if (this.fileType.length() == 0) {
                throw new Exception("fileType 不能为空");
            }
            handlerFileName();
            String str = "pudu_cloud_platform/" + this.fileType + '/' + StringsKt.replace$default(NetDataUtils.INSTANCE.mac(), ":", "", false, 4, (Object) null) + '/' + this.fileName;
            File file3 = this.file;
            if (file3 == null) {
                Intrinsics.throwNpe();
            }
            PdUploadConfig pdUploadConfig = new PdUploadConfig(file3, this.fileName, this.fileType, this.bucketType, str, null);
            Log.i(this.TAG, "builder > " + pdUploadConfig);
            return pdUploadConfig;
        }

        private final void handlerFileName() {
            File file = this.file;
            if (file != null) {
                if (file == null) {
                    Intrinsics.throwNpe();
                }
                String name = file.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "name");
                int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) name, ".", 0, false, 6, (Object) null);
                if (name == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String substring = name.substring(lastIndexOf$default);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                StringBuilder sb = new StringBuilder();
                File file2 = this.file;
                if (file2 == null) {
                    Intrinsics.throwNpe();
                }
                sb.append(String.valueOf(ExtKt.md5(file2)));
                sb.append(substring);
                this.fileName = sb.toString();
                return;
            }
            this.fileName = "pd.unnamed";
        }
    }
}
