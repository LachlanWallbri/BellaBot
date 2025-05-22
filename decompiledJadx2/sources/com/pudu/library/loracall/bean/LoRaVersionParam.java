package com.pudu.library.loracall.bean;

import androidx.exifinterface.media.ExifInterface;
import com.pudu.library.loracall.KotlinUtilsKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: LoRaVersionParam.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u0006H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\b\"\u0004\b\u0015\u0010\nR\u001a\u0010\u0016\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\b\"\u0004\b\u0018\u0010\nR\u0011\u0010\u0019\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\b¨\u0006\u001d"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/LoRaVersionParam;", "", "data", "", "([B)V", "bootLoaderVersion", "", "getBootLoaderVersion", "()Ljava/lang/String;", "setBootLoaderVersion", "(Ljava/lang/String;)V", "getData", "()[B", "firmwareType", "", "getFirmwareType", "()I", "setFirmwareType", "(I)V", "firmwareVersion", "getFirmwareVersion", "setFirmwareVersion", "hdVersion", "getHdVersion", "setHdVersion", "showVersion", "getShowVersion", "toString", "Companion", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaVersionParam {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static byte[] loRaVersion = new byte[0];
    private String bootLoaderVersion;
    private final byte[] data;
    private int firmwareType;
    private String firmwareVersion;
    private String hdVersion;

    public LoRaVersionParam(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.data = data;
        this.hdVersion = "";
        this.bootLoaderVersion = "";
        this.firmwareVersion = "";
        this.firmwareType = -1;
        byte[] bArr = this.data;
        if (bArr.length >= 7) {
            int bytesToIntTwo = KotlinUtilsKt.bytesToIntTwo(bArr, 0);
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(bytesToIntTwo / 100));
            sb.append(".");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Integer.valueOf(bytesToIntTwo % 100)};
            String format = String.format("%02d", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            sb.append(format);
            this.hdVersion = sb.toString();
            int bytesToIntTwo2 = KotlinUtilsKt.bytesToIntTwo(this.data, 2);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(String.valueOf(bytesToIntTwo2 / 100));
            sb2.append(".");
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Object[] objArr2 = {Integer.valueOf(bytesToIntTwo2 % 100)};
            String format2 = String.format("%02d", Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkNotNullExpressionValue(format2, "java.lang.String.format(format, *args)");
            sb2.append(format2);
            this.bootLoaderVersion = sb2.toString();
            int bytesToIntTwo3 = KotlinUtilsKt.bytesToIntTwo(this.data, 4);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(String.valueOf(bytesToIntTwo3 / 100));
            sb3.append(".");
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            Object[] objArr3 = {Integer.valueOf(bytesToIntTwo3 % 100)};
            String format3 = String.format("%02d", Arrays.copyOf(objArr3, objArr3.length));
            Intrinsics.checkNotNullExpressionValue(format3, "java.lang.String.format(format, *args)");
            sb3.append(format3);
            this.firmwareVersion = sb3.toString();
            byte[] bArr2 = this.data;
            this.firmwareType = bArr2[6];
            loRaVersion = bArr2;
        }
    }

    public final byte[] getData() {
        return this.data;
    }

    public final String getHdVersion() {
        return this.hdVersion;
    }

    public final void setHdVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.hdVersion = str;
    }

    public final String getBootLoaderVersion() {
        return this.bootLoaderVersion;
    }

    public final void setBootLoaderVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.bootLoaderVersion = str;
    }

    public final String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public final void setFirmwareVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.firmwareVersion = str;
    }

    public final int getFirmwareType() {
        return this.firmwareType;
    }

    public final void setFirmwareType(int i) {
        this.firmwareType = i;
    }

    public final String getShowVersion() {
        if (this.firmwareType < 0) {
            return this.firmwareVersion;
        }
        return ExifInterface.GPS_DIRECTION_TRUE + this.firmwareType + "_V" + this.firmwareVersion;
    }

    /* compiled from: LoRaVersionParam.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/LoRaVersionParam$Companion;", "", "()V", "loRaVersion", "", "getLoRaVersion", "()[B", "setLoRaVersion", "([B)V", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte[] getLoRaVersion() {
            return LoRaVersionParam.loRaVersion;
        }

        public final void setLoRaVersion(byte[] bArr) {
            Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
            LoRaVersionParam.loRaVersion = bArr;
        }
    }

    public String toString() {
        return "{\nhdVersion:" + this.hdVersion + "\nbootLoaderVersion:" + this.bootLoaderVersion + "\nfirmwareVersion:" + this.firmwareVersion + "\nfirmwareType:" + this.firmwareType + "\n}";
    }
}
